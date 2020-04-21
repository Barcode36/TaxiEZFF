package controllers;

import com.jfoenix.controls.*;
import controllers.crudsControllers.ClientesCrudController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import models.Cliente;
import models.Direccion;
import models.interfaces.AddRegistro;
import models.interfaces.IAccion;
import models.interfaces.Registro;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientesController implements Initializable,IAccion {

    @FXML
    private AnchorPane fondo_clientes;

    @FXML
    private JFXTextField txtF_buscar;

    @FXML
    private JFXTreeTableView<Cliente> table_view_clientes;

    @FXML
    private TreeTableColumn<Cliente, String> column_nombre;

    @FXML
    private TreeTableColumn<Cliente,Direccion> column_direccion;

    @FXML
    private TreeTableColumn<Cliente,Direccion>  column_telefono;

    @FXML
    private TreeTableColumn<Cliente,Direccion>  column_observaciones;

    @FXML
    private Label label_clientes;

    @FXML
    private JFXButton button_agregarCliente;

    @FXML
    private JFXButton button_eliminarCliente;

    @FXML
    private JFXButton button_actualizarCliente;

    ObservableList<Cliente> listaServicios = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.column_nombre.setCellValueFactory(new TreeItemPropertyValueFactory("nombre"));
        this.column_telefono.setCellValueFactory(new TreeItemPropertyValueFactory("numero"));
        this.column_direccion.setCellValueFactory(new TreeItemPropertyValueFactory("direccion"));
        this.column_observaciones.setCellValueFactory(new TreeItemPropertyValueFactory("observaciones"));
        listaServicios.add(new Cliente("452123456", true, "Vic as kasdkjl", "observando", new Direccion(1, "calle1", "col1", "1", "2")));
        listaServicios.add(new Cliente("a452123456", true, "Vic as kasdkjl", "observando", new Direccion(1, "calle1", "col1", "1", "2")));
        listaServicios.add(new Cliente("4s52123456", true, "Vic as kasdkjl", "observando", new Direccion(1, "calle1", "col1", "1", "2")));
        TreeItem<Cliente> clienteRecursiveTreeItem = new RecursiveTreeItem<>(listaServicios, (recursiveTreeObject) -> recursiveTreeObject.getChildren());

        this.table_view_clientes.setRoot(clienteRecursiveTreeItem);
        this.table_view_clientes.setShowRoot(false);
        this.column_direccion.setCellFactory(new Callback<TreeTableColumn<Cliente, Direccion>, TreeTableCell<Cliente, Direccion>>() {
            public TreeTableCell<Cliente, Direccion> call(TreeTableColumn<Cliente, Direccion> param) {
                TreeTableCell<Cliente, Direccion> cell = new TreeTableCell<Cliente, Direccion>() {
                    protected void updateItem(Direccion item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            this.setText("Calle: " + item.getCalle() + " \nNum ext: " + item.getNumExt() + (item.getNumInt() == null ? "" : " Num int: " + item.getNumInt()) + "\nColonia: " + item.getColonia());
                        } else {
                            this.setText((String)null);
                        }

                    }
                };
                return cell;
            }
        });

   /*     table_view_clientes.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.ENTER){

                        button_actualizarCliente.fire();
                        System.out.println("Actualizar reg selectede");
                }else if(event.getCode() == KeyCode.DELETE){
                    button_eliminarCliente.fire();
                    System.out.println("Eliminar regselected");
                }
            }
        });
        */
        //definir una fila de fabrica.
        table_view_clientes.setRowFactory((param) -> {
            // TableRow<Empleados> row = new TableRow<>();
            JFXTreeTableRow<Cliente> row = new JFXTreeTableRow<>();

            row.setOnMouseClicked(event->{

                //si un registro es seleccionado con 1 o 2 clic
                if(! row.isEmpty() && event.getButton()== MouseButton.PRIMARY && event.getClickCount() == 2){
                    Cliente clickedRow = row.getItem();
              //      btnDelete_Cliente.disableProperty().set(false);
                //    btnEdit_Cliente.disableProperty().set(false);
                  //  System.out.println(clickedRow.getNombre());
                    //abrirá la ventana para edición
                    button_actualizarCliente.fire();

                }else
                if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY && event.getClickCount() == 1) {

                    ////  Empleados clickedRow = row.getItem();
                  //  btnDelete_Cliente.disableProperty().set(false);
                   // btnEdit_Cliente.disableProperty().set(false);
                   // //System.out.println(clickedRow.getNombre());
                }


            });

            return row;
        });
    }

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
        abrirVentanaCrud(event, new AddRegistro(table_view_clientes.getSelectionModel().getSelectedItem().getValue()) {
            @Override
            public void addRegistro(Registro registro) {
                table_view_clientes.getSelectionModel().getSelectedItem().setValue((Cliente)registro);
                System.out.println("Edicion");
            }
        });
    }

    @FXML
    void btnAddCliente_OnAction(ActionEvent event) {
        abrirVentanaCrud(event, new AddRegistro(null) {
            @Override
            public void addRegistro(Registro registro) {
                listaServicios.add((Cliente) registro);
            }
        });
    }

    @FXML
    void btnDeleteCliente_OnAction(ActionEvent event) {

    }

    private void abrirVentanaCrud(ActionEvent event, AddRegistro addRegistro){
        try {

            FXMLLoader controladorLoader = new FXMLLoader(getClass().getResource("/views/Cruds/ClientesCRUD.fxml"));
            AnchorPane contenedorCRUDClientes = controladorLoader.load();
            ClientesCrudController clientesCrudController = controladorLoader.getController();

            clientesCrudController.setAddRegistroListener(addRegistro);

            Stage primaryStage = new Stage();
            // Parent root = FXMLLoader.load(getClass().getResource("/views/Cruds/taxisCRUD.fxml"));
            primaryStage.setTitle("Taxis añadir");
            primaryStage.setScene(new Scene(contenedorCRUDClientes));
            primaryStage.setResizable(false);
            primaryStage.initOwner(this.button_actualizarCliente.getScene().getWindow());
            primaryStage.initModality(Modality.WINDOW_MODAL);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

