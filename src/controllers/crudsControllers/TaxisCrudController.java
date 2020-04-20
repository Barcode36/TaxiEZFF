package controllers.crudsControllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import models.Taxi;
import models.Taxista;
import models.interfaces.AddRegistro;
import models.interfaces.SetAddRegistroListener;

public class TaxisCrudController extends SetAddRegistroListener {
    @FXML
    private AnchorPane root;

    @FXML
    private Label lbl_tittle;

    @FXML
    private JFXTextField textField_unidad;

    @FXML
    private JFXComboBox<?> comboBox_marca;

    @FXML
    private JFXTextField textField_placa;

    @FXML
    private JFXTextField textField_modelo;

    @FXML
    private JFXComboBox<?> comboBox_taxista;

    @FXML
    private Button btn_Agregar;

    @FXML
    void btn_Agregar_Click(ActionEvent event) {
        enviarRegistro(null);
    }

    @FXML
    void btn_Cancelar_Click(ActionEvent event) {

    }



}
