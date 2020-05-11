package models;

import java.time.LocalDateTime;

/**
Clase para dar compatibilidad al parametro de clase y ser asignado a la clase padre.

 */
public class ServicioRegular extends Servicio<ServicioRegular> {

    public ServicioRegular(String nombre, String observaciones, Direccion direccion, int idServicio, LocalDateTime fechaAgregacion, LocalDateTime fechaServicio, LocalDateTime fechaAplcacion, boolean isCancelado, Cliente cliente, Empleado empleado) {
        super(nombre, observaciones, direccion, idServicio, fechaAgregacion, fechaServicio, fechaAplcacion, isCancelado, cliente, empleado);
    }

    public ServicioRegular(Persona datos, int idServicio, LocalDateTime fechaAgregacion, LocalDateTime fechaServicio, LocalDateTime fechaAplcacion, boolean isCancelado, Cliente cliente, Empleado empleado) {
        super(datos, idServicio, fechaAgregacion, fechaServicio, fechaAplcacion, isCancelado, cliente, empleado);
    }

    String telefonoAux = "";
    //para cuando se asigne unidad
    private int idUnidad ;
    private Taxi taxi;

    public String getTelefonoAux() {
        return telefonoAux;
    }

    public void setTelefonoAux(String telefonoAux) {
        this.telefonoAux = telefonoAux;
    }

    public int getIdUnidad() {
        return idUnidad;
    }

    public void setIdUnidad(int idUnidad) {
        this.idUnidad = idUnidad;
    }

    public Taxi getTaxi() {
        return taxi;
    }

    public void setTaxi(Taxi taxi) {
        this.taxi = taxi;
    }



}
