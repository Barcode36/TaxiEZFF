package models;

import models.interfaces.Registro;

public class Cliente extends Persona implements Registro {

    private String numero;//primaryKey
    private boolean visible;


    public Cliente(String numero, boolean visible,String nombre, String observaciones, Direccion direccion) {
        super(nombre, observaciones, direccion);
        this.numero = numero;
        this.visible = visible;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
