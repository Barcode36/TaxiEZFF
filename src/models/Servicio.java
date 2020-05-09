package models;

import models.interfaces.Registro;

import java.time.LocalDateTime;

/**
 * Servicio no es una persona literalmente, pero contiene los mismos campos.
 * @param <S>
 */
public class Servicio<S> extends Persona<S> implements Registro {

    private int idServicio;//primarykey
    private String clienteTelefono;//foreign key
    private int idEmpleado;//foreign key, no es necesario guardar todos los datos del empleado, solo su ID.
    private LocalDateTime fechaAgregacion;
    private LocalDateTime fechaServicio;
    private LocalDateTime fechaAplcacion;

    //campos para acceso secundario.
    private Integer unidad = null;
    private String nombreEmpleado;
    private int idCliente;

    public Servicio(int idServicio, int idCliente, String clienteTelefono, int idEmpleado, LocalDateTime fechaAgregacion, LocalDateTime fechaServicio, LocalDateTime fechaAplcacion, String nombre, String observaciones, Direccion direccion) {
        super(nombre, observaciones, direccion);
        this.idServicio = idServicio;
        this.clienteTelefono = clienteTelefono;
        this.idEmpleado = idEmpleado;
        this.fechaAgregacion = fechaAgregacion;
        this.fechaAplcacion = fechaAplcacion;
        this.fechaServicio = fechaServicio;
        this.idCliente = idCliente;
    }

    public Servicio(int idServicio, int idCliente, String clienteTelefono, int idEmpleado, LocalDateTime fechaAgregacion, LocalDateTime fechaServicio, LocalDateTime fechaAplcacion, Persona datos) {
        super(datos);
        this.idServicio = idServicio;
        this.clienteTelefono = clienteTelefono;
        this.idEmpleado = idEmpleado;
        this.fechaAgregacion = fechaAgregacion;
        this.fechaAplcacion = fechaAplcacion;
        this.fechaServicio = fechaServicio;
        this.idCliente = idCliente;
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

    public LocalDateTime getFechaServicio() {
        return fechaServicio;
    }

    public void setFechaServicio(LocalDateTime fechaServicio) {
        this.fechaServicio = fechaServicio;
    }

    public LocalDateTime getFechaAgregacion() {
        return fechaAgregacion;
    }

    public void setFechaAgregacion(LocalDateTime fechaAgregacion) {
        this.fechaAgregacion = fechaAgregacion;
    }

    public LocalDateTime getFechaAplcacion() {
        return fechaAplcacion;
    }

    public void setFechaAplcacion(LocalDateTime fechaAplcacion) {
        this.fechaAplcacion = fechaAplcacion;
    }

    public Integer getUnidad() {
        return unidad;
    }

    public void setUnidad(Integer unidad) {
        this.unidad = unidad;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
}
