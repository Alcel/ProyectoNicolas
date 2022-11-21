package com.example.proyectonicolas.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import com.example.proyectonicolas.modelo.Brand;
import com.example.proyectonicolas.HelloController;

public class BrandDAO {
    private final String servidor = "jdbc:mariadb://localhost:5555/noinch?useSSL=false";
    private final String usuario = "adminer";
    private final String passwd = "adminer";

    private Connection conexionBBDD;

    public ObservableList<Brand> obtenerBrands() {

        ObservableList<Brand> datosResultadoConsulta = FXCollections.observableArrayList();
        try {
            // Nos conectamos
            conexionBBDD = DriverManager.getConnection(servidor, usuario, passwd);
            String SQL = "SELECT * "
                    + "FROM brands "
                    + "ORDER By brandNumber";

            // Ejecutamos la consulta y nos devuelve una matriz de filas (registros) y columnas (datos)
            ResultSet resultadoConsulta = conexionBBDD.createStatement().executeQuery(SQL);

            // Debemos cargar los datos en el ObservableList (Que es un ArrayList especial)
            // Los datos que devuelve la consulta no son directamente cargables en nuestro objeto
            while (resultadoConsulta.next()) {
                datosResultadoConsulta.add(new Brand(
                        resultadoConsulta.getInt("brandNumber"),
                        resultadoConsulta.getString("brandName"),
                        resultadoConsulta.getFloat("earnings"),
                        resultadoConsulta.getDate("fundation"),
                        resultadoConsulta.getString("headquarters"),
                        resultadoConsulta.getString("web"),
                        resultadoConsulta.getInt("isSporty"),
                        resultadoConsulta.getString("isin"))
                );
                System.out.println("Row [1] added " + resultadoConsulta.toString());
            }
            conexionBBDD.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error:" + e.toString());
            conexionBBDD.close();
        } finally {
            return datosResultadoConsulta;
        }
    }
    public ObservableList<Brand> obtenerBrandsBusqueda() {

        ObservableList<Brand> datosResultadoConsultaBusqueda = FXCollections.observableArrayList();
        try {
            // Nos conectamos
            conexionBBDD = DriverManager.getConnection(servidor, usuario, passwd);
            System.out.println(HelloController.idSearchBox);
            String SQL="SELECT * "
                    + "FROM brands "
                    + "ORDER By brandNumber";
            if(HelloController.idSearchBox!=null){
                SQL = "SELECT * "
                        + "FROM brands "
                        + "ORDER By brandNumber" +
                        "WHERE brandNumber="+HelloController.idSearchBox.getText();
            }


            // Ejecutamos la consulta y nos devuelve una matriz de filas (registros) y columnas (datos)
            ResultSet resultadoConsulta = conexionBBDD.createStatement().executeQuery(SQL);

            // Debemos cargar los datos en el ObservableList (Que es un ArrayList especial)
            // Los datos que devuelve la consulta no son directamente cargables en nuestro objeto
            while (resultadoConsulta.next()) {
                datosResultadoConsultaBusqueda.add(new Brand(
                        resultadoConsulta.getInt("brandNumber"),
                        resultadoConsulta.getString("brandName"),
                        resultadoConsulta.getFloat("earnings"),
                        resultadoConsulta.getDate("fundation"),
                        resultadoConsulta.getString("headquarters"),
                        resultadoConsulta.getString("web"),
                        resultadoConsulta.getInt("isSporty"),
                        resultadoConsulta.getString("isin"))
                );
                System.out.println("Row [1] added " + resultadoConsulta.toString());
            }
            conexionBBDD.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error:" + e.toString());
            conexionBBDD.close();
        } finally {
            return datosResultadoConsultaBusqueda;
        }
    }
}
