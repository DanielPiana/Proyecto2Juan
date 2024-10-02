package com.example.proyecto2juan.Controller;

import com.example.proyecto2juan.domain.Producto;
import com.example.proyecto2juan.domain.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import static com.example.proyecto2juan.Controller.MainController.con;
import static com.example.proyecto2juan.DAO.UsuarioDAO.*;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.proyecto2juan.Controller.MainController.mostrarEscena;

public class VentaController implements Initializable {
    @FXML
    private Button idButtonAtras;
    @FXML
    private Button idButtonProcesarVenta;

    @FXML
    private TableColumn<Usuario, String> idColumCorreoUsu;

    @FXML
    private TableColumn<Usuario, String> idColumNombreUsu;

    @FXML
    private TableColumn<Producto, String> idColumNombreProd;

    @FXML
    private TableColumn<Producto, Double> idColumPrecioProd;

    @FXML
    private TableView<Producto> idTablaProductos;

    @FXML
    private TableView<Usuario> idTablaUsuarios;

    @FXML
    private Label idTextTotalCompra;

    @FXML
    void onProcesarVentaClick(ActionEvent event) {

    }

    public void onAtrasClick(ActionEvent actionEvent) {
        mostrarEscena(idButtonAtras,"ui/Inicio-Registro.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idColumNombreUsu.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        idColumCorreoUsu.setCellValueFactory(new PropertyValueFactory<>("correo"));

        List<Usuario> listaUsuarios  = conseguirUsuarios(con);

        ObservableList<Usuario> datosACargar = FXCollections.observableList(listaUsuarios);

        idTablaUsuarios.setItems(datosACargar);
    }
}
