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
import com.ulatina.grupo5.dao.impl.LoginDAOImpl;
import com.ulatina.grupo5.dao.impl.UsuariosDAOImpl;
import com.ulatina.grupo5.modelo.Usuarios;
import com.ulatina.grupo5.vista.Login;
import com.ulatina.grupo5.vista.Menu_Admin;
import com.ulatina.grupo5.vista.Menu_Empleado;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoginController implements ActionListener {

    com.ulatina.grupo5.modelo.Login login = new com.ulatina.grupo5.modelo.Login();
    BaseDAO daoUsuario = new UsuariosDAOImpl();
    BaseDAO dao = new LoginDAOImpl();
    Login vista = new Login();
    Menu_Admin menu = new Menu_Admin();
    public static com.ulatina.grupo5.modelo.Login session = new com.ulatina.grupo5.modelo.Login();
    public static com.ulatina.grupo5.modelo.Usuarios sessionUsr = new com.ulatina.grupo5.modelo.Usuarios();
    
    public LoginController(Login vista) {
        this.vista = vista;
        this.vista.btnLogin.addActionListener(this);
        this.vista.btnCancel.addActionListener(this);
    }

    public void iniciar() {

    }

    public boolean agregar(int usuario, String password) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());

        login.setIdLogin(dao.nextID());
        login.setCedula(usuario);
        login.setNumTickets(0);
        login.setFechaLogin(java.sql.Date.valueOf(date.toString()));
        login.setFechaLogoff(java.sql.Date.valueOf(date.toString()));
        
        boolean r = dao.insertar(login);
        if (r) {
            session = login;
            JOptionPane.showMessageDialog(vista, "Ingreso con exito");
        } else {
            JOptionPane.showMessageDialog(vista, "Error al ingresar, por favor intente de nuevo");
        }
        return r;

    }

    private void cargarMenuAdmin() {
        Menu_Admin vistaMenuAdmin = new Menu_Admin();
        Menu_AdminController controller = new Menu_AdminController(vistaMenuAdmin);    
    }
    
    private void cargarMenuEmpleado() {
        Menu_Empleado vistaMenuAdmin = new Menu_Empleado();
        MenuEmpleadoController controller = new MenuEmpleadoController(vistaMenuAdmin);    
    }
    
    private void cargarMenuUsuario() {
        //TODO  
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnLogin) {
            if (vista.txtUsername.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Debe ingresar el Usuario", "Error al autenticar", JOptionPane.INFORMATION_MESSAGE);
                vista.txtUsername.requestFocus();
            } else if (vista.txtPassword.getPassword().toString().isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Debe ingresar la contraseña", "Error al autenticar", JOptionPane.INFORMATION_MESSAGE);
                vista.txtPassword.requestFocus();
            } else {
                int cedula = Integer.parseInt(vista.txtUsername.getText());
                String password = vista.txtPassword.getPassword().toString();
                Usuarios usuario = (Usuarios) daoUsuario.listarUno(cedula);
                if (usuario != null) {
                    if (usuario.password == password) {
                        if(agregar(cedula,password)){
                            sessionUsr = usuario;
                            switch(sessionUsr.getTipoUsuario())
                            {
                                case 1:
                                    cargarMenuAdmin();
                                    break;
                                case 2:
                                    cargarMenuEmpleado();
                                    break;
                                default:
                                     cargarMenuUsuario();
                                     break;
                            }
                            
                            cargarMenuEmpleado();
                        } 
                    } else {
                        JOptionPane.showMessageDialog(vista, "Usuario o contraseña invalidos", "Error al autenticar", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(vista, "Usuario o contraseña invalidos", "Error al autenticar", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } else if (e.getSource() == vista.btnCancel) {
            vista.setVisible(false); 
            vista.dispose();
        }
    }
}
