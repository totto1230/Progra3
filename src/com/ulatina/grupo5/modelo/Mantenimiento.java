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
    public Integer idenAtrac;
    public Integer cedula;
    public Date fechaRevi;
    public Boolean error;
    public String descripcion;
    public String solucion;

    public Mantenimiento() {
    }

    public Mantenimiento(Integer idMantenimiento,Integer idenAtrac, Integer cedula, Date fechaRevi, Boolean error, String descripcion, String solucion) {
        this.idMantenimiento = idMantenimiento;
        this.idenAtrac = idenAtrac;
        this.cedula = cedula;
        this.fechaRevi = fechaRevi;
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
     

    public Integer getIdenAtrac() {
        return idenAtrac;
    }

    public void setIdenAtrac(Integer idenAtrac) {
        this.idenAtrac = idenAtrac;
    }

    public Integer getCedula() {
        return cedula;
    }

    public void setCedula(Integer cedula) {
        this.cedula = cedula;
    }

    public Date getFechaRevi() {
        return fechaRevi;
    }

    public void setFechaRevi(Date fechaRevi) {
        this.fechaRevi = fechaRevi;
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
        return "Mantenimiento{" + "idenAtrac=" + idenAtrac + ", usuario=" + cedula + ", fechaRevi=" + fechaRevi + ", error=" + error + ", descripcion=" + descripcion + ", solucion=" + solucion + '}';
    }

}
