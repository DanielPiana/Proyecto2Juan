package com.example.proyecto2juan.Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
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
    private TextField txtContraseñaInicio;

    @FXML
    private TextField txtCorreoCrear;

    @FXML
    private TextField txtNombreCrear;

    @FXML
    private TextField txtNombreInicioSesion;

    @FXML
    void onInicioSesionClick(ActionEvent event) {
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
