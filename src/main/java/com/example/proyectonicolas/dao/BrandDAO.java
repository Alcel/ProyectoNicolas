package com.example.proyectonicolas.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import com.example.proyectonicolas.modelo.Brand;

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
    public ObservableList<Brand> obtenerBrandsBusqueda(String cajaNum, String cajaNombre, String cajaFechaI, String cajaFechaF, int deportivo) {
        String numero=cajaNum;
        String nombre=cajaNombre;
        String fechaI= cajaFechaI;
        String fechaF = cajaFechaF;
        int deptiv = deportivo;


        ObservableList<Brand> datosResultadoConsultaBusqueda = FXCollections.observableArrayList();
        try {
            // Nos conectamos
            System.out.println(numero);
            System.out.println(nombre);
            conexionBBDD = DriverManager.getConnection(servidor, usuario, passwd);

            String SQL="SELECT * "
                    + "FROM brands "
                    + "ORDER By brandNumber";

            if(!numero.isEmpty()){ /*Solo numero (Puesto que el id es una clave unica, no tiene sentido buscar por ningun filtro mas
                pues a lo unico que puede conducir es al error y que no se encuentre ningun resultado*/
                 SQL="SELECT * "
                        + "FROM brands "+"WHERE brandNumber ="+Integer.parseInt(numero);
            }
            if(numero.isEmpty()&&!nombre.isEmpty()){ //Solo nombre
                SQL="SELECT * "
                        + "FROM brands "+"WHERE brandName ='"+nombre+"' AND fundation BETWEEN "+"'"+cajaFechaI+"'"+"AND"+"'"+cajaFechaF +"' AND isSporty ="+deptiv;
            }
            if(numero.isEmpty()&&nombre.isEmpty()){ //Solo nombre
                SQL="SELECT * "
                        + "FROM brands "+"WHERE fundation BETWEEN "+"'"+cajaFechaI+"'"+"AND"+"'"+cajaFechaF +"' AND isSporty ="+deptiv;
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
    public void insert(String cajaNombre, Float earnings, String cajaFecha, String headquarters ,String web
            ,int deportivo, String isin ){
        String sql = "INSERT INTO brands(brandName,earnings,fundation,headquarters,web,isSporty,isin) VALUES " +
                "('"+cajaNombre+"',"+earnings+",'"+cajaFecha+"','"+headquarters+"','"
                +web+"',"+deportivo+",'"+isin+"')";
        try {
            conexionBBDD = DriverManager.getConnection(servidor, usuario, passwd);
            PreparedStatement pst = conexionBBDD.prepareStatement(sql);
            pst.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
