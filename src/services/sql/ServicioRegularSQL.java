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
        query="SELECT servicio.idServicio,servicio.nombre,servicio.observaciones,servicio.fechaAgregacion,servicio.fechaServicio,servicio.fechaAplicacion,servicio.isCancelado,servicio.idCliente,servicio.idEmpleado,servicio.idDireccion, " +
                "direccion.idDireccion,direccion.calle,direccion.colonia,direccion.numInt,direccion.numExt, " +
                "cliente.telefono, cliente.idCliente, " +
                "empleado.idEmpleado, empleado.nombre " +
                "FROM " +
                "servicio " +
                "JOIN direccion ON servicio.idDireccion = direccion.idDireccion " +
                "JOIN cliente ON servicio.idCliente = cliente.idCliente " +
                "JOIN empleado ON servicio.idEmpleado = empleado.idEmpleado " +
                "WHERE servicio.isCancelado = 0 and servicio.fechaAplicacion IS NULL " +
                "LIMIT 0, 25";
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
    public boolean insertar(ServicioRegular servicioRegular) {

        query = "INSERT INTO servicio " +
                "(nombre, observaciones, fechaAgregacion, fechaServicio, fechaAplicacion, isCancelado, idCliente, idEmpleado, idDireccion) " +
                "VALUES" +
                " ('Victor Servicio', 'Observación serv 1 vic', '2020-05-06 00:00:00', '2020-05-08 00:06:00', NULL, '0', '3', '1', '12')";

        try {

            ps = connection.prepareStatement(query);
            ps.setString(1, servicioRegular.getNombre());
            ps.setString(2, servicioRegular.getObservaciones());
            ps.setTimestamp(3, Timestamp.valueOf( servicioRegular.getFechaAgregacion()));
            ps.setTimestamp(4, Timestamp.valueOf( servicioRegular.getFechaServicio()));
            ps.setTimestamp(5, null);//fecha aplicacion nula para servicio pendiente

            ps.setBoolean(6, false);
            ps.setInt(7,servicioRegular.getIdCliente());
            ps.setInt(8,servicioRegular.getIdEmpleado());
            ps.setInt(9,servicioRegular.getDireccion().getIdDireccion());


            ps.executeUpdate();

            servicioRegular.setIdServicio(Statics.getLastId());
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
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



        LocalDateTime localDateTimeAgregacion = rs.getTimestamp("fechaAgregacion").toLocalDateTime();
        LocalDateTime localDateTimeServicio = rs.getTimestamp("fechaServicio").toLocalDateTime();


        LocalDateTime localDateTimeAplicacion =
                rs.getTimestamp("fechaAplicacion") ==null?
                        null:rs.getTimestamp("fechaAplicacion").toLocalDateTime();



        ServicioRegular servicioRegular =
            new ServicioRegular(rs.getInt("idServicio"),rs.getInt("idCliente"),rs.getString("cliente.telefono"),rs.getInt("idEmpleado"),
                    localDateTimeAgregacion,localDateTimeServicio,localDateTimeAplicacion,datos
                    );

        //servicioRegular.setUnidad(rs.getInt(""));
        servicioRegular.setNombreEmpleado(rs.getInt("empleado.idEmpleado") + " " + rs.getString("empleado.nombre"));
        return servicioRegular;

    }



}
