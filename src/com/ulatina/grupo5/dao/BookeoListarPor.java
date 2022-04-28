/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ulatina.grupo5.dao;

import java.util.Date;

/**
 *
 * @author fernando
 */
public class BookeoListarPor {
    public Integer tipoUsuario;
    public Integer ticket;
    public Integer cedula;
    public Date  fechaDesde;
    public Date  fechaHasta;

    public BookeoListarPor(Integer tipoUsuario, Integer ticket, Integer cedula, Date fechaDesde, Date fechaHasta) {
        this.tipoUsuario = tipoUsuario;
        this.ticket = ticket;
        this.cedula = cedula;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
    }

    public BookeoListarPor() {
    }

    public Integer getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(Integer tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Integer getTicket() {
        return ticket;
    }

    public void setTicket(Integer ticket) {
        this.ticket = ticket;
    }

    public Integer getCedula() {
        return cedula;
    }

    public void setCedula(Integer cedula) {
        this.cedula = cedula;
    }

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }
    

    

    
    
}
