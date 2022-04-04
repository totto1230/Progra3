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

    public Integer idAtracciones;
    public String nombreAtraccion;
    public Date fechaInstalacion;
    public Integer capacidad;
    public String seccion;
    public Integer edadMin;
    public Integer edadMax;
    public float precioNormal;
    public Boolean activo;


    public Atracciones() {
    }

    public Atracciones(Integer idAtracciones, String nombreAtraccion, Date fechaInstalacion, Integer capacidad, String seccion, Integer edadMin, float precioNormal, Boolean activo) {
        this.idAtracciones = idAtracciones;
        this.nombreAtraccion = nombreAtraccion;
        this.fechaInstalacion = fechaInstalacion;
        this.capacidad = capacidad;
        this.seccion = seccion;
        this.edadMin = edadMin;
        this.precioNormal = precioNormal;
        this.activo = activo;
    }

    
    public Atracciones(Integer idAtracciones, String nombreAtraccion, Date fechaInstalacion, Integer capacidad, String seccion, Integer edadMin, Integer edadMax, float precioNormal, Boolean activo) {
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

    public Integer getIdAtracciones() {
        return idAtracciones;
    }

    public void setIdAtracciones(Integer idAtracciones) {
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
