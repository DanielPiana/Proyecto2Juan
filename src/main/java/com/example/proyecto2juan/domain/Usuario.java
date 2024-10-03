package com.example.proyecto2juan.domain;

public class Usuario {
    private int id;
    private String nombre;
    private String contraseña;
    private String correo;

    public Usuario() {
    }

    public Usuario(String nombre, String contraseña, String correo) {
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.correo = correo;
    }

    public Usuario(String correo, String contraseña) {
        this.correo = correo;
        this.contraseña = contraseña;
    }

    public Usuario(String correo) {
        this.correo = correo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return "domain{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", contraseña='" + contraseña + '\'' +
                ", correo='" + correo + '\'' +
                '}';
    }
}
