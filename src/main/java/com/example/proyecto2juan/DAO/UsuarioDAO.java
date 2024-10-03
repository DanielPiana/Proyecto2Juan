package com.example.proyecto2juan.DAO;

import com.example.proyecto2juan.Main;
import com.example.proyecto2juan.domain.Usuario;
import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;

import javax.xml.transform.Result;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class UsuarioDAO {


    public static void añadirUsuario(Connection con, Usuario usuario) {
        try {
            String sql = "INSERT INTO USUARIOS (NOMBRE,CONTRASEÑA,CORREO) VALUES (?,?,?)";
            PreparedStatement statement = con.prepareStatement(sql);

            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getContraseña());
            statement.setString(3, usuario.getCorreo());

            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static boolean comprobarDisponibilidadCorreo(Connection con,String correo) {
        try {
            String sql = "SELECT * FROM USUARIOS WHERE CORREO = ?";
            PreparedStatement statement = con.prepareStatement(sql);

            statement.setString(1,correo);

            ResultSet resultSet = statement.executeQuery();

            return resultSet.next();

        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }


    public static void eliminarUsuario(Connection con, Usuario usuario) {
        try {
            String sql = "DELETE FROM USUARIOS WHERE CORREO = ?";
            PreparedStatement statement = con.prepareStatement(sql);

            statement.setString(1, usuario.getCorreo());

            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public static boolean buscarUsuarioLogin(Connection con, Usuario usuario) {
        try {
            String sql = "SELECT * FROM USUARIOS WHERE CORREO = ? AND CONTRASEÑA = ?";
            PreparedStatement statement = con.prepareStatement(sql);

            statement.setString(1, usuario.getCorreo());
            statement.setString(2, usuario.getContraseña());

            ResultSet resultSet = statement.executeQuery();

            return resultSet.next();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static boolean comprobarContraseñaCifrada(Connection con, String contraseña) {
        try {
            String sql = "SELECT * FROM USUARIOS WHERE CONTRASEÑA = ?";
            PreparedStatement statement = con.prepareStatement(sql);

            statement.setString(1, contraseña);
            ResultSet resultSet = statement.executeQuery();

            return resultSet.next();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    public static List<Usuario> conseguirUsuarios(Connection con) {
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        try {

            Usuario usuario;
            String sql = "SELECT * FROM USUARIOS";
            PreparedStatement statement = con.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                usuario = new Usuario();
                usuario.setNombre(resultSet.getString("NOMBRE"));
                usuario.setCorreo(resultSet.getString("CORREO"));
                listaUsuarios.add(usuario);
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listaUsuarios;
    }
}
