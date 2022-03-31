package com.ulatina.grupo5.controlador;

import com.ulatina.grupo5.dao.BaseDAO;
import com.ulatina.grupo5.dao.impl.UsuariosDAOImpl;
import com.ulatina.grupo5.modelo.Usuarios;
import com.ulatina.grupo5.vista.Menu_Admin;
import com.ulatina.grupo5.vista.Ver_Usuarios_Admin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;

public class UsuariosController implements ActionListener {

    Usuarios usuario = new Usuarios();
    BaseDAO dao = new UsuariosDAOImpl();
    Ver_Usuarios_Admin vista = new Ver_Usuarios_Admin();
    Menu_Admin main = new Menu_Admin();

    public UsuariosController(Ver_Usuarios_Admin vista) {
        this.vista = vista;
        this.vista.jButton_Back_Ver_Usuarios_Admin.addActionListener(this);
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
