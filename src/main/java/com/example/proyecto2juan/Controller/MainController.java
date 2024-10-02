package com.example.proyecto2juan.Controller;

import com.example.proyecto2juan.Clases.ConexionBBDD;
import com.example.proyecto2juan.Main;
import com.example.proyecto2juan.Util.Alerts;
import com.example.proyecto2juan.domain.Usuario;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.example.proyecto2juan.DAO.UsuarioDAO.*;


public class MainController implements Initializable {

    public static void mostrarEscena (Button boton, String fxml){
        try {
            //Cargamos fxml
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml));
            Parent root = fxmlLoader.load();
            //Obtenemos el controlador del fxml cargado
            Object controller = fxmlLoader.getController();
            //Creamos nueva escena con el root
            Scene scene = new Scene(root);
            //Obtenemos el stage del botón que ha causado el evento
            Stage stage = (Stage) boton.getScene().getWindow();
            //Seteamos el stage con la escena actual.
            stage.setScene(scene);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }




    @FXML
    private TextField txtCorreoInicioSesion;
    @FXML
    private TextField txtContraseñaInicioSesion;
    @FXML
    private Pane idPaneRoot;
    @FXML
    private CheckBox cbPolitica;

    @FXML
    private Button idButtonCrear;

    @FXML
    private Button idButtonInicio;

    @FXML
    private TextField txtConfContraseñaCrear;

    @FXML
    private TextField txtContraseñaCrear;

    @FXML
    private TextField txtCorreoCrear;

    @FXML
    private TextField txtNombreCrear;
    public static Connection con;


    @FXML
    void onInicioSesionClick(ActionEvent event) {
        if (txtCorreoInicioSesion.getText().isEmpty() || txtContraseñaInicioSesion.getText().isEmpty()){
            Alerts.alertaGeneral("Debe rellenar todos los campos","WARNING");
        } else {
            //Primero ciframos la contraseña para poder contrastarla con la de la base de datos
            String contraseñaCifrada = DigestUtils.sha256Hex(txtContraseñaInicioSesion.getText());
            System.out.println("cifrafo de contraseña --> " + contraseñaCifrada);
            //Creamos usuario con el correo y la contraseña cifrada
            Usuario usuario = new Usuario(txtCorreoInicioSesion.getText(),contraseñaCifrada);
            //Usamos metodo de buscar usuario a ver si existe en la base de datos, si existe, cargamos nuevo fxml, si no existe
            if (buscarUsuarioLogin(con,usuario)) {
                mostrarEscena(idButtonInicio,"ui/Venta.fxml");
            }else {
                Alerts.alertaGeneral("Correo o contraseña incorrecto, pruebe otra vez","WARNING");
            }
        }
    }

    @FXML
    void onRegistrarmeClick(ActionEvent event) {
        if (txtNombreCrear.getText().isEmpty() || txtContraseñaCrear.getText().isEmpty() || txtConfContraseñaCrear.getText().isEmpty() || txtCorreoCrear.getText().isEmpty()) {
            Alerts.alertaGeneral("Debe rellenar todos los campos","WARNING");
        } else {
            if (Objects.equals(txtContraseñaCrear.getText(), txtConfContraseñaCrear.getText())) {
                String contraseñaCifrada = DigestUtils.sha256Hex(txtContraseñaCrear.getText());
                if (cbPolitica.isSelected()) {
                    System.out.println(txtCorreoCrear.getText());
                    if (!comprobarDisponibilidadCorreo(con,txtCorreoCrear.getText())) {
                        Usuario usuario = new Usuario(txtNombreCrear.getText(),contraseñaCifrada,txtCorreoCrear.getText());
                        añadirUsuario(con,usuario);
                        Alerts.alertaGeneral("Usuario registrado con éxito" +
                                "\n Confirma tu cuenta logueandote","CONFIRMATION");
                    } else {
                        Alerts.alertaGeneral("Ese correo no esta disponible","ERROR");
                    }
                    //Comprobar que correo no este en uso y luego crear cuenta
                } else {
                    Alerts.alertaGeneral("Lo siento, aceptar la política es obligatorio","WARNING");
                }

            } else {
                Alerts.alertaGeneral("Las contraseñas no coinciden","ERROR");
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            con = ConexionBBDD.conectar();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //Esto evita que el focus se ponga en un textfield y me quite el prompt
            Platform.runLater(() -> idPaneRoot.requestFocus());
        }
}
