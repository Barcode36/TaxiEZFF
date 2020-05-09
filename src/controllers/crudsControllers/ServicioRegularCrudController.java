package controllers.crudsControllers;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import com.jfoenix.controls.base.IFXValidatableControl;
import com.jfoenix.validation.RequiredFieldValidator;
import com.sun.javafx.robot.FXRobot;
import com.sun.javafx.robot.FXRobotFactory;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.ServicioRegular;
import models.interfaces.AddRegistro;
import models.interfaces.IValidateCRUD;
import models.interfaces.Registro;
import models.interfaces.SetAddRegistroListener;
import services.StringLengthValidator;
import services.sql.TaxisSQL;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ServicioRegularCrudController extends SetAddRegistroListener implements Initializable,IValidateCRUD {

    @FXML
    private AnchorPane root;

    @FXML
    private Label lbl_tittle;

    @FXML
    private JFXTextField textField_telefono;

    @FXML
    private JFXTextField textField_nombre;

    @FXML
    private JFXTextField textField_calle;

    @FXML
    private JFXTextField textField_colonia;

    @FXML
    private JFXTextField textField_num_ext;

    @FXML
    private JFXTextField textField_numInt;

    @FXML
    private JFXTextField textField_observaciones;

    @FXML
    private JFXTimePicker timePicker_horaServicio;

    @FXML
    private JFXDatePicker datePicker_dia;

    @FXML
    private Button btn_aceptar;

    @FXML
    private Button btn_cancelar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        timePicker_horaServicio.setValue(LocalTime.now());
        datePicker_dia.setValue(LocalDate.now());


    }

    @FXML
    void btnAceptar_OnAction(ActionEvent event) {
        if(validarCampos() )
            if(enviarRegistro(((Stage)btn_aceptar.getScene().getWindow())))
                ((Stage)btn_aceptar.getScene().getWindow()).close();

    }

    @FXML
    void btnCancelar_OnAction(ActionEvent event) {
        ((Stage)btn_aceptar.getScene().getWindow()).close();

    }



    @Override
    public ArrayList<IFXValidatableControl> listControlsRequired() {
        return null;
    }

    @Override
    public void setFieldValidations() {

        this.setLengthValidation();
        this.setRequiredValidation();
        this.setFocusedProperty();
    }

    @Override
    public void setLengthValidation() {

        textField_telefono.getValidators().add(new StringLengthValidator("Máximo 11 carácteres.",11));
        textField_colonia.getValidators().add(new StringLengthValidator("Máximo 45 carácteres.",45));
        textField_calle.getValidators().add(new StringLengthValidator("Máximo 45 carácteres.",45));
        textField_num_ext.getValidators().add(new StringLengthValidator("Máximo 10 carácteres.",10));
        textField_numInt.getValidators().add(new StringLengthValidator("Máximo 10 carácteres.",10));


    }

    @Override
    public void setRequiredValidation() {
        textField_telefono.getValidators().add(new RequiredFieldValidator("Este campo es requerido."));
        textField_colonia.getValidators().add(new RequiredFieldValidator("Este campo es requerido."));
        textField_calle.getValidators().add(new RequiredFieldValidator("Este campo es requerido."));
        datePicker_dia.getValidators().add(new RequiredFieldValidator("Este campo es requerido."));
        timePicker_horaServicio.getValidators().add(new RequiredFieldValidator("Este campo es requerido."));
    }

    private void setFocusedProperty() {

        textField_nombre.focusedProperty().addListener((observable,  oldValue,  newValue)-> {
            if(!newValue)
                textField_telefono.validate();
        });

        textField_colonia.focusedProperty().addListener((observable,  oldValue,  newValue)-> {
            if(!newValue)
                textField_colonia.validate();
        });
        textField_calle.focusedProperty().addListener((observable,  oldValue,  newValue)-> {
            if(!newValue)
                textField_calle.validate();
        });
        datePicker_dia.focusedProperty().addListener((observable,  oldValue,  newValue)-> {
            if(!newValue)
                datePicker_dia.validate();
        });
        timePicker_horaServicio.focusedProperty().addListener((observable,  oldValue,  newValue)-> {
            if(!newValue)
                timePicker_horaServicio.validate();
        });
        textField_numInt.focusedProperty().addListener((observable,  oldValue,  newValue)-> {
            if(!newValue)
                textField_numInt.validate();
        });
        textField_num_ext.focusedProperty().addListener((observable,  oldValue,  newValue)-> {
            if(!newValue)
                textField_num_ext.validate();
        });



    }

    @Override
    public boolean validarCampos() {
        ObservableList<Node> listaHijos = root.getChildren();
        boolean validacioExitosa = true;


        for(Node node : listaHijos){
            if(node instanceof IFXValidatableControl){
                boolean validate = ((IFXValidatableControl) node).validate();
                validacioExitosa = validacioExitosa&&validate;
            }
        }


        return validacioExitosa;
    }

    @Override
    public void extraerRegistro(Registro registro) {

        //los servicios son editables, este metodo nunca se usará en este controller.

    }

    @Override
    public Registro guardarCambiosRegistros() {


        return null;

    }

}
