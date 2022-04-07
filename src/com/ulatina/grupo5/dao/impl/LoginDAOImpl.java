/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ulatina.grupo5.dao.impl;

import com.ulatina.grupo5.dao.BaseDAO;
import com.ulatina.grupo5.helpers.Conexion;
import com.ulatina.grupo5.modelo.Login;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author fernando
 */
public class LoginDAOImpl implements BaseDAO {

    Conexion conectar = new Conexion();

    PreparedStatement ps;
    ResultSet rs;
    Connection con;

    Login p = new Login();

    @Override
    public int nextID() {
        String sql = "select COALESCE(max(idLogin),0) + 1 as nextCode from Login";
        Integer nextCode = 0;
        try {
            conectar.connectar();
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

    @Override
    public Boolean insertar(Object obj) {
        p = (Login) obj;
        String sql = "INSERT INTO Login (idLogin,cedula,numTickets,fechaLogin,fechaLogoff) VALUES (?,?,?,?,?);";
        try {
            conectar.connectar();
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);

            ps.setInt(1, p.getIdLogin());
            ps.setInt(2, p.getNumTickets());
            ps.setInt(3, p.getCedula());
            ps.setDate(4, new java.sql.Date(p.getFechaLogin().getTime()));
            ps.setDate(5, new java.sql.Date(p.getFechaLogoff().getTime()));

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

        p = (Login) obj;

        String sql = "UPDATE Login SET numTickets = ?, numTickets = ?, fechaLogin= ?, fechaLogoff = ? WHERE id= ?";
        try {

            conectar.connectar();

            con = conectar.getConnection();
            ps = con.prepareStatement(sql);

            ps.setInt(1, p.getNumTickets());
            ps.setDate(2, new java.sql.Date(p.getFechaLogin().getTime()));
            ps.setDate(3, new java.sql.Date(p.getFechaLogoff().getTime()));
            ps.setInt(4, p.getIdLogin());

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

        p = (Login) obj;

        String sql = "DELETE FROM Login WHERE WHERE id= ?";

        try {

            conectar.connectar();
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);

            ps.setInt(1, p.getIdLogin());

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

        String[] titulos = {"ID", "cedula", "Ticketes", "Fecha Login", "Fecha Logogg"};
        String[] registros = new String[titulos.length];
        DefaultTableModel model = new DefaultTableModel(null, titulos);

        String sql = "SELECT idLogin, cedula, numTickets, fechaLogin, fechaLogoff FROM Login";

        try {
            conectar.connectar();
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                registros[1] = rs.getString("idLogin");
                registros[2] = rs.getString("cedula");
                registros[3] = rs.getString("numTickets");
                registros[4] = rs.getString("fechaLogin");
                registros[5] = rs.getString("fechaLogoff");
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
        return null;
    }

    @Override
    public Object[] listarPor(Object obj) {
        return null;
    }

}
