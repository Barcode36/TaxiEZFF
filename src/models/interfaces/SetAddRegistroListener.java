package models.interfaces;

public abstract class SetAddRegistroListener {
    protected AddRegistro addRegistroListener;

    public SetAddRegistroListener() {
    }

    public void setAddRegistroListener(AddRegistro addRegistroListener) {
        this.addRegistroListener = addRegistroListener;
    }

    public void enviarRegistro(Registro registro){
        if(addRegistroListener!=null){
            this.addRegistroListener.addRegistro(registro);
        }
    }
}