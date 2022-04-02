package com.ulatina.grupo5.modelo;

import java.util.Date;

/*
`idenAtrac` INT NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `fechaRevi` DATETIME NOT NULL,
  `error` TINYINT NOT NULL,
  `descripcion` VARCHAR(255) NOT NULL,
  `solucion` VARCHAR(255) NOT NULL,
 */
public class Mantenimiento {

    public Integer idMantenimiento;
    public Integer idAtracciones;
    public Integer cedula;
    public Date fechaRevision;
    public Boolean error;
    public String descripcion;
    public String solucion;

    public Mantenimiento() {
    }

    public Mantenimiento(Integer idMantenimiento, Integer idAtracciones, Integer cedula, Date fechaRevision, Boolean error, String descripcion, String solucion) {
        this.idMantenimiento = idMantenimiento;
        this.idAtracciones = idAtracciones;
        this.cedula = cedula;
        this.fechaRevision = fechaRevision;
        this.error = error;
        this.descripcion = descripcion;
        this.solucion = solucion;
    }

    public Integer getIdMantenimiento() {
        return idMantenimiento;
    }

    public void setIdMantenimiento(Integer idMantenimiento) {
        this.idMantenimiento = idMantenimiento;
    }

    public Integer getIdAtracciones() {
        return idAtracciones;
    }

    public void setIdAtracciones(Integer idAtracciones) {
        this.idAtracciones = idAtracciones;
    }

    public Integer getCedula() {
        return cedula;
    }

    public void setCedula(Integer cedula) {
        this.cedula = cedula;
    }

    public Date getFechaRevision() {
        return fechaRevision;
    }

    public void setFechaRevision(Date fechaRevision) {
        this.fechaRevision = fechaRevision;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getSolucion() {
        return solucion;
    }

    public void setSolucion(String solucion) {
        this.solucion = solucion;
    }

    @Override
    public String toString() {
        return "Mantenimiento{" + "idMantenimiento=" + idMantenimiento + ", idAtracciones=" + idAtracciones + ", cedula=" + cedula + ", fechaRevision=" + fechaRevision + ", error=" + error + ", descripcion=" + descripcion + ", solucion=" + solucion + '}';
    }

   

}
