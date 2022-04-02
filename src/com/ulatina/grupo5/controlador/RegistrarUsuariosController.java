package com.ulatina.grupo5.controlador;

import com.ulatina.grupo5.dao.BaseDAO;
import com.ulatina.grupo5.dao.impl.UsuariosDAOImpl;
import com.ulatina.grupo5.modelo.Usuarios;
import com.ulatina.grupo5.vista.Menu_Admin;
import com.ulatina.grupo5.vista.Registro_Usuarios_Admin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.JOptionPane;

class RegistrarUsuariosController implements ActionListener {

    BaseDAO dao = new UsuariosDAOImpl();
    Registro_Usuarios_Admin vista = new Registro_Usuarios_Admin();
    Usuarios user = new Usuarios();

    Menu_AdminController menuAdminCtrl;
    Menu_Admin menuVista = new Menu_Admin();

    public RegistrarUsuariosController(Registro_Usuarios_Admin vista) {
        this.vista = vista;
        this.vista.jButton_create_user.addActionListener(this);
        this.vista.jButton_previous_register.addActionListener(this);
        this.vista.btnActualizar.addActionListener(this);
    }

    public void iniciar() {
        vista.txtCed.setText("");
        vista.txtContra.setText("");
        vista.txtCorreo.setText("");
        vista.txtNombre.setText("");
        vista.txtPrimerApe.setText("");
        vista.txtSegundoApe.setText("");
    }

    public void iniciar(Usuarios user) {
        vista.txtCed.setText(user.getCedula().toString());
        vista.txtContra.setText(user.getPassword());
        vista.txtCorreo.setText(user.getEmail());
        vista.txtNombre.setText(user.getNombre());
        vista.txtPrimerApe.setText(user.getApellido1());
        vista.txtSegundoApe.setText(user.getAppellido2());
        dao.actualizar(user);
    }

    public Usuarios devolverUsers() {
        int cedula = Integer.parseInt(vista.txtCed.getText());
        String password = Arrays.toString(vista.txtContra.getPassword());
        String email = vista.txtCorreo.getText();
        String nombre = vista.txtNombre.getText();
        String apellido1 = vista.txtPrimerApe.getText();
        String appellido2 = vista.txtSegundoApe.getText();
        int tipoUsuario = vista.ddlTipoUser.getSelectedIndex() + 1;

        return user = new Usuarios(cedula, password, email, nombre, apellido1, appellido2, tipoUsuario);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.jButton_previous_register) {
            menuAdminCtrl = new Menu_AdminController(menuVista);
            menuVista.setVisible(true);
            vista.dispose();
        } else if (e.getSource() == vista.jButton_create_user) {

            user = devolverUsers();

            boolean resultado = dao.insertar(user);

            if (resultado) {
                JOptionPane.showMessageDialog(vista, "Agregado Correctamente");
            } else {
                JOptionPane.showMessageDialog(vista, "Error al ingresar, por favor intente de nuevo");
            }
        } else if (e.getSource() == vista.btnActualizar) {
            user = devolverUsers();

            boolean resultado = dao.actualizar(user);

            if (resultado) {
                JOptionPane.showMessageDialog(vista, "Actualizado Correctamente");
            } else {
                JOptionPane.showMessageDialog(vista, "Error al ingresar, por favor intente de nuevo");
            }
        }

    }
}
