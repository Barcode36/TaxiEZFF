package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.AnchorPane;
import models.interfaces.IAccion;

import java.net.URL;
import java.util.ResourceBundle;

public class ServiciosController implements Initializable, IAccion {

    @FXML
    private AnchorPane anchorPaneRoot;

    @FXML
    private JFXTabPane tabPaneServicios;

    @FXML
    private Tab tabServicios;

    @FXML
    private JFXTextField textField_buscarServicios;

    @FXML
    private JFXTreeTableView<?> tablaServicio;

    @FXML
    private TreeTableColumn<?, ?> cmServicios_fechaAdd;

    @FXML
    private TreeTableColumn<?, ?> cmServicios_FechaAplic;

    @FXML
    private TreeTableColumn<?, ?> cmServicios_telefono;

    @FXML
    private TreeTableColumn<?, ?> cmServicios_nombre;

    @FXML
    private TreeTableColumn<?, ?> cmServicios_direccion;

    @FXML
    private TreeTableColumn<?, ?> cmServicios_notas;

    @FXML
    private TreeTableColumn<?, ?> cmServicios_unidad;

    @FXML
    private TreeTableColumn<?, ?> cmServicios_modulador;

    @FXML
    private JFXTextField textField_buscarPendiente;

    @FXML
    private JFXTextField textField_servicioRapido;

    @FXML
    private JFXTextField textField_cantidad;

    @FXML
    private JFXButton btnAddServicio;

    @FXML
    private JFXButton btnCancelServicio;

    @FXML
    private JFXButton btnAplicarServicio;

    @FXML
    private Tab tabServiciosPendientes;

    @FXML
    private JFXTreeTableView<?> tablaServicioPend;

    @FXML
    private TreeTableColumn<?, ?> cmServiciosPend_fechaAdd;

    @FXML
    private TreeTableColumn<?, ?> cmServiciosPend_telefono;

    @FXML
    private TreeTableColumn<?, ?> cmServiciosPend_nombre;

    @FXML
    private TreeTableColumn<?, ?> cmServiciosPend_direccion;

    @FXML
    private TreeTableColumn<?, ?> cmServiciosPend_notas;

    @FXML
    private TreeTableColumn<?, ?> cmServiciosPend_modulador;

    @FXML
    private Tab tabServiciosProgramados;

    @FXML
    private JFXTreeTableView<?> tablaServicioProgr;

    @FXML
    private TreeTableColumn<?, ?> cmServiciosProg_FechaInicio;

    @FXML
    private TreeTableColumn<?, ?> cmServiciosProg_FechaFin;

    @FXML
    private TreeTableColumn<?, ?> cmServiciosProg_telefono;

    @FXML
    private TreeTableColumn<?, ?> cmServiciosProg_nombre;

    @FXML
    private TreeTableColumn<?, ?> cmServiciosProg_dirección;

    @FXML
    private TreeTableColumn<?, ?> cmServiciosProg_notas;

    @FXML
    private TreeTableColumn<?, ?> cmServiciosProg_modulador;

    @FXML
    private TreeTableColumn<?, ?> cmServiciosProg_diasServicio;

    @FXML
    private JFXTextField textField_buscarProgramado;

    @FXML
    private JFXButton btnAddProgramado;

    @FXML
    private JFXButton btnTerminarProgramacion;

    @FXML
    private JFXButton btnAplicarServicioProgramado;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @Override
    public void accionPrimaria() {
        if(tabPaneServicios.getSelectionModel().getSelectedItem() == tabServiciosProgramados){
            btnAddProgramado.fire();
        }else{
            btnAddServicio.fire();
        }
    }

    @Override
    public void accionSecundaria() {
        if(tabPaneServicios.getSelectionModel().getSelectedItem() == tabServiciosProgramados){
            btnTerminarProgramacion.fire();
        }else{
            btnCancelServicio.fire();
        }
    }

    @Override
    public void accionTerciaria() {
        if(tabPaneServicios.getSelectionModel().getSelectedItem() == tabServiciosProgramados){
            btnAplicarServicioProgramado.fire();
        }else{
            btnAplicarServicio.fire();
        }
    }

    @FXML
    void btnAddServicioNormal_OnAction(ActionEvent event) {
        System.out.println("Add servicio normal.");
    }

    @FXML
    void btnAddServicioProgramado_OnAction(ActionEvent event) {
        System.out.println("Add servicio programado .");
    }

    @FXML
    void btnAplicarServicioNormal_OnAction(ActionEvent event) {
        System.out.println("Aplicar servicio normal.");
    }

    @FXML
    void btnAplicarServicioProgramado_OnAction(ActionEvent event) {
        System.out.println("Aplicar servicio programado.");
    }

    @FXML
    void btnCancelServicioNormal_OnAction(ActionEvent event) {
        System.out.println("Cancel servicio normal.");
    }

    @FXML
    void btnFinalizarServicioProgramado_OnAction(ActionEvent event) {
        System.out.println("FInalizar servicio programado.");
    }
}
