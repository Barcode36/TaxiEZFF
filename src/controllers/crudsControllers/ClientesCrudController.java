package controllers.crudsControllers;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.base.IFXValidatableControl;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.Cliente;
import models.Direccion;
import models.interfaces.IValidateCRUD;
import models.interfaces.Registro;
import models.interfaces.SetAddRegistroListener;
import services.StringLengthValidator;
import javafx.scene.control.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ClientesCrudController extends SetAddRegistroListener implements Initializable, IValidateCRUD {
    @FXML
    private AnchorPane root;

    @FXML
    private JFXTextField textField_telefono;

    @FXML
    private Label lbl_tittle;

    @FXML
    private JFXTextField textField_nombre;

    @FXML
    private JFXTextField textField_calle;

    @FXML
    private JFXTextField textField_colonia;

    @FXML
    private JFXTextField textField_numExt;

    @FXML
    private JFXTextField textField_numInt;

    @FXML
    private JFXTextField textField_observ;

    @FXML
    private Button button_Aceptar;

    @FXML
    private Button button_Cancelar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       // setFieldValidations();
        button_Aceptar.setOnKeyReleased((event)->{
            if(event.getCode() == KeyCode.ENTER)
                button_Aceptar.fire();
        });
    }
    @FXML
    void btn_Agregar_Click(ActionEvent event) {
        enviarRegistro();
        ((Stage)button_Aceptar.getScene().getWindow()).close();
    }

    @FXML
    void btn_Cancelar_Click(ActionEvent event) {
        //cerrar ventana
    }

    @Override
    public void extraerRegistro(Registro registro) {
        //extrae la instancia.
        //cuando es null entonces es para registro nuevo, no una edición-
        if(registro !=null){
            Cliente clienteExtraido = (Cliente)registro;
            Direccion direccionCliente = clienteExtraido.getDireccion();
            textField_calle.setText(direccionCliente.getCalle());
            textField_colonia.setText(direccionCliente.getColonia());
            textField_numExt.setText(direccionCliente.getNumExt());
            textField_numInt.setText(direccionCliente.getNumInt());

            textField_nombre.setText(clienteExtraido.getNombre());
            textField_telefono.setText(clienteExtraido.getNumero());
            textField_observ.setText(clienteExtraido.getObservaciones());
        }
    }

    @Override
    public Registro guardarCambiosRegistros() {
        //validar campos
        return getClienteVentana();//return new Cliente
    }

    /**
     * Mapea la ventana clientes, para generar un objeto, con los datos disponibles en la ventana.
     * Este método debe llamarse despues de validar los campos, para no tenereun campo nulo.
     * @return
     * Retorna objeto cliente, del resultado de la extracción de los datos de la ventana Clientes.
     */
    private Cliente getClienteVentana(){
        Direccion direccion =  new Direccion(0, textField_calle.getText(), textField_colonia.getText(), textField_numInt.getText(), textField_numExt.getText());
        Cliente clienteVentana = new Cliente(textField_telefono.getText(),true,textField_nombre.getText(), textField_observ.getText(),direccion);

        return clienteVentana;
    }

    @Override
    public ArrayList<IFXValidatableControl> listControlsRequired() {
        return null;
    }

    @Override
    public void setFieldValidations() {
        setLengthValidation();
        setRequiredValidation();
    }

    @Override
    public void setLengthValidation() {
        this.textField_nombre.getValidators().add(new StringLengthValidator("Longuitud máxima de 70 carácteres.", 70));
        this.textField_nombre.getValidators().add(new StringLengthValidator("Longuitud máxima de 15 carácteres.", 15));
        this.textField_calle.getValidators().add(new StringLengthValidator("Longuitud máxima de 45 carácteres.", 45));
        this.textField_colonia.getValidators().add(new StringLengthValidator("Longuitud máxima de 45 carácteres.", 45));
        textField_nombre.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                //mandará el validate cada que presionen una tecla
                textField_nombre.validate();
            }
        });
        //listener de foco
     /*   ((Node)actual).focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                    actual.validate();
                }
            }
        });
    */
    }

    @Override
    public void setRequiredValidation() {
        this.textField_telefono.getValidators().add(new RequiredFieldValidator("Este campo es requerido."));
        this.textField_calle.getValidators().add(new RequiredFieldValidator("Este campo es requerido."));
        this.textField_colonia.getValidators().add(new RequiredFieldValidator("Este campo es requerido."));
    }

    @Override
    public boolean validarCampos() {
        return false;
    }
}
