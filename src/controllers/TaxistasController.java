package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import controllers.crudsControllers.EmpleadosCrudController;
import controllers.crudsControllers.TaxistasCrudController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.interfaces.AddRegistro;
import models.interfaces.IAccion;
import models.interfaces.Registro;

import java.io.IOException;

public class TaxistasController implements IAccion {
    @FXML
    private AnchorPane fondo_taxistas;

    @FXML
    private Label label_titulo_taxistas;

    @FXML
    private JFXTextField txt_buscar;

    @FXML
    private JFXTreeTableView<?> table_taxistas;

    @FXML
    private TreeTableColumn<?, ?> column_nombre_taxistas;

    @FXML
    private TreeTableColumn<?, ?> column_telefono_taxistas;

    @FXML
    private TreeTableColumn<?, ?> column_direccion_taxistas;

    @FXML
    private TreeTableColumn<?, ?> column_observaciones_taxistas;

    @FXML
    private TreeTableColumn<?, ?> column_fechaNac_taxistas;

    @FXML
    private JFXButton button_agregarTaxista;

    @FXML
    private JFXButton button_eliminarTaxista;

    @FXML
    private JFXButton button_actualizarTaxista;

    @Override
    public void accionPrimaria() {
        this.button_agregarTaxista.fire();
    }

    @Override
    public void accionSecundaria() {
        this.button_eliminarTaxista.fire();
    }

    @Override
    public void accionTerciaria() {
        this.button_actualizarTaxista.fire();
    }
    @FXML
    void btnActualizarTaxista_OnAction(ActionEvent event) {

    }

    @FXML
    void btnAgregarTaxista_OnAction(ActionEvent event) {
        try {

            FXMLLoader controladorLoader = new FXMLLoader(getClass().getResource("/views/Cruds/TaxistasCRUD.fxml"));
            AnchorPane contenedorServicios = controladorLoader.load();
            TaxistasCrudController taxistasCrudController = controladorLoader.getController();

            taxistasCrudController.setAddRegistroListener(new AddRegistro() {
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
    void btnEliminarTaxista_OnAction(ActionEvent event) {

    }

}
