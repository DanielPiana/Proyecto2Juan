package com.example.proyecto2juan.Controller;

import com.example.proyecto2juan.DAO.ProductosDAO;
import com.example.proyecto2juan.DAO.UsuarioDAO;
import com.example.proyecto2juan.Util.Alerts;
import com.example.proyecto2juan.Util.Scenes;
import com.example.proyecto2juan.domain.Producto;
import com.example.proyecto2juan.domain.Usuario;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;


import static com.example.proyecto2juan.Controller.MainController.con;
import static com.example.proyecto2juan.DAO.UsuarioDAO.*;
import static com.example.proyecto2juan.DAO.ProductosDAO.*;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import static com.example.proyecto2juan.Util.Scenes.mostrarEscena;

public class VentaController implements Initializable {

    @FXML
    private Button idButtonActualizarUsuario;

    @FXML
    private Button idButtonEliminarProducto;

    @FXML
    private Button idButtonEliminarUsuario;

    @FXML
    private Button idButtonActualizarProducto;

    @FXML
    private ComboBox<Integer> cbCantidad;

    @FXML
    private Button idButtonAtras;

    @FXML
    private Button idButtonProcesarVenta;

    @FXML
    private TableView<Usuario> idTablaUsuarios;

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
    private Label idTextTotalCompra;
    private double precioFinal;
    private String nombre;

    @FXML
    void onProcesarVentaClick() {
        //Consigo el usuario y el producto seleccionado para usar sus atributos
        Usuario usuarioSeleccionado = idTablaUsuarios.getSelectionModel().getSelectedItem();
        Producto productoSeleccionado = idTablaProductos.getSelectionModel().getSelectedItem();
        //Si hay usuario y seleccionado, sigo con el programa, si no, salta una alerta
        if (usuarioSeleccionado != null && productoSeleccionado != null) {
            //Guardo el nombre del usuario seleccionado en una variable
            nombre = usuarioSeleccionado.getNombre();
            //Calculo el precio final del producto seleccionado y la cantidad elegida en el comboBox
            precioFinal = productoSeleccionado.getPrecio() * cbCantidad.getSelectionModel().getSelectedItem();
            //Seteo un TextField con la información
            idTextTotalCompra.setText("Factura a nombre de: "+nombre +" por un total de: "+ String.valueOf(precioFinal) + "€");
        } else {
            Alerts.alertaGeneral("Debe seleccionar un usuario y un producto","INFORMATION");
        }

    }
    public void onEliminarProductoClick() {
        //Consigo el producto seleccionado
        Producto productoSeleccionado = idTablaProductos.getSelectionModel().getSelectedItem();
        if (productoSeleccionado != null) {
            //Creo un producto con el nombre, no necesitamos mas para el metodo de borrar
            Producto producto = new Producto(productoSeleccionado.getNombre());
            //Eliminamos el producto
            eliminarProducto(con,producto);
            //Volvemos a cargar la tabla para que se actualice y no muestre el producto borrado
            cargarTablaProductos();
        } else {
            Alerts.alertaGeneral("Debe seleccionar un producto","WARNING");
        }
    }

    public void onEliminarUsuarioClick() {
        //Consigo el usuario seleccionado
        Usuario usuarioSeleccionado = idTablaUsuarios.getSelectionModel().getSelectedItem();
        if (usuarioSeleccionado!=null) {
            //Creo el usuario con el correo, no necesitamos mas para el metodo de borrar
            Usuario usuario = new Usuario(usuarioSeleccionado.getCorreo());
            //Eliminamos el usuario
            eliminarUsuario(con,usuario);
            //Volvemos a cargar la tabla para que se actualice y no muestre el usuario borrado
            cargarTablaUsuarios();
        } else {
            Alerts.alertaGeneral("Debe seleccionar un usuario","WARNING");
        }
    }

    public void onAtrasClick() {
        //Volvemos al fxml principal
        mostrarEscena(idButtonAtras,"ui/Inicio-Registro.fxml");
    }

    public void cargarTablaUsuarios() {//Metodo para cargar información a una tabla, importante convertir
        //a ObservableList, pues no podemos cargar si no lo es.
        idColumNombreUsu.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        idColumCorreoUsu.setCellValueFactory(new PropertyValueFactory<>("correo"));

        List<Usuario> listaUsuarios  = conseguirUsuarios(con);

        ObservableList<Usuario> usuariosACargar = FXCollections.observableList(listaUsuarios);

        idTablaUsuarios.setItems(usuariosACargar);
    }
    public void cargarTablaProductos() {
        idColumNombreProd.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        idColumPrecioProd.setCellValueFactory(new PropertyValueFactory<>("precio"));

        List<Producto> listaProductos  = conseguirProductos(con);

        ObservableList<Producto> productosACargar = FXCollections.observableList(listaProductos);

        idTablaProductos.setItems(productosACargar);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Cargamos usuarios en la tabla
        cargarTablaUsuarios();

        //Cargamos productos en la tabla
        cargarTablaProductos();

        //Cargamos datos en un comboBox
        cbCantidad.getItems().addAll(1,2,3,4,5,6,7,8,9,10);
    }

    public void onActualizarProductoClick(ActionEvent event) throws IOException {
        //Consigo el producto seleccionado
        Producto productoSeleccionado = idTablaProductos.getSelectionModel().getSelectedItem();
        if (productoSeleccionado != null) {
            //Guardamos los datos en variables
            String nombre = productoSeleccionado.getNombre();
            double precio = productoSeleccionado.getPrecio();
            //Pasamos el evento,nombre,precio y fxml al metodo de cargar escena con parametros
            Scenes.mostrarEscenaConParametros(event,nombre,precio,"ui/Actualizar.fxml");
        } else {
            Alerts.alertaGeneral("Debe seleccionar un producto","WARNING");
        }
    }

    public void onActualizarUsuarioClick(ActionEvent event) throws IOException {
        Usuario usuarioseleccionado = idTablaUsuarios.getSelectionModel().getSelectedItem();
        if (usuarioseleccionado != null) {
            String nombre = usuarioseleccionado.getNombre();
            String correo = usuarioseleccionado.getCorreo();
            String contraseña = usuarioseleccionado.getContraseña();
            Scenes.mostrarEscenaConParametrosUsuario(event,nombre,contraseña,correo,"ui/Actualizar.fxml");
        } else {
            Alerts.alertaGeneral("Debe seleccionar un usuario","WARNING");
        }
    }

}
