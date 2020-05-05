package controllers;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import resources.Statics;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private AnchorPane fondoAP;

    @FXML
    private JFXTextField txt_usuario;

    @FXML
    private JFXPasswordField txt_contrasena;

    @FXML
    private JFXCheckBox cb_recordar;

    @FXML
    private Button btn_login;


    @FXML
    private Button btn_config;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            Statics.createConnection();
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            //crear ventana error.
        }

    }


    @FXML
    void btnConfig_OnAction(ActionEvent event) {
        Stage stage = new Stage();
        stage.getIcons().add(new Image("/resources/imagenes/iconos/Taxi/taxi.png"));
        Parent parent = null;
        try {

            parent = FXMLLoader.load(getClass().getResource("/views/Configuracion.fxml"));
            stage.setTitle("Taxis");

            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(btn_login.getScene().getWindow());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @FXML
    private void txtUsuario_ReleasedKey(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER){
            txt_contrasena.requestFocus();
        }
    }

    @FXML
    private void txtPassword_ReleasedKey(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER){
            btn_login.fire();
            txt_usuario.requestFocus();
        }
    }

    @FXML
    void btnLogin_Click(ActionEvent event) throws IOException {

        Stage stage = new Stage();
        stage.getIcons().add(new Image("/resources/imagenes/iconos/Taxi/taxi.png"));
        Parent parent = FXMLLoader.load(getClass().getResource("/views/Principal.fxml"));
        stage.setTitle("Taxis");

        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
        ((Stage)((Node)event.getSource()).getScene().getWindow()).close();

    }

}
