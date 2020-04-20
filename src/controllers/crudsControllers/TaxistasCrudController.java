package controllers.crudsControllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import models.interfaces.SetAddRegistroListener;

public class TaxistasCrudController extends SetAddRegistroListener {
    @FXML
    private AnchorPane root;

    @FXML
    private Label lbl_tittle;

    @FXML
    private JFXTextField textField_nombre;

    @FXML
    private JFXTextField textField_telefono;

    @FXML
    private JFXComboBox<?> comboBox_sexo;

    @FXML
    private JFXDatePicker datePicker_nacimiento;

    @FXML
    private JFXTextField textField_calle;

    @FXML
    private JFXTextField textField_colonia;

    @FXML
    private JFXTextField textField_numExt;

    @FXML
    private JFXTextField textField_numInt;

    @FXML
    private JFXTextField textField_observaciones;

    @FXML
    void btn_Agregar_Click(ActionEvent event) {
        enviarRegistro(null);
    }

    @FXML
    void btn_Cancelar_Click(ActionEvent event) {

    }
}
