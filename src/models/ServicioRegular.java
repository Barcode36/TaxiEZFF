package models;

import java.time.LocalDateTime;

/**
Clase para dar compatibilidad al parametro de clase y ser asignado a la clase padre.

 */
public class ServicioRegular extends Servicio<ServicioRegular> {

    public ServicioRegular(int idServicio,int idCliente, String clienteTelefono, int idEmpleado, LocalDateTime fechaAgregación,LocalDateTime fechaServicio, LocalDateTime fechaAplcación, String nombre, String observaciones, Direccion direccion) {
        super(idServicio, idCliente,clienteTelefono, idEmpleado, fechaAgregación,fechaServicio, fechaAplcación, nombre, observaciones, direccion);
    }

    public ServicioRegular(int idServicio, int idCliente,String clienteTelefono, int idEmpleado, LocalDateTime fechaAgregación, LocalDateTime fechaServicio, LocalDateTime fechaAplcación, Persona datos) {
        super(idServicio,idCliente, clienteTelefono, idEmpleado, fechaAgregación, fechaServicio, fechaAplcación, datos);
    }

}
