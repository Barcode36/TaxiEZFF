package models.interfaces;

import javafx.event.ActionEvent;

public abstract class SetAddRegistroListener {
    protected AddRegistro addRegistroListener;
   // protected Registro registroActualizar=null;

    public SetAddRegistroListener() {
    }

    public void setAddRegistroListener(AddRegistro addRegistroListener) {
        this.addRegistroListener = addRegistroListener;
        extraerRegistro(addRegistroListener.getRegistro());
    }

    /**
     * Envia el registro a la referencia indicada, es decir a la pantalla padre(Listener).
     */
    public boolean enviarRegistro(ActionEvent event){
        if(addRegistroListener!=null){
            //haciendo callback con un booleano digamos, para confirmar la adición a la DB y cerrar la ventana. cambiando firma de void a bool.
            return this.addRegistroListener.addRegistro(guardarCambiosRegistros(),event);
        }
        //casi no deseable, este pundo jamás debe llegar,porque significaria que el listener es null. Las ventanas CRUDD que implementan esta clase
        //son invocadas por otra ventana por eso siempre hay un listener, y en caso de que no lo haya entonces la ventana no existe y tampoco ese punto del programa.

        return false;
    }

    /**
     * Extrae los datos del registro para mostrarlos en la ventana crud, para hacer su  adición.
     * Se implementa diferente en cada crud, por eso es abrstracto.
     * @param registro
     * Registro el cual se va a editar.
     */
    public abstract void extraerRegistro(Registro registro);

    /**
     * Su implementación debe contener primero una validación de datos para después
     * retornar la instancia editada o creada.
     * @return
     * Instancia editada o creada.
     */
    public abstract Registro guardarCambiosRegistros();
}