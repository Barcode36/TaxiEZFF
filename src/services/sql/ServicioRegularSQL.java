package services.sql;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import models.*;
import resources.Statics;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServicioRegularSQL {


    private Connection connection;

    private boolean key;
    private String query;
    private ResultSet rs;
    private PreparedStatement ps;
    private ActionEvent event;


    public ServicioRegularSQL() {
        this.connection = Statics.getConnections();
    }


    public ObservableList<ServicioRegular> getServiciosRegularesPendientes(){
        ObservableList<ServicioRegular> serviciosRegulares =  FXCollections.observableArrayList();
       /* query="SELECT servicio.idServicio,servicio.nombre,servicio.observaciones,servicio.fechaAgregacion,servicio.fechaServicio,servicio.fechaAplicacion,servicio.isCancelado,servicio.idCliente,servicio.idEmpleado,servicio.idDireccion, " +
                "direccion.idDireccion,direccion.calle,direccion.colonia,direccion.numInt,direccion.numExt, " +
                "cliente.telefono, cliente.idCliente, " +
                "empleado.idEmpleado, empleado.nombre " +
                "FROM " +
                "servicio " +
                "JOIN direccion ON servicio.idDireccion = direccion.idDireccion " +
                "JOIN cliente ON servicio.idCliente = cliente.idCliente " +
                "JOIN empleado ON servicio.idEmpleado = empleado.idEmpleado " +
                "WHERE servicio.isCancelado = 0 and servicio.fechaAplicacion IS NULL " +
                "LIMIT 0, 25";*/
       query = "SELECT * FROM " +
               "servicio " +
               "JOIN cliente ON servicio.idCliente = cliente.idCliente " +
               "JOIN empleado  on servicio.idEmpleado = empleado.idEmpleado " +
               "JOIN direccion ON servicio.idDireccion = direccion.idDireccion " +
               "WHERE servicio.isCancelado = 0 and servicio.fechaAplicacion IS NULL " +
               "LIMIT 0, 250";
        try
        {
            ps = connection.prepareStatement(query);
            rs=ps.executeQuery();
            while(rs.next())
            {
                serviciosRegulares.add( crearServicio(rs) );
            }

            ps.close();
        }
        catch(SQLException ex)
        {
            Logger.getLogger(ClienteSQL.class.getName()).log(Level.SEVERE, "Error al extraer clientes.", ex);
        }

        return serviciosRegulares;
    }


    /**
     * Inserta un @{@link ServicioRegular} "pendiente" es decir, con la fecha de aplicacion nula y el campo isCancelado como false.
     * Esta instancia es creada dentro del programa por lo tanto el idServicio no se toma en cuenta en la inserción (campo AutoIncrement) posteriormente
     * de la insersión se modifica el idServicio de la instancia.
     * @param servicioRegular
     * Instancia a insertar en la base de datos.
     * @return
     * True si fue exitosa la inserción.
     * False si lo contrario.
     */
    public boolean insertarServicioRegular(ServicioRegular servicioRegular) {

        query = "INSERT INTO servicio " +
                "(nombre, observaciones, fechaAgregacion, fechaServicio, fechaAplicacion, isCancelado, idCliente, idEmpleado, idDireccion) " +
                "VALUES" +
                " (?,?,?,?, ?, ?, ?, ?, ?)";

        try {

            ps = connection.prepareStatement(query);
            ps.setString(1, servicioRegular.getNombre());
            ps.setString(2, servicioRegular.getObservaciones());
            ps.setTimestamp(3, Timestamp.valueOf( servicioRegular.getFechaAgregacion()));
            ps.setTimestamp(4, Timestamp.valueOf( servicioRegular.getFechaServicio()));
            ps.setTimestamp(5, null);//fecha aplicacion nula para servicio pendiente

            ps.setBoolean(6, false);
            ps.setInt(7,servicioRegular.getCliente().getIdCliente());
            ps.setInt(8,servicioRegular.getEmpleado().getIdEmpleado());
            ps.setInt(9,servicioRegular.getDireccion().getIdDireccion());


            ps.executeUpdate();

            servicioRegular.setIdServicio(Statics.getLastId());
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    public boolean cancelarServicioPendiente(int idServicioRegularPendiente) throws SQLException {

        query = ("UPDATE servicio SET isCancelado = 1 WHERE servicio.idServicio = ?");
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,idServicioRegularPendiente);

        return preparedStatement.executeUpdate() == 1;
    }

    /**
     * idServicio nombre observaciones fechaAgregacion fechaServicio fechaAplicacion isCancelado idCliente idEmpleado idDireccion (La instancia )
     * idDireccion calle colonia numInt numExt
     * cliente.telefono
     * @param rs
     * @return
     */
    private ServicioRegular crearServicio(ResultSet rs) throws SQLException {

        Direccion direccion =
                new Direccion(rs.getInt("direccion.idDireccion"), rs.getString("direccion.calle"),rs.getString("direccion.colonia"), rs.getString("direccion.numInt"), rs.getString("direccion.numExt"));

        Persona datos = new Persona(rs.getString("servicio.nombre"),rs.getString("servicio.observaciones"),direccion);



        LocalDateTime localDateTimeAgregacion = rs.getTimestamp("servicio.fechaAgregacion").toLocalDateTime();
        LocalDateTime localDateTimeServicio = rs.getTimestamp("servicio.fechaServicio").toLocalDateTime();


        LocalDateTime localDateTimeAplicacion =
                rs.getTimestamp("fechaAplicacion") ==null?
                        null:rs.getTimestamp("fechaAplicacion").toLocalDateTime();


        Cliente cliente = new Cliente(rs.getInt("cliente.idCliente"),rs.getString("cliente.telefono"),rs.getBoolean("Visible"), rs.getString("cliente.nombre"),
                rs.getString("observaciones"), new DireccionSQL().get(rs.getInt("cliente.idDireccion")));

        Empleado empleado = new Empleado(
                rs.getString("empleado.nombre"),rs.getString("empleado.observaciones"),
                new  DireccionSQL().get(rs.getInt("empleado.idDireccion")),
                rs.getInt("empleado.idEmpleado"), rs.getString("empleado.telefono"), rs.getString("contrasena"),
                rs.getDate("fechaNac")==null?null:rs.getDate("fechaNac").toLocalDate(), rs.getBoolean("tipoEmpleado"));

        ServicioRegular servicioRegular =
            new ServicioRegular(
                    datos,
                    rs.getInt("servicio.idServicio"),localDateTimeAgregacion, localDateTimeServicio, localDateTimeAplicacion,
                    rs.getBoolean("servicio.isCancelado"),cliente,empleado);


        return servicioRegular;

    }



}
