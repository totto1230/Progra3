package com.ulatina.grupo5.controlador;

import com.ulatina.grupo5.dao.BaseDAO;
import com.ulatina.grupo5.dao.impl.AtraccionesDAOImpl;
import com.ulatina.grupo5.dao.impl.MantenimientoDAOImpl;
import com.ulatina.grupo5.dao.impl.UsuariosDAOImpl;
import com.ulatina.grupo5.modelo.Atracciones;
import com.ulatina.grupo5.modelo.Mantenimiento;
import com.ulatina.grupo5.vista.MantenimientoView;
import com.ulatina.grupo5.modelo.Usuarios;
import com.ulatina.grupo5.vista.MenuAdminView;
import com.ulatina.grupo5.vista.MenuEmpleadoView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class SeguimientoController implements ActionListener {

    Usuarios currentUser = LoginController.sessionUsr;
    Mantenimiento seguimiento = new Mantenimiento();
    Atracciones atraccion = new Atracciones();
    BaseDAO dao = new MantenimientoDAOImpl();
    BaseDAO daoAtracciones = new AtraccionesDAOImpl();
    UsuariosDAOImpl daoUsuarios = new UsuariosDAOImpl();

    MantenimientoView vista = new MantenimientoView();
    MenuAdminView vistaAdmin = new MenuAdminView();
    MenuEmpleadoView vistaEmpl = new MenuEmpleadoView();

    public SeguimientoController(MantenimientoView vista) {
        this.vista = vista;
        this.vista.jButton_Agregar_Seguimiento_Atra_Admin.addActionListener(this);
        this.vista.jButton_volver_seguimiento_atracciones_admin.addActionListener(this);
        this.vista.btnActualizar.addActionListener(this);
        vista.Date_Fecha_Revi_Seguimiento.setMaxSelectableDate(new Date());
    }

    public void iniciar() {
        dao.listar(vista.jTable_Seguimiento_Atracciones);
        loadComboBoxAtracciones();
        loadComboBoxUsuarios();
        vista.Date_Fecha_Revi_Seguimiento.setDate(new Date());
        vista.jTextArea_Descrip_Error_Segui_Atra_Admi.setText("");
        vista.jTextArea_Solc_Comenta_Segui_De_Atrac_Admin.setText("");
        vista.btnActualizar.setVisible(false);
    }
    

    public void iniciar(Mantenimiento seguimiento) {
        dao.listar(vista.jTable_Seguimiento_Atracciones);
        ComboItem itemAtracciones = new ComboItem("", String.valueOf(seguimiento.idAtracciones));
        ComboItem itemUsuarios = new ComboItem("", String.valueOf(seguimiento.cedula));       
        selectComboBoxAtracciones(itemAtracciones);
        selectComboBoxUsuarios(itemUsuarios);
        vista.Date_Fecha_Revi_Seguimiento.setDate(seguimiento.getFechaRevision());
        vista.jCheckBox_Error.setText(seguimiento.getError().toString());
        vista.jTextArea_Descrip_Error_Segui_Atra_Admi.setText(seguimiento.getDescripcion());
        vista.jTextArea_Solc_Comenta_Segui_De_Atrac_Admin.setText(seguimiento.getSolucion());
        vista.btnActualizar.setVisible(true);
        vista.jButton_Agregar_Seguimiento_Atra_Admin.setVisible(false);
       
    }

    public Mantenimiento devolverSeguimiento() {
        Integer idMantenimiento = dao.nextID();
        Integer idenAtrac = Integer.parseInt(((ComboItem)vista.ddlAtracciones.getSelectedItem()).getValue());
        Integer cedula = Integer.parseInt(((ComboItem)vista.ddlUsuario.getSelectedItem()).getValue());
        Date fechaRevi = vista.Date_Fecha_Revi_Seguimiento.getDate();
        Boolean error = vista.jCheckBox_Error.isSelected();
        String descripcion = vista.jTextArea_Descrip_Error_Segui_Atra_Admi.getText();
        String solucion = vista.jTextArea_Solc_Comenta_Segui_De_Atrac_Admin.getText();
        return seguimiento = new Mantenimiento(idMantenimiento, idenAtrac, cedula, fechaRevi, error, descripcion, solucion);
    }

    private void cargarMenuAdmin() {
        MenuAdminView vistaMenuAdmin = new MenuAdminView();
        Menu_AdminController controller = new Menu_AdminController(vistaMenuAdmin);
        vistaMenuAdmin.setVisible(true);
        vista.dispose();

    }

    private void cargarMenuEmpleado() {
        MenuEmpleadoView vistaMenuAdmin = new MenuEmpleadoView();
        MenuEmpleadoController controller = new MenuEmpleadoController(vistaMenuAdmin);
        vistaMenuAdmin.setVisible(true);
        vista.dispose();
    }

    private void loadComboBoxAtracciones() {
        Object[] atracciones = daoAtracciones.listarPor(true);
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        for (int i = 0; i < atracciones.length; i++) {
            model.addElement(new ComboItem(((Atracciones)atracciones[i]).getNombreAtraccion(), String.valueOf(((Atracciones)atracciones[i]).getIdAtracciones())));
        }

        this.vista.ddlAtracciones.setModel(model);
    }
    
    private void selectComboBoxAtracciones(ComboItem item)
    {
        for (int i = 0; i < vista.ddlAtracciones.getItemCount();  i++) {
            Object ddlItem = vista.ddlAtracciones.getItemAt(i);
            if (((ComboItem)ddlItem).getValue().equals(item.getValue())) {
                vista.ddlAtracciones.setSelectedIndex(i);
            }
        }
    }
    
    private void loadComboBoxUsuarios() {
        Object[] usuarios = daoUsuarios.listarPor();
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        for (int i = 0; i < usuarios.length; i++) {
            model.addElement(new ComboItem(((Usuarios)usuarios[i]).email, String.valueOf(((Usuarios)usuarios[i]).getCedula())));
        }

        this.vista.ddlUsuario.setModel(model);
    }
    
    private void selectComboBoxUsuarios(ComboItem item)
    {
        for (int i = 0; i < vista.ddlUsuario.getItemCount();  i++) {
            Object ddlItem = vista.ddlUsuario.getItemAt(i);
            if (((ComboItem)ddlItem).getValue().equals(item.getValue())) {
                vista.ddlUsuario.setSelectedIndex(i);
            }
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.jButton_Agregar_Seguimiento_Atra_Admin) {
            Mantenimiento seguemi = devolverSeguimiento();
            boolean resultado = dao.insertar(seguemi);
            if (resultado) {
                iniciar(seguemi);
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
