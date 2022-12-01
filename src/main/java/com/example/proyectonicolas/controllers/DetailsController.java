package com.example.proyectonicolas.controllers;

import com.example.proyectonicolas.HelloApplication;
import com.example.proyectonicolas.dao.GarmentDAO;
import com.example.proyectonicolas.modelo.Brand;
import com.example.proyectonicolas.modelo.Garment;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

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

    private GarmentDAO garment= new GarmentDAO();

    private ObservableList<Garment> prendas;

    @javafx.fxml.FXML
    private TableView tvGarment;


    private int numero;

    private Executor exec;

    private TextField nombre;
    private TextField fecha;
    private TextField earnings;
    private TextField com;
    private ToggleButton tb;
    private ToggleButton deptvTogle;
    private Button add;

    private int buleano;



    public void initialize(int num) {
        numero = num;
        numC.setCellValueFactory(new PropertyValueFactory<Brand, Integer>("clothesNumber"));
        nameC.setCellValueFactory(new PropertyValueFactory<Brand, String>("clothesName"));
        earningsC.setCellValueFactory(new PropertyValueFactory<Brand, Float>("earnings"));
        launchC.setCellValueFactory(new PropertyValueFactory<Brand, Date>("launchDate"));
        dispoC.setCellValueFactory(new PropertyValueFactory<Brand, Integer>("avalible"));
        comC.setCellValueFactory(new PropertyValueFactory<Brand, String>("countryManufacture"));
        exec = Executors.newCachedThreadPool((runnable) -> {
            Thread t = new Thread (runnable);
            t.setDaemon(true);
            return t;
        });
        cargarDatosTabla(numero);

    }

    private void cargarDatosTabla (int num) {

        prendas =  garment.obtenerGarments(num);

        tvGarment.setItems(prendas);
    }
    @javafx.fxml.FXML
    public void openEdit(ActionEvent actionEvent) {
         Stage stage=new Stage();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("modDet-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 320, 240);
            ModDetController modcont = (ModDetController) fxmlLoader.getController();
            modcont.initialize((Garment) tvGarment.getSelectionModel().getSelectedItem());
            stage.setTitle("IWear - Ropa - Edición");
            stage.setScene(scene);
            stage.setMinWidth(600);
            stage.setMinHeight(413);
            stage.setResizable(false);
            stage.showAndWait();
            cargarDatosTabla(numero);

        }catch(NullPointerException npe){
            alert.setTitle("Información");
            alert.setHeaderText("Ha de elegir una fila a editar");
            alert.showAndWait().ifPresent(rs -> {
            });

        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    public void addEdit(ActionEvent actionEvent) {
        //Create Stage
        Stage stage=new Stage();

        stage.setTitle("IWear - Ropa - Añadir");
        //Create view in Java

        nombre = new TextField();
        nombre.setPromptText("Nombre");
        fecha = new TextField();
        fecha.setPromptText("Fecha AAAA-MM-DD");
        earnings = new TextField();
        earnings.setPromptText("Beneficios (en miles de millones)");
        com = new TextField();
        com.setPromptText("País de fabricación");

        deptvTogle= new ToggleButton("No");

        add = new Button("Añadir");


        add.setOnAction(event -> {
            addNew();
        });
        deptvTogle.setOnAction(event -> {
            cambioDep();
        });


        Label pregunta = new Label("¿Está disponible?");

        nombre.setFocusTraversable(false);
        fecha.setFocusTraversable(false);
        earnings.setFocusTraversable(false);
        com.setFocusTraversable(false);
        VBox containerV1 = new VBox(nombre,fecha,earnings,com);
        HBox containerH1 = new HBox(add);
        HBox containerH2 = new HBox(pregunta,deptvTogle);
        VBox container = new VBox(containerV1,containerH2,containerH1);
        containerV1.setSpacing(10);
        containerV1.setSpacing(10);
        container.setMinWidth(400);
        container.setMinHeight(400);

        container.setSpacing(15);
        container.setPadding(new Insets(25));
        container.setAlignment(Pos.CENTER);
        stage.setScene(new Scene(container));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setMinWidth(400);
        stage.setMinHeight(400);
        stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
        stage.setResizable(false);

        stage.showAndWait();
        cargarDatosTabla(numero);


    }

    @javafx.fxml.FXML
    public void deleteEdit(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        Garment prenda = (Garment) tvGarment.getSelectionModel().getSelectedItem();
        Alert eleccion = new Alert(Alert.AlertType.CONFIRMATION);
        eleccion.setTitle("Alerta");
        eleccion.setHeaderText("¿Esta seguro de que quiere eliminar?");
        try{
        eleccion.showAndWait().ifPresent(type -> {
            if(type==ButtonType.OK){
                garment.delete(prenda.getClothesNumber());
                cargarDatosTabla(numero);}
        });}
        catch(NullPointerException npe) {
            alert.setTitle("Información");
            alert.setHeaderText("Ha de elegir una fila a eliminar");
            alert.showAndWait().ifPresent(rs -> {
            });
        }
    }
    public void addNew() {
         Stage stage=new Stage();

        GarmentDAO garmentDAO = new GarmentDAO();
        String nombreS = nombre.getText();

        String beneficiosS = earnings.getText();
        Float beneficiosF = 0F;
        String fechaS = fecha.getText();
        String comS = com.getText();


        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (beneficiosS.isEmpty() || fechaS.isEmpty() || nombreS.isEmpty() || comS.isEmpty()) {
            alert.setTitle("Información");
            alert.setHeaderText("Ha de rellenar todos los campos");
            alert.setContentText("El campo web es opcional");
            alert.showAndWait().ifPresent(rs -> {
            });
        } else {
            if (!beneficiosS.matches("^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*$")) {
                alert.setTitle("Información");
                alert.setHeaderText("Ha de introducir un numero positivo");
                alert.setContentText("Como ejemplo: 100.910 o 1.0");
                alert.showAndWait().ifPresent(rs -> {
                });
            } else if (!fechaS.matches("((18|19|20)[0-9]{2}[\\-.](0[13578]|1[02])[\\-.](0[1-9]|[12][0-9]|3[01]))|" +
                    "(18|19|20)[0-9]{2}[\\-.](0[469]|11)[\\-.](0[1-9]|[12][0-9]|30)|(18|19|20)[0-9]{2}[\\-.](02)[\\-.]" +
                    "(0[1-9]|1[0-9]|2[0-8])|(((18|19|20)(04|08|[2468][048]|[13579][26]))|2000)[\\-.](02)[\\-.]29")) {
                alert.setTitle("Información");
                alert.setHeaderText("Ha de introducir una fecha valida");
                alert.setContentText("Como ejemplo: 2001-02-05 o 1996-12-29");
                alert.showAndWait().ifPresent(rs -> {
                });
            } else {
                beneficiosF = Float.parseFloat(beneficiosS);

                garmentDAO.insert(nombreS, beneficiosF, fechaS, buleano, comS, numero);

                stage.close();
            }


        }
    }
    public void cambioDep() {
        if (deptvTogle.isSelected()) {
            buleano = 1;
            deptvTogle.setText("Si");
        } else {
            buleano = 0;
            deptvTogle.setText("No");
        }
    }
}
