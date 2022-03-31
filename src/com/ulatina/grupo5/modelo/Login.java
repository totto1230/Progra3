package com.ulatina.grupo5.modelo;

import java.sql.Date;


public class Login {

    public Integer idLogin;
    public Integer numTickets;
    public Date fechaLogin;
    public Date fechaLogoff;
    public String email;
    
    public Login() {
        
    }

    public Login(Integer idLogin,String email, Integer numTickets, Date fechaLogin, Date fechaLogoff) {
        this.idLogin = idLogin;
        this.email = email;
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
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
