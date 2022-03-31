
package com.ulatina.grupo5.modelo;

import java.sql.Date;

public class Ganancias {
    
    public Integer numeroAtrac;
    public String nombreAtrac;
    public Integer recaudacionAtrac;
    public Date fechaSelec;
    public Integer cantPersonas;

    public Ganancias(Integer numeroAtrac, String nombreAtrac, Integer recaudacionAtrac, Date fechaSelec, Integer cantPersonas) {
        this.numeroAtrac = numeroAtrac;
        this.nombreAtrac = nombreAtrac;
        this.recaudacionAtrac = recaudacionAtrac;
        this.fechaSelec = fechaSelec;
        this.cantPersonas = cantPersonas;
    }

    public Ganancias() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String toString() {
        return "Ganancias{" + "numeroAtrac=" + numeroAtrac + ", nombreAtrac=" + nombreAtrac + ", recaudacionAtrac=" + recaudacionAtrac + ", fechaSelec=" + fechaSelec + ", cantPersonas=" + cantPersonas + '}';
    }

    public Integer getNumeroAtrac() {
        return numeroAtrac;
    }

    public void setNumeroAtrac(Integer numeroAtrac) {
        this.numeroAtrac = numeroAtrac;
    }

    public String getNombreAtrac() {
        return nombreAtrac;
    }

    public void setNombreAtrac(String nombreAtrac) {
        this.nombreAtrac = nombreAtrac;
    }

    public Integer getRecaudacionAtrac() {
        return recaudacionAtrac;
    }

    public void setRecaudacionAtrac(Integer recaudacionAtrac) {
        this.recaudacionAtrac = recaudacionAtrac;
    }

    public Date getFechaSelec() {
        return fechaSelec;
    }

    public void setFechaSelec(Date fechaSelec) {
        this.fechaSelec = fechaSelec;
    }

    public Integer getCantPersonas() {
        return cantPersonas;
    }

    public void setCantPersonas(Integer cantPersonas) {
        this.cantPersonas = cantPersonas;
    }
    
    
}
