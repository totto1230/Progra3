package com.ulatina.grupo5.modelo;

import java.sql.Date;


public class Login {

    public Integer idLogin;
    public Integer numTickets;
    public Date fechaLogin;
    public Date fechaLogoff;
    public Integer cedula;
    
    public Login() {
        
    }

    public Login(Integer idLogin,Integer cedula, Integer numTickets, Date fechaLogin, Date fechaLogoff) {
        this.idLogin = idLogin;
        this.cedula = cedula;
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
    
    public int getCedula() {
        return cedula;
    }

    public void setCedula(Integer cedula) {
        this.cedula = cedula;
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
