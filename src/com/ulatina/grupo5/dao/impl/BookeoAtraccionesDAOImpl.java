/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ulatina.grupo5.dao.impl;

import com.ulatina.grupo5.dao.BaseDAO;
import com.ulatina.grupo5.helpers.Conexion;
import com.ulatina.grupo5.modelo.BookeoAtracciones;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author fernando
 */
public class BookeoAtraccionesDAOImpl implements BaseDAO {

    Conexion conectar = new Conexion();

    PreparedStatement ps;
    ResultSet rs;
    Connection con;

    BookeoAtracciones p = new BookeoAtracciones();

    @Override
    public Boolean insertar(Object obj) {

        p = (BookeoAtracciones) obj;

        String sql = "INSERT INTO BookeoAtracciones (orderId,ticket,idAtracciones) VALUES(?,?,?)";
        try {

            conectar.connectar();

            con = conectar.getConnection();
            ps = con.prepareStatement(sql);

            ps.setInt(1, p.getOrderId());
            ps.setInt(2, p.getTicket());
            ps.setInt(3, p.getIdAtracciones());

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

        p = (BookeoAtracciones) obj;

        String sql = "UPDATE BookeoAtracciones SET ticket = ?, idAtracciones = ? WHERE orderId = ?";
        try {

            conectar.connectar();

            con = conectar.getConnection();
            ps = con.prepareStatement(sql);

            ps.setInt(1, p.getTicket());
            ps.setInt(2, p.getIdAtracciones());
            ps.setInt(3, p.getOrderId());

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
        p = (BookeoAtracciones) obj;
        String sql = "DELETE FROM BookeoAtracciones WHERE orderId = ?";
        try {
            conectar.connectar();
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);

            ps.setInt(1, p.getOrderId());

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
        String sql = "DELETE FROM BookeoAtracciones WHERE ticket = ?";

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

        String[] titulos = {"Orden", "Ticket", "idAtracciones"};
        String[] registros = new String[titulos.length];
        DefaultTableModel model = new DefaultTableModel(null, titulos);

        String sql = "SELECT orderId,ticket,idAtracciones FROM BookeoAtracciones";

        try {

            con = conectar.getConnection();

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                registros[1] = rs.getString("orderId");
                registros[2] = rs.getString("ticket");
                registros[3] = rs.getString("idenAtrac");
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
        String sql = "SELECT orderId,ticket,idAtracciones FROM BookeoAtracciones where orderId = ?";
        try {

            conectar.connectar();
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                p.setOrderId(Integer.parseInt(rs.getString("orderId")));
                p.setTicket(rs.getInt("ticket"));
                p.setIdAtracciones(Integer.parseInt(rs.getString("idAtracciones")));

            }
            con.close();

        } catch (Exception ex) {
            System.out.println("Error");
        }
        return p;
    }

    @Override
    public BookeoAtracciones[] listarPor(Object obj) {
        String sql = "SELECT orderId,ticket,idAtracciones FROM BookeoAtracciones where ticket = ?";
        p = (BookeoAtracciones) obj;
        ArrayList<BookeoAtracciones> bookeoAtracciones = new ArrayList<>();
        try {
            conectar.connectar();
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, p.ticket);
            rs = ps.executeQuery();
            while (rs.next()) {
                BookeoAtracciones bookeoAtraccion = new BookeoAtracciones(); 
                p.setOrderId(Integer.parseInt(rs.getString("orderId")));
                p.setTicket(rs.getInt("ticket"));
                p.setIdAtracciones(Integer.parseInt(rs.getString("idAtracciones")));
                bookeoAtracciones.add(bookeoAtraccion);
            }
            con.close();

        } catch (Exception ex) {
            System.out.println("Error");
        }
        return (BookeoAtracciones[]) bookeoAtracciones.toArray();
    }

    @Override
    public int nextID() {
        String sql = "select COALESCE(max(orderId),0) + 1 as nextCode from BookeoAtracciones";
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
