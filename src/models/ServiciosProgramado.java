package models;

import java.time.LocalDateTime;

public class ServiciosProgramado extends Servicio<ServiciosProgramado> {

    private LocalDateTime fechaUltimaAplicacion;
    private boolean isLunes;
    private boolean isMartes;
    private boolean isMiercoles;
    private boolean isJueves;
    private boolean isViernes;
    private boolean isSabado;
    private boolean isDomingo;


    public ServiciosProgramado(String nombre, String observaciones, Direccion direccion, int idServicio, LocalDateTime fechaAgregacion, LocalDateTime fechaServicio, LocalDateTime fechaAplcacion, boolean isCancelado, Cliente cliente, Empleado empleado, LocalDateTime fechaUltimaAplicacion, boolean isLunes, boolean isMartes, boolean isMiercoles, boolean isJueves, boolean isViernes, boolean isSabado, boolean isDomingo) {
        super(nombre, observaciones, direccion, idServicio, fechaAgregacion, fechaServicio, fechaAplcacion, isCancelado, cliente, empleado);
        this.fechaUltimaAplicacion = fechaUltimaAplicacion;
        this.isLunes = isLunes;
        this.isMartes = isMartes;
        this.isMiercoles = isMiercoles;
        this.isJueves = isJueves;
        this.isViernes = isViernes;
        this.isSabado = isSabado;
        this.isDomingo = isDomingo;
    }

    public ServiciosProgramado(Persona datos, int idServicio, LocalDateTime fechaAgregacion, LocalDateTime fechaServicio, LocalDateTime fechaAplcacion, boolean isCancelado, Cliente cliente, Empleado empleado, LocalDateTime fechaUltimaAplicacion, boolean isLunes, boolean isMartes, boolean isMiercoles, boolean isJueves, boolean isViernes, boolean isSabado, boolean isDomingo) {
        super(datos, idServicio, fechaAgregacion, fechaServicio, fechaAplcacion, isCancelado, cliente, empleado);
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
