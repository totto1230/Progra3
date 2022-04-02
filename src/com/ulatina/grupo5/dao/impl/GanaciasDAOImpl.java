package com.ulatina.grupo5.dao.impl;

import com.ulatina.grupo5.helpers.Conexion;
import com.ulatina.grupo5.modelo.Ganancias;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class GanaciasDAOImpl {

    Conexion conectar = new Conexion();

    PreparedStatement ps;
    ResultSet rs;
    Connection con;

    Ganancias p = new Ganancias();

    public void listar(JTable table, String seccion, boolean filtrar, Date dateFrom,Date dateTo) {

        String[] titulos = {"numeroAtrac", "nombreAtrac ", "recaudacionAtrac", "fechaSelec", "cantPersonas"};
        String[] registros = new String[titulos.length];
        DefaultTableModel model = new DefaultTableModel(null, titulos);

        String sql =    "set @prmseccion = ?; /*I, infantil A adulto Familiar*/\n" +
                        "set @prmFiltrar = ?;\n" +
                        "set @prmdatefrom = ?;\n" +
                        "set @prmdateto = ?;\n" +
                        "\n" +
                        "Select \n" +
                        "	0 orden,\n" +
                        "       a.idAtracciones,\n" +
                        "	a.nombreAtrac,\n" +
                        "	case a.seccion \n" +
                        "		when 'I' then 'Infantil' \n" +
                        "		when '' then 'Adulto'\n" +
                        "		Else 'Familiar' end as Seccion,\n" +
                        "	sum(a.precioNormal) TotalVenta, \n" +
                        "	count(bp.cedula) TotalPersoas \n" +
                        "from Atracciones a\n" +
                        "inner join BookeoAtracciones ba on\n" +
                        "a.idAtracciones = b.idAtracciones\n" +
                        "inner join Bookeo b on\n" +
                        "ba.ticket = b.ticket   \n" +
                        "inner join BookeoPersonas bp on\n" +
                        "bp.ticket = b.ticket   \n" +
                        "where (@prmseccion = 'T' or a.seccion = @prmseccion)\n" +
                        "and (@prmFiltrar = false or b.fechaVisita between @prmdatefrom and @prmdateto)\n" +
                        "Union \n" +
                        "Select 1 orden,0, 'Pases Especiales' nombreAtrac, 'Todas' Seccion, sum(b.totalVenta) TotalVenta, count(bp.cedula) TotalPersoas\n" +
                        "from Bookeo \n" +
                        "inner join BookeoPersonas bp on\n" +
                        "bp.ticket = b.ticket   \n" +
                        "where paseEspecial = true\n" +
                        "and (@prmFiltrar = false or b.fechaVisita between @prmdatefrom and @prmdateto)\n" +
                        "group by a.idAtracciones,a.nombreAtrac,Seccion\n" +
                        "order by orden,a.nombreAtrac";

        try {

            conectar.connectar();
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, seccion);
            ps.setBoolean(1, filtrar);
            ps.setDate(1, dateFrom);
            ps.setDate(1, dateTo);

            while (rs.next()) {
                registros[0] = rs.getString("orden");
                registros[1] = rs.getString("nombreAtrac");
                registros[2] = rs.getString("Seccion");
                registros[3] = rs.getString("TotalVenta");
                model.addRow(registros);
            }
            table.setModel(model);

            con.close();

        } catch (Exception ex) {
            System.out.println("Error");
        }

    }

}
