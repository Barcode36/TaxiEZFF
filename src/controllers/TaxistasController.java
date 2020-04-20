package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class TaxistasController {
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

}
