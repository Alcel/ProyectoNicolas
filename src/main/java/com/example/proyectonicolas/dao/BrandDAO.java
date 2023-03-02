package com.example.proyectonicolas.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import com.example.proyectonicolas.modelo.Brand;
import javafx.scene.control.Alert;

/**
 * This class purpose is to access and alter the database wich features the Brand entity
 */
public class BrandDAO {
    private final String servidor = "jdbc:mariadb://localhost:5555/noinch?useSSL=false";
    private final String usuario = "adminer";
    private final String passwd = "adminer";

    private Connection conexionBBDD;

    /**The purpose of this method is to get all brands
     * @return An ObservableList of all the Brands in the database
     */
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
                System.out.println("Row [1] added " + resultadoConsulta);
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

    /**
     * The purpose of this method is to filter brands
     * @param cajaNum Code of the brand
     * @param cajaNombre Name of the brand
     * @param cajaFechaI First date
     * @param cajaFechaF Last date
     * @param deportivo Is deportive or not
     * @return An ObservableList of brands
     */
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
            if(numero.isEmpty()&&!nombre.isEmpty()){ //Con nombre
                SQL="SELECT * "
                        + "FROM brands "+"WHERE brandName ='"+nombre+"' AND fundation BETWEEN "+"'"+cajaFechaI+"'"+"AND"+"'"+cajaFechaF +"' AND isSporty ="+deptiv;
            }
            if(numero.isEmpty()&&nombre.isEmpty()){ //Sin nombre
                SQL="SELECT * "
                        + "FROM brands "+"WHERE fundation BETWEEN "+"'"+cajaFechaI+"'"+"AND"+"'"+cajaFechaF +"' AND isSporty ="+deptiv;
            }


            System.out.println(SQL);
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


    /**
     * The purpose of this method is to insert a new brand into the database
     * @param cajaNombre Brand name
     * @param earnings Brand's earnings
     * @param cajaFecha First date
     * @param headquarters Headquarters location
     * @param web Web page url
     * @param deportivo Is sporty or not
     * @param isin ISIN Code
     */
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

    /**The purpose of this method is to update a brand in the database
     * @param id Brand code
     * @param cajaNombre Brand name
     * @param earnings Brand's earnings
     * @param cajaFecha First date
     * @param headquarters Headquarters location
     * @param web Web page url
     * @param deportivo Is sporty or not
     * @param isin ISIN Code
     *
     */
    public void update(int id,String cajaNombre, Float earnings, String cajaFecha, String headquarters ,String web
            ,int deportivo, String isin ){
        String sql= "update brands set brandName = '" + cajaNombre+
                "',earnings = "+earnings+",fundation ='" +cajaFecha+
                "',headquarters = '"+headquarters+"', web= '"+web+
                "',isSporty= "+deportivo+",isin= '"+isin+"' where brandNumber ="+id ;
        try {
            conexionBBDD = DriverManager.getConnection(servidor, usuario, passwd);
            PreparedStatement pst = conexionBBDD.prepareStatement(sql);
            pst.executeUpdate();

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Información");
            alert.setHeaderText("No puede incluir caracteres especiales");
            alert.setContentText("Como por ejemplo: '´`ç");
            alert.showAndWait().ifPresent(rs -> {
            });

        }
    }

    /**The purpose of this method is to delete the brand with the id that has been given
     * @param id Brand Code
     *
     */
    public void delete (int id){
        String sql= "delete from brands where brandNumber ="+id ;
        try {
            conexionBBDD = DriverManager.getConnection(servidor, usuario, passwd);
            PreparedStatement pst = conexionBBDD.prepareStatement(sql);
            pst.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
