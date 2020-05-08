package controllers.crudsControllers;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import com.jfoenix.controls.base.IFXValidatableControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import models.interfaces.IValidateCRUD;
import models.interfaces.Registro;
import models.interfaces.SetAddRegistroListener;

import java.net.URL;
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

    }

    @FXML
    void btnAceptar_OnAction(ActionEvent event) {

    }

    @FXML
    void btnCancelar_OnAction(ActionEvent event) {

    }



    @Override
    public ArrayList<IFXValidatableControl> listControlsRequired() {
        return null;
    }

    @Override
    public void setFieldValidations() {

    }

    @Override
    public void setLengthValidation() {

    }

    @Override
    public void setRequiredValidation() {

    }

    @Override
    public boolean validarCampos() {
        return false;
    }

    @Override
    public void extraerRegistro(Registro registro) {

    }

    @Override
    public Registro guardarCambiosRegistros() {
        return null;
    }
}
