package com.example.proyecto2juan.Util;

import com.example.proyecto2juan.Controller.ActualizarController;
import com.example.proyecto2juan.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;


public class Scenes {
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
    //Aparte del evento y el fxml necesario para cargar el fxml, le paso los datos que quiero del otro controlador
    public static void mostrarEscenaConParametros(ActionEvent event,String nombre,double precio,String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml));
        Parent root = fxmlLoader.load();
        //Creamos instancia del controlador al que queremos pasar datos
        ActualizarController actualizarController = fxmlLoader.getController();
        //Y ya tenemos acceso a los metodos del controller
        actualizarController.displayProducto(nombre, precio);
        //Y por ultimo cargamos nuestro nuevo fxml con los datos establecidos correctamente
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public static void mostrarEscenaConParametrosUsuario(ActionEvent event,String nombre,String contraseña,String correo,String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml));
        Parent root = fxmlLoader.load();
        //Creamos instancia del controlador del que pasamos datos
        ActualizarController actualizarController = fxmlLoader.getController();
        //Y ya tenemos acceso a los metodos del controller
        actualizarController.displayUsuario(nombre,correo,contraseña);
        //Y por ultimo cargamos nuestro nuevo fxml con los datos establecidos correctamente
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}