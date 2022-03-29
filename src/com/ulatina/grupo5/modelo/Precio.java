package com.ulatina.grupo5.modelo;

public class Precio {

    public int idPrecio;
    public String descripcion;
    public int precio;
    public boolean activoBIT;

    public Precio(int idPrecio, String descripcion, int precio, boolean activoBIT) {
        this.idPrecio = idPrecio;
        this.descripcion = descripcion;
        this.precio = precio;
        this.activoBIT = activoBIT;
    }

    public Precio() {
    }

    public int getIdPrecio() {
        return idPrecio;
    }

    public void setIdPrecio(int idPrecio) {
        this.idPrecio = idPrecio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public boolean isActivoBIT() {
        return activoBIT;
    }

    public void setActivoBIT(boolean activoBIT) {
        this.activoBIT = activoBIT;
    }

    @Override
    public String toString() {
        return "Precio{" + "idPrecio=" + idPrecio + ", descripcion=" + descripcion + ", precio=" + precio + ", activoBIT=" + activoBIT + '}';
    }

}
