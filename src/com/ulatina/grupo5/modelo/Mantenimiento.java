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

    public Integer idenAtrac;
    public String email;
    public Date fechaRevi;
    public Boolean error;
    public String descripcion;
    public String solucion;

    public Mantenimiento() {
    }

    public Mantenimiento(Integer idenAtrac, String email, Date fechaRevi, Boolean error, String descripcion, String solucion) {
        this.idenAtrac = idenAtrac;
        this.email = email;
        this.fechaRevi = fechaRevi;
        this.error = error;
        this.descripcion = descripcion;
        this.solucion = solucion;
    }

    public Integer getIdenAtrac() {
        return idenAtrac;
    }

    public void setIdenAtrac(Integer idenAtrac) {
        this.idenAtrac = idenAtrac;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        return "Mantenimiento{" + "idenAtrac=" + idenAtrac + ", email=" + email + ", fechaRevi=" + fechaRevi + ", error=" + error + ", descripcion=" + descripcion + ", solucion=" + solucion + '}';
    }

}
