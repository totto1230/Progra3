package com.ulatina.grupo5.controlador;

import com.ulatina.grupo5.dao.BaseDAO;
import com.ulatina.grupo5.dao.impl.UsuariosDAOImpl;
import com.ulatina.grupo5.modelo.Usuarios;
import com.ulatina.grupo5.vista.MenuAdminView;
import com.ulatina.grupo5.vista.UsuariosView;
import com.ulatina.grupo5.vista.UsuariosListadoView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;

public class UsuariosController implements ActionListener {

    Usuarios usuario = new Usuarios();
    BaseDAO dao = new UsuariosDAOImpl();
    UsuariosListadoView vista = new UsuariosListadoView();
    MenuAdminView main = new MenuAdminView();

    public UsuariosController(UsuariosListadoView vista) {
        this.vista = vista;
        this.vista.jButton_Back_Ver_Usuarios_Admin.addActionListener(this);
        this.vista.jTable_Ver_Usuarios_Admin.addMouseListener(new MouseAdapter() {
        @Override
            public void mouseClicked(MouseEvent e) {
                int row = vista.jTable_Ver_Usuarios_Admin.getSelectedRow();
                int cedula  = Integer.parseInt(vista.jTable_Ver_Usuarios_Admin.getModel().getValueAt(row, 0).toString());
                usuario = (Usuarios)dao.listarUno(cedula);
                UsuariosView registroUsuariosVista = new UsuariosView();
                RegistrarUsuariosController registrarUsuarios = new RegistrarUsuariosController(registroUsuariosVista);
                registrarUsuarios.iniciar(usuario);
                registroUsuariosVista.setVisible(true);
                vista.dispose();
            }
        });
    }

    public void listarTabla(JTable tabla) {
        dao.listar(tabla);
    }

    public void iniciar() {
        this.listarTabla(vista.jTable_Ver_Usuarios_Admin);
    }

    public void volver() {
        main.setVisible(true);
        vista.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.jButton_Back_Ver_Usuarios_Admin) {
            volver();
        }
    }

}
