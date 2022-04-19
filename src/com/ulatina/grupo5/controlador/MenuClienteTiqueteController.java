package com.ulatina.grupo5.controlador;

import com.ulatina.grupo5.dao.impl.BookeoDAOImpl;
import com.ulatina.grupo5.modelo.Atracciones;
import com.ulatina.grupo5.modelo.Bookeo;
import com.ulatina.grupo5.vista.AtraccionesView;
import com.ulatina.grupo5.vista.BookeoView;
import com.ulatina.grupo5.vista.MenuClienteTiqueteView;
import com.ulatina.grupo5.vista.MenuClienteView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;

public class MenuClienteTiqueteController implements ActionListener {

    MenuClienteTiqueteView vista = new MenuClienteTiqueteView();
    MenuClienteController menuController;
    MenuClienteView vistaMenu = new MenuClienteView();
    BookeoDAOImpl dao = new BookeoDAOImpl();
    Bookeo bookeo;

    public MenuClienteTiqueteController(MenuClienteTiqueteView vista) {
        this.vista = vista;
        this.vista.btnVolver.addActionListener(this);
        this.vista.tblTiquetes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = vista.tblTiquetes.getSelectedRow();
                int idAtraccion = Integer.parseInt(vista.tblTiquetes.getModel().getValueAt(row, 0).toString());
                bookeo = (Bookeo) dao.listarUno(idAtraccion);
                BookeoView editarBookeoView = new BookeoView();
                BookeoController editarBookeoController = new BookeoController(editarBookeoView);
                editarBookeoController.iniciar();
                editarBookeoView.setVisible(true);
                vista.dispose();
            }
        });

    }

    public void iniciar() {
        listarTabla(vista.tblTiquetes);
    }

    public void listarTabla(JTable tabla) {
        dao.listar(tabla);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnVolver) {
            menuController = new MenuClienteController(vistaMenu);
            menuController.iniciar();
            vistaMenu.setVisible(true);
            vista.dispose();
        }
    }

}
