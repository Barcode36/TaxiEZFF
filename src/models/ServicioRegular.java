package models;

import java.time.LocalDateTime;

/**
Clase para dar compatibilidad al parametro de clase.
 */
public class ServicioRegular extends Servicio<ServicioRegular> {
    public ServicioRegular(int idServicio, String clienteTelefono, int idEmpleado, LocalDateTime fechaAgregación, LocalDateTime fechaAplcación, String nombre, String observaciones, Direccion direccion) {
        super(idServicio, clienteTelefono, idEmpleado, fechaAgregación, fechaAplcación, nombre, observaciones, direccion);
    }

    public ServicioRegular(int idServicio, String clienteTelefono, int idEmpleado, LocalDateTime fechaAgregación, LocalDateTime fechaAplcación, Persona datos) {
        super(idServicio, clienteTelefono, idEmpleado, fechaAgregación, fechaAplcación, datos);
    }
}
