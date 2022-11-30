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
    public void addNew(ActionEvent actionEvent) {

        GarmentDAO garmentDAO = new GarmentDAO();
        String nombreS = nameBox.getText();

        String beneficiosS = earningsBox.getText();
        Float beneficiosF = 0F;
        String fechaS = dateBox.getText();
        String comS = comBox.getText();


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

                garmentDAO.insert(nombreS, beneficiosF, fechaS, buleano, comS, comp);
                Stage stage = (Stage) cancelButton.getScene().getWindow();
                stage.close();
            }


        }
    }

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
