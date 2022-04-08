package com.ulatina.grupo5.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.ulatina.grupo5.dao.BaseDAO;
import com.ulatina.grupo5.dao.impl.AtraccionesDAOImpl;
import com.ulatina.grupo5.modelo.Atracciones;
import com.ulatina.grupo5.vista.AtraccionesListadoView;
import com.ulatina.grupo5.vista.GananciasListadoView;
import com.ulatina.grupo5.vista.LoginView;
import com.ulatina.grupo5.vista.MenuAdminView;
import com.ulatina.grupo5.vista.AtraccionesView;
import com.ulatina.grupo5.vista.UsuariosView;
import com.ulatina.grupo5.vista.MantenimientoView;
import com.ulatina.grupo5.vista.UsuariosListadoView;

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
    MenuAdminView vista = new MenuAdminView();

    UsuariosView registroUsuariosVista = new UsuariosView();
    UsuariosListadoView verUsuarioVista = new UsuariosListadoView();
    AtraccionesView registroAtraccVista = new AtraccionesView();
    AtraccionesListadoView buscarAtracVista = new AtraccionesListadoView();
    MantenimientoView seguimientoAtraVist = new MantenimientoView();
    GananciasListadoView ganaciaVista = new GananciasListadoView();
    LoginView loginVista = new LoginView();

    public Menu_AdminController(MenuAdminView vista) {
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
