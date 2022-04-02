/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ulatina.grupo5.controlador;

import com.ulatina.grupo5.modelo.Usuarios;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.Date;
/**
 *
 * @author fernando
 */
public class BaseController {
    public Integer CalcularEdad(Usuarios usr)
    {
        Date currentDate = getCurrentDate();
        
        Integer edad = Period.between(toLocalDate(currentDate), toLocalDate(currentDate)).getYears();
        return edad;
    }
    
    public Date getCurrentDate()
    {
        return new Date(System.currentTimeMillis());
    }
    
    public LocalDate toLocalDate(Date dateToConvert)
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy");
        int year = Integer.parseInt((new SimpleDateFormat("yyyy")).format(dateToConvert));
        int month = Integer.parseInt((new SimpleDateFormat("MM")).format(dateToConvert));
        int day = Integer.parseInt((new SimpleDateFormat("dd")).format(dateToConvert));
        return LocalDate.of(year,month, month);
    }
}
