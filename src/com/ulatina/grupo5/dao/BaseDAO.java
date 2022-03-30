package com.ulatina.grupo5.dao;

import javax.swing.JTable;

public interface BaseDAO {

    public int nextID();
    
    public Boolean insertar(Object obj);

    public Boolean actualizar(Object obj);

    public Boolean eliminar(Object obj);
    
    public Boolean eliminarTodos(Integer id);

    public void listar(JTable table);
    
    public Object listarUno(Integer id);

    public Object[] listarPor(Object obj);
}
