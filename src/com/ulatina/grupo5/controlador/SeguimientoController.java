package com.ulatina.grupo5.controlador;

import com.ulatina.grupo5.dao.BaseDAO;
import com.ulatina.grupo5.dao.impl.MantenimientoDAOImpl;
import com.ulatina.grupo5.modelo.Atracciones;
import com.ulatina.grupo5.modelo.Mantenimiento;
import com.ulatina.grupo5.vista.Seguimiento_De_Atracciones_Admin;
import com.ulatina.grupo5.modelo.Usuarios;
import com.ulatina.grupo5.vista.Menu_Admin;
import com.ulatina.grupo5.vista.Menu_Empleado;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JOptionPane;

public class SeguimientoController implements ActionListener {

    Usuarios currentUser = LoginController.sessionUsr;
    Mantenimiento seguimiento = new Mantenimiento();
    Atracciones atraccion = new Atracciones();
    BaseDAO dao = new MantenimientoDAOImpl();

    Seguimiento_De_Atracciones_Admin vista = new Seguimiento_De_Atracciones_Admin();
    Menu_Admin vistaAdmin = new Menu_Admin();
    Menu_Empleado vistaEmpl = new Menu_Empleado();

    public SeguimientoController(Seguimiento_De_Atracciones_Admin vista) {
        this.vista = vista;
        this.vista.jButton_Agregar_Seguimiento_Atra_Admin.addActionListener(this);
        this.vista.jButton_volver_seguimiento_atracciones_admin.addActionListener(this);
        this.vista.btnActualizar.addActionListener(this);
    }

    public void iniciar() {
        vista.txtAtrac.setText("");
        vista.jTextField_Revisor_Segui_De_Atra_Admin.setText("");
        vista.jTextArea_Descrip_Error_Segui_Atra_Admi.setText("");
        vista.jTextArea_Solc_Comenta_Segui_De_Atrac_Admin.setText("");
    }

    public void iniciar(Mantenimiento seguimiento) {

        vista.txtAtrac.setText(seguimiento.getIdAtracciones().toString());
        vista.jTextField_Revisor_Segui_De_Atra_Admin.setText(seguimiento.getCedula().toString());
        vista.Date_Fecha_Revi_Seguimiento.setDate(seguimiento.getFechaRevision());
        vista.jCheckBox_Error.setText(seguimiento.getError().toString());
        vista.jTextArea_Descrip_Error_Segui_Atra_Admi.setText(seguimiento.getDescripcion());
        vista.jTextArea_Solc_Comenta_Segui_De_Atrac_Admin.setText(seguimiento.getSolucion());
        dao.actualizar(seguimiento);
    }

    public Mantenimiento devolverSeguimiento() {
        Integer idMantenimiento = dao.nextID();
        Integer idenAtrac = Integer.parseInt(vista.txtAtrac.getText());
        Integer cedula = Integer.parseInt(vista.jTextField_Revisor_Segui_De_Atra_Admin.getText());
        Date fechaRevi = vista.Date_Fecha_Revi_Seguimiento.getDate();
        Boolean error = vista.jCheckBox_Error.isSelected();
        String descripcion = vista.jTextArea_Descrip_Error_Segui_Atra_Admi.getText();
        String solucion = vista.jTextArea_Solc_Comenta_Segui_De_Atrac_Admin.getText();
        return seguimiento = new Mantenimiento(idMantenimiento, idenAtrac, cedula, fechaRevi, error, descripcion, solucion);
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
        if (e.getSource() == vista.jButton_Agregar_Seguimiento_Atra_Admin) {
            Mantenimiento seguemi = devolverSeguimiento();

            boolean resultado = dao.insertar(seguemi);

            if (resultado) {
                JOptionPane.showMessageDialog(vista, "Agregado Correctamente");
            } else {
                JOptionPane.showMessageDialog(vista, "Error al ingresar, por favor intente de nuevo");
            }
        } else if (e.getSource() == vista.jButton_volver_seguimiento_atracciones_admin) {
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
        } else if (e.getSource() == vista.btnActualizar) {
            seguimiento = devolverSeguimiento();
            boolean resultado = dao.actualizar(seguimiento);

            if (resultado) {
                JOptionPane.showMessageDialog(vista, "Actualizado Correctamente");
            } else {
                JOptionPane.showMessageDialog(vista, "Error al ingresar, por favor intente de nuevo");
            }
        }
    }

}
