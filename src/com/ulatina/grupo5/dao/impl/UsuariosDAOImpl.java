package com.ulatina.grupo5.dao.impl;

import com.ulatina.grupo5.dao.BaseDAO;
import com.ulatina.grupo5.helpers.Conexion;
import com.ulatina.grupo5.modelo.BookeoPersona;
import com.ulatina.grupo5.modelo.Usuarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class UsuariosDAOImpl implements BaseDAO {

    Conexion conectar = new Conexion();

    PreparedStatement ps;
    ResultSet rs;
    Connection con;

    Usuarios p = new Usuarios();

    @Override
    public Boolean insertar(Object obj) {

        p = (Usuarios) obj;

        String sql = "INSERT INTO usuarios (cedula, password, correo, nombre,apellido1,apellido2,tipoUsuario) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {

            conectar.connectar();

            con = conectar.getConnection();
            ps = con.prepareStatement(sql);

            ps.setInt(1, p.getCedula());
            ps.setString(2, p.getPassword());
            ps.setString(3, p.getCorreo());
            ps.setString(4, p.getNombre());
            ps.setString(5, p.getApellido1());
            ps.setString(5, p.getAppellido2());
            ps.setInt(7, p.getTipoUsuario());

            int registros = ps.executeUpdate();

            if (registros > 0) {
                con.close();
                return true;
            } else {
                con.close();
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Error");
            return false;
        }

    }

    @Override
    public Boolean actualizar(Object obj) {

        p = (Usuarios) obj;

        String sql = "UPDATE SET usuarios password = ?, correo = ? , nombre=?, apellido1=? , apellido2=?, tipoUsuario=? WHERE cedula = ?";
        try {

            conectar.connectar();

            con = conectar.getConnection();
            ps = con.prepareStatement(sql);

            ps.setInt(1, p.getCedula());
            ps.setString(2, p.getPassword());
            ps.setString(3, p.getCorreo());
            ps.setString(4, p.getNombre());
            ps.setString(5, p.getApellido1());
            ps.setString(5, p.getAppellido2());
            ps.setInt(7, p.getTipoUsuario());

            int registros = ps.executeUpdate();

            if (registros > 0) {
                con.close();
                return true;
            } else {
                con.close();
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Error");
            return false;
        }
    }

    @Override
    public Boolean eliminar(Object obj) {

        p = (Usuarios) obj;

        String sql = "DELETE FROM usuarios WHERE cedula = ?";

        try {

            conectar.connectar();
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);

            ps.setInt(1, p.getCedula());

            int registros = ps.executeUpdate();

            if (registros > 0) {
                con.close();
                return true;
            } else {
                con.close();
                return false;
            }

        } catch (SQLException e) {

            return false;
        }

    }

    @Override
    public Boolean eliminarTodos(Integer id) {
        String sql = "DELETE FROM usuarios WHERE cedula = ?";

        try {

            conectar.connectar();
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            int registros = ps.executeUpdate();

            if (registros > 0) {
                con.close();
                return true;
            } else {
                con.close();
                return false;
            }

        } catch (SQLException e) {

            return false;
        }
    }

    @Override
    public void listar(JTable table) {

        String[] users = {"cedula", "password", "correo", "nombre", "apellido1", "apellido2", "tipoUsuario"};
        String[] registros = new String[users.length];
        DefaultTableModel model = new DefaultTableModel(null, users);

        String sql = "select * from usuarios";

        try {

            con = conectar.getConnection();

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                registros[0] = rs.getString("cedula");
                registros[1] = rs.getString("password");
                registros[2] = rs.getString("correo");
                registros[3] = rs.getString("nombre");
                registros[4] = rs.getString("apellido1");
                registros[5] = rs.getString("apellido2");
                registros[6] = rs.getString("tipoUsuario");
                model.addRow(registros);

            }
            table.setModel(model);

            con.close();

        } catch (Exception ex) {
            System.out.println("Error");
        }

    }

    @Override
    public Object listarUno(Integer id) {
        String sql = "select cedula, correo, password,nombre, apellido1 , apellido2, tipoUsuario from usuarios where cedula = ?";
        try {

            conectar.connectar();
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            while (rs.next()) {
                
                p.setCedula(Integer.parseInt(rs.getString("Cedula")));
                p.setCorreo(rs.getString("correo"));
                p.setPassword(rs.getString("Password"));
                p.setNombre(rs.getString("Nombre"));
                p.setApellido1(rs.getString("Apellido1"));
                p.setAppellido2(rs.getString("Apellido2"));
                p.setTipoUsuario(Integer.parseInt(rs.getString("TipoUsuario")));
            }
            con.close();

        } catch (Exception ex) {
            System.out.println("Error");
        }
        return p;
    }

    @Override
    public Object[] listarPor(Object obj) {
        p = (Usuarios) obj;
        ArrayList<Usuarios> Usuarios = new ArrayList<Usuarios>();
        String sql = "select cedula, correo, password,nombre, apellido1,apellido2, tipoUsuario from usuarios where cedula = ?";
        try {

            conectar.connectar();
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, p.cedula);

            while (rs.next()) {
                Usuarios Usuario = new Usuarios();
                Usuario.setCedula(Integer.parseInt(rs.getString("Cedula")));
                Usuario.setCorreo(rs.getString("correo"));
                Usuario.setPassword(rs.getString("Password"));
                Usuario.setNombre(rs.getString("Nombre"));
                Usuario.setApellido1(rs.getString("Apellido1"));
                Usuario.setAppellido2(rs.getString("Apellido2"));
                Usuario.setTipoUsuario(Integer.parseInt(rs.getString("TipoUsuario")));
                Usuarios.add(Usuario);
            }
            con.close();

        } catch (Exception ex) {
            System.out.println("Error");
        }
        return (Usuarios[]) Usuarios.toArray();
    }

    @Override
    public int nextID() {
        String sql = "select COALESCE(max(idLogin),0) + 1 as nextCode from Login";
        Integer nextCode = 0;
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                nextCode = Integer.parseInt(rs.getString("nextCode"));
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error");
            return -1;
        }
        return nextCode;
    }

}
