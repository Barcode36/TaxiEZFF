package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

    public class ClientesController {

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


    }

