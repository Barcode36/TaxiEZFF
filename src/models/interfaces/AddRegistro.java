package models.interfaces;

import controllers.crudsControllers.ClientesCrudController;
import javafx.util.Callback;

public abstract class AddRegistro {

    protected Registro registro = null;

    public AddRegistro(Registro registro) {
        this.registro = registro;
    }

    public abstract void addRegistro(Registro registro);

    public AddRegistro() {
        super();
    }


    public Registro getRegistro() {
        return registro;
    }
}
