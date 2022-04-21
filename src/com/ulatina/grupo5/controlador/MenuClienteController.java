package com.ulatina.grupo5.controlador;

import com.ulatina.grupo5.vista.BookeoView;
import com.ulatina.grupo5.vista.LoginView;
import com.ulatina.grupo5.vista.MenuClienteTiqueteView;
import com.ulatina.grupo5.vista.MenuClienteView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuClienteController implements ActionListener {
    LoginController loginC;
    MenuClienteTiqueteController controlTiqute;
    MenuClienteView vista = new MenuClienteView();
    MenuClienteTiqueteView vistaTiquete= new MenuClienteTiqueteView();
    BookeoView bookeoView = new BookeoView();
    LoginView loginVista = new LoginView();

    public MenuClienteController(MenuClienteView vista) {
        this.vista = vista;
        this.vista.btnVerTiquete.addActionListener(this);
    }

    public void iniciar(){
        
    }
 
    @Override
    public void actionPerformed(ActionEvent e) {
       
        if (e.getSource() == vista.btnVerTiquete){
           controlTiqute = new MenuClienteTiqueteController (vistaTiquete);
           controlTiqute.iniciar();
           vistaTiquete.setVisible(true);
           vista.dispose();
        }
    }
    
}
