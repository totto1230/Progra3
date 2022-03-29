package com.ulatina.grupo5.modelo;

import java.sql.Date;

/*
 `idLogin` INT NOT NULL, -
  `numTickets` INT NOT NULL,-
  `fechaLogin` DATETIME(6) NULL,-
  `fechaLogoff` DATETIME(6) NULL,-
 */
public class Login {

    public Integer idLogin;
    public Integer numTickets;
    public Date fechaLogin;
    public Date fechaLogoff;

    public Login() {
    }

    public Login(Integer idLogin, Integer numTickets, Date fechaLogin, Date fechaLogoff) {
        this.idLogin = idLogin;
        this.numTickets = numTickets;
        this.fechaLogin = fechaLogin;
        this.fechaLogoff = fechaLogoff;
    }

    public Integer getIdLogin() {
        return idLogin;
    }

    public void setIdLogin(Integer idLogin) {
        this.idLogin = idLogin;
    }

    public Integer getNumTickets() {
        return numTickets;
    }

    public void setNumTickets(Integer numTickets) {
        this.numTickets = numTickets;
    }

    public Date getFechaLogin() {
        return fechaLogin;
    }

    public void setFechaLogin(Date fechaLogin) {
        this.fechaLogin = fechaLogin;
    }

    public Date getFechaLogoff() {
        return fechaLogoff;
    }

    public void setFechaLogoff(Date fechaLogoff) {
        this.fechaLogoff = fechaLogoff;
    }

    @Override
    public String toString() {
        return "Login{" + "idLogin=" + idLogin + ", numTickets=" + numTickets + ", fechaLogin=" + fechaLogin + ", fechaLogoff=" + fechaLogoff + '}';
    }

}
