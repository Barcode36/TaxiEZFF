package models.interfaces;

import controllers.crudsControllers.ClientesCrudController;
import javafx.event.ActionEvent;
import javafx.util.Callback;



public abstract class AddRegistro {

    protected Registro registro = null;

    public AddRegistro(Registro registro) {
        this.registro = registro;
    }

    public abstract boolean addRegistro(Registro registro, ActionEvent event);

    public AddRegistro() {
        super();
    }


    public Registro getRegistro() {
        return registro;
    }
}
