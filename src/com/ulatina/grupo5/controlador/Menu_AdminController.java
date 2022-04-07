package com.ulatina.grupo5.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.ulatina.grupo5.dao.BaseDAO;
import com.ulatina.grupo5.dao.impl.AtraccionesDAOImpl;
import com.ulatina.grupo5.modelo.Atracciones;
import com.ulatina.grupo5.vista.Buscar_Atracciones_Admin;
import com.ulatina.grupo5.vista.Ganancias_Admin_Empleado;
import com.ulatina.grupo5.vista.Login;
import com.ulatina.grupo5.vista.Menu_Admin;
import com.ulatina.grupo5.vista.Registro_Atracciones_Admin;
import com.ulatina.grupo5.vista.Registro_Usuarios_Admin;
import com.ulatina.grupo5.vista.Seguimiento_De_Atracciones_Admin;
import com.ulatina.grupo5.vista.Ver_Usuarios_Admin;

public class Menu_AdminController implements ActionListener {

    UsuariosController verUsuarios;
    RegistrarUsuariosController registrarUsuarios;
    RegistroAtraccionesController registroAtrac;
    RegistroAtraccionesController agregarAtrac;
    BuscarAtraccionesController buscarAtrac;
    SeguimientoController seguimiento;
    GananciasAdminEmpleadoController ganacias;
    LoginController loginC;

    //Atracciones atraccion = new Atracciones();
    //BaseDAO dao = new AtraccionesDAOImpl();
    Menu_Admin vista = new Menu_Admin();

    Registro_Usuarios_Admin registroUsuariosVista = new Registro_Usuarios_Admin();
    Ver_Usuarios_Admin verUsuarioVista = new Ver_Usuarios_Admin();
    Registro_Atracciones_Admin registroAtraccVista = new Registro_Atracciones_Admin();
    Buscar_Atracciones_Admin buscarAtracVista = new Buscar_Atracciones_Admin();
    Seguimiento_De_Atracciones_Admin seguimientoAtraVist = new Seguimiento_De_Atracciones_Admin();
    Ganancias_Admin_Empleado ganaciaVista = new Ganancias_Admin_Empleado();
    Login loginVista = new Login();

    public Menu_AdminController(Menu_Admin vista) {
        this.vista = vista;
        this.vista.jButton_Buscar_Atraccio_Main_Admin.addActionListener(this);
        this.vista.jButton_Registrar_Usuarios_Main_admin.addActionListener(this);
        this.vista.jButton_Ver_Usuarios_Main_Admin.addActionListener(this);
        this.vista.jButton_add_atraccion_main_admin.addActionListener(this);
        this.vista.jButton_ganancias_main_menu.addActionListener(this);
        this.vista.jButton_mantenimiento_atracciones_main_admin.addActionListener(this);
        this.vista.jButton5_back_main_menu_admin.addActionListener(this);
        this.vista.jButton_ganancias_main_menu.addActionListener(this);
    }

  

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.jButton_Registrar_Usuarios_Main_admin) {
            registrarUsuarios = new RegistrarUsuariosController(registroUsuariosVista);
            registrarUsuarios.iniciar();
            registroUsuariosVista.setVisible(true);
            vista.dispose();
        } else if (e.getSource() == vista.jButton_Ver_Usuarios_Main_Admin) {
            verUsuarios = new UsuariosController(verUsuarioVista);
            verUsuarios.iniciar();
            verUsuarioVista.setVisible(true);
            vista.dispose();
        } else if (e.getSource() == vista.jButton_add_atraccion_main_admin) {
            registroAtrac = new RegistroAtraccionesController(registroAtraccVista);
            registroAtrac.iniciar();
            registroAtraccVista.setVisible(true);
            vista.dispose();
        } else if (e.getSource() == vista.jButton_Buscar_Atraccio_Main_Admin) {
            buscarAtrac = new BuscarAtraccionesController(buscarAtracVista);
            buscarAtrac.iniciar();
            buscarAtracVista.setVisible(true);
            vista.dispose();
        } else if (e.getSource() == vista.jButton_mantenimiento_atracciones_main_admin) {
            seguimiento = new SeguimientoController(seguimientoAtraVist);
            seguimiento.iniciar();
            seguimientoAtraVist.setVisible(true);
            vista.dispose();
        } else if (e.getSource() == vista.jButton_ganancias_main_menu) {
            ganacias = new GananciasAdminEmpleadoController(ganaciaVista);
            ganacias.iniciar();
            ganaciaVista.setVisible(true);
            vista.dispose();
        } else if (e.getSource() == vista.jButton5_back_main_menu_admin) {
            loginC = new LoginController(loginVista);
            loginC.iniciar();
            loginVista.setVisible(true);
            vista.dispose();
        }
    }
}
