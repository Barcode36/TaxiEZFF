package models;

import models.interfaces.Registro;

/**
 * Sirve para paras los datos  de confirmación de servicio para aplicar servicio.
 */
public class ConfirmaciónServicioData implements Registro {

    private int idUnidad;
    private int idServicio;
    private String observaciones;


    public ConfirmaciónServicioData(int idUnidad, int idServicio, String observaciones) {
        this.idUnidad = idUnidad;
        this.idServicio = idServicio;
        this.observaciones = observaciones;
    }

    public int getIdUnidad() {
        return idUnidad;
    }

    public void setIdUnidad(int idUnidad) {
        this.idUnidad = idUnidad;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }
}
