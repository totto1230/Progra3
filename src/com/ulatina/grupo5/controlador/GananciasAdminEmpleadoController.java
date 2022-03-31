package com.ulatina.grupo5.controlador;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import com.ulatina.grupo5.dao.BaseDAO;
import com.ulatina.grupo5.dao.impl.AtraccionesDAOImpl;
import com.ulatina.grupo5.modelo.Atracciones;
import com.ulatina.grupo5.vista.Buscar_Atracciones_Admin;
import com.ulatina.grupo5.vista.Ganancias_Admin_Empleado;
import com.ulatina.grupo5.vista.Menu_Admin;

public class GananciasAdminEmpleadoController implements ActionListener{
    
    Atracciones atraccion = new Atracciones();
    BaseDAO dao = new AtraccionesDAOImpl();
    Ganancias_Admin_Empleado vista = new Ganancias_Admin_Empleado();

    
    public GananciasAdminEmpleadoController(Ganancias_Admin_Empleado vista) {
        this.vista = vista;
        this.vista.btnAgregar.addActionListener(this);
        this.vista.btnAtras.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.btnAgregar) {
            
        }
        else if (e.getSource() == vista.btnAtras) {
            vista.setVisible(false);
            vista.dispose();
        }
    }
}
