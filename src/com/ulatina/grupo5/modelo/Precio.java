package com.ulatina.grupo5.modelo;

public class Precio {

    public Integer idPrecio;
    public Integer idAtraccion;
    public String descripcion;
    public Integer precio;
    public boolean activo;
    public Integer edadMin;
    public Integer edadMax;

    public Precio(Integer idPrecio, Integer idAtraccion, String descripcion, Integer precio, boolean activo, Integer edadMin, Integer edadMax) {
        this.idPrecio = idPrecio;
        this.idAtraccion = idAtraccion;
        this.descripcion = descripcion;
        this.precio = precio;
        this.activo = activo;
        this.edadMin = edadMin;
        this.edadMax = edadMax;
    }

    public Integer getIdPrecio() {
        return idPrecio;
    }

    public void setIdPrecio(Integer idPrecio) {
        this.idPrecio = idPrecio;
    }

    public Integer getIdAtraccion() {
        return idAtraccion;
    }

    public void setIdAtraccion(Integer idAtraccion) {
        this.idAtraccion = idAtraccion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Integer getEdadMin() {
        return edadMin;
    }

    public void setEdadMin(Integer edadMin) {
        this.edadMin = edadMin;
    }

    public Integer getEdadMax() {
        return edadMax;
    }

    public void setEdadMax(Integer edadMax) {
        this.edadMax = edadMax;
    }

    @Override
    public String toString() {
        return "Precio{" + "idPrecio=" + idPrecio + ", idAtraccion=" + idAtraccion + ", descripcion=" + descripcion + ", precio=" + precio + ", activo=" + activo + ", edadMin=" + edadMin + ", edadMax=" + edadMax + '}';
    }
    

    

}
