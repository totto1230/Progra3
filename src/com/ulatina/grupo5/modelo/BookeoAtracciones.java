/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ulatina.grupo5.modelo;

/**
  `orderId` INT NOT NULL,
  `ticket` INT NOT NULL,
  `idenAtrac` INT NOT NULL,
 */
public class BookeoAtracciones {
    public Integer orderId;
    public Integer ticket;
    public Integer idAtracciones;

    public BookeoAtracciones() {
    }

    public BookeoAtracciones(Integer orderId, Integer ticket, Integer idAtracciones) {
        this.orderId = orderId;
        this.ticket = ticket;
        this.idAtracciones = idAtracciones;
    }

    @Override
    public String toString() {
        return "BookeoAtracciones{" + "orderId=" + orderId + ", ticket=" + ticket + ", idAtracciones=" + idAtracciones + '}';
    }

    
    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getTicket() {
        return ticket;
    }

    public void setTicket(Integer ticket) {
        this.ticket = ticket;
    }

    public Integer getIdAtracciones() {
        return idAtracciones;
    }

    public void setIdAtracciones(Integer idAtracciones) {
        this.idAtracciones = idAtracciones;
    }
    
    
}
