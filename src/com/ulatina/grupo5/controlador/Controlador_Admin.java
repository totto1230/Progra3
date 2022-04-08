package com.ulatina.grupo5.controlador;



import com.ulatina.grupo5.dao.impl.UsuariosDAOImpl;
import com.ulatina.grupo5.modelo.Usuarios;
import com.ulatina.grupo5.vista.MenuAdminView;
import com.ulatina.grupo5.vista.UsuariosView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Integer.parseInt;
import javax.swing.JOptionPane;


public class Controlador_Admin extends MenuAdminView implements ActionListener{
    
    MenuAdminView ui;
    UsuariosView ui2;
    Usuarios mod;
    UsuariosDAOImpl impl;

    public Controlador_Admin(MenuAdminView ui, UsuariosView ui2, Usuarios mod, UsuariosDAOImpl impl) {
        this.ui = ui;
        this.ui2= ui2;
        this.mod = mod;
        this.impl = impl;
        this.ui.jButton5_back_main_menu_admin.addActionListener(this);
        this.ui.jButton_Registrar_Usuarios_Main_admin.addActionListener(this);
        this.ui.jButton_add_atraccion_main_admin.addActionListener(this);
        this.ui.jButton_ganancias_main_menu.addActionListener(this);
        this.ui.jButton_mantenimiento_atracciones_main_admin.addActionListener(this);
        this.ui2.jButton_create_user.addActionListener(this);
        this.ui2.jButton_previous_register.addActionListener(this);
        
    }

  
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
    
    
    
    
    
}
