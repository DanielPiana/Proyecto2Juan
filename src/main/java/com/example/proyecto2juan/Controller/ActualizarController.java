package com.example.proyecto2juan.Controller;

import static com.example.proyecto2juan.DAO.ProductosDAO.*;
import static com.example.proyecto2juan.DAO.UsuarioDAO.*;
import static com.example.proyecto2juan.Util.Scenes.mostrarEscena;

import com.example.proyecto2juan.Util.Alerts;
import com.example.proyecto2juan.domain.Producto;
import com.example.proyecto2juan.domain.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Objects;

import static com.example.proyecto2juan.Controller.MainController.con;

public class ActualizarController {
    @FXML
    private Button idButtonActualizar;

    @FXML
    private Button idButtonActualizar2;

    @FXML
    private Button idButtonAtras;

    @FXML
    private Button idButtonAtras2;

    @FXML
    private Pane idPanelProducto;

    @FXML
    private Pane idPanelUsuario;

    @FXML
    private Label labelAntiguoCorreoUsu;

    @FXML
    private Label labelAntiguoNombre;

    @FXML
    private Label labelAntiguoNombreUsu;

    @FXML
    private Label labelAntiguoPrecio;

    @FXML
    private TextField textFieldNuevoNombre;

    @FXML
    private TextField textFieldNuevoPrecio;

    @FXML
    private TextField txtFieldConfContraseña;

    @FXML
    private TextField txtFieldNuevoCorreo;

    @FXML
    private TextField txtFieldNuevoNombreUsu;

    @FXML
    private TextField txtfieldNuevaContraseña;

    private String nombreProdActual;
    private double precioActual;
    private String nombreUsuActual;
    private String contraseñaActual;
    private String correoActual;


    @FXML
    void onActualizarButtonClick(ActionEvent event) {
        if (textFieldNuevoNombre.getText().isEmpty() || textFieldNuevoPrecio.getText().isEmpty()) {
            Alerts.alertaGeneral("Debe rellenar todos los campos","WARNING");
        } else {
            String nombre = textFieldNuevoNombre.getText();
            double precio = Double.parseDouble(textFieldNuevoPrecio.getText());
            Producto producto = new Producto(precio,nombre);
            if (actualizarProducto(con,producto,labelAntiguoNombre.getText())== 1) {
                Alerts.alertaGeneral("Usuario actualizado con exito","CONFIRMATION");
                mostrarEscena(idButtonAtras,"ui/Venta.fxml");
            } else {
                Alerts.alertaGeneral("Error al actualizar usuario","ERROR");
            }

        }
    }
    public void onActualizar2ButtonClick(ActionEvent actionEvent) {
        if (txtFieldNuevoNombreUsu.getText().isEmpty() || txtFieldNuevoCorreo.getText().isEmpty() || txtfieldNuevaContraseña.getText().isEmpty() || txtFieldConfContraseña.getText().isEmpty()) {
            Alerts.alertaGeneral("Debe rellenar todos los campos","WARNING");
        } else {
            if (!Objects.equals(txtfieldNuevaContraseña.getText(), txtFieldConfContraseña.getText())) {
                Alerts.alertaGeneral("Las contraseñas deben coincidir","WARNING");
            } else {
                Usuario usuario = new Usuario(labelAntiguoCorreoUsu.getText());
                if (!comprobarDisponibilidadCorreo(con,txtFieldNuevoCorreo.getText())) {
                    String contraseña = DigestUtils.sha256Hex(txtfieldNuevaContraseña.getText());
                    if (actualizarUsuario(con,usuario,txtFieldNuevoNombreUsu.getText(),contraseña,txtFieldNuevoCorreo.getText()) == 1) {
                        Alerts.alertaGeneral("Usuario actualizado con exito","CONFIRMATION");
                        mostrarEscena(idButtonAtras2,"ui/Venta.fxml");
                    } else {

                        Alerts.alertaGeneral("Error al actualizar usuario","ERROR");
                    }
                    //Seguir con codigo
                } else {
                    Alerts.alertaGeneral("Ese correo no está disponible","INFORMATION");
                }
            }
        }
    }
    public void displayProducto (String nombre,double precio) {
        //Aprovecho que solo llamo a este metodo cuando quiero actualizar el producto y pongo ese panel en visible y el de usuario en invisible
        idPanelProducto.setVisible(true);
        idPanelUsuario.setVisible(false);
        //le doy el valor de los datos pasados por parametros a 2 nuevas variables para poder usarlas en el metodo de actualizar.
        precioActual = precio;
        nombreProdActual = nombre;
        //Seteo las etiquetas con el valor del producto seleccionado en el otro fxml
        labelAntiguoNombre.setText(nombre);
        labelAntiguoPrecio.setText(String.valueOf(precio));
    }
    public void displayUsuario (String nombre, String correo,String contraseña) {
        idPanelProducto.setVisible(false);
        idPanelUsuario.setVisible(true);
        nombreUsuActual = nombre;
        correoActual = correo;
        contraseñaActual = contraseña;
        labelAntiguoNombreUsu.setText(nombreUsuActual);
        labelAntiguoCorreoUsu.setText(correoActual);
    }

    public void onAtrasButtonClick() {
        mostrarEscena(idButtonAtras,"ui/Venta.fxml");
    }

    public void onAtras2ButtonClick(ActionEvent actionEvent) {
        mostrarEscena(idButtonAtras2,"ui/Venta.fxml");
    }
}
