/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ulatina.grupo5.controlador;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import com.ulatina.grupo5.dao.BaseDAO;
import com.ulatina.grupo5.dao.impl.AtraccionesDAOImpl;
import com.ulatina.grupo5.modelo.Atracciones;
import com.ulatina.grupo5.modelo.Usuarios;
import com.ulatina.grupo5.vista.Buscar_Atracciones_Admin;
import com.ulatina.grupo5.vista.Menu_Admin;
import com.ulatina.grupo5.vista.Menu_Empleado;
import com.ulatina.grupo5.vista.Registro_Atracciones_Admin;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class BuscarAtraccionesController implements ActionListener {

    Usuarios currentUser = LoginController.sessionUsr;
    Atracciones atraccion = new Atracciones();
    BaseDAO dao = new AtraccionesDAOImpl();
    Buscar_Atracciones_Admin vista = new Buscar_Atracciones_Admin();
    Menu_Admin main = new Menu_Admin();

    public BuscarAtraccionesController(Buscar_Atracciones_Admin vista) {
        this.vista = vista;
        this.vista.btnBackAtracAdmin.addActionListener(this);
        this.vista.jTable_Buscar_Atracciones_Admin.addMouseListener(new MouseAdapter() {
        @Override
            public void mouseClicked(MouseEvent e) {
                int row = vista.jTable_Buscar_Atracciones_Admin.getSelectedRow();
                int idAtraccion  = Integer.parseInt(vista.jTable_Buscar_Atracciones_Admin.getModel().getValueAt(row, 0).toString());
                atraccion = (Atracciones)dao.listarUno(idAtraccion);
                Registro_Atracciones_Admin registroAtraccionesAdmin = new Registro_Atracciones_Admin();
                RegistroAtraccionesController registrarAtracciones = new RegistroAtraccionesController(registroAtraccionesAdmin);
                registrarAtracciones.iniciar(atraccion);
                registroAtraccionesAdmin.setVisible(true);
                vista.dispose();
            }
        });
    }

    public void listarTabla(JTable tabla) {
        dao.listar(tabla);
    }

    private void cargarMenuAdmin() {
        Menu_Admin vistaMenuAdmin = new Menu_Admin();
        Menu_AdminController controller = new Menu_AdminController(vistaMenuAdmin);
        vistaMenuAdmin.setVisible(true);
        vista.dispose();

    }

    private void cargarMenuEmpleado() {
        Menu_Empleado vistaMenuAdmin = new Menu_Empleado();
        MenuEmpleadoController controller = new MenuEmpleadoController(vistaMenuAdmin);
        vistaMenuAdmin.setVisible(true);
        vista.dispose();
    }
    public void volver() {
        switch (currentUser.getTipoUsuario()) {
                case 1:
                    cargarMenuAdmin();
                    break;
                case 2:
                    cargarMenuEmpleado();
                    break;
            }
    }

    public void iniciar() {
        this.listarTabla(vista.jTable_Buscar_Atracciones_Admin);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnBackAtracAdmin) {
            volver();
        }
    }

}
