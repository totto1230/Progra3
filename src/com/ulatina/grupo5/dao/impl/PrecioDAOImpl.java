package com.ulatina.grupo5.dao.impl;

import com.ulatina.grupo5.dao.BaseDAO;
import com.ulatina.grupo5.helpers.Conexion;
import com.ulatina.grupo5.modelo.BookeoPersona;
import com.ulatina.grupo5.modelo.Precio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PrecioDAOImpl implements BaseDAO {

    Conexion conectar = new Conexion();

    PreparedStatement ps;
    ResultSet rs;
    Connection con;

    Precio p = new Precio();

    @Override
    public Boolean insertar(Object obj) {

        p = (Precio) obj;

        String sql = "INSERT INTO Precio (idPrecio, descripcion, precio, activoBIT) VALUES (?, ?, ?, ?)";
        try {

            conectar.connectar();

            con = conectar.getConnection();
            ps = con.prepareStatement(sql);

            ps.setInt(1, p.getIdPrecio());
            ps.setString(2, p.getDescripcion());
            ps.setInt(3, p.getPrecio());
            ps.setBoolean(4, p.isActivoBIT());

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

        p = (Precio) obj;

        String sql = "UPDATE SET Precio descripcion = ?, precio = ?, activoBIT = ? WHERE idPrecio = ?";
        try {

            conectar.connectar();

            con = conectar.getConnection();
            ps = con.prepareStatement(sql);

            ps.setInt(1, p.getIdPrecio());
            ps.setString(2, p.getDescripcion());
            ps.setInt(3, p.getPrecio());
            ps.setBoolean(4, p.isActivoBIT());

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
    public Object[] listarPor(Object obj) {
        p = (Precio) obj;
        ArrayList<Precio> Precio = new ArrayList<Precio>();
        String sql = "select  descripcion = ?, precio = ?, activoBIT = ? FROM Precio WHERE idPrecio = ?";
        try {

            conectar.connectar();
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, p.idPrecio);

            while (rs.next()) {
                Precio precio = new Precio();
                precio.setIdPrecio(Integer.parseInt(rs.getString("orderId")));
                precio.setDescripcion(rs.getString("descripcion"));
                precio.setPrecio(Integer.parseInt(rs.getString("precio")));
                precio.setActivoBIT(rs.getBoolean("activoBIT"));
                Precio.add(precio);
            }
            con.close();

        } catch (Exception ex) {
            System.out.println("Error");
        }
        return (Precio[]) Precio.toArray();
    }

    @Override
    public Boolean eliminar(Object obj) {

        p = (Precio) obj;

        String sql = "DELETE FROM Precio WHERE idPrecio = ?";

        try {

            conectar.connectar();
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);

            ps.setInt(1, p.getIdPrecio());

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
        return null;
    }

    @Override
    public void listar(JTable table) {

        String[] titulos = {"ID de Precio", "Descripción", "Precio", "Activo"};
        String[] registros = new String[titulos.length];
        DefaultTableModel model = new DefaultTableModel(null, titulos);

        String sql = "SELECT idPrecio, descripcion, precio, activo FROM Precio";

        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                registros[0] = rs.getString("idPrecio");
                registros[1] = rs.getString("descripcion");
                registros[2] = rs.getString("precio");
                registros[3] = rs.getString("activo");
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
        String sql = "SELECT idPrecio, descripcion, precio, activo FROM Precio where idPrecio = ?";
        try {

            conectar.connectar();
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            while (rs.next()) {
                p.setIdPrecio(Integer.parseInt(rs.getString("idPrecio")));
                p.setDescripcion(rs.getString("descripcion"));
                p.setActivoBIT(rs.getBoolean("activo"));
                p.setPrecio(Integer.parseInt(rs.getString("precio")));
            }
            con.close();

        } catch (Exception ex) {
            System.out.println("Error");
        }
        return p;
    }

    @Override
    public int nextID() {
        String sql = "select COALESCE(max(idPrecio),0) + 1 as nextCode from Precio";
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
