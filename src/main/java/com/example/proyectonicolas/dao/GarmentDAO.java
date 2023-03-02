package com.example.proyectonicolas.dao;

import com.example.proyectonicolas.modelo.Garment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.*;

/**
 * This class purpose is to access and alter the database wich features the Garment entity
 */
public class GarmentDAO {
    private final String servidor = "jdbc:mariadb://localhost:5555/noinch?useSSL=false";
    private final String usuario = "adminer";
    private final String passwd = "adminer";

    private Connection conexionBBDD;

    /** This method purpose is to obtain one Garment data.
     * @param num
     * @return
     */
    public ObservableList<Garment> obtenerGarments(int num) {

        ObservableList<Garment> datosResultadoConsulta = FXCollections.observableArrayList();
        try {
            // Nos conectamos
            conexionBBDD = DriverManager.getConnection(servidor, usuario, passwd);
            String SQL = "SELECT * "
                    + "FROM clothes "
                    + "where clothesBrandNumber ="+num;
            System.out.println(SQL);

            // Ejecutamos la consulta y nos devuelve una matriz de filas (registros) y columnas (datos)
            ResultSet resultadoConsulta = conexionBBDD.createStatement().executeQuery(SQL);

            // Debemos cargar los datos en el ObservableList (Que es un ArrayList especial)
            // Los datos que devuelve la consulta no son directamente cargables en nuestro objeto
            while (resultadoConsulta.next()) {
                datosResultadoConsulta.add(new Garment(
                        resultadoConsulta.getInt("clothesNumber"),
                        resultadoConsulta.getString("clothesName"),
                        resultadoConsulta.getInt("clothesBrandNumber"),
                        resultadoConsulta.getDate("launchDate"),
                        resultadoConsulta.getFloat("earnings"),
                        resultadoConsulta.getInt("avalible"),
                        resultadoConsulta.getString("countryManufacture"))
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

    /** This method purpose is to update a Garment
     * @param id
     * @param clothesName
     * @param earnings
     * @param launchDate
     * @param avalible
     * @param countryManufacture
     */
    public void update(int id,String clothesName, Float earnings, String launchDate,
            int avalible, String countryManufacture ){
        String sql= "update clothes set clothesName = '" + clothesName+
                "',earnings = "+earnings+",launchDate ='" +launchDate+
                "',avalible= "+avalible+",countryManufacture= '"+countryManufacture+"' where clothesNumber ="+id ;
        try {
            conexionBBDD = DriverManager.getConnection(servidor, usuario, passwd);
            PreparedStatement pst = conexionBBDD.prepareStatement(sql);
            pst.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**This method purpose is to insert a new Garment into the database
     * @param clothesName
     * @param earnings
     * @param launchDate
     * @param avalible
     * @param countryManufacture
     * @param idComp
     */
    public void insert(String clothesName, Float earnings, String launchDate,
                       int avalible, String countryManufacture, int idComp){
        String SQL = "insert into clothes (clothesName,earnings,launchDate,avalible,countryManufacture,clothesBrandNumber) values ('"+
                clothesName+"',"+earnings+",'"+launchDate+"',"+avalible+",'"+countryManufacture+"',"+idComp+")";
        System.out.println(SQL);
        try {
            conexionBBDD = DriverManager.getConnection(servidor, usuario, passwd);
            PreparedStatement pst = conexionBBDD.prepareStatement(SQL);
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

    /** This method purpose is to delete the Garment wich has the clothesNumber that we have used as parameter
     * @param num
     */
    public void delete(int num){
        String SQL = "DELETE From clothes where clothesNumber ="+num;
        try {
            conexionBBDD = DriverManager.getConnection(servidor, usuario, passwd);
            PreparedStatement pst = conexionBBDD.prepareStatement(SQL);
            pst.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
