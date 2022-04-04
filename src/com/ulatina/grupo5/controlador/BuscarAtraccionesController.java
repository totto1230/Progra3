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
import com.ulatina.grupo5.vista.Buscar_Atracciones_Admin;
import com.ulatina.grupo5.vista.Menu_Admin;


public class BuscarAtraccionesController implements ActionListener {

    Atracciones atraccion = new Atracciones();
    BaseDAO dao = new AtraccionesDAOImpl();
    Buscar_Atracciones_Admin vista = new Buscar_Atracciones_Admin();
    Menu_Admin main = new Menu_Admin();

    public BuscarAtraccionesController(Buscar_Atracciones_Admin vista) {
        this.vista = vista;
        this.vista.jButton_Back_Buscar_Atrac_Admin.addActionListener(this);
    }

    public void listarTabla(JTable tabla) {
        dao.listar(tabla);
    }

    public void volver() {
        main.setVisible(true);
        vista.dispose();
    }

    public void iniciar() {
        this.listarTabla(vista.jTable_Buscar_Atracciones_Admin);

    }

    @Override
    public void actionPerformed(ActionEvent e) {


        if (e.getSource() == vista.jButton_Back_Buscar_Atrac_Admin) {
            volver();
        }
    }

}
