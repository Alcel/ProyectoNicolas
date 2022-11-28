package com.example.proyectonicolas.controllers;

import com.example.proyectonicolas.HelloApplication;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.sql.Date;

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
    public TableView tvBrands;
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
    @FXML
    private ToggleButton deptv;
    public int buleano;
    @FXML
    private Button addButton;
    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;


    public void initialize()  {

        cargarDatosTabla();

        brandAux = new Brand(0,"",0f,fecha,"","",0,"");
        cargarGestorDobleCLick();

    }

    public void cargarDatosTabla () {

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


        if(!idSearchBoxS.isEmpty()&&!idSearchBoxS.matches("[0-9]*")){
            //Informamos al usuario del error
            alert.setTitle("Información");
            alert.setHeaderText("En el campo id solo se han de introducir numeros enteros");
            alert.showAndWait().ifPresent(rs -> {
            });
        }
        else{
            if(!dateSearchInitialS.isEmpty()&&!dateSearchInitialS.matches("((18|19|20)[0-9]{2}[\\-.](0[13578]|1[02])[\\-.](0[1-9]|[12][0-9]|3[01]))|(18|19|20)[0-9]{2}[\\-.](0[469]|11)[\\-.](0[1-9]|[12][0-9]|30)|(18|19|20)[0-9]{2}[\\-.](02)[\\-.](0[1-9]|1[0-9]|2[0-8])|(((18|19|20)(04|08|[2468][048]|[13579][26]))|2000)[\\-.](02)[\\-.]29")){
                alert.setTitle("Información");
                alert.setHeaderText("Tanto el campo fecha inicio como fecha final el formato a usar es el de Año-Mes-Dia");
                alert.setContentText("Como ejemplo: 2000-04-01");
                alert.showAndWait().ifPresent(rs -> {
                });
            } else if (!dateSearchFinalS.isEmpty()&&!dateSearchFinalS.matches("((18|19|20)[0-9]{2}[\\-.](0[13578]|1[02])[\\-.](0[1-9]|[12][0-9]|3[01]))|(18|19|20)[0-9]{2}[\\-.](0[469]|11)[\\-.](0[1-9]|[12][0-9]|30)|(18|19|20)[0-9]{2}[\\-.](02)[\\-.](0[1-9]|1[0-9]|2[0-8])|(((18|19|20)(04|08|[2468][048]|[13579][26]))|2000)[\\-.](02)[\\-.]29")) {
                alert.setTitle("Información");
                alert.setHeaderText("Tanto el campo fecha inicio como fecha final el formato a usar es el de Año-Mes-Dia");
                alert.setContentText("Como ejemplo: 2000-04-01");
                alert.showAndWait().ifPresent(rs -> {
                });
                System.out.println(dateSearchFinalS.isEmpty());
            }
                if (dateSearchInitialS.isEmpty()) {
                    dateSearchInitialS = "0-0-0";
                    System.out.println(dateSearchInitialS);
                }
                if (dateSearchFinalS.isEmpty()) {
                    dateSearchFinalS = "9999-12-30";
                }


        }
        marcasAux = brandDAO.obtenerBrandsBusqueda(idSearchBoxS, nameSearchBoxS, dateSearchInitialS, dateSearchFinalS,buleano );

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


    @FXML
    public void cambioDep(ActionEvent actionEvent) {
        if (deptv.isSelected()){
            buleano=1;
        }
        else {
            buleano=0;
        }
    }

    @FXML
    public void add(ActionEvent actionEvent) {
        try {

            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("add-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 320, 240);
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.setMinWidth(720);
            stage.setMinHeight(413);
            stage.show();
            stage.onCloseRequestProperty(); //Aqui



        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void openEdit(ActionEvent actionEvent) {

        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("mod-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 320, 240);
            ModController modcont = (ModController) fxmlLoader.getController();
            modcont.initialize((Brand) tvBrands.getSelectionModel().getSelectedItem());
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.setMinWidth(720);
            stage.setMinHeight(413);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    public void deleteRow(ActionEvent actionEvent) {
        Brand marca= (Brand) tvBrands.getSelectionModel().getSelectedItem();
        brandDAO.delete(marca.getBrandNumber());
        cargarDatosTabla();
    }

    private void cargarGestorDobleCLick () {

        tvBrands.setRowFactory(tv -> {
            TableRow<Brand> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {

                    brandAux.setBrandNumber(row.getItem().getBrandNumber());
                    brandAux.setBrandName(row.getItem().getBrandName());
                    brandAux.setEarnings(row.getItem().getEarnings());
                    brandAux.setFundation(row.getItem().getFundation());
                    brandAux.setHeadquarters(row.getItem().getHeadquarters());
                    final int bul=row.getItem().getIsSporty();
                    if (bul==0){
                        brandAux.setSporty(false);
                    }else {
                        brandAux.setSporty(true);
                    }

                    brandAux.setWeb(row.getItem().getWeb());
                    brandAux.setIsin(row.getItem().getIsin());
                    System.out.println(brandAux.getBrandName());
                    openDet(brandAux.getBrandNumber());

                }
            });

            return row;
        });
    }

    public void openDet(int num){
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("details-view.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 320, 240);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        DetailsController detcont = (DetailsController) fxmlLoader.getController();
        detcont.initialize(num);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setMinWidth(720);
        stage.setMinHeight(413);
        stage.show();
    }
}