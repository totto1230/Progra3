/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ulatina.grupo5.controlador;

import com.ulatina.grupo5.dao.BaseDAO;
import com.ulatina.grupo5.dao.impl.AtraccionesDAOImpl;
import com.ulatina.grupo5.modelo.Atracciones;
import com.ulatina.grupo5.modelo.Usuarios;
import com.ulatina.grupo5.modelo.Precio;
import com.ulatina.grupo5.modelo.Bookeo;
import com.ulatina.grupo5.modelo.BookeoAtracciones;
import com.ulatina.grupo5.modelo.BookeoPersona;

import com.ulatina.grupo5.dao.impl.AtraccionesDAOImpl;
import com.ulatina.grupo5.dao.impl.BookeoAtraccionesDAOImpl;
import com.ulatina.grupo5.dao.impl.BookeoDAOImpl;
import com.ulatina.grupo5.dao.impl.BookeoPersonaDAOImpl;
import com.ulatina.grupo5.dao.impl.PrecioDAOImpl;
import com.ulatina.grupo5.dao.impl.UsuariosDAOImpl;

import com.ulatina.grupo5.vista.BookeoView;
import com.ulatina.grupo5.vista.MenuAdminView;
import com.ulatina.grupo5.vista.MenuClienteView;
import com.ulatina.grupo5.vista.MenuEmpleadoView;
import com.ulatina.grupo5.vista.UsuariosView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author fernando
 */
public class BookeoController implements ActionListener {

    BookeoView vista = new BookeoView();
    MenuAdminView main = new MenuAdminView();

    BaseDAO daoAtracciones = new AtraccionesDAOImpl();
    PrecioDAOImpl daoPrecios = new PrecioDAOImpl(); // para poder crear la subclase parametros
    BaseDAO daoBookeo = new BookeoDAOImpl();
    BaseDAO daoBookeoAtracciones = new BookeoAtraccionesDAOImpl();
    BaseDAO daoBookeoPersonas = new BookeoPersonaDAOImpl();
    BaseDAO daoUsuarios = new UsuariosDAOImpl();

    BaseController common = new BaseController();

    public BookeoController(BookeoView vista) {
        this.vista = vista;
        this.vista.btnAgregarAtraccion.addActionListener(this);
        this.vista.btnAtras.addActionListener(this);
        this.vista.btnCrearTickete.addActionListener(this);
        this.vista.btnUsuarios.addActionListener(this);
        this.vista.btnAgregarUsuarioAtraccion.addActionListener(this);
        this.vista.btnBuscar.addActionListener(this);
        this.vista.btnEliminarAtraccion.addActionListener(this);
        this.vista.btnEliminarUsuarioAtraccion.addActionListener(this);

        this.vista.chkPaseEspecial.addActionListener(this);
        iniciar();
    }

    public void iniciar() {
        vista.pnAtracciones.setVisible(false);
        vista.chkPaseEspecial.setSelected(true);
        loadComboBoxAtracciones();
        vista.txtUsuarioTickete.setText(LoginController.sessionUsr.email);
        vista.txtUsuarioTickete.setEnabled(false);
        Date date = new Date();
        vista.dtpFechaVisita.setDate(date);
    }

    private void loadComboBoxAtracciones() {
        Object[] atracciones = daoAtracciones.listarPor(true);
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        for (int i = 0; i < atracciones.length; i++) {
            model.addElement(new ComboItem(((Atracciones) atracciones[i]).getNombreAtraccion(), String.valueOf(((Atracciones) atracciones[i]).getIdAtracciones())));
        }

        this.vista.ddlAtracciones.setModel(model);

    }

    private void buscarUsuarios() {
        Usuarios usrSearch = new Usuarios(0, "", "", vista.txtUsuarioAtraccion.getText(), vista.txtUsuarioAtraccion.getText(), vista.txtUsuarioAtraccion.getText(), null, 0);
        Object[] usuarios = daoUsuarios.listarPor(usrSearch);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        String[] titulos = {"Cedula", "Nombre", "Apellidos", "Fecha Nacimiento"};
        String[] registros = new String[titulos.length];
        DefaultTableModel model = new DefaultTableModel(null, titulos);

        for (Object o : usuarios) {
            Usuarios usr = (Usuarios) o;
            registros[0] = usr.getCedula().toString();
            registros[1] = usr.getNombre();
            registros[2] = usr.getApellido1() + " " + usr.getAppellido2();
            registros[3] = dateFormat.format(usr.getFechaNacimiento());
            model.addRow(registros);
        }
        vista.tblUsuariosBusqueda.setModel(model);
    }

    private void insertarAtraccionJTable() {
        String[] titulos = {"Id de Atracción", "Nombre Atracción"};
        String[] registros = new String[titulos.length];
        DefaultTableModel model = new DefaultTableModel(null, titulos);
        if (vista.tblAtracciones.getRowCount() > 0) {
            model = (DefaultTableModel) vista.tblAtracciones.getModel();
        }
        ComboItem item = (ComboItem) this.vista.ddlAtracciones.getSelectedItem();
        registros[0] = item.getKey();
        registros[1] = item.getValue();
        model.addRow(registros);
        vista.tblAtracciones.setModel(model);
    }

    private void insertarUsuarioJTable() {
        if (vista.tblUsuariosBusqueda.getSelectedRow() != -1) {
            String[] titulos = {"Cedula", "Nombre", "Apellidos", "Fecha Nacimiento"};
            String[] registros = new String[titulos.length];
            DefaultTableModel model = new DefaultTableModel(null, titulos);
            if (vista.tblUsuarios.getRowCount() > 0) {
                model = (DefaultTableModel) vista.tblUsuarios.getModel();
            }
            int row = vista.tblUsuariosBusqueda.getSelectedRow();
            registros[0] = vista.tblUsuariosBusqueda.getModel().getValueAt(row, 0).toString();
            registros[1] = vista.tblUsuariosBusqueda.getModel().getValueAt(row, 1).toString();
            registros[2] = vista.tblUsuariosBusqueda.getModel().getValueAt(row, 2).toString();
            registros[3] = vista.tblUsuariosBusqueda.getModel().getValueAt(row, 3).toString();
            model.addRow(registros);
            vista.tblUsuarios.setModel(model);
        }

    }

    private void borrarLineaJTable(JTable table) {
        if (table.getSelectedRow() != -1) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.removeRow(table.getSelectedRow());

        }
    }

    private Boolean crearTickete() {
        Boolean rtn = false;
        int numeroTickete = daoBookeo.nextID();
        int cedulaUsuario = LoginController.sessionUsr.getCedula();
        Date fechaCompra = common.getCurrentDate();
        Date fechaVisita = vista.dtpFechaVisita.getDate();
        double totalVenta = 0;
        Boolean esPaseEspecial = vista.chkPaseEspecial.isSelected();
        Bookeo bookeo = new Bookeo(numeroTickete, cedulaUsuario, fechaCompra, fechaVisita, totalVenta, esPaseEspecial);
        rtn = daoBookeo.insertar(bookeo);
        if (rtn) {
            for (int i = 0; i < vista.tblUsuarios.getRowCount(); i++) {
                BookeoPersona bookeoPersona = new BookeoPersona();
                bookeoPersona.setOrderId(daoBookeoPersonas.nextID());
                bookeoPersona.setTicket(numeroTickete);
                bookeoPersona.setCedula(Integer.parseInt(vista.tblUsuarios.getModel().getValueAt(i, 0).toString()));
                rtn = daoBookeoPersonas.insertar(bookeoPersona);

                if (!rtn) {
                    break;
                }
            }
        }
        if (rtn && !esPaseEspecial) {
            for (int i = 0; i < vista.tblAtracciones.getRowCount(); i++) {
                BookeoAtracciones bookeoAtraccion = new BookeoAtracciones();
                bookeoAtraccion.setOrderId(daoBookeoAtracciones.nextID());
                bookeoAtraccion.setTicket(numeroTickete);
                bookeoAtraccion.setIdAtracciones(Integer.parseInt(vista.tblUsuarios.getModel().getValueAt(i, 0).toString()));
                rtn = daoBookeoAtracciones.insertar(bookeoAtraccion);
                if (rtn) {
                    totalVenta += calcularPrecioAtraccion(bookeoAtraccion);
                }

            }
        } else if (rtn) {
            BookeoAtracciones bookeoAtraccion = new BookeoAtracciones();
            bookeoAtraccion.setIdAtracciones(-1); //idAtraccion pase especial
            totalVenta += calcularPrecioAtraccion(bookeoAtraccion);
        } else if (!rtn) {
            return rtn;
        }
        bookeo.setTotalVenta(totalVenta);
        rtn = daoBookeo.actualizar(bookeo);
        return rtn;
    }

    private double calcularPrecioAtraccion(BookeoAtracciones bookeoAtraccion) {
        double rtn = 0.0;
        try {
            for (int i = 0; i < vista.tblUsuarios.getRowCount(); i++) {
                String str = vista.tblUsuarios.getModel().getValueAt(i, 3).toString();
                Date fechaCumpleaños = new SimpleDateFormat("dd/MM/yyyy").parse(str);
                Usuarios usr = new Usuarios(0, "", "", "", "", "", fechaCumpleaños, 0);
                PrecioDAOImpl.listarPorParametros parametros = daoPrecios.new listarPorParametros(bookeoAtraccion.idAtracciones, usr);
                Object[] precios = daoPrecios.listarPor(parametros);
                rtn += ((Precio) precios[0]).getPrecio();
            }
        } catch (Exception e) {
            rtn = -1;
        }
        return rtn;
    }

    public void volver() {
        Usuarios currentUser = LoginController.sessionUsr;
        switch (currentUser.getTipoUsuario()) {
            case 1:
                MenuAdminView vistaMenuAdmin = new MenuAdminView();
                Menu_AdminController controller = new Menu_AdminController(vistaMenuAdmin);
                vistaMenuAdmin.setVisible(true);
                vista.dispose();
                break;
            case 2:
                MenuEmpleadoView vistaMenuEmpleado = new MenuEmpleadoView();
                MenuEmpleadoController controllerEmpleado = new MenuEmpleadoController(vistaMenuEmpleado);
                vistaMenuEmpleado.setVisible(true);
                vista.dispose();
                break;
            case 3:
                MenuClienteView vistaCliente = new MenuClienteView();
                MenuClienteController controllerCliente = new MenuClienteController(vistaCliente);
                vistaCliente.setVisible(true);
                vista.dispose();
                break;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.btnAgregarAtraccion) {
            insertarAtraccionJTable();
        } else if (e.getSource() == vista.chkPaseEspecial) {
            vista.pnAtracciones.setVisible(!vista.chkPaseEspecial.isSelected());
        } else if (e.getSource() == vista.btnBuscar) {
            buscarUsuarios();
        } else if (e.getSource() == vista.btnUsuarios) {
            UsuariosView registroUsuariosVista = new UsuariosView();
            RegistrarUsuariosController registrarUsuarios = new RegistrarUsuariosController(registroUsuariosVista);
            registrarUsuarios.iniciar();
            registroUsuariosVista.setVisible(true);
        } else if (e.getSource() == vista.btnAgregarUsuarioAtraccion) {
            insertarUsuarioJTable();
        } else if (e.getSource() == vista.btnEliminarUsuarioAtraccion) {
            borrarLineaJTable(vista.tblUsuarios);
        } else if (e.getSource() == vista.btnEliminarAtraccion) {
            borrarLineaJTable(vista.tblAtracciones);
        } else if (e.getSource() == vista.btnCrearTickete) {
            crearTickete();
        } else if (e.getSource() == vista.btnAtras) {
            volver();
        }
    }

}
