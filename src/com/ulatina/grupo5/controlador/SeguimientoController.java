
package com.ulatina.grupo5.controlador;

import com.ulatina.grupo5.dao.BaseDAO;
import com.ulatina.grupo5.dao.impl.MantenimientoDAOImpl;
import com.ulatina.grupo5.modelo.Atracciones;
import com.ulatina.grupo5.vista.Seguimiento_De_Atracciones_Admin;



public class SeguimientoController {
    Atracciones atraccion = new Atracciones();
    BaseDAO dao = new MantenimientoDAOImpl();
    Seguimiento_De_Atracciones_Admin vista = new Seguimiento_De_Atracciones_Admin();
    
    public SeguimientoController(Seguimiento_De_Atracciones_Admin vista) {
        this.vista = vista;
        //this.vista.jButton_Back_Ver_Usuarios_Admin.addActionListener(this);
    }
    
}
