package com.ulatina.grupo5.controlador;

import com.ulatina.grupo5.dao.BaseDAO;
import com.ulatina.grupo5.dao.impl.AtraccionesDAOImpl;
import com.ulatina.grupo5.dao.impl.PrecioDAOImpl;
import com.ulatina.grupo5.modelo.Atracciones;
import com.ulatina.grupo5.modelo.Precio;
import com.ulatina.grupo5.modelo.Usuarios;
import com.ulatina.grupo5.vista.MenuAdminView;
import com.ulatina.grupo5.vista.MenuEmpleadoView;
import com.ulatina.grupo5.vista.PrecioView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class PrecioController implements ActionListener {

    Usuarios currentUser = LoginController.sessionUsr;
    BaseDAO dao = new PrecioDAOImpl();
    BaseDAO daoAtracciones = new AtraccionesDAOImpl();

    PrecioView vista = new PrecioView();
    Precio precios = new Precio();

    Menu_AdminController menuAdminCtrl;
    MenuAdminView menuVistaAdmin = new MenuAdminView();
    MenuEmpleadoView menuVistaEmpleado = new MenuEmpleadoView();

    public PrecioController(PrecioView vista) {
        this.vista = vista;
        this.vista.btnActualizarPrecio.addActionListener(this);
        this.vista.btnAgregarPrecio.addActionListener(this);
        this.vista.btnBackPrecios.addActionListener(this);

        this.vista.tblPrecios.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = vista.tblPrecios.getSelectedRow();
                int idPrecio = Integer.parseInt(vista.tblPrecios.getModel().getValueAt(row, 0).toString());
                precios = (Precio) dao.listarUno(idPrecio);
                iniciar(precios);
            }
        });

    }

    public void iniciar() {
        loadComboBoxAtracciones();
        dao.listar(vista.tblPrecios);
        vista.txtPrecio.setText("");
        vista.txtIdPrecio.setText("");
        //vista.txtIdAtrac.setText("");
        vista.txtDescrip.setText("");
    }

    public void iniciar(Precio precio) {
        dao.listar(vista.tblPrecios);
        vista.txtIdPrecio.setText(precio.getIdPrecio().toString());
        vista.txtIdPrecio.setEnabled(false);

        vista.txtPrecio.setText("");
        vista.txtIdPrecio.setText("");
        //vista.txtIdAtrac.setText("");
        vista.txtDescrip.setText("");
        loadComboBoxAtracciones();
    }

    public Precio devolverPrecio() {
        Integer idPrecio = Integer.parseInt(vista.txtIdPrecio.getText());
        Integer idAtraccion = vista.ddlAtracciones.getSelectedIndex();
        String descripcion = vista.txtDescrip.getText();
        Integer precio = Integer.parseInt(vista.txtPrecio.getText());
        boolean activo = vista.chboxActivo.isSelected();
        Integer edadMin = getEdadMinima(vista.ddlEdadMinPrecio.getSelectedIndex());
        Integer edadMax = getEdadMax(vista.ddlEdadMaxPrecio.getSelectedIndex());

        return precios = new Precio(idPrecio, idAtraccion, descripcion, precio, activo, edadMin, edadMax);
    }

    private int getEdadMinima(int index) {
        int rtn = -1;
        switch (index) {
            case 0:
                rtn = 0;
                break;
            case 1:
                rtn = 3;
                break;
            case 2:
                rtn = 18;
                break;
        }
        return rtn;
    }

    private int getEdadMax(int index) {
        int rtn = -1;
        switch (index) {
            case 0:
                rtn = 3;
                break;
            case 1:
                rtn = 18;
                break;
            case 2:
                rtn = 120;
                break;
        }
        return rtn;
    }

    private int setEdadMinima(int edad) {
        int rtn = -1;
        switch (edad) {
            case 0:
                rtn = 0;
                break;
            case 3:
                rtn = 1;
                break;
            case 18:
                rtn = 2;
                break;
        }
        return rtn;
    }

    private int setEdadMax(int edad) {
        int rtn = -1;
        switch (edad) {
            case 3:
                rtn = 0;
                break;
            case 18:
                rtn = 1;
                break;
            case 120:
                rtn = 2;
                break;
        }
        return rtn;
    }

    private void loadComboBoxAtracciones() {
        Object[] atracciones = daoAtracciones.listarPor(true);
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        for (int i = 0; i < atracciones.length; i++) {
            model.addElement(new ComboItem(((Atracciones) atracciones[i]).getNombreAtraccion(), String.valueOf(((Atracciones) atracciones[i]).getIdAtracciones())));
        }
        this.vista.ddlAtracciones.setModel(model);
    }

    public void selectComboBox(int idTraccion) {
        for (int i = 0; i < this.vista.ddlAtracciones.getItemCount(); i++) {
            vista.ddlAtracciones.setSelectedIndex(i);
            ComboItem item = (ComboItem) vista.ddlAtracciones.getSelectedItem();
            if (item.getKey().equals(idTraccion)) {
                return;
            }
        }
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
