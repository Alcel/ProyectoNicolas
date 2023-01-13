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
        String rsS="";
        //String cajaNombre, Float earnings, String cajaFecha, String headquarters ,String web
        //            ,int deportivo, String isin
        brand.insert("Homero", 180.25f, "2050-02-12", "Malaga", "No tiene", 0, "345");

        try {
            // Nos conectamos

            conexionBBDD = DriverManager.getConnection(servidor, usuario, passwd);

            String sql = "delete from brands" +
                    "order by brandNumber desc limit 1";



            ResultSet resultadoConsulta = conexionBBDD.createStatement().executeQuery(sql);


            conexionBBDD.close();
            resultadoConsulta.next();
            rsS=resultadoConsulta.getString(2);
            assertEquals(rsS,"Homero");
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