
package com.ulatina.grupo5.controlador;

import com.ulatina.grupo5.dao.BaseDAO;
import com.ulatina.grupo5.dao.impl.AtraccionesDAOImpl;
import com.ulatina.grupo5.modelo.Atracciones;
import com.ulatina.grupo5.vista.Registro_Atracciones_Admin;
import com.ulatina.grupo5.vista.Seguimiento_De_Atracciones_Admin;



public class SeguimientoController {
    Atracciones atraccion = new Atracciones();
    BaseDAO dao = new AtraccionesDAOImpl();
    Seguimiento_De_Atracciones_Admin vista = new Seguimiento_De_Atracciones_Admin();
    
    public SeguimientoController(Seguimiento_De_Atracciones_Admin vista) {
        this.vista = vista;
        //this.vista.jButton_Back_Ver_Usuarios_Admin.addActionListener(this);
    }
    
}
