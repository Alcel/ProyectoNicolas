package com.example.proyectonicolas.controllers;

import com.example.proyectonicolas.dao.GarmentDAO;
import com.example.proyectonicolas.modelo.Brand;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class AddDetController {
    @javafx.fxml.FXML
    private TextField comBox;
    @javafx.fxml.FXML
    private TextField earningsBox;
    @javafx.fxml.FXML
    private TextField nameBox;
    @javafx.fxml.FXML
    private TextField dateBox;
    @javafx.fxml.FXML
    private Button resetButton;
    @javafx.fxml.FXML
    private Button cancelButton;
    @javafx.fxml.FXML
    private Button addNewButton;
    @javafx.fxml.FXML
    private ToggleButton deptvTogle;

    private int buleano;

    private int comp;

    //Transferir clase controlador de ventana en run time







    @javafx.fxml.FXML
    public void reset(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void closeWindow(ActionEvent actionEvent) {
    }

    public void intialize(){

    }


    public void initialize(int num) {
        comp = num;
    }

    @javafx.fxml.FXML


    public void cambioDep(ActionEvent actionEvent) {
        if (deptvTogle.isSelected()) {
            buleano = 1;
            deptvTogle.setText("No");
        } else {
            buleano = 0;
            deptvTogle.setText("Si");
        }
    }
}
