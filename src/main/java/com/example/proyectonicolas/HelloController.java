package com.example.proyectonicolas;

import com.example.proyectonicolas.dao.BrandDAO;
import com.example.proyectonicolas.modelo.Brand;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;

public class HelloController {

    private ObservableList <Brand> marcas;
    private ObservableList <Brand> marcasAux;

    private BrandDAO brandDAO= new BrandDAO();
    private Brand brandAux;
    @FXML
    private TableColumn brandNumberC;
    @FXML
    private TableColumn brandNameC;
    @FXML
    private TableColumn earningsC;
    @FXML
    private TableColumn headquartersC;
    @FXML
    private TableColumn webC;
    @FXML
    private TableColumn isSportyC;
    @FXML
    private TableColumn isinC;
    private Date fecha;
    @FXML
    private TableColumn fundationC;
    @FXML
    private TableView tvBrands;
    @FXML
    public TextField idSearchBox;
    @FXML
    private TextField nameSearchBox;
    @FXML
    private Button searchButton;
    private StringProperty fcaja = new SimpleStringProperty();
    @FXML
    private TextField dateSearchInitial;
    @FXML
    private TextField dateSearchFinal;

    public void initialize()  {
        fecha = new Date("01/01/1000");
        cargarDatosTabla();

        brandAux = new Brand(0,"",0f,fecha,"","",0,"");

    }

    private void cargarDatosTabla () {

        marcas = brandDAO.obtenerBrands();

        brandNumberC.setCellValueFactory(new PropertyValueFactory<Brand, Integer>("brandNumber"));
        brandNameC.setCellValueFactory(new PropertyValueFactory<Brand, String>("brandName"));
        earningsC.setCellValueFactory(new PropertyValueFactory<Brand, Float>("earnings"));
        fundationC.setCellValueFactory(new PropertyValueFactory<Brand, Date>("fundation"));
        headquartersC.setCellValueFactory(new PropertyValueFactory<Brand, String>("headquarters"));
        webC.setCellValueFactory(new PropertyValueFactory<Brand, String>("web"));

        isSportyC.setCellValueFactory(new PropertyValueFactory<Brand, Integer>("isSporty"));
        isinC.setCellValueFactory(new PropertyValueFactory<Brand, String>("isin"));

        tvBrands.setItems(marcas);
    }


    @FXML
    public void search(ActionEvent actionEvent) {
        String idSearchBoxS =idSearchBox.getText();
        String nameSearchBoxS = nameSearchBox.getText();
        String dateSearchInitialS = dateSearchInitial.getText();
        String dateSearchFinalS = dateSearchFinal.getText();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        //Apañar para que funcione busqueda solo por nombre
        if(idSearchBoxS.matches("-?(0|[1-9]\\d*)")&&!idSearchBoxS.isEmpty()&&nameSearchBoxS.isEmpty()&&dateSearchInitialS.isEmpty()
                &&dateSearchFinalS.isEmpty()){ //Campo id tiene un valor correcto pero es el unico campo rellenado
            marcasAux = brandDAO.obtenerBrandsBusqueda(idSearchBoxS,nameSearchBoxS);
        }
        if(!idSearchBoxS.matches("-?(0|[1-9]\\d*)")&&!idSearchBoxS.isEmpty()&&nameSearchBoxS.isEmpty()&&dateSearchInitialS.isEmpty()
                &&dateSearchFinalS.isEmpty()){ //Campo id tiene un valor incorrecto y es el unico campo rellenado
            idSearchBoxS = "";
            marcasAux = brandDAO.obtenerBrandsBusqueda(idSearchBoxS, nameSearchBoxS);
            //Informamos al usuario del error
            alert.setTitle("Información");
            alert.setHeaderText("En el campo id solo se han de introducir numeros enteros");
            alert.showAndWait().ifPresent(rs -> {
            });
        }
        if(idSearchBoxS.isEmpty()&&nameSearchBoxS.isEmpty()&&dateSearchInitialS.isEmpty()
                &&dateSearchFinalS.isEmpty()){ //Campo id tiene un valor incorrecto y es el unico campo rellenado
            idSearchBoxS=new String();

            marcasAux = brandDAO.obtenerBrandsBusqueda(idSearchBoxS, nameSearchBoxS);


        }


        if(idSearchBoxS.isEmpty()&&nameSearchBoxS.isEmpty()&&dateSearchInitialS.isEmpty()&&dateSearchFinalS.isEmpty()){
            marcasAux = brandDAO.obtenerBrands();
        }

        brandNumberC.setCellValueFactory(new PropertyValueFactory<Brand, Integer>("brandNumber"));
        brandNameC.setCellValueFactory(new PropertyValueFactory<Brand, String>("brandName"));
        earningsC.setCellValueFactory(new PropertyValueFactory<Brand, Float>("earnings"));
        fundationC.setCellValueFactory(new PropertyValueFactory<Brand, Date>("fundation"));
        headquartersC.setCellValueFactory(new PropertyValueFactory<Brand, String>("headquarters"));
        webC.setCellValueFactory(new PropertyValueFactory<Brand, String>("web"));
        isSportyC.setCellValueFactory(new PropertyValueFactory<Brand, Integer>("isSporty"));
        isinC.setCellValueFactory(new PropertyValueFactory<Brand, String>("isin"));
        tvBrands.setItems(marcasAux);
    }
}