package com.ulatina.grupo5.dao.impl;

import com.ulatina.grupo5.dao.BaseDAO;
import com.ulatina.grupo5.helpers.Conexion;
import com.ulatina.grupo5.modelo.Atracciones;
import com.ulatina.grupo5.modelo.Ganancias;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class GanaciasDAOImpl implements BaseDAO {

    Conexion conectar = new Conexion();

    PreparedStatement ps;
    ResultSet rs;
    Connection con;

    Ganancias p = new Ganancias();

    @Override
    public Object listarUno(Integer id) {
        return null;
    }

    @Override
    public Object[] listarPor(Object obj) {
        return null;
    }

    @Override
    public int nextID() {
        String sql = "select COALESCE(max(IdAtraccion),0) + 1 as nextCode from Ganancias";
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

    public Boolean insertar(Object obj) {

        p = (Ganancias) obj;

        String sql = "INSERT INTO Ganacias (numeroAtrac, nombreAtrac, recaudacionAtrac, fechaSelec, cantPersonas) VALUES (?, ?, ?, ?, ?)";
        try {

            conectar.connectar();

            con = conectar.getConnection();
            ps = con.prepareStatement(sql);

            ps.setInt(1, p.getNumeroAtrac());
            ps.setString(2, p.getNombreAtrac());
            ps.setInt(3, p.getRecaudacionAtrac());
            ps.setDate(4, p.getFechaSelec());
            ps.setInt(5, p.getCantPersonas());

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

        p = (Ganancias) obj;

        String sql = "UPDATE SET Ganancias nombreAtrac = ?, recaudacionAtrac = ?, fechaSelec = ?, cantPersonas = ? WHERE numeroAtrac = ?";
        try {

            conectar.connectar();

            con = conectar.getConnection();
            ps = con.prepareStatement(sql);

            ps.setInt(1, p.getNumeroAtrac());
            ps.setString(2, p.getNombreAtrac());
            ps.setInt(3, p.getRecaudacionAtrac());
            ps.setDate(4, p.getFechaSelec());
            ps.setInt(5, p.getCantPersonas());

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

        p = (Ganancias) obj;

        String sql = "DELETE FROM Ganancias WHERE numeroAtrac = ?";

        try {

            conectar.connectar();
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);

            ps.setInt(1, p.getNumeroAtrac());

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

        String[] titulos = {"numeroAtrac", "nombreAtrac ", "recaudacionAtrac", "fechaSelec", "cantPersonas"};
        String[] registros = new String[titulos.length];
        DefaultTableModel model = new DefaultTableModel(null, titulos);

        String sql = "select * from Ganacias";

        try {

            con = conectar.getConnection();

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                registros[0] = rs.getString("numeroAtrac");
                registros[1] = rs.getString("nombreAtrac");
                registros[2] = rs.getString("recaudacionAtrac");
                registros[3] = rs.getString("fechaSelec");
                registros[4] = rs.getString("cantPersonas");
                model.addRow(registros);

            }
            table.setModel(model);

            con.close();

        } catch (Exception ex) {
            System.out.println("Error");
        }

    }

}
