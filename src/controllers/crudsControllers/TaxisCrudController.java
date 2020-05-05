package controllers.crudsControllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.base.IFXValidatableControl;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import models.Taxi;
import models.Taxista;
import models.interfaces.IValidateCRUD;
import models.interfaces.Registro;
import models.interfaces.SetAddRegistroListener;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TaxisCrudController extends SetAddRegistroListener implements Initializable, IValidateCRUD {
    @FXML
    private AnchorPane root;

    @FXML
    private Label lbl_tittle;

    @FXML
    private JFXTextField textField_unidad;

    @FXML
    private JFXTextField textField_marca;

    @FXML
    private JFXTextField textField_placa;

    @FXML
    private JFXTextField textField_modelo;

    @FXML
    private JFXComboBox<Taxista> comboBox_taxista;

    @FXML
    private Button btn_Agregar;

    @FXML
    private Button btn_Cancelar;

    ObservableList<Taxista> listaTaxistas = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btn_Agregar.setOnKeyReleased((event)->{
            if(event.getCode() == KeyCode.ENTER)
                btn_Agregar.fire();
        });
//obtener los taxistas de la DB
        comboBox_taxista.setItems(listaTaxistas);
        comboBox_taxista.setCellFactory(new Callback<ListView<Taxista>, ListCell<Taxista>>() {
            @Override
            public ListCell<Taxista> call(ListView<Taxista> param) {

                ListCell<Taxista> listCell = new ListCell<Taxista>(){
                    @Override
                    protected void updateItem(Taxista item, boolean empty) {
                        super.updateItem(item, empty);

                        if(item!=null){
                            this.setText(item.getIdTaxista() + " "+item.getNombre());
                        }

                    }
                };

                return listCell;
            }
        });
    }

    @FXML
    void btn_Agregar_Click(ActionEvent event) {
        enviarRegistro(((Stage)btn_Agregar.getScene().getWindow()));
        ((Stage)this.comboBox_taxista.getScene().getWindow()).close();
    }

    @FXML
    void btn_Cancelar_Click(ActionEvent event) {
        ((Stage)this.comboBox_taxista.getScene().getWindow()).close();

    }


    @Override
    public void extraerRegistro(Registro registro) {
        if(registro!=null){
            Taxi taxi = (Taxi) registro;
            textField_marca.setText(taxi.getMarca());
            textField_modelo.setText(taxi.getModelo());
            textField_unidad.setText(taxi.getIdUnidad()+"");
            textField_placa.setText(taxi.getPlaca());
            //si el
            for(Taxista taxista :listaTaxistas){
                if(taxista.getIdTaxista() == taxi.getTaxista().getIdTaxista()){
                    comboBox_taxista.getSelectionModel().select(taxista);
                }
            }
        }
    }

    @Override
    public Registro guardarCambiosRegistros() {
        return getTaxiVentana();
    }

    private Taxi getTaxiVentana() {
        Taxi taxi = new Taxi(
                Integer.parseInt(textField_unidad.getText()), textField_marca.getText(), textField_modelo.getText(),
                textField_placa.getText(),
                comboBox_taxista.getSelectionModel().getSelectedItem()
        );

        return taxi;
    }


    @Override
    public ArrayList<IFXValidatableControl> listControlsRequired() {
        return null;
    }

    @Override
    public void setFieldValidations() {
        this.setLengthValidation();
        this.setRequiredValidation();
    }

    @Override
    public void setLengthValidation() {

    }

    @Override
    public void setRequiredValidation() {
        this.textField_unidad.getValidators().add(new RequiredFieldValidator("Campo requerido."));
        this.textField_marca.getValidators().add(new RequiredFieldValidator("Campo requerido."));
        this.textField_placa.getValidators().add(new RequiredFieldValidator("Campo requerido."));
        this.textField_modelo.getValidators().add(new RequiredFieldValidator("Campo requerido."));
        this.comboBox_taxista.getValidators().add(new RequiredFieldValidator("Campo requerido."));
    }

    @Override
    public boolean validarCampos() {
        return false;
    }
}
