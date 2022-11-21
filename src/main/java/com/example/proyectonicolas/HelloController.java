package com.example.proyectonicolas;

import com.example.proyectonicolas.dao.BrandDAO;
import com.example.proyectonicolas.modelo.Brand;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

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
    public static TextField idSearchBox;
    @FXML
    public static TextField nameSearchBox;
    @FXML
    public static Button searchButton;
    @FXML
    public static TextField idSearchBox1;
    @FXML
    public static TextField idSearchBox11;

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
    public void search(ActionEvent actionEvent) {//COmo el metodo anterior apañando en el dao
        marcasAux = brandDAO.obtenerBrandsBusqueda();

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