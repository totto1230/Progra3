package com.ulatina.grupo5.dao.impl;

import com.ulatina.grupo5.dao.BaseDAO;
import com.ulatina.grupo5.helpers.Conexion;
import com.ulatina.grupo5.modelo.Atracciones;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class AtraccionesDAOImpl implements BaseDAO{

    Conexion conectar = new Conexion();

    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    
    Atracciones p = new Atracciones ();

    @Override
    public Boolean insertar(Object obj) {

        p = (Atracciones) obj;

        String sql = "INSERT INTO Atracciones (idAtracciones, nombreAtrac, fechaInsta, capacidad, seccion, edadMin, precioNormal,activo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {

            conectar.connectar();

            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setInt(1,p.getIdAtracciones());
            ps.setString(2, p.getNombreAtrac());
            ps.setDate(3, p.getFechaInsta());
            ps.setInt(4, p.getCapacidad());
            ps.setString(5, p.getSeccion());
            ps.setInt(6, p.getEdadMin());
            ps.setFloat(7, p.getPrecioNormal());
            ps.setBoolean(8, p.getActivo());
            
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
        
        p = (Atracciones) obj;
        
        String sql = "UPDATE SET Atracciones nombreAtrac = ?, fechaInsta = ?, capacidad = ?, seccion = ?, edadMin = ?, precioNormal = ?, activo = ? WHERE idAtracciones = ?";
        try {
            
            conectar.connectar();
            
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);

            ps.setInt(1,p.getIdAtracciones());
            ps.setString(2, p.getNombreAtrac());
            ps.setDate(3, p.getFechaInsta());
            ps.setInt(4, p.getCapacidad());
            ps.setString(5, p.getSeccion());
            ps.setInt(6, p.getEdadMin());
            ps.setFloat(7, p.getPrecioNormal());
            ps.setBoolean(8, p.getActivo());
            
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
        
        p = (Atracciones) obj;
        
        String sql = "DELETE FROM persona WHERE idAtracciones = ?";
        
        try {
            
            conectar.connectar();
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setInt(1,p.getIdAtracciones());
            
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
        
        String[] titulos = {"Id de Atracción", "Nombre Atracción", "Fecha de Instalación" , "Capacidad" , "Sección", "Edad Mínima" , "Precio Normal", "Activo"};
        String[] registros = new String[titulos.length];
        DefaultTableModel model = new DefaultTableModel(null, titulos);
        
        String sql = "select * from Atracciones";
        
        try {
            
            con = conectar.getConnection();
            
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()){
            
                registros[0] = rs.getString("Id de Atracción"); 
                registros[1] = rs.getString("Nombre Atracción");
                registros[2] = rs.getString("Fecha de Instalación");
                registros[3] = rs.getString("Capacidad");
                registros[4] = rs.getString("Sección");
                registros[5] = rs.getString("Edad Mínima");
                registros[6] = rs.getString("Precio Normal");
                registros[7] = rs.getString("Activo");
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
