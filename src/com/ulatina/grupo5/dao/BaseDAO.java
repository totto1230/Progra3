package com.ulatina.grupo5.dao;

import javax.swing.JTable;

public interface BaseDAO {

    public Boolean insertar(Object obj);

    public Boolean actualizar(Object obj);

    public Boolean eliminar(Object obj);

    public void listar(JTable table);

}
