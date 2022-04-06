package com.ulatina.grupo5.dao.impl;

import com.ulatina.grupo5.dao.BaseDAO;
import com.ulatina.grupo5.helpers.Conexion;
import com.ulatina.grupo5.modelo.Bookeo;
import com.ulatina.grupo5.modelo.BookeoPersona;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class BookeoPersonaDAOImpl implements BaseDAO {

    Conexion conectar = new Conexion();

    PreparedStatement ps;
    ResultSet rs;
    Connection con;

    BookeoPersona b = new BookeoPersona();

    @Override
    public Boolean insertar(Object obj) {

        b = (BookeoPersona) obj;
        String sql = "INSERT INTO bookeopersona (orderId,cedula,ticket) VALUES(?,?,?)";

        try {

            conectar.connectar();

            con = conectar.getConnection();
            ps = con.prepareStatement(sql);

            ps.setInt(1, b.getOrderId());
            ps.setInt(2, b.getCedula());
            ps.setInt(3, b.getTicket());

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

        b = (BookeoPersona) obj;

        String sql = "UPDATE bookeopersona SET cedula = ? WHERE orderId = ?";
        try {
            conectar.connectar();
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);

            ps.setInt(1, b.getCedula());
            ps.setInt(2, b.getOrderId());


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

        b = (BookeoPersona) obj;

        String sql = "DELETE FROM BookeoPersona WHERE orderId = ?";

        try {

            conectar.connectar();
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);

            ps.setInt(1, b.getOrderId());

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
        
        
        String sql = "DELETE FROM BookeoPersona WHERE ticket = ?";

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

        String[] titulos = {"Orden", "E-Mail", "Ticket"};
        String[] registros = new String[titulos.length];
        DefaultTableModel model = new DefaultTableModel(null, titulos);

        String sql = "select * from BookeoPersona";

        try {

            con = conectar.getConnection();

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                registros[0] = rs.getString("orderId");
                registros[1] = rs.getString("cedula");
                registros[2] = rs.getString("ticket");

                model.addRow(registros);

            }
            table.setModel(model);

            con.close();

        } catch (Exception ex) {
            System.out.println("Error");
        }

    }

    @Override
    public BookeoPersona listarUno(Integer id) {
        String sql = "select orderId, cedula, ticket from BookeoPersona where orderId = ?";
        try {

            conectar.connectar();
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                b.setOrderId(Integer.parseInt(rs.getString("orderId")));
                b.setCedula(rs.getInt("cedula"));
                b.setTicket(Integer.parseInt(rs.getString("ticket")));

            }
            con.close();

        } catch (Exception ex) {
            System.out.println("Error");
        }
        return b;

    }

    @Override
    public BookeoPersona[] listarPor(Object obj) {
        b = (BookeoPersona) obj;
        ArrayList<BookeoPersona> bookeoPersonas = new ArrayList<BookeoPersona>();
        String sql = "select orderId, cedula, ticket from BookeoPersona where ticket = ?";
        try {

            conectar.connectar();
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, b.ticket);
            rs = ps.executeQuery();
            while (rs.next()) {
                BookeoPersona bookeoPersona = new BookeoPersona();
                bookeoPersona.setOrderId(Integer.parseInt(rs.getString("orderId")));
                bookeoPersona.setCedula(rs.getInt("cedula"));
                bookeoPersona.setTicket(Integer.parseInt(rs.getString("ticket")));
                bookeoPersonas.add(bookeoPersona);
            }
            con.close();

        } catch (Exception ex) {
            System.out.println("Error");
        }
        return (BookeoPersona[]) bookeoPersonas.toArray();

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
