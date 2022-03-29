/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ulatina.grupo5.helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Conexion {

    Connection cnn = null;
    Statement st = null;

    public Conexion() {

    }

    public void connectar() {
        try {
            Class.forName(Database.DRIVER);
            cnn = DriverManager.getConnection(
                    Database.URL,
                    Database.USER,
                    Database.PASS
            );

            st = cnn.createStatement();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "CONEXIÓN ERRONEA" + e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConnection() {
        return cnn;
    }

    public void desconexion() {
        try {
            cnn.close();
            System.exit(0);
        } catch (SQLException e) {
        }
    }

}
