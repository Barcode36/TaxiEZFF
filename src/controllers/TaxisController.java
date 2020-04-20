package controllers;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import controllers.crudsControllers.TaxisCrudController;
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
import models.interfaces.Registro;
import models.interfaces.AddRegistro;
import models.interfaces.IAccion;

import java.io.IOException;

public class TaxisController implements IAccion {
    @FXML
    private Label label_taxis;

    @FXML
    private JFXTextField txt_buscarTaxis;

    @FXML
    private JFXButton button_agregarTaxi;

    @FXML
    private JFXButton button_eliminarTaxi;

    @FXML
    private JFXButton button_actualizarTaxi;

    @FXML
    private JFXTreeTableView<?> table_taxis;

    @FXML
    private TreeTableColumn<?, ?> column_unidad;

    @FXML
    private TreeTableColumn<?, ?> column_marca;

    @FXML
    private TreeTableColumn<?, ?> column_modelo;

    @FXML
    private TreeTableColumn<?, ?> column_placa;

    @FXML
    private TreeTableColumn<?, ?> column_taxista;

    @FXML
    void btnAgregarTaxi_OnAction(ActionEvent event) throws IOException {

        try {

            FXMLLoader controladorLoader = new FXMLLoader(getClass().getResource("/views/Cruds/TaxisCRUD.fxml"));
            AnchorPane contenedorServicios = controladorLoader.load();
            TaxisCrudController taxisCrudController = controladorLoader.getController();

            taxisCrudController.setAddRegistroListener(new AddRegistro() {
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
    void btnActualizarTaxi_OnAction(ActionEvent event) {

    }

    @FXML
    void btnEliminarTaxi_OnAction(ActionEvent event) {

    }

    @Override
    public void accionPrimaria() {
        this.button_agregarTaxi.fire();
    }

    @Override
    public void accionSecundaria() {
        this.button_eliminarTaxi.fire();
    }

    @Override
    public void accionTerciaria() {
        this.button_actualizarTaxi.fire();
    }
}
