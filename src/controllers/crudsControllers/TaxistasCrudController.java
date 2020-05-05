package controllers.crudsControllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import models.Direccion;
import models.Taxista;
import models.interfaces.Registro;
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
        enviarRegistro(event);
    }

    @FXML
    void btn_Cancelar_Click(ActionEvent event) {

    }

    int idTaxista = 0;
    int idDireccion = 0;
    @Override
    public void extraerRegistro(Registro registro) {

        if(registro!=null){
            Taxista taxista = (Taxista) registro;
            Direccion direccion = taxista.getDireccion();
            this.idTaxista = taxista.getIdTaxista();
            this.idDireccion = taxista.getDireccion().getIdDireccion();
            textField_telefono.setText(taxista.getTelefono());
            textField_nombre.setText(taxista.getNombre());

            datePicker_nacimiento.setValue(taxista.getFechaNac());
            textField_calle.setText(direccion.getCalle());
            textField_colonia.setText(direccion.getColonia());
            textField_numExt.setText(direccion.getNumExt());
            textField_numInt.setText(direccion.getNumInt());

            textField_observaciones.setText(taxista.getObservaciones());
        }

    }

    @Override
    public Registro guardarCambiosRegistros() {

        Direccion direccion =  new Direccion(this.idDireccion, textField_calle.getText(), textField_colonia.getText(), textField_numInt.getText(), textField_numExt.getText());
        Taxista taxista = new Taxista(this.idTaxista, datePicker_nacimiento.getValue(), textField_telefono.getText(), textField_nombre.getText(), textField_observaciones.getText(), direccion);


        return taxista;
    }
}
