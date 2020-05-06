package services.sql;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import models.Direccion;
import models.Empleado;
import models.Taxista;
import resources.Statics;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TaxistaSQL {

    private Connection connection;
    private boolean key;
    private String query;
    private ResultSet rs;
    private PreparedStatement ps;
    private ActionEvent event;

    private int idTaxista;
    private int idDireccion;

    public TaxistaSQL() {

        this.connection = Statics.getConnections();

    }


    /**
     * La insersión puede ser correcta o no, ya que el usuario puede cancelarla, pero también puede haber
     * error SQL que va al Logger.
     * @param taxista
     * El taxista a insertar, este puede tener o no un numero de telefono que ya exista en la bse de datos.
     * @return
     * True si se insertó correctamente.
     * False si no se insertó.
     *
     */
    public boolean insertar(Taxista taxista)  {


        if(!new DireccionSQL().insertarDireccion(taxista.getDireccion())){
            return false;
        }
        try{
            query = "INSERT INTO taxista (nombre, telefono,fechaNac, observaciones, idDireccion) " +
                    "VALUES ( ?, ?, ?, ?, ?)";

            ps = connection.prepareStatement(query);
            ps.setString(1, taxista.getNombre());
            ps.setString(2, taxista.getTelefono());
            ps.setDate(3, Date.valueOf(taxista.getFechaNac()));
            ps.setString(4, taxista.getObservaciones());
            ps.setInt(5, taxista.getDireccion().getIdDireccion());

            ps.executeUpdate();
            return true;


        }
        catch (SQLException sqlE){
            sqlE.printStackTrace();
            //ventana error v:
        }

        return false;

    }
    public boolean actualizar(Taxista taxista)  {
        //si existe entonces se "borrará" (ocultará) el registro(s) que contenga a ese numero de telefono.
        if(!new DireccionSQL().actualizar(taxista.getDireccion())){
            return false;
        }
        try {
            query = "UPDATE taxista SET nombre = ?,fechaNac =?,telefono = ?,observaciones = ? WHERE taxista.idTaxista = ?";
            ps = connection.prepareStatement(query);


            ps.setString(1, taxista.getNombre());
            ps.setDate(2, Date.valueOf(taxista.getFechaNac()));
            ps.setString(3, taxista.getTelefono());
            ps.setString(4, taxista.getObservaciones());
            ps.setInt(5, taxista.getIdTaxista());


            ps.executeUpdate();
            return true;

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    /**
     * Buscará ese registro para saber si existe en la bse de datos.
     *
     * @param taxista
     * Registro a buscar.
     * @return
     * True si existe.
     * False si no.
     * @throws SQLException
     */
    public boolean existe(Taxista taxista) throws SQLException {
        boolean existe = false;

        query = "SELECT * FROM taxista WHERE idEmpleado = ? AND visible = 1";

        ps = connection.prepareStatement(query);
        ps.setInt(1, taxista.getIdTaxista());

        //0 no existe, mayor qué 0 existe
        ResultSet resultSet = ps.executeQuery();
        if(resultSet.next()){
            existe = true;

        }


        return existe;

    }
    public boolean eliminar(Taxista taxista){
        //eliminar todos los que tengan ese telefono
        query= "UPDATE taxista SET visible = 0 WHERE taxista.idTaxista = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, taxista.getIdTaxista());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * @return
     * Lista de los taxistas existentes actualmente en la base de datos.
     */
    public ObservableList<Taxista> getTaxistas()
    {
        ObservableList<Taxista> taxistas =  FXCollections.observableArrayList();
        query="SELECT * FROM taxista JOIN direccion on taxista.idDireccion = direccion.idDireccion WHERE visible = 1";
        Direccion direccion;
        try
        {
            ps = connection.prepareStatement(query);
            rs=ps.executeQuery();
            while(rs.next())
            {
                taxistas.add( crearTaxista(rs) );
            }

            ps.close();
        }
        catch(SQLException ex)
        {
            Logger.getLogger(ClienteSQL.class.getName()).log(Level.SEVERE, "Error al extraer clientes.", ex);
        }

        return taxistas;
    }

    /**
     * @param rs tupla {@link Taxista}.
     * La tupla obtenia es:
     * @return Instancia de Cliente a partir de la tupla contenida en rs.
     * idTaxista	nombre	telefono	fechNac	observaciones	idDireccion
     * idDireccion calle colonia numInt numExt.
     * @throws SQLException
     */
    private Taxista crearTaxista(ResultSet rs) throws SQLException {
        //Cliente cliente = new Cliente(rs.getString(0), rs.getBoolean(3), rs.getString(1), rs.getString(2), );

        Direccion direcion  = new Direccion(
                rs.getInt(7),
                rs.getString(8),
                rs.getString(9),
                rs.getString(10),
                rs.getString(11)
        );

        Taxista taxista =
                new Taxista(this.idTaxista, rs.getDate(4).toLocalDate(), rs.getString(3), rs.getString(2), rs.getString(5), direcion);

        return taxista;
    }
}
