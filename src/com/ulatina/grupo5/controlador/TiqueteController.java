package com.ulatina.grupo5.controlador;

import com.ulatina.grupo5.dao.BaseDAO;
import com.ulatina.grupo5.dao.impl.AtraccionesDAOImpl;
import com.ulatina.grupo5.dao.impl.BookeoAtraccionesDAOImpl;
import com.ulatina.grupo5.dao.impl.BookeoDAOImpl;
import com.ulatina.grupo5.dao.impl.BookeoPersonaDAOImpl;
import com.ulatina.grupo5.dao.impl.PrecioDAOImpl;
import com.ulatina.grupo5.dao.impl.UsuariosDAOImpl;
import com.ulatina.grupo5.modelo.Atracciones;
import com.ulatina.grupo5.modelo.Bookeo;
import com.ulatina.grupo5.modelo.BookeoAtracciones;
import com.ulatina.grupo5.modelo.BookeoPersona;
import com.ulatina.grupo5.modelo.Usuarios;
import com.ulatina.grupo5.vista.BookeoView;
import com.ulatina.grupo5.vista.BookeoListadoView;
import com.ulatina.grupo5.vista.MenuClienteTiqueteView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Integer.parseInt;
import javax.swing.table.DefaultTableModel;

public class TiqueteController implements ActionListener {

    BookeoListadoView vista = new BookeoListadoView();
    BookeoView main = new BookeoView();
    MenuClienteTiqueteView vistaTiquete = new MenuClienteTiqueteView();
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
    }

    public void iniciar(Bookeo bookeo) {
        this.bookeo = bookeo;
        int tiquete = bookeo.getTicket();
        Object persona = daoUsuarios.listarUno(bookeo.getCedula());

        String usuario = ((Usuarios) persona).getNombre() + " " + ((Usuarios) persona).getApellido1() + " " + ((Usuarios) persona).getAppellido2();

        String fechaVisita = bookeo.getFechaVisita().toString();
        boolean paseEspe = bookeo.isPaseEspecial();
        vista.lblTiquete.setText(String.valueOf(tiquete));
        vista.lblUsuario.setText(usuario);
        vista.chkPaseEspecial.setVisible(false);
        vista.lblFechaDeVisita.setText(fechaVisita);
        vista.chkPaseEspecial.setText(String.valueOf(paseEspe));
        if (!bookeo.isPaseEspecial()) {
            atracciones(bookeo);
            vista.pnAtracciones.setVisible(true);
        } else {
            vista.pnAtracciones.setVisible(false);
        }
        personas(bookeo);
    }

    public void personas(Bookeo bookeo) {

        BookeoPersona bookeoPersona = new BookeoPersona(bookeo.getTicket());
        Object[] personas = daoBookeoPersonas.listarPor(bookeoPersona);

        String[] titulos = {"Cedula", "Nombre", "Apellidos"};
        String[] registros = new String[titulos.length];
        DefaultTableModel model = new DefaultTableModel(null, titulos);

        for (Object o : personas) {
            BookeoPersona b = (BookeoPersona) o;
            Object persona = daoUsuarios.listarUno(b.getCedula());
            registros[0] = b.getCedula().toString();
            registros[1] = ((Usuarios) persona).getNombre();
            registros[2] = ((Usuarios) persona).getApellido1() + " " + ((Usuarios) persona).getAppellido2();
            model.addRow(registros);
        }
        vista.tblUsuario.setModel(model);
    }

    public void atracciones(Bookeo bookeo) {
        BookeoAtracciones bookeoAtraccion = new BookeoAtracciones(bookeo.getTicket());
        Object[] atracciones = daoBookeoAtracciones.listarPor(bookeoAtraccion);

        String[] titulos = {"Nombre Atraccion"};
        String[] registros = new String[titulos.length];
        DefaultTableModel model = new DefaultTableModel(null, titulos);

        for (Object o : atracciones) {
            BookeoAtracciones b = (BookeoAtracciones) o;
            Object atraccion = daoAtracciones.listarUno(b.getIdAtracciones());
            registros[0] = ((Atracciones) atraccion).getNombreAtraccion();
            model.addRow(registros);
        }
        vista.tblAtraccion.setModel(model);
    }

    public void volver() {
        BookeoView bookeoView = new BookeoView();
        BookeoController bookeoC = new BookeoController(bookeoView);
        bookeoC.iniciar();
        bookeoView.setVisible(true);
        vista.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnBackTiquetes) {
            volver();
        } else if (e.getSource() == vista.chkPaseEspecial) {
            vista.tblAtraccion.setVisible(true);
        } 
        else if (e.getSource() == vistaTiquete.btnBuscar) {
            bookeo.setTicket(parseInt(vistaTiquete.txtTicket.getText()));
        }else {
            vista.tblAtraccion.setVisible(false);
        }

    }

}
