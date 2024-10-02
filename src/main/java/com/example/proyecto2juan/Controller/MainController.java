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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.apache.commons.codec.digest.DigestUtils;
import static com.example.proyecto2juan.DAO.UsuarioDAO.buscarUsuarioLogin;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;



public class MainController implements Initializable {

    public static void mostrarEscena (Button boton, String fxml){
        try {
            // CARGAR EL ARCHIVO FXML
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml));
            Parent root = fxmlLoader.load();
            // OBTENER CONTROLLER
            Object controller = fxmlLoader.getController();
            Scene scene = new Scene(root); // CREAR UNA NUEVA ESCENA
            // OBTENER EL STAGE ACTUAL A PARTIR DEL BOTON QUE SE HA CLICADO
            Stage stage = (Stage) boton.getScene().getWindow();
            stage.setScene(scene); // ESTABLECER LA NUEVA ESCENA AL STAGE ACTUAL
            // MOSTRAR VENTANA SI NO ESTA VISIBLE
            if (!stage.isShowing()) {
                stage.show();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage()); // SI HAY ERROR EN LA CARGA DEL FXML, SE LANZA LA EXCEPCION
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
    void onInicioSesionClick(ActionEvent event) throws IOException, SQLException {
        if (txtCorreoInicioSesion.getText().isEmpty() || txtContraseñaInicioSesion.getText().isEmpty()){
            Alerts.alertaGeneral("Debe rellenar todos los campos","WARNING");
        } else {
            //Primero ciframos la contraseña para poder contrastarla con la de la base de datos
            String contraseñaCifrada = DigestUtils.sha256Hex(txtContraseñaInicioSesion.getText());
            System.out.println("cifrafo de contraseña " + contraseñaCifrada);
            //Creamos usuario con el correo y la contraseña cifrada
            Usuario usuario = new Usuario(txtCorreoInicioSesion.getText(),contraseñaCifrada);
            if (buscarUsuarioLogin(con,usuario)) {
                System.out.println("hola");
                mostrarEscena(idButtonInicio,"ui/Venta.fxml");
            }else {
                System.out.println("adios");
            }
        }



    }

    @FXML
    void onRegistrarmeClick(ActionEvent event) {

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
