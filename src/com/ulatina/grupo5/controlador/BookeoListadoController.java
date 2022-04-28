
package com.ulatina.grupo5.controlador;

import static com.ulatina.grupo5.controlador.LoginController.sessionUsr;
import com.ulatina.grupo5.dao.BaseDAO;
import com.ulatina.grupo5.dao.BookeoListarPor;
import com.ulatina.grupo5.dao.impl.AtraccionesDAOImpl;
import com.ulatina.grupo5.dao.impl.BookeoAtraccionesDAOImpl;
import com.ulatina.grupo5.dao.impl.BookeoDAOImpl;
import com.ulatina.grupo5.dao.impl.BookeoPersonaDAOImpl;
import com.ulatina.grupo5.dao.impl.PrecioDAOImpl;
import com.ulatina.grupo5.dao.impl.UsuariosDAOImpl;
import com.ulatina.grupo5.modelo.Bookeo;
import com.ulatina.grupo5.modelo.Usuarios;
import com.ulatina.grupo5.vista.BookeoListadoView;
import com.ulatina.grupo5.vista.BookeoTicketeView;
import com.ulatina.grupo5.vista.BookeoView;
import com.ulatina.grupo5.vista.MenuAdminView;
import com.ulatina.grupo5.vista.MenuClienteView;
import com.ulatina.grupo5.vista.MenuEmpleadoView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Integer.parseInt;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

public class BookeoListadoController implements ActionListener{
    
    Usuarios currentUser = LoginController.sessionUsr;
    BookeoListadoView vistaTiquete = new BookeoListadoView();
    Bookeo bookeo = new Bookeo();

    BaseDAO daoAtracciones = new AtraccionesDAOImpl();
    PrecioDAOImpl daoPrecios = new PrecioDAOImpl(); // para poder crear la subclase parametros
    BaseDAO daoBookeo = new BookeoDAOImpl();
    BaseDAO daoBookeoAtracciones = new BookeoAtraccionesDAOImpl();
    BaseDAO daoBookeoPersonas = new BookeoPersonaDAOImpl();
    BaseDAO daoUsuarios = new UsuariosDAOImpl();
    Date fechaDesde= new Date(2022, 4, 28, 0, 0);
    Date fechaHasta= new Date(2022, 4, 29, 0, 0);
    
    public BookeoListadoController(BookeoListadoView vistaTiquete) {
        this.vistaTiquete= vistaTiquete;
        this.vistaTiquete.btnBuscar.addActionListener(this);
        this.vistaTiquete.btnVolver.addActionListener(this);
    }
    
    
    public void iniciar(){
        cargarTabla();
        this.vistaTiquete.dtpFechaDesde.setDate(fechaDesde);
        this.vistaTiquete.dtpFechaHasta.setDate(fechaHasta);
        
    }
     private void cargarTabla() {
        int ticket = -1;
        if (!vistaTiquete.txtTicket.getText().isBlank()) {
            ticket = parseInt(vistaTiquete.txtTicket.getText());
        }
      
        fechaDesde = vistaTiquete.dtpFechaDesde.getDate();
        fechaHasta= vistaTiquete.dtpFechaHasta.getDate();
       
        BookeoListarPor filtro = new BookeoListarPor(sessionUsr.getTipoUsuario(), ticket, sessionUsr.getCedula(), fechaDesde, fechaHasta);

        Object[] tickets = daoBookeo.listarPor(filtro);
        String[] titulos = {"ticket", "cedula", "fechaCompra", "fechaVisita", "totalVenta", "paseEspecial"};
        String[] registros = new String[titulos.length];
        DefaultTableModel model = new DefaultTableModel(null, titulos);

        for (Object o : tickets) {
            Bookeo bko = (Bookeo) o;
            registros[0] = bko.getTicket().toString();
            registros[1] = bko.getCedula().toString();
            registros[2] = bko.getFechaCompra().toString();
            registros[3] = bko.getFechaVisita().toString();
            registros[4] = String.valueOf(bko.getTotalVenta());
            model.addRow(registros);
        }
        vistaTiquete.tblTiquetes.setModel(model);

    }

     public void volver() {
        BookeoView bookeoView = new BookeoView();
        BookeoController bookeoC = new BookeoController(bookeoView);
        bookeoC.iniciar();
        bookeoView.setVisible(true);
        vistaTiquete.dispose();
    }
     
     private void cargarMenuAdmin() {
        MenuAdminView vistaMenuAdmin = new MenuAdminView();
        Menu_AdminController controller = new Menu_AdminController(vistaMenuAdmin);
        vistaMenuAdmin.setVisible(true);
        vistaTiquete.dispose();

    }

    private void cargarMenuEmpleado() {
        MenuEmpleadoView vistaMenuEmpleado = new MenuEmpleadoView();
        MenuEmpleadoController controller = new MenuEmpleadoController(vistaMenuEmpleado);
        vistaMenuEmpleado.setVisible(true);
        vistaTiquete.dispose();
    }
    
    private void cargarMenuCliente(){
        MenuClienteView vistaMenuCliente = new MenuClienteView();
        MenuClienteController controller = new MenuClienteController(vistaMenuCliente);
        vistaMenuCliente.setVisible(true);
        vistaTiquete.dispose();
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaTiquete.btnVolver) {
            switch (currentUser.getTipoUsuario()) {
                case 1:
                    cargarMenuAdmin();
                    break;
                case 2:
                    cargarMenuEmpleado();
                    break;
                case 3:
                    cargarMenuCliente();
                    break;
                default:
                    cargarMenuEmpleado();
                    break;
            }
      
        } else if (e.getSource() == vistaTiquete.btnBuscar) {
            cargarTabla();
    
    }
     
}
}
