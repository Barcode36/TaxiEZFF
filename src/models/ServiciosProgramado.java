package models;

import java.time.LocalDateTime;

public class ServiciosProgramado extends Servicio {

    private LocalDateTime fechaUltimaAplicacion;
    private boolean isLunes;
    private boolean isMartes;
    private boolean isMiercoles;
    private boolean isJueves;
    private boolean isViernes;
    private boolean isSabado;
    private boolean isDomingo;


    public ServiciosProgramado(int idServicio, String clienteTelefono, int idEmpleado, LocalDateTime fechaAgregación, LocalDateTime fechaAplcación, String nombre, String observaciones, Direccion direccion, LocalDateTime fechaUltimaAplicacion,
                               boolean isLunes, boolean isMartes, boolean isMiercoles, boolean isJueves, boolean isViernes, boolean isSabado, boolean isDomingo) {
        super(idServicio, clienteTelefono, idEmpleado, fechaAgregación, fechaAplcación, nombre, observaciones, direccion);
        this.fechaUltimaAplicacion = fechaUltimaAplicacion;
        this.isLunes = isLunes;
        this.isMartes = isMartes;
        this.isMiercoles = isMiercoles;
        this.isJueves = isJueves;
        this.isViernes = isViernes;
        this.isSabado = isSabado;
        this.isDomingo = isDomingo;
    }

    public ServiciosProgramado(int idServicio, String clienteTelefono, int idEmpleado, LocalDateTime fechaAgregación, LocalDateTime fechaAplcación, Persona datos, LocalDateTime fechaUltimaAplicacion,
                               boolean isLunes, boolean isMartes, boolean isMiercoles, boolean isJueves, boolean isViernes, boolean isSabado, boolean isDomingo) {
        super(idServicio, clienteTelefono, idEmpleado, fechaAgregación, fechaAplcación, datos);
        this.fechaUltimaAplicacion = fechaUltimaAplicacion;
        this.isLunes = isLunes;
        this.isMartes = isMartes;
        this.isMiercoles = isMiercoles;
        this.isJueves = isJueves;
        this.isViernes = isViernes;
        this.isSabado = isSabado;
        this.isDomingo = isDomingo;
    }


}
