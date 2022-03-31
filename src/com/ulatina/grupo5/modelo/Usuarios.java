package com.ulatina.grupo5.modelo;

public class Usuarios {

    public int cedula;
    public String password;
    public String email;
    public String nombre;
    public String apellido1;
    public String appellido2;
    public int tipoUsuario;

    public Usuarios(int cedula, String password, String email, String nombre, String apellido1, String appellido2, int tipoUsuario) {
        this.cedula = cedula;
        this.password = password;
        this.email = email;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.appellido2 = appellido2;
        this.tipoUsuario = tipoUsuario;
    }

    public Usuarios() {
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String paasword) {
        this.password = paasword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getAppellido2() {
        return appellido2;
    }

    public void setAppellido2(String appellido2) {
        this.appellido2 = appellido2;
    }

    public int getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(int tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public String toString() {
        return "Usuarios{" + "cedula=" + cedula + ", password=" + password + ", correo=" + email + ", nombre=" + nombre + ", apellido1=" + apellido1 + ", appellido2=" + appellido2 + ", TipoUsuario=" + tipoUsuario + '}';
    }

}
