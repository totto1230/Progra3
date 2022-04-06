package com.ulatina.grupo5.dao.impl;

import com.ulatina.grupo5.dao.BaseDAO;
import com.ulatina.grupo5.helpers.Conexion;
import com.ulatina.grupo5.modelo.BookeoPersona;
import com.ulatina.grupo5.modelo.Precio;
import com.ulatina.grupo5.modelo.Usuarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.ulatina.grupo5.controlador.BaseController;

public class PrecioDAOImpl implements BaseDAO {

    Conexion conectar = new Conexion();

    PreparedStatement ps;
    ResultSet rs;
    Connection con;

    Precio p = new Precio();

    @Override
    public int nextID() {
        String sql = "select COALESCE(max(idPrecio),0) + 1 as nextCode from Precio";
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

        p = (Precio) obj;

        String sql = "INSERT INTO Precio (idPrecio,idAtraccion,descripcion,precio,activo,edadMin,edadMax) VALUES (?,?,?,?,?,?,?)";
        try {

            conectar.connectar();

            con = conectar.getConnection();
            ps = con.prepareStatement(sql);

            ps.setInt(7, p.getIdPrecio());
            ps.setInt(1, p.getIdAtraccion());
            ps.setString(2, p.getDescripcion());
            ps.setInt(3, p.getPrecio());
            ps.setBoolean(4, p.isActivo());
            ps.setInt(5, p.getEdadMin());
            ps.setInt(6, p.getEdadMax());

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

        String sql = "UPDATE Precio SET idAtraccion = ?, descripcion = ?, precio = ?, activo = ?, edadMin = ?, edadMax = ? WHERE idPrecio = ?";
        try {

            conectar.connectar();

            con = conectar.getConnection();
            ps = con.prepareStatement(sql);

            ps.setInt(1, p.getIdAtraccion());
            ps.setString(2, p.getDescripcion());
            ps.setInt(3, p.getPrecio());
            ps.setBoolean(4, p.isActivo());
            ps.setInt(5, p.getEdadMin());
            ps.setInt(6, p.getEdadMax());
            ps.setInt(7, p.getIdPrecio());

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

        String sql = "DELETE FROM Precio WHERE idAtraccion = ?";

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

        String[] titulos = {"ID de Precio", "idAtraccion", "Descripción", "Precio", "Activo", "Edad Minima", "Edad Maxima"};
        String[] registros = new String[titulos.length];
        DefaultTableModel model = new DefaultTableModel(null, titulos);

        String sql = "SELECT idPrecio, idAtraccion, descripcion, precio, activo, edadMin, edadMax FROM Precio";

        try {
            conectar.connectar();
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                registros[0] = rs.getString("idPrecio");
                registros[1] = rs.getString("idAtraccion");
                registros[2] = rs.getString("descripcion");
                registros[3] = rs.getString("precio");
                registros[4] = rs.getString("activo");
                registros[5] = rs.getString("edadMin");
                registros[6] = rs.getString("edadMax");

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
        String sql = "SELECT idPrecio, idAtraccion, descripcion, precio, activo, edadMin, edadMax FROM Precio where idPrecio = ?";
        try {

            conectar.connectar();
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                p.setIdPrecio(Integer.parseInt(rs.getString("idPrecio")));
                p.setIdAtraccion(rs.getInt("idAtraccion"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setPrecio(rs.getInt("precio"));
                p.setActivo(rs.getBoolean("activo"));
                p.setEdadMin(rs.getInt("edadMin"));
                p.setEdadMax(rs.getInt("edadMax"));
            }
            con.close();

        } catch (Exception ex) {
            System.out.println("Error");
        }
        return p;
    }

    @Override
    /**
     * Esta funcion retorna los precios por rango de edad de un cliente.
     * parametro es un objeto tipo Usuarios, retorna una arreglo de precios.
     */
    public Object[] listarPor(Object obj) {
        listarPorParametros parametros = (listarPorParametros) obj;
        ArrayList<Precio> Precio = new ArrayList<Precio>();
        String sql = "SELECT idPrecio, idAtraccion, descripcion, precio, activo, edadMin, edadMax FROM Precio where idAtraccion = ? and edadMin >= ? and edadMax < ?";
        try {
            BaseController common = new BaseController();
            conectar.connectar();
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            int edad = common.CalcularEdad(parametros.getUsuario());
            ps.setInt(1, parametros.getIdAtraccion());
            ps.setInt(1, edad);
            ps.setInt(2, edad);
            rs = ps.executeQuery();
            while (rs.next()) {
                Precio precio = new Precio();
                precio.setIdPrecio(Integer.parseInt(rs.getString("idPrecio")));
                precio.setIdAtraccion(rs.getInt("idAtraccion"));
                precio.setDescripcion(rs.getString("descripcion"));
                precio.setPrecio(rs.getInt("precio"));
                precio.setActivo(rs.getBoolean("activo"));
                precio.setEdadMin(rs.getInt("edadMin"));
                precio.setEdadMax(rs.getInt("edadMax"));
                Precio.add(precio);
            }
            con.close();

        } catch (Exception ex) {
            System.out.println("Error");
        }
        return (Precio[]) Precio.toArray();
    }

    public class listarPorParametros {

        Integer idAtraccion;
        Usuarios usuario;

        public listarPorParametros() {
        }

        public listarPorParametros(Integer idAtraccion, Usuarios usuario) {
            this.idAtraccion = idAtraccion;
            this.usuario = usuario;
        }

        public Integer getIdAtraccion() {
            return idAtraccion;
        }

        public void setIdAtraccion(Integer idAtraccion) {
            this.idAtraccion = idAtraccion;
        }

        public Usuarios getUsuario() {
            return usuario;
        }

        public void setUsuario(Usuarios usuario) {
            this.usuario = usuario;
        }

    }

}
