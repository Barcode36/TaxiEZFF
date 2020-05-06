package services.sql;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import models.Cliente;
import models.Direccion;
import resources.Statics;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteSQL {

    private Connection connection;

    private boolean key;
    private String query;
    private ResultSet rs;
    private PreparedStatement ps;
    private ActionEvent event;

    public ClienteSQL() {

        connection = Statics.getConnections();


    }


    /**
     * La insersión puede ser correcta o no, ya que el usuario puede cancelarla, pero también puede haber
     * error SQL que va al Logger.
     * @param cliente
     * El cliente a insertar, este puede tener o no un numero de telefono que ya exista en la bse de datos.
     * @param stage
     * La pantalla para mostrar confirmación en caso de ser requerido.
     * @return
     * True si se insertó correctamente.
     * False si no se insertó.
     *
     */
    public boolean insertar(Cliente cliente, Stage stage)  {
        //si existe entonces se "borrará" (ocultará) el registro(s) que contenga a ese numero de telefono.
        this.event = event;
        try{
            //si existe y se confirma existencia, "elimina" el (los) registro(s) anterior(es)
            if(existe(cliente ,true)){

                Optional<Boolean> resultConfirmacion =
                        Statics.crearConfirmacion(stage,"Ese número de telefono ya está registrado","Los nuevos datos reemplazarán al anterior. \n ¿Desea continuar?");
                //por si solo se cierra la ventana.
                existe = false;

                //si la confimaición da false entonces el borrado queda cancelado.
                if(resultConfirmacion.isPresent()){
                    if( resultConfirmacion.get()){
                        eliminar(cliente);
                    }else{
                        //se cancela la insercion.
                        return false;
                    }
                }
            }
            //si falla la inserción
            if (!new DireccionSQL().insertarDireccion(cliente.getDireccion())) {
                return false;
            }

            query = "INSERT INTO cliente (telefono, observaciones, visible, nombre, idDireccion,idCliente) VALUES (?, ?,?, ?,?,?)";
            ps = connection.prepareStatement(query);
            ps.setString(1, cliente.getNumero());
            ps.setString(2, cliente.getObservaciones());
            ps.setBoolean(3, cliente.isVisible());
            ps.setString(4, cliente.getNombre());
            ps.setInt(5, cliente.getDireccion().getIdDireccion());
            ps.setInt(6,this.getLastId());

            if(ps.executeUpdate() ==1){
                return true;
            }

        }
        catch (SQLException sqlE){
            sqlE.printStackTrace();
            //ventana error v:
        }

    return false;

    }
    public boolean actualizar(Cliente cliente)  {
        //si existe entonces se "borrará" (ocultará) el registro(s) que contenga a ese numero de telefono.
        if(!new DireccionSQL().actualizar(cliente.getDireccion())){
            return false;
        }
        try {
            query = "UPDATE cliente SET telefono = ?, observaciones = ?, visible = ?, nombre = ? WHERE cliente.idCliente = ?";
            ps = connection.prepareStatement(query);


            ps.setString(1, cliente.getNumero());
            ps.setString(2, cliente.getObservaciones());
            ps.setBoolean(3, cliente.isVisible());
            ps.setString(4, cliente.getNombre());
            ps.setInt(5,cliente.getIdCliente());

            if(ps.executeUpdate() ==1){
                return true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }
    boolean existe =false;

    /**
     * Buscará ese registro para saber si existe en la bse de datos.
     *
     * @param cliente
     * Registro a buscar.
     * @param borrar
     * Marcará si se borrará el registro en caso de que exista.
     * Si solo se quiere consultar si existe o no, este miembro debe ser false.
     * @return
     * True si existe.
     * False si no.
     * @throws SQLException
     */
    public boolean existe(Cliente cliente,boolean borrar) throws SQLException {
        query = "SELECT * FROM cliente WHERE telefono = ? AND visible = 1";

        ps = connection.prepareStatement(query);
        ps.setString(1, cliente.getNumero());

        //0 no existe, mayor qué 0 existe
        ResultSet resultSet = ps.executeQuery();
        if(resultSet.next()){
            existe = true;

        }


        return existe;

    }
    public boolean eliminar(Cliente cliente){
        //eliminar todos los que tengan ese telefono
        query= "UPDATE cliente SET visible = 0 WHERE cliente.telefono = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, cliente.getNumero());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getLastId() throws SQLException {

        int idDireccion = -1;
        String query=" SELECT MAX(idCliente) FROM cliente";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            idDireccion = resultSet.getInt(1) + 1;
            if( idDireccion == 0){
                idDireccion = 1;
            }
        }

        return idDireccion;
    }
    /**
     * @return
     * Lista de los clientes existentes actualmente en la base de datos.
     */
    public ObservableList<Cliente> getClientes()
    {

        ObservableList<Cliente> clientes =  FXCollections.observableArrayList();
        query="SELECT * FROM `cliente` JOIN direccion on cliente.idDireccion = direccion.idDireccion WHERE visible = 1";
        Direccion direccion;
        try
        {
            ps = connection.prepareStatement(query);
            rs=ps.executeQuery();
            while(rs.next())
            {
                clientes.add( crearCliente(rs) );
            }

            ps.close();
        }
        catch(SQLException ex)
        {
            Logger.getLogger(ClienteSQL.class.getName()).log(Level.SEVERE, "Error al extraer clientes.", ex);
        }

        return clientes;
    }

    /**
     * @param rs tupla Cliente.
     * La tupla obtenia es:
     * idTelefono telefono observaciones visible nombre idDireccion
     * idDireccion calle colonia numInt numExt.
     * @return Instancia de Cliente a partir de la tupla contenida en rs.
     * @throws SQLException
     */
    private Cliente crearCliente(ResultSet rs) throws SQLException {
        //Cliente cliente = new Cliente(rs.getString(0), rs.getBoolean(3), rs.getString(1), rs.getString(2), );

        Direccion direcion  = new Direccion(
                rs.getInt(7),
                rs.getString(8),
                rs.getString(9),
                rs.getString(10),
                rs.getString(11)
        );

        Cliente cliente = new Cliente(
                rs.getInt(1),
                rs.getString(2),
                rs.getBoolean(4),
                rs.getString(5),
                rs.getString(3),
                direcion);

        return cliente;
    }
}