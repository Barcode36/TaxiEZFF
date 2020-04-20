package controllers.crudsControllers;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class EmpleadosCrudController {
    @FXML
    private AnchorPane root;

    @FXML
    private Label lbl_tittle;

    @FXML
    private JFXTextField textField_nombre;

    @FXML
    private JFXTextField textField_telefono;

    @FXML
    private JFXPasswordField textField_password;

    @FXML
    private JFXDatePicker datePicker_nacimiento;

    @FXML
    private JFXComboBox<?> comboBox_sexo;

    @FXML
    private JFXComboBox<?> comboBox_tipo_empleado;

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
    void btn_Agregar_Click(ActionEvent event) {

    }

    @FXML
    void btn_Cancelar_Click(ActionEvent event) {

    }
}
