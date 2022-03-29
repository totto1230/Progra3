package com.ulatina.grupo5.dao.impl;

import com.ulatina.grupo5.dao.BaseDAO;
import com.ulatina.grupo5.helpers.Conexion;
import com.ulatina.grupo5.modelo.BookeoPersona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class BookeoPersonaDAOImpl implements BaseDAO{

    Conexion conectar = new Conexion();

    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    
    BookeoPersona b = new BookeoPersona ();

    @Override
    public Boolean insertar(Object obj) {

        b = (BookeoPersona) obj;
        String sql = "INSERT INTO bookeopersona (orderId,email,ticket) VALUES(?,?,?)";
        
        try {

            conectar.connectar();

            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setInt(1,b.getOrderId());
            ps.setString(2, b.getEmail());
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
        
        String sql = "UPDATE bookeopersona SET orderId = ?, email = ?, ticket = ? WHERE orderId = ?";
        try {
            
            conectar.connectar();
            
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);

            ps.setInt(1,b.getOrderId());
            ps.setString(2, b.getEmail());
            ps.setInt(3, b.getTicket());
            ps.setInt(4, b.getOrderId());
            
            
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
        
        b = (BookeoPersona) obj;
        
        String sql = "DELETE FROM BookeoPersona WHERE orderId = ?";
        
        try {
            
            conectar.connectar();
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setInt(1,b.getOrderId());
            
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
        
        String[] titulos = {"Orden", "E-Mail", "Ticket" };
        String[] registros = new String[titulos.length];
        DefaultTableModel model = new DefaultTableModel(null, titulos);
        
        String sql = "select * from BookeoPersona";
        
        try {
            
            con = conectar.getConnection();
            
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()){
            
                registros[0] = rs.getString("orderId"); 
                registros[1] = rs.getString("email");
                registros[2] = rs.getString("ticket");
                
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
