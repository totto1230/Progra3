package com.ulatina.grupo5.controlador;

import com.ulatina.grupo5.vista.Buscar_Atracciones_Admin;
import com.ulatina.grupo5.vista.Login;
import com.ulatina.grupo5.vista.Menu_Empleado;
import com.ulatina.grupo5.vista.Seguimiento_De_Atracciones_Admin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuEmpleadoController implements ActionListener {

    SeguimientoController seguimiento;
    BuscarAtraccionesController buscarAtrac;
    LoginController loginC;

    //BaseDAO dao = new AtraccionesDAOImpl();
    Menu_Empleado vista = new Menu_Empleado();

    Buscar_Atracciones_Admin buscarAtracVista = new Buscar_Atracciones_Admin();
    Seguimiento_De_Atracciones_Admin seguimientoAtraVist = new Seguimiento_De_Atracciones_Admin();
    Login loginVista = new Login();

    public MenuEmpleadoController(Menu_Empleado vista) {
        this.vista = vista;
        this.vista.jButton5_back_main_menu_empleado.addActionListener(this);
        this.vista.btnSeguimiento.addActionListener(this);
        this.vista.btnBuscar.addActionListener(this);
    }
    
    public void init()
    {
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.btnSeguimiento) {
            seguimiento = new SeguimientoController(seguimientoAtraVist);
            seguimiento.iniciar();
            seguimientoAtraVist.setVisible(true);
            vista.dispose();
        } else if (e.getSource() == vista.btnBuscar) {
            buscarAtrac = new BuscarAtraccionesController(buscarAtracVista);
            buscarAtrac.iniciar();
            buscarAtracVista.setVisible(true);
            vista.dispose();
        } else if (e.getSource() == vista.jButton5_back_main_menu_empleado) {
            loginC = new LoginController(loginVista);
            loginC.iniciar();
            loginVista.setVisible(true);
            vista.dispose();
        }

    }
}
