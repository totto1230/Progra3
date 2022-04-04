/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ulatina.grupo5.controlador;

/**
 *
 * @author fernando
 */
public class ComboItem {
    /**
     * Se crea el metodo ComboItem donde se declaran variables
     * key, value
     */
    private String key;
    private String value;
    
    
    /**
     *Define un constructor que  permite asignar a la variable "key" la representación actual de un elemento señalado
     * y a la variable "value" el valor actual que tiene dicho elemento para luego ser usado dentro de un
     * combobox
     * @param key identificador del elemento
     * @param value valor actual del elemento
     */
    public ComboItem(String key, String value)
    {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString()
    {
        return key;
    }
/**
 * Permite conseguir la forma como se representara
 * un elemento para un combobox.
 * @return La representacion de un elemento en un combobox
 */
    public String getKey()
    {
        return key;
    }

   /**
    * Permite conseguir el valor actual que
    * tiene la variable "value".
    * @return el valor actual de un elemento para combobox
    */
    public String getValue()
    {
        return value;
    }
    
}
