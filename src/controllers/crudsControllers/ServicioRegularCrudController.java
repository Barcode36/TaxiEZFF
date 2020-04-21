package controllers.crudsControllers;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import models.interfaces.Registro;
import models.interfaces.SetAddRegistroListener;

public class ServicioRegularCrudController extends SetAddRegistroListener {

    @FXML
    private AnchorPane root;

    @FXML
    private JFXTextField textField_telefono_buscar;

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
    private JFXTextField textField_notas;

    @FXML
    private JFXTextField textField_observaciones;

    @FXML
    private JFXTimePicker timePicker_horaServicio;

    @FXML
    private JFXDatePicker datePicker_dia;

    @FXML
    private JFXTextField txt_destino;

    @FXML
    private Button btn_aceptar;

    @FXML
    private Button btn_cancelar;

    @FXML
    void DetectFocusable_OnMouse(MouseEvent event) {

    }

    @FXML
    void btnAceptar_OnAction(ActionEvent event) {

    }

    @FXML
    void btnCancelar_OnAction(ActionEvent event) {

    }


    @FXML
    void txtBuscarTelefono_OnKeyRealased(KeyEvent event) {

    }

    @Override
    public void extraerRegistro(Registro registro) {

    }

    @Override
    public Registro guardarCambiosRegistros() {
        return null;
    }
}
