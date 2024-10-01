package com.example.proyecto2juan.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class VentaController implements Initializable {

    @FXML
    private Button idButtonProcesarVenta;

    @FXML
    private TableColumn<?, ?> idColumCorreoUsu;

    @FXML
    private TableColumn<?, ?> idColumNombreProd;

    @FXML
    private TableColumn<?, ?> idColumNombreUsu;

    @FXML
    private TableColumn<?, ?> idColumPrecioProd;

    @FXML
    private TableView<?> idTablaProductos;

    @FXML
    private TableView<?> idTablaUsuarios;

    @FXML
    private Label idTextTotalCompra;

    @FXML
    void onProcesarVentaClick(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
