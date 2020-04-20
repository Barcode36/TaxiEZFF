package models;

import models.interfaces.Registro;

public class Empleado extends Persona<Empleado> implements Registro {

    int idEmpleado;//primarykey

    public Empleado(int idEmpleado,String nombre, String observaciones, Direccion direccion) {
        super(nombre, observaciones, direccion);
        this.idEmpleado = idEmpleado;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
}
