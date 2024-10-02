package com.example.proyecto2juan.DAO;

import com.example.proyecto2juan.Main;
import com.example.proyecto2juan.domain.Usuario;
import com.mysql.cj.protocol.Resultset;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class UsuarioDAO {


    public static void añadirUsuario(Connection con,Usuario usuario) throws SQLException{
        String sql ="INSERT INTO USUARIOS (NOMBREUSUARIO,CONTRASEÑA,CORREO) VALUES (?,?,?)";

        PreparedStatement statement =con.prepareStatement(sql);
        statement.setString(1, usuario.getNombre());
        statement.setString(2,usuario.getContraseña());
        statement.setString(3, usuario.getCorreo());
        statement.executeUpdate();
    }


    public static void eliminarUsuario(Connection con,Usuario usuario) throws SQLException{
        String sql = "DELETE FROM USUARIOS WHERE CORREO = ?";

        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1,usuario.getCorreo());
        statement.executeUpdate();
    }


    public static boolean buscarUsuarioLogin(Connection con,Usuario usuario) {
        String sql = "SELECT * FROM USUARIOS WHERE CORREO = ? AND CONTRASEÑA = ?";
        try {
            PreparedStatement statement = con.prepareStatement(sql);

            statement.setString(1,usuario.getCorreo());
            statement.setString(2,usuario.getContraseña());

            ResultSet resultado = statement.executeQuery();

            return resultado.next();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }


        return false;
    }
    public static boolean comprobarContraseñaCifrada(Connection con,String contraseña) throws SQLException{
        String sql = "SELECT * FROM USUARIOS WHERE CONTRASEÑA = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1,contraseña);
        ResultSet resultado = statement.executeQuery();

        return resultado.next();
    }


}
