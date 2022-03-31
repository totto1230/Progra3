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
    BaseDAO dao = new GanaciasDAOImpl();
    Ganancias_Admin_Empleado vista = new Ganancias_Admin_Empleado();

    Menu_AdminController menuAdminCtrl;
    Menu_Admin menuVista = new Menu_Admin();
    Ganancias ganancia = new Ganancias();

    public GananciasAdminEmpleadoController(Ganancias_Admin_Empleado vista) {
        this.vista = vista;
        this.vista.btnAgregarGanaciaBack.addActionListener(this);
        this.vista.btnAgregarGanacia.addActionListener(this);

    }

    public void iniciar() {
        vista.txtIdAtraccion.setText("");
        vista.txtNombreAtraccion.setText("");
        vista.txtRecaudacionFondos.setText("");
        this.listarTabla(vista.tbkAtracciones);

    }
    
    public void listarTabla(JTable tabla) {
        dao.listar(tabla);
    }

    public Ganancias devolverGanacias() {
        Integer numeroAtrac = Integer.parseInt(vista.txtIdAtraccion.getText());
        String nombreAtrac = vista.txtNombreAtraccion.getText();
        Integer recaudacionAtrac = Integer.parseInt(vista.txtRecaudacionFondos.getText());
        Date fechaSelec = (Date) vista.dtpFecha.getDate();
        Integer cantPersonas = vista.jSlider_ganacias_admin_empleado.getValue();

        return ganancia = new Ganancias(numeroAtrac, nombreAtrac, recaudacionAtrac, fechaSelec, cantPersonas);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.btnAgregarGanaciaBack) {
            menuAdminCtrl = new Menu_AdminController(menuVista);
            menuVista.setVisible(true);
            vista.dispose();

        } else if (e.getSource() == vista.btnAgregarGanacia) {
            ganancia = devolverGanacias();

            boolean resultado = dao.insertar(ganancia);

            if (resultado) {
                JOptionPane.showMessageDialog(vista, "Actualizado Correctamente");
            } else {
                JOptionPane.showMessageDialog(vista, "Error al ingresar, por favor intente de nuevo");
            }

        }
    }
}
