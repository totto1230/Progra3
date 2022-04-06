package com.ulatina.grupo5.controlador;

import com.ulatina.grupo5.dao.BaseDAO;
import com.ulatina.grupo5.dao.impl.PrecioDAOImpl;
import com.ulatina.grupo5.modelo.Precio;
import com.ulatina.grupo5.modelo.Usuarios;
import com.ulatina.grupo5.vista.Menu_Admin;
import com.ulatina.grupo5.vista.Menu_Empleado;
import com.ulatina.grupo5.vista.PrecioView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class PrecioController implements ActionListener {

    Usuarios currentUser = LoginController.sessionUsr;
    BaseDAO dao = new PrecioDAOImpl();
    PrecioView vista = new PrecioView();
    Precio precios = new Precio();

    Menu_AdminController menuAdminCtrl;
    Menu_Admin menuVistaAdmin = new Menu_Admin();
    Menu_Empleado menuVistaEmpleado = new Menu_Empleado();

    public PrecioController(PrecioView vista) {
        this.vista = vista;
        this.vista.btnActualizarPrecio.addActionListener(this);
        this.vista.btnAgregarPrecio.addActionListener(this);
        this.vista.btnBackPrecios.addActionListener(this);
    }

    public void iniciar() {
        dao.listar(vista.tblPrecios);
        vista.txtPrecio.setText("");
        vista.txtIdPrecio.setText("");
        //vista.txtIdAtrac.setText("");
        vista.txtDescrip.setText("");
    }

    public Precio devolverPrecio() {
        Integer idPrecio = Integer.parseInt(vista.txtIdPrecio.getText());
        Integer idAtraccion = vista.ddlAtracciones.getSelectedIndex();
        String descripcion = vista.txtDescrip.getText();
        Integer precio = Integer.parseInt(vista.txtPrecio.getText());
        boolean activo = vista.chboxActivo.isSelected();
        Integer edadMin = vista.ddlEdadMinPrecio.getSelectedIndex();
        Integer edadMax = vista.ddlEdadMaxPrecio.getSelectedIndex();

        return precios = new Precio(idPrecio, idAtraccion, descripcion, precio, activo, edadMin, edadMax);
    }

    private void cargarMenuAdmin() {
        Menu_Admin vistaMenuAdmin = new Menu_Admin();
        Menu_AdminController controller = new Menu_AdminController(vistaMenuAdmin);
        vistaMenuAdmin.setVisible(true);
        vista.dispose();

    }

    private void cargarMenuEmpleado() {
        Menu_Empleado vistaMenuAdmin = new Menu_Empleado();
        MenuEmpleadoController controller = new MenuEmpleadoController(vistaMenuAdmin);
        vistaMenuAdmin.setVisible(true);
        vista.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnAgregarPrecio) {
            precios = devolverPrecio();

            boolean resultado = dao.insertar(precios);

            if (resultado) {
                JOptionPane.showMessageDialog(vista, "Agregado Correctamente");
            } else {
                JOptionPane.showMessageDialog(vista, "Error al ingresar, por favor intente de nuevo");
            }
        } else if (e.getSource() == vista.btnActualizarPrecio) {
            precios = devolverPrecio();

            boolean resultado = dao.insertar(precios);

            if (resultado) {
                JOptionPane.showMessageDialog(vista, "Actualizado Correctamente");
            } else {
                JOptionPane.showMessageDialog(vista, "Error al ingresar, por favor intente de nuevo");
            }
        } else if (e.getSource() == vista.btnBackPrecios) {
            switch (currentUser.getTipoUsuario()) {
                case 1:
                    cargarMenuAdmin();
                    break;
                case 2:
                    cargarMenuEmpleado();
                    break;
                default:
                    cargarMenuEmpleado();
                    break;
            }
        }

    }
}
