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
import com.ulatina.grupo5.vista.Registro_Atracciones_Admin;

public class RegistroAtraccionesController implements ActionListener {

    Atracciones atraccion = new Atracciones();
    BaseDAO dao = new AtraccionesDAOImpl();
    Registro_Atracciones_Admin vista = new Registro_Atracciones_Admin();

    public RegistroAtraccionesController(Registro_Atracciones_Admin vista) {
        this.vista = vista;
        this.vista.btnAgregar.addActionListener(this);
        this.vista.btnVolver.addActionListener(this);
    }
    
    public void iniciar(Atracciones atraccion) {
        vista.txtCodigoAtraccion.setText(atraccion.getIdAtracciones().toString());
        vista.txtNombreAtraccion.setText(atraccion.getNombreAtraccion());
        vista.dtpFechaInstalacion.setDate(atraccion.getFechaInstalacion());
        vista.sldCapacidad.setValue(atraccion.getCapacidad());
        vista.ddlSeccion.setSelectedIndex(getSeccion(atraccion.getSeccion()));
        vista.ddlRangoEdadMin.setSelectedIndex(atraccion.getEdadMin()-1);
        vista.ddlRangoEdadMax.setSelectedIndex(atraccion.getEdadMax()-1);
        vista.txtPrecio.setText(String.valueOf(atraccion.getPrecioNormal()));
        
    }

    private int getSeccion(String seccion)
    {
        int rtn = 0;
        switch (seccion) {
            case "I":
                rtn = 0;
                break;
            case "A":
                rtn = 1;
                break;
            case "F":
                rtn = 2;
                break;
        }
        return rtn;
    }

    public void listarTabla(JTable tabla) {
        dao.listar(tabla);
    }

    public void limpiarCampos() {
        //vista..setText("");        
    }

    public void iniciar() {
        this.listarTabla(vista.tblAtracciones);
        limpiarCampos();

    }
    
    public Atracciones devolverUsers() {
        Integer idAtraccion = Integer.parseInt(vista.txtCodigoAtraccion.getText());
        String nombreAtraccion = vista.txtNombreAtraccion.getText();
        java.sql.Date fechaInsta = java.sql.Date.valueOf(vista.dtpFechaInstalacion.getDate().toString());
        Integer capacidad = vista.sldCapacidad.getValue();
        String seccion = String.valueOf(vista.ddlSeccion.getSelectedItem().toString().charAt(0)).toUpperCase();// optiene la primer letra (I,A,F)
        Integer edadMin = Integer.parseInt(vista.ddlRangoEdadMin.getSelectedItem().toString());
        Float precio = Float.parseFloat(vista.txtPrecio.getText());
        Boolean activo = true;

        atraccion = new Atracciones(idAtraccion, nombreAtraccion, fechaInsta, capacidad, seccion, edadMin, precio, activo);
        return atraccion;
    }

    public void agregar() {
        

        boolean r = dao.insertar(atraccion);

        if (r) {
            listarTabla(vista.tblAtracciones);
            limpiarCampos();
            JOptionPane.showMessageDialog(vista, "La atraccion fue agregado correctamente");
        } else {
            JOptionPane.showMessageDialog(vista, "La atraccion no fue agregada");
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.btnAgregar) {
            if (vista.txtCodigoAtraccion.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Debe ingresar el codgo de la atracción", "Error al Agregar", JOptionPane.INFORMATION_MESSAGE);
                vista.txtCodigoAtraccion.requestFocus();
            } else if (vista.txtNombreAtraccion.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Debe ingresar el nombre de la atracción", "Error al Agregar", JOptionPane.INFORMATION_MESSAGE);
                vista.txtNombreAtraccion.requestFocus();
            } else if (vista.txtPrecio.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Debe ingresar el nombre de la atracción", "Error al Agregar", JOptionPane.INFORMATION_MESSAGE);
                vista.txtPrecio.requestFocus();
            } else {
                agregar();
            }
            listarTabla(vista.tblAtracciones);
        }
    }
}
