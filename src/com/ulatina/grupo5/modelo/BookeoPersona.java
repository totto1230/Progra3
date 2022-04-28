package com.ulatina.grupo5.modelo;

/*
 `orderId` INT NOT NULL, -
  `email` VARCHAR(255) NOT NULL, -
  `ticket` INT NOT NULL, -
 */
public class BookeoPersona {

    public Integer orderId;
    public Integer cedula;
    public Integer ticket;

    public BookeoPersona() {
    }

    public BookeoPersona(Integer orderId, Integer cedula, Integer ticket) {
        this.orderId = orderId;
        this.cedula = cedula;
        this.ticket = ticket;
    }

    public BookeoPersona(Integer ticket) {
        this.ticket = ticket;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getCedula() {
        return cedula;
    }

    public void setCedula(Integer cedula) {
        this.cedula = cedula;
    }



    public Integer getTicket() {
        return ticket;
    }

    public void setTicket(Integer ticket) {
        this.ticket = ticket;
    }

    @Override
    public String toString() {
        return "BookeoPersona{" + "orderId=" + orderId + ", cedula=" + cedula + ", ticket=" + ticket + '}';
    }



}
