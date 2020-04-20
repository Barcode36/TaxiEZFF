package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


public class EmpleadosController {
    @FXML
    private AnchorPane fondo_empleados;

    @FXML
    private JFXTextField txt_buscar;

    @FXML
    private JFXTreeTableView<?> table_empleados;

    @FXML
    private TreeTableColumn<?, ?> column_nombre;

    @FXML
    private TreeTableColumn<?, ?> column_telefono;

    @FXML
    private TreeTableColumn<?, ?> column_direccion;

    @FXML
    private TreeTableColumn<?, ?> column_observaciones;

    @FXML
    private TreeTableColumn<?, ?> column_fechaNac;

    @FXML
    private Label label_empleados;

    @FXML
    private JFXButton button_agregarEmpleado;

    @FXML
    private JFXButton button_eliminarEmpleado;

    @FXML
    private JFXButton button_actualizarEmpleado;


}
