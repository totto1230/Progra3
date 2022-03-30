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
import com.ulatina.grupo5.dao.impl.UsuariosDAOImpl;
import com.ulatina.grupo5.vista.Login;


/**
 *
 * @author fernando
 */
public class LoginController implements ActionListener {

    com.ulatina.grupo5.modelo.Login login = new com.ulatina.grupo5.modelo.Login();
    BaseDAO dao = new UsuariosDAOImpl();
    Login vista = new Login();
    

    public LoginController(Login vista) {
        this.vista = vista;
        this.vista.btnLogin.addActionListener(this);
        this.vista.btnCancel.addActionListener(this);
        
    }

    public void listarTabla(JTable tabla) {
        dao.listar(tabla);
    }

    public void limpiarCampos() {
        //vista..setText("");        
    }

    public void iniciar() {
        limpiarCampos();

    }

    public void agregar() {
        String usuario = vista.txtUsername.getText();
        String password = vista.txtPassword.getPassword().toString();// optiene la primer letra (I,A,F)
        login.setIdLogin(Integer.SIZE);
        

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

        if (e.getSource() == vista.btnLogin) {
            if (vista.txtUsername.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Debe ingresar el Usuario", "Error al autenticar", JOptionPane.INFORMATION_MESSAGE);
                vista.txtUsername.requestFocus();
            } else if (vista.txtPassword.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Debe ingresar la contraseña", "Error al autenticar", JOptionPane.INFORMATION_MESSAGE);
                vista.txtPassword.requestFocus();
            }
            else {
                agregar();
            }
        }
        else if (e.getSource() == vista.btnCancel) {
            vista.setVisible(false); //you can't see me!
            vista.dispose();
        }
    }
}
