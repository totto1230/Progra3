/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ulatina.grupo5.controlador;

import com.ulatina.grupo5.dao.BaseDAO;
import com.ulatina.grupo5.dao.impl.AtraccionesDAOImpl;
import com.ulatina.grupo5.modelo.Atracciones;
import com.ulatina.grupo5.modelo.Usuarios;
import com.ulatina.grupo5.modelo.Precio;
import com.ulatina.grupo5.modelo.Bookeo;
import com.ulatina.grupo5.modelo.BookeoAtracciones;
import com.ulatina.grupo5.modelo.BookeoPersona;

import com.ulatina.grupo5.dao.impl.AtraccionesDAOImpl;
import com.ulatina.grupo5.dao.impl.BookeoAtraccionesDAOImpl;
import com.ulatina.grupo5.dao.impl.BookeoDAOImpl;
import com.ulatina.grupo5.dao.impl.BookeoPersonaDAOImpl;
import com.ulatina.grupo5.dao.impl.PrecioDAOImpl;
import com.ulatina.grupo5.dao.impl.UsuariosDAOImpl;

import com.ulatina.grupo5.vista.BookeoView;
import com.ulatina.grupo5.vista.Menu_Admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author fernando
 */
public class BookeoController implements ActionListener {

    BookeoView vista = new BookeoView();
    Menu_Admin main = new Menu_Admin();
    
    Bookeo bookeo = new Bookeo();
    BookeoPersona bookeoPersona =  new BookeoPersona();
    BookeoAtracciones bookeoAtracciones =  new BookeoAtracciones();
    
    BaseDAO daoAtracciones = new AtraccionesDAOImpl();
    BaseDAO daoPrecios = new PrecioDAOImpl();
    BaseDAO daoBookeo = new BookeoDAOImpl();
    BaseDAO daoBookeoAtracciones = new BookeoAtraccionesDAOImpl();
    BaseDAO daoBookeoPersonas = new BookeoPersonaDAOImpl();
    BaseDAO daoUsuarios= new UsuariosDAOImpl();
    

    public BookeoController(BookeoView vista) {
        this.vista = vista;
        this.vista.btnAgregarAtraccion.addActionListener(this);
        this.vista.btnAtras.addActionListener(this);
        this.vista.btnCrearTickete.addActionListener(this);
        this.vista.btnUsuarios.addActionListener(this);
        this.vista.btnAgregarUsuarioAtraccion.addActionListener(this);
        this.vista.chkPaseEspecial.addActionListener(this);
        
        iniciar();
    }

    private void loadComboBoxAtracciones()
    {
        Atracciones[] atracciones = (Atracciones[])daoAtracciones.listarPor(true);
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        for (int i = 0; i < atracciones.length; i++) {
            model.addElement(new ComboItem(atracciones[i].getNombreAtraccion(), String.valueOf(atracciones[i].getIdAtracciones())));
        }

        this.vista.ddlAtracciones.setModel(model);

    }
    
    private void insertarAtraccionTable()
    {
        String[] titulos = {"Id de Atracción", "Nombre Atracción"};
        String[] registros = new String[titulos.length];
        DefaultTableModel model = new DefaultTableModel(null, titulos);
        if(vista.tblAtracciones.getRowCount() > 0)
        {
            model = (DefaultTableModel)vista.tblAtracciones.getModel();
        }
        
        model.addRow(registros); 
        vista.tblAtracciones.setModel(model);
    }
    
    public void volver() {
        main.setVisible(true);
        vista.dispose();
    }

    public void iniciar() {
        loadComboBoxAtracciones();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == vista.btnAgregarAtraccion)
        {
            vista.tblAtracciones.add
        }
        else if (e.getSource() == vista.btnAtras) {
            volver();
        }
    }
    
}
