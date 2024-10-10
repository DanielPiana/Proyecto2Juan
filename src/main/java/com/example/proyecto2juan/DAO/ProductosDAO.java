package com.example.proyecto2juan.DAO;

import com.example.proyecto2juan.domain.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductosDAO {

    public static List<Producto> conseguirProductos(Connection con) {
        ArrayList<Producto> listaProductos = new ArrayList<>();
        try {

            Producto producto;
            String sql = "SELECT * FROM PRODUCTOS";
            PreparedStatement statement = con.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                //Bucle que mientras el resultSet siga sacando datos, ira creando un producto con esos datos
                //y metiendolos en una lista para devolverla y poder cargarlo en la tabla.
                producto = new Producto();
                producto.setNombre(resultSet.getString("NOMBRE"));
                producto.setPrecio(resultSet.getDouble("PRECIO"));
                listaProductos.add(producto);
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listaProductos;
    }
    public static void eliminarProducto(Connection con, Producto producto) {
        try {
            String sql = "DELETE FROM PRODUCTOS WHERE NOMBRE = ?";
            PreparedStatement statement = con.prepareStatement(sql);

            statement.setString(1, producto.getNombre());

            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    //El nombre del producto que quiero cambiar lo mando como parametro y los nuevos datos en el objeto.
    public static int actualizarProducto(Connection con, Producto producto,String nombre) {
        try {
            String sql = "UPDATE PRODUCTOS SET PRECIO = ?, NOMBRE = ? WHERE NOMBRE = ?";
            PreparedStatement statement = con.prepareStatement(sql);

            statement.setDouble(1,producto.getPrecio());
            statement.setString(2,producto.getNombre());
            statement.setString(3,nombre);

            return statement.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }
}
