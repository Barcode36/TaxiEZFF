package controllers;

import com.jfoenix.controls.*;
import com.sun.javafx.robot.FXRobot;
import com.sun.javafx.robot.FXRobotFactory;
import controllers.crudsControllers.ServicioRegularCrudController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import models.*;
import models.interfaces.AddRegistro;
import models.interfaces.IAccion;
import models.interfaces.Registro;
import services.sql.ClienteSQL;
import services.sql.DireccionSQL;
import services.sql.ServicioRegularSQL;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
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
    private JFXTreeTableView<ServicioRegular> tablaServicio;

    @FXML
    private TreeTableColumn<ServicioRegular, LocalDateTime> cmServicios_fechaAdd;

    @FXML
    public TreeTableColumn<ServicioRegular,LocalDateTime> cmServicios_fechaServicio;

    @FXML
    private TreeTableColumn<ServicioRegular, LocalDateTime>  cmServicios_FechaAplic;

    @FXML
    private TreeTableColumn<ServicioRegular, Cliente> cmServicios_telefono;

    @FXML
    private TreeTableColumn<ServicioRegular, String> cmServicios_nombre;

    @FXML
    private TreeTableColumn<ServicioRegular, Direccion> cmServicios_direccion;

    @FXML
    private TreeTableColumn<ServicioRegular, String> cmServicios_observaciones;

    @FXML
    private TreeTableColumn<ServicioRegular, Taxi> cmServicios_unidad;

    @FXML
    private TreeTableColumn<ServicioRegular,Empleado> cmServicios_modulador;

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
    private JFXTreeTableView<ServicioRegular> tablaServicioPend;

    @FXML
    private TreeTableColumn<ServicioRegular, LocalDateTime> cmServiciosPend_fechaAdd;

    @FXML
    public TreeTableColumn<ServicioRegular, LocalDateTime> cmServiciosPend_fechaServicio;

    @FXML
    private TreeTableColumn<ServicioRegular, Cliente> cmServiciosPend_telefono;

    @FXML
    private TreeTableColumn<ServicioRegular, String> cmServiciosPend_nombre;

    @FXML
    private TreeTableColumn<ServicioRegular, Direccion> cmServiciosPend_direccion;

    @FXML
    private TreeTableColumn<ServicioRegular, String> cmServiciosPend_notas;

    @FXML
    private TreeTableColumn<ServicioRegular, Empleado> cmServiciosPend_modulador;

    @FXML
    private Tab tabServiciosProgramados;

    @FXML
    private JFXTreeTableView<?> tablaServicioProgr;

    @FXML
    public TreeTableColumn<?, ?> cmServiciosProg_FechaAdicion;

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

    ObservableList<ServicioRegular> listaServicioRegularesPendientes;
    ObservableList<ServicioRegular> listaServicioRegularesAplicados;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        /*----------------------------------------------------Aplicados ---------------------------------------------------------------*/

        /*this.tablaServicio.setRoot(clienteRecursiveTreeItem);
        this.tablaServicio.setShowRoot(false);

        cmServicios_fechaAdd.setCellValueFactory(new TreeItemPropertyValueFactory("fechaAgregacion"));
        cmServicios_fechaServicio.setCellValueFactory(new TreeItemPropertyValueFactory("fechaServicio"));
        cmServicios_FechaAplic.setCellValueFactory(new TreeItemPropertyValueFactory("fechaAplicacion"));
        cmServicios_telefono.setCellValueFactory(new TreeItemPropertyValueFactory("clienteTelefono"));
        cmServicios_nombre.setCellValueFactory(new TreeItemPropertyValueFactory("nombre"));
        cmServicios_direccion.setCellValueFactory(new TreeItemPropertyValueFactory("direccion"));
        cmServicios_observaciones.setCellValueFactory(new TreeItemPropertyValueFactory("observaciones"));
        cmServicios_unidad.setCellValueFactory(new TreeItemPropertyValueFactory("unidad"));
        cmServicios_modulador.setCellValueFactory(new TreeItemPropertyValueFactory("nombreEmpleado"));
*/

        cmServicios_fechaAdd.setCellValueFactory(new TreeItemPropertyValueFactory("fechaAgregacion"));
        cmServicios_fechaServicio.setCellValueFactory(new TreeItemPropertyValueFactory("fechaServicio"));
        cmServicios_FechaAplic.setCellValueFactory(new TreeItemPropertyValueFactory("fechaAplicacion"));
        cmServicios_telefono.setCellValueFactory(new TreeItemPropertyValueFactory("cliente"));//solo numero de telefono
        cmServicios_nombre.setCellValueFactory(new TreeItemPropertyValueFactory("nombre"));
        cmServicios_direccion.setCellValueFactory(new TreeItemPropertyValueFactory("direccion"));
        cmServicios_observaciones.setCellValueFactory(new TreeItemPropertyValueFactory("observaciones"));
        cmServicios_unidad.setCellValueFactory(new TreeItemPropertyValueFactory("taxi"));
        cmServicios_modulador.setCellValueFactory(new TreeItemPropertyValueFactory("empleado"));

        cmServicios_fechaAdd.setCellFactory(callbackDateTime);
        cmServicios_fechaServicio.setCellFactory(callbackDateTime);
        cmServicios_FechaAplic.setCellFactory(callbackDateTime);

        cmServicios_telefono.setCellFactory(new Callback<TreeTableColumn<ServicioRegular, Cliente>, TreeTableCell<ServicioRegular, Cliente>>() {
            @Override
            public TreeTableCell<ServicioRegular, Cliente> call(TreeTableColumn<ServicioRegular, Cliente> param) {


                TreeTableCell<ServicioRegular, Cliente> cell = new TreeTableCell<ServicioRegular, Cliente>(){
                    @Override
                    protected void updateItem(Cliente item, boolean empty) {
                        super.updateItem(item, empty);
                        if(item!=null){
                            setText(item.getNumero());
                        }
                    }
                };
                return cell;

            }
        });

        cmServicios_direccion.setCellFactory(callbackDireccion);


        cmServicios_unidad.setCellFactory(new Callback<TreeTableColumn<ServicioRegular, Taxi>, TreeTableCell<ServicioRegular, Taxi>>() {
            @Override
            public TreeTableCell<ServicioRegular, Taxi> call(TreeTableColumn<ServicioRegular, Taxi> param) {
                TreeTableCell<ServicioRegular, Taxi> cell = new TreeTableCell<ServicioRegular, Taxi>(){

                    @Override
                    protected void updateItem(Taxi item, boolean empty) {
                        super.updateItem(item, empty);
                        if(item!=null){
                            setText(item.getIdUnidad() + " " + item.getTaxista().getNombre());
                        }
                    }

                };
                return cell;
            }
        });

        cmServicios_modulador.setCellFactory(new Callback<TreeTableColumn<ServicioRegular, Empleado>, TreeTableCell<ServicioRegular, Empleado>>() {
            @Override
            public TreeTableCell<ServicioRegular, Empleado> call(TreeTableColumn<ServicioRegular, Empleado> param) {

                TreeTableCell<ServicioRegular, Empleado> cell = new TreeTableCell<ServicioRegular, Empleado>(){

                    @Override
                    protected void updateItem(Empleado item, boolean empty) {
                        super.updateItem(item, empty);
                        if(item!=null){
                            setText(item.getIdEmpleado() + " " + item.getNombre());
                        }
                    }

                };
                return cell;

            }
        });

        try {
            this.listaServicioRegularesAplicados = new ServicioRegularSQL().getServiciosRegularesAplicadosyCancelados();
            TreeItem<ServicioRegular> serivicioRegularAplicadosRecursiveTreeItem =
                    new RecursiveTreeItem<>(listaServicioRegularesAplicados, (recursiveTreeObject) -> recursiveTreeObject.getChildren());

            this.tablaServicio.setRoot(serivicioRegularAplicadosRecursiveTreeItem);
            this.tablaServicio.setShowRoot(false);

        } catch (SQLException e) {
            e.printStackTrace();
            //ventana error
        }

        /*----------------------------------------------------FIN APLICADOS ---------------------------------------------------------------*/

        /*---------------------------------------------------- PENDIENTES ---------------------------------------------------------------*/
        this.cmServiciosPend_fechaAdd.setCellValueFactory(new TreeItemPropertyValueFactory("fechaAgregacion"));
        this.cmServiciosPend_fechaServicio.setCellValueFactory(new TreeItemPropertyValueFactory("fechaServicio"));
        this.cmServiciosPend_telefono.setCellValueFactory(new TreeItemPropertyValueFactory("cliente"));
        this.cmServiciosPend_nombre.setCellValueFactory(new TreeItemPropertyValueFactory("nombre"));
        this.cmServiciosPend_direccion.setCellValueFactory(new TreeItemPropertyValueFactory("direccion"));
        this.cmServiciosPend_notas.setCellValueFactory(new TreeItemPropertyValueFactory("observaciones"));
        this.cmServiciosPend_modulador.setCellValueFactory(new TreeItemPropertyValueFactory("empleado"));

        this.cmServiciosPend_fechaAdd.setCellFactory(callbackDateTime);
        this.cmServiciosPend_fechaServicio.setCellFactory(callbackDateTime);
        this.cmServiciosPend_direccion.setCellFactory(callbackDireccion);
        this.cmServiciosPend_telefono.setCellFactory(new Callback<TreeTableColumn<ServicioRegular, Cliente>, TreeTableCell<ServicioRegular, Cliente>>() {
            @Override
            public TreeTableCell<ServicioRegular, Cliente> call(TreeTableColumn<ServicioRegular, Cliente> param) {


                TreeTableCell<ServicioRegular,Cliente> cell = new TreeTableCell<ServicioRegular,Cliente>(){
                    @Override
                    protected void updateItem(Cliente item, boolean empty) {
                        super.updateItem(item, empty);
                        if(item!=null){
                            setText(item.getNumero());
                        }
                    }
                };
                return cell;

            }
        });

        this.cmServiciosPend_modulador.setCellFactory((param -> {

            TreeTableCell<ServicioRegular,Empleado> cell = new TreeTableCell<ServicioRegular,Empleado>(){
                @Override
                protected void updateItem(Empleado item, boolean empty) {
                    super.updateItem(item, empty);
                    if(item!=null){
                        setText(item.getIdEmpleado() + " " + item.getNombre());
                    }
                }
            };
            return cell;

        }));





        listaServicioRegularesPendientes = new ServicioRegularSQL().getServiciosRegularesPendientes();

        TreeItem<ServicioRegular> serivicioRegularPendienteRecursiveTreeItem =
                new RecursiveTreeItem<>(listaServicioRegularesPendientes, (recursiveTreeObject) -> recursiveTreeObject.getChildren());

        this.tablaServicioPend.setRoot(serivicioRegularPendienteRecursiveTreeItem);
        this.tablaServicioPend.setShowRoot(false);
/*----------------------------------------------------FIN PENDIENTES ---------------------------------------------------------------*/


    }

    Callback<TreeTableColumn<ServicioRegular, Direccion>, TreeTableCell<ServicioRegular, Direccion>> callbackDireccion = new Callback<TreeTableColumn<ServicioRegular, Direccion>, TreeTableCell<ServicioRegular, Direccion>>() {
        @Override
        public TreeTableCell<ServicioRegular, Direccion> call(TreeTableColumn<ServicioRegular, Direccion> param) {

            TreeTableCell<ServicioRegular, Direccion> cell = new TreeTableCell<ServicioRegular, Direccion>() {
                protected void updateItem(Direccion item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item != null) {
                        this.setText("Calle: " + item.getCalle() + " Colonia: " + item.getColonia()+ " \nNum ext: " + item.getNumExt() + (item.getNumInt() == null ? "" : " Num int: " + item.getNumInt()) );
                        //this.setPrefHeight(35);
                    } else {
                        this.setText((String)null);
                    }
                }
            };
            return cell;

        }
    };

    Callback<TreeTableColumn<ServicioRegular, LocalDateTime>, TreeTableCell<ServicioRegular, LocalDateTime>> callbackDateTime = new Callback<TreeTableColumn<ServicioRegular, LocalDateTime>, TreeTableCell<ServicioRegular, LocalDateTime>>() {
        @Override
        public TreeTableCell<ServicioRegular, LocalDateTime> call(TreeTableColumn<ServicioRegular, LocalDateTime> param) {
            TreeTableCell<ServicioRegular, LocalDateTime> cell = new TreeTableCell<ServicioRegular,LocalDateTime>(){
                @Override
                protected void updateItem(LocalDateTime item, boolean empty) {
                    super.updateItem(item, empty);

                    if(item!= null){
                        setText(item.toString().replace('T','\n'));
                    }
                }
            };
            return cell;
        }
    };

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

        abrirVentanaCrud(event, new AddRegistro() {
            @Override
            public boolean addRegistro(Registro registro, Stage stage) {

                ServicioRegular servicioRegular= (ServicioRegular) registro;
                Direccion direccion = servicioRegular.getDireccion();
                Cliente cliente = servicioRegular.getCliente();
                //si el id es 0 entonces esa direccón es nueva, por lo tanto se introduce primero a la DB.
               /* if(direccion.getIdDireccion()==0){
                    if(!new DireccionSQL().insertarDireccion(direccion)){
                        return false;
                    }
                }*/
                //si el ciente es igual a null significa que cuando se busco el numero para añadir los datos de dirección autamaticamente.
                //Entonces se debe crear un nuevo cliente con los mismos datos de dirección que contiene el servicio
                if(cliente == null){

                    cliente =
                            new Cliente(0,servicioRegular.getTelefonoAux(),true,servicioRegular.getNombre(),servicioRegular.getObservaciones(),direccion);

                    new ClienteSQL().insertar(cliente, ((Stage)((Node)event.getSource()).getScene().getWindow()));
                    servicioRegular.setCliente(cliente);
                }


                if(new ServicioRegularSQL().insertarServicioRegular((ServicioRegular) registro)){
                    listaServicioRegularesPendientes.add((ServicioRegular) registro);

                    return true;
                }
                return false;
            }
        });

    }

    @FXML
    void btnAddServicioProgramado_OnAction(ActionEvent event) {
        System.out.println("Add servicio programado .");
    }

    @FXML
    void btnAplicarServicioNormal_OnAction(ActionEvent event) {

        //si no hay selección, a pastar.
        if(tablaServicioPend.getSelectionModel().isEmpty()){
            return;
        }

        try {
            FXMLLoader controladorLoader = new FXMLLoader(getClass().getResource("/views/AsignarUnidad.fxml"));
            AnchorPane contenedorAsignarUnidad = controladorLoader.load();
            AsignarUnidadController servicioRegularCrudController = controladorLoader.getController();

            servicioRegularCrudController.setAddRegistroListener(new AddRegistro(tablaServicioPend.getSelectionModel().getSelectedItem().getValue()) {
                @Override
                public boolean addRegistro(Registro registro, Stage stage) {

                    //regresa la instancia que mandamos pero con ID Direccion = true;
                    ConfirmaciónServicioData CSD = (ConfirmaciónServicioData) registro;
                    ServicioRegular servicioRegularPostModificiacion = tablaServicioPend.getSelectionModel().getSelectedItem().getValue();
                    try {
                        if(new ServicioRegularSQL().aplicarServicioRegular(CSD)){
                            servicioRegularPostModificiacion.setIdUnidad(CSD.getIdUnidad());
                            servicioRegularPostModificiacion.setObservaciones(CSD.getObservaciones());
                            //TODO añadir a la lista de aplicados.
                            listaServicioRegularesPendientes.remove(servicioRegularPostModificiacion);
                            //tablaServicioPend.getSelectionModel().getSelectedItem().setValue(servicioRegularPostModificiacion);
                            return true;
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }


                    return false;
                }

            });
            Stage primaryStage = new Stage();
            // Parent root = FXMLLoader.load(getClass().getResource("/views/Cruds/taxisCRUD.fxml"));
            primaryStage.setTitle("Asignar unidad");
            Scene scene = new Scene(contenedorAsignarUnidad);
            scene.getAccelerators().put(new KeyCodeCombination(KeyCode.ENTER), new Runnable() {
                @Override
                public void run() {
                    FXRobot robot = FXRobotFactory.createRobot(scene);
                    robot.keyPress(KeyCode.TAB);
                }
            });
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.initOwner(this.textField_servicioRapido.getScene().getWindow());
            primaryStage.initModality(Modality.WINDOW_MODAL);
            primaryStage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Aplicar servicio normal.");
    }

    @FXML
    void btnAplicarServicioProgramado_OnAction(ActionEvent event) {
        System.out.println("Aplicar servicio programado.");
    }

    @FXML
    void btnCancelServicioNormal_OnAction(ActionEvent event) {

        try {
            ServicioRegular servicioRegularSeleccionado = tablaServicioPend.getSelectionModel().getSelectedItem().getValue();
            if(servicioRegularSeleccionado!=null){
                if(new ServicioRegularSQL().cancelarServicioPendiente(servicioRegularSeleccionado.getIdServicio())){
                    listaServicioRegularesPendientes.remove(servicioRegularSeleccionado);
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Cancel servicio normal.");
    }

    @FXML
    void btnFinalizarServicioProgramado_OnAction(ActionEvent event) {
        System.out.println("FInalizar servicio programado.");
    }


    private void abrirVentanaCrud(ActionEvent event, AddRegistro addRegistro){
        try {

            FXMLLoader controladorLoader = new FXMLLoader(getClass().getResource("/views/Cruds/ServicioRegularCRUD.fxml"));
            AnchorPane contenedorServicioRegularCRUD = controladorLoader.load();
            ServicioRegularCrudController servicioRegularCrudController = controladorLoader.getController();

            servicioRegularCrudController.setAddRegistroListener(addRegistro);

            Stage primaryStage = new Stage();
            // Parent root = FXMLLoader.load(getClass().getResource("/views/Cruds/taxisCRUD.fxml"));
            primaryStage.setTitle("Servicios");
            Scene scene = new Scene(contenedorServicioRegularCRUD);
            scene.getAccelerators().put(new KeyCodeCombination(KeyCode.ENTER), new Runnable() {
                @Override
                public void run() {
                    FXRobot robot = FXRobotFactory.createRobot(scene);
                    robot.keyPress(KeyCode.TAB);
                }
            });
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.initOwner(this.textField_servicioRapido.getScene().getWindow());
            primaryStage.initModality(Modality.WINDOW_MODAL);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
