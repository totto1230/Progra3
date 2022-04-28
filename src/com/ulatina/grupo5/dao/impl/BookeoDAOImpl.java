package com.ulatina.grupo5.dao.impl;

import com.ulatina.grupo5.dao.BaseDAO;
import com.ulatina.grupo5.dao.BookeoListarPor;
import com.ulatina.grupo5.helpers.Conexion;
import com.ulatina.grupo5.modelo.Bookeo;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class BookeoDAOImpl implements BaseDAO {

    Conexion conectar = new Conexion();

    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    Bookeo p = new Bookeo();

    @Override
    public Boolean insertar(Object obj) {

        p = (Bookeo) obj;

        String sql = "INSERT INTO bookeo (ticket,cedula,fechaCompra,fechaVisita,totalVenta,paseEspecial)VALUES(?,?,?,?,?,?)";
        try {

            conectar.connectar();

            con = conectar.getConnection();
            ps = con.prepareStatement(sql);

            ps.setInt(1, p.getTicket());
            ps.setInt(2, p.getCedula());
            ps.setDate(3, new java.sql.Date(p.getFechaCompra().getTime()));
            ps.setDate(4, new java.sql.Date(p.getFechaVisita().getTime()));
            ps.setDouble(5, p.getTotalVenta());
            ps.setBoolean(6, p.isPaseEspecial());


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

        p = (Bookeo) obj;

        String sql = "UPDATE bookeo SET cedula = ?, fechaCompra = ?, fechaVisita = ?, totalVenta = ?, paseEspecial = ? WHERE ticket = ?";
        try {

            conectar.connectar();

            con = conectar.getConnection();
            ps = con.prepareStatement(sql);

            
            ps.setInt(1, p.getCedula());
            ps.setDate(2, new java.sql.Date(p.getFechaCompra().getTime()));
            ps.setDate(3, new java.sql.Date(p.getFechaVisita().getTime()));
            ps.setDouble(4, p.getTotalVenta());
            ps.setBoolean(5, p.isPaseEspecial());
            ps.setInt(6, p.getTicket());

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

        p = (Bookeo) obj;

        String sql = "DELETE FROM bookeo WHERE ticket = ?";

        try {

            conectar.connectar();
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);

            ps.setInt(1, p.getTicket());

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
        return false;
    }

    @Override
    public void listar(JTable table) {

        String[] titulos = {"Tiket", "E-Mail", "Fecha Compra", "Fecha Visita", "Total Venta", "Pase Especial"};
        String[] registros = new String[titulos.length];
        DefaultTableModel model = new DefaultTableModel(null, titulos);

        String sql = "select ticket, cedula, fechaCompra, fechaVisita, totalVenta, paseEspecial from bookeo";

        try {

            conectar.connectar();
            con = conectar.getConnection();

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                registros[1] = rs.getString("Tiket");
                registros[2] = rs.getString("cedula");
                registros[3] = rs.getString("fechaCompra");
                registros[4] = rs.getString("fechaVisita");
                registros[5] = rs.getString("totalVenta");
                registros[6] = rs.getString("paseEspecial");
                model.addRow(registros);

            }
            table.setModel(model);

            con.close();

        } catch (Exception ex) {
            System.out.println("Error");
        }

    }

    @Override
    public Bookeo listarUno(Integer id) {
        String sql = "select ticket, email, fechaCompra, fechaVisita, totalVenta, paseEspecial from bookeo where ticket = ?";
        try {

            conectar.connectar();
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, p.getTicket());
            rs = ps.executeQuery();
            while (rs.next()) {
                p.setTicket(Integer.parseInt(rs.getString("ticket")));
                p.setCedula(rs.getInt("cedula"));
                p.setFechaCompra(Date.valueOf(rs.getString("fechaCompra")));
                p.setFechaVisita(Date.valueOf(rs.getString("fechaVisita")));
                p.setTotalVenta(Double.parseDouble(rs.getString("totalVenta")));
                p.setPaseEspecial(Boolean.parseBoolean(rs.getString("paseEspecial")));
            }
            con.close();

        } catch (Exception ex) {
            System.out.println("Error");
        }
        return p;

    }

    @Override
    public Object[] listarPor(Object obj) {
        BookeoListarPor filtro = (BookeoListarPor)obj;
        ArrayList<Bookeo> bookeos = new ArrayList<Bookeo>();
        String sql = "";
        if(filtro.getTipoUsuario() == 3)
        {
            sql =   "set @filtro := ?;\n" +
                    "\n" +
                    "Select b.ticket, email, fechaCompra, fechaVisita, totalVenta, paseEspecial  from Bookeo b\n" +
                    "inner join BookeoPersona bp on\n" +
                    "b.ticket = bp.ticket\n" +
                    "where bp.cedula = ?\n" +
                    "and (case when @filtro = -1 then b.ticket > 1 else b.ticket = @filtro end)\n" +
                    "and fechaVisita between ? and ?";
        }
        else
        {
            sql =   "set @filtro := ?;\n" +
                    "\n" +
                    "Select b.ticket, email, fechaCompra, fechaVisita, totalVenta, paseEspecial  from Bookeo b\n" +
                    "where -1 != ?" +//esta condicion siempre se cumple, necesaria para tener un parametro en la misma posicion del usuario tipo 3
                    "and (case when @filtro = -1 then b.ticket > 1 else b.ticket = @filtro end)\n" +
                    "and fechaVisita between ? and ?";
        }
        try {

            conectar.connectar();
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, filtro.getTicket());// si el ticket es -1 trae todos los ticketes, si se indica envia solo el que se esta pidiendo
            ps.setInt(2, filtro.getCedula());
            ps.setDate(3, new java.sql.Date(filtro.getFechaDesde().getTime()));
            ps.setDate(4, new java.sql.Date(filtro.getFechaHasta().getTime()));
            rs = ps.executeQuery();
            while (rs.next()) {
                Bookeo bookeo = new Bookeo();
                bookeo.setTicket(Integer.parseInt(rs.getString("ticket")));
                bookeo.setCedula(rs.getInt("cedula"));
                bookeo.setFechaCompra(Date.valueOf(rs.getString("fechaCompra")));
                bookeo.setFechaVisita(Date.valueOf(rs.getString("fechaVisita")));
                bookeo.setTotalVenta(Double.parseDouble(rs.getString("totalVenta")));
                bookeo.setPaseEspecial(Boolean.parseBoolean(rs.getString("paseEspecial")));
                bookeos.add(bookeo);
            }
            con.close();

        } catch (Exception ex) {
            System.out.println("Error");
        }
        return (Object[]) bookeos.toArray();
    }

    @Override
    public int nextID() {
        String sql = "select COALESCE(max(ticket),0) + 1 as nextCode from bookeo";
        Integer nextCode = 0;
        try {
            conectar.connectar();
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                nextCode = rs.getInt("nextCode");
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error");
            return -1;
        }
        return nextCode;
    }
}
