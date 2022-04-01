package com.ulatina.grupo5.controlador;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import com.ulatina.grupo5.dao.BaseDAO;
import com.ulatina.grupo5.dao.impl.GanaciasDAOImpl;
import com.ulatina.grupo5.modelo.Atracciones;
import com.ulatina.grupo5.modelo.Ganancias;
import com.ulatina.grupo5.vista.Ganancias_Admin_Empleado;
import com.ulatina.grupo5.vista.Menu_Admin;
import java.sql.Date;
import javax.swing.JTable;

public class GananciasAdminEmpleadoController implements ActionListener {

    Atracciones atraccion = new Atracciones();
    Ganancias ganacia = new Ganancias();
    GanaciasDAOImpl dao = new GanaciasDAOImpl();
    Ganancias_Admin_Empleado vista = new Ganancias_Admin_Empleado();

    Menu_AdminController menuAdminCtrl;
    Menu_Admin menuVista = new Menu_Admin();
    Ganancias ganancia = new Ganancias();

    public GananciasAdminEmpleadoController(Ganancias_Admin_Empleado vista) {
        this.vista = vista;
        this.vista.btnPreviousGanacias.addActionListener(this);
        this.vista.btnFiltrar.addActionListener(this);
        this.vista.ddlFiltroSec.addActionListener(this);
    }

    public void iniciar() {
        this.listarTabla(vista.tbkAtraccionesGanacias);
    }

    public void listarTabla(JTable tabla) {
        String seccion = String.valueOf(vista.ddlFiltroSec.getSelectedItem().toString().charAt(0)).toUpperCase();
        Boolean filtrar = vista.chkFiltroFechas.isSelected();
        Date dateFrom = (Date) vista.dateChFechaInicio.getDate();
        Date dateTo = (Date) vista.dateChFechaFinal.getDate();
        dao.listar(tabla, seccion, filtrar, dateFrom, dateTo);

    }

    public Ganancias devolverValor() {
        String seccion = String.valueOf(vista.ddlFiltroSec.getSelectedItem().toString().charAt(0)).toUpperCase();
        Boolean filtrar = vista.chkFiltroFechas.isSelected();
        Date dateFrom = (Date) vista.dateChFechaInicio.getDate();
        Date dateTo = (Date) vista.dateChFechaFinal.getDate();
        Ganancias valores = new Ganancias(seccion, filtrar, dateFrom, dateTo);
        return valores;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.btnPreviousGanacias) {
            menuAdminCtrl = new Menu_AdminController(menuVista);
            menuVista.setVisible(true);
            vista.dispose();

        }
        if (e.getSource() == vista.btnFiltrar) {
            listarTabla(vista.tbkAtraccionesGanacias);
        }
    }
}
