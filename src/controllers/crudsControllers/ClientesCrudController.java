package controllers.crudsControllers;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ClientesCrudController {
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
    void DetectFocusable_OnMouse(MouseEvent event) {

    }

    @FXML
    void btn_Agregar_Click(ActionEvent event) {

    }

    @FXML
    void btn_Cancelar_Click(ActionEvent event) {

    }

    @FXML
    void focusable_OnKey(KeyEvent event) {

    }
}
