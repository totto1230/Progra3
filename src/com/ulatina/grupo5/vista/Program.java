/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ulatina.grupo5.vista;

import com.ulatina.grupo5.controlador.LoginController;

/**
 *
 * @author fernando
 */
public class Program {

    public static void main(String args[]) {
        LoginView vistaLogin = new LoginView();
        LoginController login = new LoginController(vistaLogin);
        login.iniciar();
        vistaLogin.setVisible(true);
    }
}
