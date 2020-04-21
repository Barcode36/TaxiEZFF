package controllers.crudsControllers;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import models.Cliente;
import models.Direccion;
import models.interfaces.Registro;
import models.interfaces.SetAddRegistroListener;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientesCrudController extends SetAddRegistroListener implements Initializable {
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    void btn_Agregar_Click(ActionEvent event) {
        enviarRegistro();
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
}
