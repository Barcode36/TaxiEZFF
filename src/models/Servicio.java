package models;

import models.interfaces.Registro;

import java.time.LocalDateTime;

/**
 * Servicio no es una persona literalmente, pero contiene los mismos campos.
 * @param <S>
 */
public class Servicio<S> extends Persona<S> implements Registro {

    private int idServicio;
    //nombre,observaciones y dirección están heredadas ya.

    private LocalDateTime fechaAgregacion;
    private LocalDateTime fechaServicio;
    private LocalDateTime fechaAplcacion;

    private boolean isCancelado;
    private Cliente cliente;
    private Empleado empleado;


    public Servicio(String nombre, String observaciones, Direccion direccion,
                    int idServicio, LocalDateTime fechaAgregacion, LocalDateTime fechaServicio, LocalDateTime fechaAplcacion,
                    boolean isCancelado, Cliente cliente, Empleado empleado) {
        super(nombre, observaciones, direccion);
        this.idServicio = idServicio;
        this.fechaAgregacion = fechaAgregacion;
        this.fechaServicio = fechaServicio;
        this.fechaAplcacion = fechaAplcacion;
        this.isCancelado = isCancelado;
        this.cliente = cliente;
        this.empleado = empleado;
    }

    public Servicio(Persona datos,
                    int idServicio, LocalDateTime fechaAgregacion, LocalDateTime fechaServicio, LocalDateTime fechaAplcacion,
                    boolean isCancelado, Cliente cliente, Empleado empleado) {
        super(datos);
        this.idServicio = idServicio;
        this.fechaAgregacion = fechaAgregacion;
        this.fechaServicio = fechaServicio;
        this.fechaAplcacion = fechaAplcacion;
        this.isCancelado = isCancelado;
        this.cliente = cliente;
        this.empleado = empleado;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public LocalDateTime getFechaAgregacion() {
        return fechaAgregacion;
    }

    public void setFechaAgregacion(LocalDateTime fechaAgregacion) {
        this.fechaAgregacion = fechaAgregacion;
    }

    public LocalDateTime getFechaServicio() {
        return fechaServicio;
    }

    public void setFechaServicio(LocalDateTime fechaServicio) {
        this.fechaServicio = fechaServicio;
    }

    public LocalDateTime getFechaAplcacion() {
        return fechaAplcacion;
    }

    public void setFechaAplcacion(LocalDateTime fechaAplcacion) {
        this.fechaAplcacion = fechaAplcacion;
    }

    public boolean isCancelado() {
        return isCancelado;
    }

    public void setCancelado(boolean cancelado) {
        isCancelado = cancelado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
}
