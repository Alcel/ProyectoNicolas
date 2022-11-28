package com.example.proyectonicolas.controllers;

import com.example.proyectonicolas.HelloApplication;
import com.example.proyectonicolas.dao.BrandDAO;
import com.example.proyectonicolas.dao.GarmentDAO;
import com.example.proyectonicolas.modelo.Brand;
import com.example.proyectonicolas.modelo.Garment;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;

public class DetailsController{

    @javafx.fxml.FXML
    private TableColumn numC;
    @javafx.fxml.FXML
    private TableColumn nameC;
    @javafx.fxml.FXML
    private TableColumn launchC;
    @javafx.fxml.FXML
    private TableColumn earningsC;
    @javafx.fxml.FXML
    private TableColumn dispoC;
    @javafx.fxml.FXML
    private TableColumn comC;

    private Garment garAux;

    private GarmentDAO garment= new GarmentDAO();

    private ObservableList<Garment> prendas;

    private ObservableList <Garment> prendasAux;
    @javafx.fxml.FXML
    private TableView tvGarment;
    @javafx.fxml.FXML
    private Button garmentNew;
    @javafx.fxml.FXML
    private Button garmentDelete;
    @javafx.fxml.FXML
    private Button garmentEdit;

    private int numero;


    public void initialize(int num) {
         numero = num;
        cargarDatosTabla(numero);

    }

    private void cargarDatosTabla (int num) {

        prendas =  garment.obtenerGarments(num);

        numC.setCellValueFactory(new PropertyValueFactory<Brand, Integer>("clothesNumber"));
        nameC.setCellValueFactory(new PropertyValueFactory<Brand, String>("clothesName"));
        earningsC.setCellValueFactory(new PropertyValueFactory<Brand, Float>("earnings"));
        launchC.setCellValueFactory(new PropertyValueFactory<Brand, Date>("launchDate"));
        dispoC.setCellValueFactory(new PropertyValueFactory<Brand, Integer>("avalible"));
        comC.setCellValueFactory(new PropertyValueFactory<Brand, String>("countryManufacture"));

        tvGarment.setItems(prendas);
    }
    public void openEdit(ActionEvent actionEvent) {

        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("modDet-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 320, 240);
            ModDetController modcont = (ModDetController) fxmlLoader.getController();
            modcont.initialize((Garment) tvGarment.getSelectionModel().getSelectedItem());
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.setMinWidth(720);
            stage.setMinHeight(413);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    public void addEdit(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AddDet-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 320, 240);
            AddDetController addDetCont = (AddDetController)  fxmlLoader.getController();
            addDetCont.initialize(numero);
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.setMinWidth(720);
            stage.setMinHeight(413);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
