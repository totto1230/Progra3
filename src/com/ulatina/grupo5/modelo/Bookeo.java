package com.ulatina.grupo5.modelo;

import java.util.Date;

/*
  `ticket` INT NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `fechaCompra` DATETIME(6) NOT NULL,
  `fechaVisita` DATETIME(6) NOT NULL,
  `totalVenta` DECIMAL(6,2) NOT NULL,
  `paseEspecial` TINYINT NOT NULL,
 */
public class Bookeo {

    public Integer ticket;
    public String email;
    public Date fechaCompra;
    public Date fechaVisita;
    public double totalVenta;
    public boolean paseEspecial;

    public Bookeo() {
    }

    public Bookeo(Integer ticket, String email, Date fechaCompra, Date fechaVisita, double totalVenta, boolean paseEspecial) {
        this.ticket = ticket;
        this.email = email;
        this.fechaCompra = fechaCompra;
        this.fechaVisita = fechaVisita;
        this.totalVenta = totalVenta;
        this.paseEspecial = paseEspecial;
    }

    @Override
    public String toString() {
        return "Bookeo{" + "ticket=" + ticket + ", email=" + email + ", fechaCompra=" + fechaCompra + ", fechaVisita=" + fechaVisita + ", totalVenta=" + totalVenta + ", paseEspecial=" + paseEspecial + '}';
    }

    public Integer getTicket() {
        return ticket;
    }

    public void setTicket(Integer ticket) {
        this.ticket = ticket;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Date getFechaVisita() {
        return fechaVisita;
    }

    public void setFechaVisita(Date fechaVisita) {
        this.fechaVisita = fechaVisita;
    }

    public double getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(double totalVenta) {
        this.totalVenta = totalVenta;
    }

    public boolean isPaseEspecial() {
        return paseEspecial;
    }

    public void setPaseEspecial(boolean paseEspecial) {
        this.paseEspecial = paseEspecial;
    }

}
