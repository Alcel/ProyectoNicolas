package com.example.proyectonicolas.dao;

import org.junit.jupiter.api.Test;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BrandDAODeleteTest {
    private final String servidor = "jdbc:mariadb://localhost:5555/noinch?useSSL=false";
    private final String usuario = "adminer";
    private final String passwd = "adminer";

    private Connection conexionBBDD;

    @Test
    void obtenerBrands() {
    }

    @Test
    void obtenerBrandsBusqueda() {

        //Datos a pasar (String cajaNum, String cajaNombre, String cajaFechaI, String cajaFechaF, int deportivo)

    }

    @Test
    void delete() throws SQLException {

        BrandDAO brand = new BrandDAO();
        String rsNum="";
        String rsNumAux="";
        int num=0;
        int numAux;
        //String cajaNombre, Float earnings, String cajaFecha, String headquarters ,String web
        //            ,int deportivo, String isin


        try {
            // Nos conectamos

            conexionBBDD = DriverManager.getConnection(servidor, usuario, passwd);
            String sql = "SELECT * FROM brands ORDER BY brandNumber DESC LIMIT 1";

            ResultSet resultadoConsulta = conexionBBDD.createStatement().executeQuery(sql);
            resultadoConsulta.next();
            rsNum=resultadoConsulta.getString(1);
            sql = "delete from brands order by brandNumber desc limit 1";
            conexionBBDD.createStatement().executeUpdate(sql);
            sql = "SELECT * FROM brands ORDER BY brandNumber DESC LIMIT 1";

            resultadoConsulta = conexionBBDD.createStatement().executeQuery(sql);
            resultadoConsulta.next();
            rsNumAux=resultadoConsulta.getString(1);
            num=Integer.parseInt(rsNumAux);
            numAux= Integer.parseInt(rsNum);
            conexionBBDD.close();

            assertEquals(numAux,num+1);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error:" + e.toString());
            try {
                conexionBBDD.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}