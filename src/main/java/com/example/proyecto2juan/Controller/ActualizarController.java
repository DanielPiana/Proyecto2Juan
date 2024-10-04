package com.example.proyecto2juan.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ActualizarController {

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

    @FXML
    void onActualizarButtonClick(ActionEvent event) {

    }
    public void display (String nombre,double precio) {
        labelAntiguoNombre.setText(nombre);
        labelAntiguoPrecio.setText(String.valueOf(precio));
    }

}
