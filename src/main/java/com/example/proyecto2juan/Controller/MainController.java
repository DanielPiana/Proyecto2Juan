package com.example.proyecto2juan.Controller;

import com.example.proyecto2juan.DAO.UsuarioDAO;
import com.example.proyecto2juan.Util.Alerts;
import com.example.proyecto2juan.domain.Usuario;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    public static void mostrarEscena (ActionEvent event, String fxml) throws IOException {
        //Con este metodo cargamos y enseñamos una escena
        FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource(fxml));
        Parent root = new Pane();
        //Creo una nueva escena
        Scene scene = new Scene(root);
        //Creo un botón y consigo el origen del botón que provocó el evento
        Button boton = (Button) event.getSource();
        //Creo el stage y consigo la escena en la que está el botón y la ventana de esa escena.
        Stage stage = (Stage) boton.getScene().getWindow();
        //Seteo la escena a la conseguida anteriormente
        stage.setScene(scene);
        //Si la ventana no estuviese visible, hacemos que sea visible.
        if (!stage.isShowing()) {
            stage.show();
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


    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    @FXML
    void onInicioSesionClick(ActionEvent event) throws IOException, SQLException {
        if (txtCorreoInicioSesion.getText().isEmpty() || txtContraseñaInicioSesion.getText().isEmpty()){
            Alerts.alertaGeneral("Debe rellenar todos los campos","WARNING");
        } else {
            //Comprobar que el correo y la contraseña sea el mismo, antes de comprobar la contraseña tengo que encriptarla para ver si coincide
            //Añadir un usuario de pruebas con correo 1 y contraseña 1, encriptarla, crear el usuario con el correo y la contraseña ya encriptada y
            // mandarlo como parametro para buscarlo, si devuelve true, significa que si lo encuentra, coincide y cargamos fxml
            String contraseñaCifrada = DigestUtils.sha256Hex(txtCorreoInicioSesion.getText());
            Usuario usuario = new Usuario(txtCorreoInicioSesion.getText(),contraseñaCifrada);
            //CONEXION NO ESTA FUNCIONANDO NO SE POR QUE
            if (usuarioDAO.buscarUsuarioLogin(usuario)) {
                mostrarEscena(event,"/com/example/proyecto2juan/ui/Venta.fxml");
            }
        }



    }

    @FXML
    void onRegistrarmeClick(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            //Esto evita que el focus se ponga en un textfield y me quite el prompt
            Platform.runLater(() -> idPaneRoot.requestFocus());
        }
}
