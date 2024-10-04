package com.example.proyecto2juan.Controller;

import static com.example.proyecto2juan.DAO.UsuarioDAO.*;
import static com.example.proyecto2juan.DAO.ProductosDAO.*;
import static com.example.proyecto2juan.Util.Scenes.mostrarEscena;

import com.example.proyecto2juan.Util.Alerts;
import com.example.proyecto2juan.domain.Producto;
import com.google.protobuf.StringValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import static com.example.proyecto2juan.Controller.MainController.con;

import java.sql.Connection;

public class ActualizarController {
    @FXML
    private Button idButtonAtras;

    @FXML
    private Button idButtonActualizar;

    @FXML
    private Label labelAntiguoNombre;

    @FXML
    private Label labelAntiguoPrecio;

    @FXML
    private TextField textFieldNuevoNombre;

    @FXML
    private TextField textFieldNuevoPrecio;
    private String nombreActual;
    private double precioActual;

    @FXML
    void onActualizarButtonClick(ActionEvent event) {
        if (textFieldNuevoNombre.getText().isEmpty() || textFieldNuevoPrecio.getText().isEmpty()) {
            Alerts.alertaGeneral("Debe rellenar todos los campos","WARNING");
        } else {
            String nombre = textFieldNuevoNombre.getText();
            double precio = Double.parseDouble(textFieldNuevoPrecio.getText());
            Producto producto = new Producto(precio,nombre);
            System.out.println(producto.getNombre());
            System.out.println(producto.getPrecio());
            actualizarProducto(con,producto,labelAntiguoNombre.getText());
        }
    }
    public void displayProducto (String nombre,double precio) {
        //le doy el valor de los datos pasados por parametros a 2 nuevas variables para poder usarlas en el metodo de actualizar.
        precioActual = precio;
        nombreActual = nombre;
        //Seteo las etiquetas con el valor del producto seleccionado en el otro fxml
        labelAntiguoNombre.setText(nombre);
        labelAntiguoPrecio.setText(String.valueOf(precio));
    }

    public void onAtrasButtonClick() {
        mostrarEscena(idButtonAtras,"ui/Venta.fxml");
    }
}
