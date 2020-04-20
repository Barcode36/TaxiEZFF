package models;

import models.interfaces.Registro;

import java.time.LocalDate;
import java.time.LocalDateTime;

//Servicio no es una persona literalmente, pero contiene los mismos campos.
public class Servicio<S> extends Persona<S> implements Registro {

    private int idServicio;//primarykey
    private String clienteTelefono;//foreign key
    private int idEmpleado;//foreign key, no es necesario guardar todos los datos del empleado, solo su ID.
    private LocalDateTime fechaAgregación;
    private LocalDateTime fechaAplcación;

    public Servicio( int idServicio, String clienteTelefono, int idEmpleado, LocalDateTime fechaAgregación, LocalDateTime fechaAplcación,String nombre, String observaciones, Direccion direccion) {
        super(nombre, observaciones, direccion);
        this.idServicio = idServicio;
        this.clienteTelefono = clienteTelefono;
        this.idEmpleado = idEmpleado;
        this.fechaAgregación = fechaAgregación;
        this.fechaAplcación = fechaAplcación;
    }

    public Servicio( int idServicio, String clienteTelefono, int idEmpleado, LocalDateTime fechaAgregación, LocalDateTime fechaAplcación,Persona datos) {
        super(datos);
        this.idServicio = idServicio;
        this.clienteTelefono = clienteTelefono;
        this.idEmpleado = idEmpleado;
        this.fechaAgregación = fechaAgregación;
        this.fechaAplcación = fechaAplcación;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public String getClienteTelefono() {
        return clienteTelefono;
    }

    public void setClienteTelefono(String clienteTelefono) {
        this.clienteTelefono = clienteTelefono;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public LocalDateTime getFechaAgregación() {
        return fechaAgregación;
    }

    public void setFechaAgregación(LocalDateTime fechaAgregación) {
        this.fechaAgregación = fechaAgregación;
    }

    public LocalDateTime getFechaAplcación() {
        return fechaAplcación;
    }

    public void setFechaAplcación(LocalDateTime fechaAplcación) {
        this.fechaAplcación = fechaAplcación;
    }
}
