package com.ulatina.grupo5.dao.impl;

import com.ulatina.grupo5.dao.BaseDAO;
import com.ulatina.grupo5.helpers.Conexion;
import com.ulatina.grupo5.modelo.Precio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PrecioDAOImpl implements BaseDAO{

    Conexion conectar = new Conexion();

    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    
    Precio p = new Precio ();

    @Override
    public Boolean insertar(Object obj) {

        p = (Precio) obj;

        String sql = "INSERT INTO Precio (idPrecio, descripcion, precio, activoBIT) VALUES (?, ?, ?, ?)";
        try {

            conectar.connectar();

            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setInt(1,p.getIdPrecio());
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

            ps.setInt(1,p.getIdPrecio());
            ps.setString(2, p.getDescripcion());
            ps.setInt(3, p.getPrecio());
            ps.setBoolean(4, p.isActivoBIT());
            
            int registros = ps.executeUpdate();
            
            if(registros > 0){
                con.close();
                return true;
            }
            else {
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
        
        String sql = "DELETE FROM persona WHERE idPrecio = ?";
        
        try {
            
            conectar.connectar();
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setInt(1,p.getIdPrecio());
            
            int registros = ps.executeUpdate();
            
            if(registros > 0){
                con.close();
                return true;
            }
            else {
                con.close();
                return false;
            }
            
        } catch (SQLException e) {
            
            return false;
        }
       
    }
    
    @Override
    public void listar(JTable table) {
        
        String[] titulos = {"ID de Precio", "Descripción", "Precio" , "Activo"};
        String[] registros = new String[titulos.length];
        DefaultTableModel model = new DefaultTableModel(null, titulos);
        
        String sql = "select * from Atracciones";
        
        try {
            
            con = conectar.getConnection();
            
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()){
            
                registros[0] = rs.getString("ID de Precio"); 
                registros[1] = rs.getString("Descripción");
                registros[2] = rs.getString("Precio");
                registros[3] = rs.getString("Capacidad");
                registros[4] = rs.getString("Activo");
           
                model.addRow(registros);
    
            }
            table.setModel(model);
            
            con.close();
            
        }
        
        catch (Exception ex){
            System.out.println("Error");
        }
        
    }
    
}
