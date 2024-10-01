package com.example.proyecto2juan.DAO;

import com.example.proyecto2juan.Main;
import com.example.proyecto2juan.domain.Usuario;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class UsuarioDAO {

    private Connection conexion;

    public  void conectar() throws ClassNotFoundException, SQLException, IOException {
        Properties configuration = new Properties();
        Main.class.getResourceAsStream("Configuration/database.properties");
        String host = configuration.getProperty("host");
        String port = configuration.getProperty("port");
        String name = configuration.getProperty("name");
        String username = configuration.getProperty("username");
        String password = configuration.getProperty("password");

        Class.forName("com.mysql.cj.jdbc.Driver");
        conexion = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + name + "?serverTimezone=UTC",
                username, password);
    }
    public  void desconectar() throws SQLException {
        conexion.close();
    }


    public  void añadirUsuario(Usuario usuario) throws SQLException{
        String sql ="INSERT INTO USUARIOS (NOMBREUSUARIO,CONTRASEÑA,CORREO) VALUES (?,?,?)";

        PreparedStatement statement =conexion.prepareStatement(sql);
        statement.setString(1, usuario.getNombre());
        statement.setString(2,usuario.getContraseña());
        statement.setString(3, usuario.getCorreo());
        statement.executeUpdate();
    }


    public  void eliminarUsuario(Usuario usuario) throws SQLException{
        String sql = "DELETE FROM USUARIOS WHERE CORREO = ?";

        PreparedStatement statement = conexion.prepareStatement(sql);
        statement.setString(1,usuario.getCorreo());
        statement.executeUpdate();
    }


    public  boolean buscarUsuarioLogin(Usuario usuario) throws SQLException{
        String sql = "SELECT * FROM USUARIO WHERE CORREO = ? AND CONTRASEÑA = ?";

        PreparedStatement statement = conexion.prepareStatement(sql);

        ResultSet resultado = statement.executeQuery();

        return resultado.next();
    }
    public boolean comprobarContraseñaCifrada(String contraseña) throws SQLException{
        String sql = "SELECT * FROM USUARIO WHERE CONTRASEÑA = ?";
        PreparedStatement statement = conexion.prepareStatement(sql);

        ResultSet resultado = statement.executeQuery();

        return resultado.next();
    }


}
