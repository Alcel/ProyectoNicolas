package com.example.proyectonicolas.dao;

import com.example.proyectonicolas.modelo.Brand;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class BrandDAOTest {
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
    void insert() throws SQLException {

        BrandDAO brand = new BrandDAO();
        String rsS="";
        //String cajaNombre, Float earnings, String cajaFecha, String headquarters ,String web
        //            ,int deportivo, String isin
        brand.insert("Homero", 180.25f, "2050-02-12", "Malaga", "No tiene", 0, "345");

        try {
            // Nos conectamos

            conexionBBDD = DriverManager.getConnection(servidor, usuario, passwd);

            String SQL = "SELECT * "
                    + "FROM brands " + "WHERE brandName='Homero' and isin='345'"
                    + "ORDER By brandNumber";


            ResultSet resultadoConsulta = conexionBBDD.createStatement().executeQuery(SQL);

            // Debemos cargar los datos en el ObservableList (Que es un ArrayList especial)
            // Los datos que devuelve la consulta no son directamente cargables en nuestro objeto
            conexionBBDD.close();
            resultadoConsulta.next();
            rsS=resultadoConsulta.getString(2);
            assertEquals(rsS,"Homero");
            rsS=resultadoConsulta.getString(3);
            assertEquals(rsS,"180.25");
            rsS=resultadoConsulta.getString(4);
            assertEquals(rsS,"2050-02-12");
            rsS=resultadoConsulta.getString(5);
            assertEquals(rsS,"Malaga");
            rsS=resultadoConsulta.getString(6);
            assertEquals(rsS,"No tiene");
            rsS=resultadoConsulta.getString(7);
            assertEquals(rsS,"0");
            rsS=resultadoConsulta.getString(8);
            assertEquals(rsS,"345");
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

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}