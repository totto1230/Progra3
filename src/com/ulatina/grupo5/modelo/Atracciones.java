package com.ulatina.grupo5.modelo;

import java.sql.Date;

/*`idAtracciones` INT NOT NULL,-
  `nombreAtrac` VARCHAR(255) NOT NULL,-
  `fechaInsta` DATETIME(6) NOT NULL,-
  `capacidad` INT NOT NULL,-
  `seccion` VARCHAR(1) NOT NULL,-
  `edadMin` INT NOT NULL,-
  `precioNormal` DECIMAL(5,2) NOT NULL,-
  `activo` TINYINT NOT NULL (Boolean), -
  PRIMARY KEY (`idAtracciones`)); -
 */
public class Atracciones {

    public int idAtracciones;
    public String nombreAtraccion;
    public Date fechaInstalacion;
    public int capacidad;
    public String seccion;
    public int edadMin;
    public int edadMax;
    public float precioNormal;
    public Boolean activo;


    public Atracciones() {
    }

    public Atracciones(int idAtracciones, String nombreAtraccion, Date fechaInstalacion, int capacidad, String seccion, int edadMin, float precioNormal, Boolean activo) {
        this.idAtracciones = idAtracciones;
        this.nombreAtraccion = nombreAtraccion;
        this.fechaInstalacion = fechaInstalacion;
        this.capacidad = capacidad;
        this.seccion = seccion;
        this.edadMin = edadMin;
        this.precioNormal = precioNormal;
        this.activo = activo;
    }

    
    public Atracciones(int idAtracciones, String nombreAtraccion, Date fechaInstalacion, int capacidad, String seccion, int edadMin, int edadMax, float precioNormal, Boolean activo) {
        this.idAtracciones = idAtracciones;
        this.nombreAtraccion = nombreAtraccion;
        this.fechaInstalacion = fechaInstalacion;
        this.capacidad = capacidad;
        this.seccion = seccion;
        this.edadMin = edadMin;
        this.edadMax = edadMax;
        this.precioNormal = precioNormal;
        this.activo = activo;
    }

    public int getIdAtracciones() {
        return idAtracciones;
    }

    public void setIdAtracciones(int idAtracciones) {
        this.idAtracciones = idAtracciones;
    }

    public String getNombreAtraccion() {
        return nombreAtraccion;
    }

    public void setNombreAtraccion(String nombreAtraccion) {
        this.nombreAtraccion = nombreAtraccion;
    }

    public Date getFechaInstalacion() {
        return fechaInstalacion;
    }

    public void setFechaInstalacion(Date fechaInstalacion) {
        this.fechaInstalacion = fechaInstalacion;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public int getEdadMin() {
        return edadMin;
    }

    public void setEdadMin(int edadMin) {
        this.edadMin = edadMin;
    }

    public int getEdadMax() {
        return edadMax;
    }

    public void setEdadMax(int edadMax) {
        this.edadMax = edadMax;
    }

    public float getPrecioNormal() {
        return precioNormal;
    }

    public void setPrecioNormal(float precioNormal) {
        this.precioNormal = precioNormal;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Atracciones{" + "idAtracciones=" + idAtracciones + ", nombreAtraccion=" + nombreAtraccion + ", fechaInstalacion=" + fechaInstalacion + ", capacidad=" + capacidad + ", seccion=" + seccion + ", edadMin=" + edadMin + ", edadMax=" + edadMax + ", precioNormal=" + precioNormal + ", activo=" + activo + '}';
    }



}
