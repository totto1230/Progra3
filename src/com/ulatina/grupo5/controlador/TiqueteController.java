package com.ulatina.grupo5.controlador;

import com.ulatina.grupo5.dao.BaseDAO;
import com.ulatina.grupo5.dao.impl.AtraccionesDAOImpl;
import com.ulatina.grupo5.dao.impl.BookeoAtraccionesDAOImpl;
import com.ulatina.grupo5.dao.impl.BookeoDAOImpl;
import com.ulatina.grupo5.dao.impl.BookeoPersonaDAOImpl;
import com.ulatina.grupo5.dao.impl.PrecioDAOImpl;
import com.ulatina.grupo5.dao.impl.UsuariosDAOImpl;
import com.ulatina.grupo5.modelo.Bookeo;
import com.ulatina.grupo5.modelo.BookeoAtracciones;
import com.ulatina.grupo5.modelo.BookeoPersona;
import com.ulatina.grupo5.modelo.Usuarios;
import com.ulatina.grupo5.vista.BookeoView;
import com.ulatina.grupo5.vista.MenuAdminView;
import com.ulatina.grupo5.vista.BookeoListadoView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TiqueteController implements ActionListener {

    BookeoListadoView vista = new BookeoListadoView();
    BookeoView main = new BookeoView();

    Bookeo bookeo = new Bookeo();

    BaseDAO daoAtracciones = new AtraccionesDAOImpl();
    PrecioDAOImpl daoPrecios = new PrecioDAOImpl(); // para poder crear la subclase parametros
    BaseDAO daoBookeo = new BookeoDAOImpl();
    BaseDAO daoBookeoAtracciones = new BookeoAtraccionesDAOImpl();
    BaseDAO daoBookeoPersonas = new BookeoPersonaDAOImpl();
    BaseDAO daoUsuarios = new UsuariosDAOImpl();

    public TiqueteController(BookeoListadoView vista) {
        this.vista = vista;
        this.vista.btnBackTiquetes.addActionListener(this);
        this.vista.chkPaseEspecial.addActionListener(this);
        iniciar(bookeo);
    }

    public void iniciar(Bookeo bookeo) {
        this.bookeo = bookeo;
        int tiquete = bookeo.getTicket();
        int usuario = bookeo.getCedula();
        String fechaVisita = bookeo.getFechaVisita().toString();
        boolean paseEspe = bookeo.isPaseEspecial();
        vista.lblTiquete.setText(String.valueOf(tiquete));
        vista.lblUsuario.setText(String.valueOf(usuario));
        vista.lblFechaDeVisita.setText(fechaVisita);
        vista.chkPaseEspecial.setText(String.valueOf(paseEspe));
        atracciones(bookeo);
        personas(bookeo);
    }
    
    public void personas(Bookeo bookeo) {
        Object[] personas = daoBookeoPersonas.listarPor(bookeo);
        
        String[] titulos = {"OrderId", "Cedula", "Ticket"};
        String[] registros = new String[titulos.length];
        DefaultTableModel model = new DefaultTableModel(null, titulos);

        for (Object o : personas) {
            BookeoPersona b = (BookeoPersona) o;
            registros[0] = b.getOrderId().toString();
            registros[1] = b.getCedula().toString();
            registros[2] = b.getTicket().toString();
            model.addRow(registros);
        }
        vista.tblUsuario.setModel(model);
    }

    public void atracciones(Bookeo bookeo) {
        Object[] atracciones = daoBookeoAtracciones.listarPor(bookeo);
        
        String[] titulos = {"OrderId", "Ticket", "idAtracciones"};
        String[] registros = new String[titulos.length];
        DefaultTableModel model = new DefaultTableModel(null, titulos);

        for (Object o : atracciones) {
            BookeoAtracciones b = (BookeoAtracciones) o;
            registros[0] = b.getOrderId().toString();
            registros[1] = b.getTicket().toString();
            registros[2] = b.getIdAtracciones().toString();
            model.addRow(registros);
        }
        vista.tblUsuario.setModel(model);
    }

    public void volver() {
        main.setVisible(true);
        vista.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnBackTiquetes) {
            volver();
        } else if (e.getSource() == vista.chkPaseEspecial) {
            vista.tblAtraccion.setVisible(true);
        }
        else{
            vista.tblAtraccion.setVisible(false);
        }

    }

}
