package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import controllers.crudsControllers.ClientesCrudController;
import controllers.crudsControllers.TaxisCrudController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.interfaces.AddRegistro;
import models.interfaces.IAccion;
import models.interfaces.Registro;

import java.io.IOException;

public class ClientesController implements IAccion {

        @FXML
        private AnchorPane fondo_clientes;

        @FXML
        private JFXTextField txtF_buscar;

        @FXML
        private JFXTreeTableView<?> table_view_clientes;

        @FXML
        private TreeTableColumn<?, ?> column_nombre;

        @FXML
        private TreeTableColumn<?, ?> column_direccion;

        @FXML
        private TreeTableColumn<?, ?> column_telefono;

        @FXML
        private TreeTableColumn<?, ?> column_observaciones;

        @FXML
        private Label label_clientes;

        @FXML
        private JFXButton button_agregarCliente;

        @FXML
        private JFXButton button_eliminarCliente;

        @FXML
        private JFXButton button_actualizarCliente;


    @Override
    public void accionPrimaria() {
        this.button_agregarCliente.fire();
    }

    @Override
    public void accionSecundaria() {
        this.button_eliminarCliente.fire();
    }

    @Override
    public void accionTerciaria() {
        this.button_actualizarCliente.fire();
    }

    @FXML
    void btnActualizarCliente_OnAction(ActionEvent event) {

    }

    @FXML
    void btnAddCliente_OnAction(ActionEvent event) {
        try {

            FXMLLoader controladorLoader = new FXMLLoader(getClass().getResource("/views/Cruds/ClientesCRUD.fxml"));
            AnchorPane contenedorServicios = controladorLoader.load();
            ClientesCrudController clientesCrudController = controladorLoader.getController();

            clientesCrudController.setAddRegistroListener(new AddRegistro() {
                @Override
                public void addRegistro(Registro registro) {
                    System.out.println("LLegó registro");
                }
            });

            Stage primaryStage = new Stage();
            // Parent root = FXMLLoader.load(getClass().getResource("/views/Cruds/taxisCRUD.fxml"));
            primaryStage.setTitle("Taxis añadir");
            primaryStage.setScene(new Scene(contenedorServicios));
            primaryStage.setResizable(false);
            primaryStage.initOwner( ((Node)event.getSource()).getScene().getWindow() );
            primaryStage.initModality(Modality.WINDOW_MODAL);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnDeleteCliente_OnAction(ActionEvent event) {

    }
}

