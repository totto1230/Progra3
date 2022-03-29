package com.ulatina.grupo5.modelo;

/*
 `orderId` INT NOT NULL, -
  `email` VARCHAR(255) NOT NULL, -
  `ticket` INT NOT NULL, -
 */
public class BookeoPersona {

    public Integer orderId;
    public String email;
    public Integer ticket;

    public BookeoPersona() {
    }

    public BookeoPersona(Integer orderId, String email, Integer ticket) {
        this.orderId = orderId;
        this.email = email;
        this.ticket = ticket;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getTicket() {
        return ticket;
    }

    public void setTicket(Integer ticket) {
        this.ticket = ticket;
    }

    @Override
    public String toString() {
        return "BookeoPersona{" + "orderId=" + orderId + ", email=" + email + ", ticket=" + ticket + '}';
    }

}
