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
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoginController implements ActionListener {

    com.ulatina.grupo5.modelo.Login login = new com.ulatina.grupo5.modelo.Login();
    BaseDAO daoUsuario = new UsuariosDAOImpl();
    BaseDAO dao = new LoginDAOImpl();
    Login vista = new Login();
    Menu_Admin menu = new Menu_Admin();

    public LoginController(Login vista) {
        this.vista = vista;
        this.vista.btnLogin.addActionListener(this);
        this.vista.btnCancel.addActionListener(this);
    }

    public void iniciar() {

    }

    public void agregar(int usuario, String password) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());

        login.setIdLogin(dao.nextID());
        login.setCedula(usuario);
        login.setNumTickets(0);
        login.setFechaLogin(java.sql.Date.valueOf(date.toString()));
        login.setFechaLogoff(java.sql.Date.valueOf(date.toString()));

        boolean r = dao.insertar(login);

        if (r) {
            JOptionPane.showMessageDialog(vista, "La atraccion fue agregado correctamente");
        } else {
            JOptionPane.showMessageDialog(vista, "La atraccion no fue agregada");
        }

    }

    private void cargarMenu() {
        Menu_Admin vistaMenuAdmin = new Menu_Admin();
        
       // PersonaController controlador = new PersonaController(vista);
       // controlador.iniciar();
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
                        agregar(cedula,password);
                        cargarMenu();
                    } else {
                        JOptionPane.showMessageDialog(vista, "Usuario o contraseña invalidos", "Error al autenticar", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(vista, "Usuario o contraseña invalidos", "Error al autenticar", JOptionPane.INFORMATION_MESSAGE);
                }

                //agregar();
            }
        } else if (e.getSource() == vista.btnCancel) {
            
            vista.setVisible(false); 
            vista.dispose();
        }
    }
}
