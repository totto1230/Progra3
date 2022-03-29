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
    public String nombreAtrac;
    public Date fechaInsta;
    public int capacidad;
    public String seccion;
    public int edadMin;
    public float precioNormal;
    public Boolean activo;

    public Atracciones() {
    }

    public Atracciones(Integer idAtracciones, String nombreAtrac, Date fechaInsta, Integer capacidad, String seccion, Integer edadMin, Float precioNormal, Boolean activo) {
        this.idAtracciones = idAtracciones;
        this.nombreAtrac = nombreAtrac;
        this.fechaInsta = fechaInsta;
        this.capacidad = capacidad;
        this.seccion = seccion;
        this.edadMin = edadMin;
        this.precioNormal = precioNormal;
        this.activo = activo;
    }

    public int getIdAtracciones() {
        return idAtracciones;
    }

    public void setIdAtracciones(int idAtracciones) {
        this.idAtracciones = idAtracciones;
    }

    public String getNombreAtrac() {
        return nombreAtrac;
    }

    public void setNombreAtrac(String nombreAtrac) {
        this.nombreAtrac = nombreAtrac;
    }

    public Date getFechaInsta() {
        return fechaInsta;
    }

    public void setFechaInsta(Date fechaInsta) {
        this.fechaInsta = fechaInsta;
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
        return "Atracciones{" + "idAtracciones=" + idAtracciones + ", nombreAtrac=" + nombreAtrac + ", fechaInsta=" + fechaInsta + ", capacidad=" + capacidad + ", seccion=" + seccion + ", edadMin=" + edadMin + ", precioNormal=" + precioNormal + ", activo=" + activo + '}';
    }

}
