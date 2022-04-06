/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ulatina.grupo5.dao.impl;

import com.ulatina.grupo5.dao.BaseDAO;
import com.ulatina.grupo5.helpers.Conexion;
import com.ulatina.grupo5.modelo.Mantenimiento;
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
public class MantenimientoDAOImpl implements BaseDAO {

    Conexion conectar = new Conexion();

    PreparedStatement ps;
    ResultSet rs;
    Connection con;

    Mantenimiento p = new Mantenimiento();

    @Override
    public Boolean insertar(Object obj) {

        p = (Mantenimiento) obj;

        String sql = "INSERT INTO Mantenimiento (idMantenimiento,idAtracciones, cedula ,fechaRevision, error, descripcion, solucion) VALUES (?,?,?,?,?,?,?)";
        try {

            conectar.connectar();

            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setInt(1, p.getIdMantenimiento());
            ps.setInt(2, p.getIdAtracciones());
            ps.setInt(3, p.getCedula());
            ps.setDate(4, (Date)p.getFechaRevision());
            ps.setBoolean(5, p.getError());
            ps.setString(6, p.getDescripcion());
            ps.setString(7, p.getSolucion());

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

        p = (Mantenimiento) obj;

        String sql = "UPDATE Mantenimiento SET idAtracciones = ?, cedula = ?,fechaRevision = ?, error = ?, descripcion = ?, solucion = ? WHERE idMantenimiento = ?";
        try {

            conectar.connectar();

            con = conectar.getConnection();
            ps = con.prepareStatement(sql);

            ps.setInt(1, p.getIdAtracciones());
            ps.setDate(2, (Date)p.getFechaRevision());
            ps.setInt(3, p.getCedula());
            ps.setBoolean(4, p.getError());
            ps.setString(5, p.getDescripcion());
            ps.setString(6, p.getSolucion());
            ps.setInt(7, p.getIdMantenimiento());


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
        p = (Mantenimiento) obj;
        ArrayList<Mantenimiento> Precio = new ArrayList<Mantenimiento>();
        String sql = "select  descripcion = ?, precio = ?, activoBIT = ? FROM Precio WHERE idPrecio = ?";
        try {

            conectar.connectar();
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
//            ps.setInt(1, p.idPrecio);
            rs = ps.executeQuery();
            while (rs.next()) {
                Mantenimiento precio = new Mantenimiento();
//                precio.setIdPrecio(Integer.parseInt(rs.getString("orderId")));
//                precio.setDescripcion(rs.getString("descripcion"));
//                precio.setPrecio(Integer.parseInt(rs.getString("precio")));
//                precio.setActivoBIT(rs.getBoolean("activoBIT"));
                Precio.add(precio);
            }
            con.close();

        } catch (Exception ex) {
            System.out.println("Error");
        }
        return (Mantenimiento[]) Precio.toArray();
    }

    @Override
    public Boolean eliminar(Object obj) {

        p = (Mantenimiento) obj;

        String sql = "DELETE FROM Mantenimiento WHERE idMantenimiento = ?";

        try {

            conectar.connectar();
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);

            ps.setInt(1, p.getIdMantenimiento());

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

        String[] titulos = {"id","IdAtraccion", "Usuario", "Fecha Revision", "Error","Descripción","Solución"};
        String[] registros = new String[titulos.length];
        DefaultTableModel model = new DefaultTableModel(null, titulos);

        String sql = "SELECT idMantenimiento,idAtracciones, cedula ,fechaRevision, error, descripcion, solucion FROM Mantenimiento";

        try {
            conectar.connectar();
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                registros[0] = rs.getString("idMantenimiento");
                registros[1] = rs.getString("idAtracciones");
                registros[2] = rs.getString("cedula");
                registros[3] = rs.getString("fechaRevision");
                registros[4] = rs.getString("error");
                registros[5] = rs.getString("descripcion");
                registros[6] = rs.getString("solucion");
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
        String sql = "SELECT idMantenimiento, idAtracciones, cedula ,fechaRevision, error, descripcion, solucion FROM Mantenimiento where idMantenimiento = ?";
        try {

            conectar.connectar();
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                p.setIdMantenimiento(rs.getInt("idMantenimiento"));
                p.setIdAtracciones(rs.getInt("idAtracciones"));
                p.setCedula(rs.getInt("cedula"));
                p.setFechaRevision(rs.getDate("fechaRevision"));
                p.setError(rs.getBoolean("error"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setSolucion(rs.getString("solucion"));
            }
            con.close();

        } catch (Exception ex) {
            System.out.println("Error");
        }
        return p;
    }

    @Override
    public int nextID() {
        String sql = "select COALESCE(max(idMantenimiento),0) + 1 as nextCode from Mantenimiento";
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
}