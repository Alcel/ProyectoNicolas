package com.example.proyectonicolas.controllers;

import com.example.proyectonicolas.dao.BrandDAO;
import com.example.proyectonicolas.dao.GarmentDAO;
import com.example.proyectonicolas.modelo.Brand;
import com.example.proyectonicolas.modelo.Garment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

public class ModDetController {

    @FXML
        private TextField earningsBox;
        @FXML
        private TextField nameBox;
        @FXML
        private TextField dateBox;
    private Integer buleano = 0;
        @FXML
        private Button addNewButton;
        @FXML
        private ToggleButton deptvTogle;
        @FXML
        private Button cancelButton;
        @FXML
        private Button resetButton;

        private int id;
    @FXML
    private TextField comBox;


    public void initialize(Garment texto){

            System.out.println(texto);
            id = texto.getClothesNumber();
            nameBox.setText(texto.getClothesName());
            earningsBox.setText(Float.toString(texto.getEarnings()));

            dateBox.setText(texto.getLaunchDate().toString());
            comBox.setText(texto.getCountryManufacture());

            earningsBox.setText(Float.toString(texto.getEarnings()));
            if (texto.getAvalible()!=0){
                deptvTogle.setSelected(true);
            }

        }
    @FXML
        public void addNew(ActionEvent actionEvent) {
            GarmentDAO garmentDAO = new GarmentDAO();
            String nombreS = nameBox.getText();

            String beneficiosS = earningsBox.getText();
            Float beneficiosF = 0F;
            String fechaS = dateBox.getText();
            String comS = comBox.getText();



            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            if(beneficiosS.isEmpty()||fechaS.isEmpty()||nombreS.isEmpty()||comS.isEmpty()){
                alert.setTitle("Información");
                alert.setHeaderText("Ha de rellenar todos los campos");
                alert.setContentText("El campo web es opcional");
                alert.showAndWait().ifPresent(rs -> {
                });
            }
            else{
                if(!beneficiosS.matches("^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*$")){
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
                }
                else {
                    beneficiosF = Float.parseFloat(beneficiosS);
                    garmentDAO.update(id,nombreS,beneficiosF,fechaS,buleano,comS);
                    Stage stage = (Stage) cancelButton.getScene().getWindow();
                    stage.close();
                }


            }
        }
        public void cambioDep(ActionEvent actionEvent) {
            if (deptvTogle.isSelected()){
                buleano=1;
            }
            else {
                buleano=0;
            }
        }

        @FXML
        public void closeWindow(ActionEvent actionEvent) {
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        }

        @FXML
        public void reset(ActionEvent actionEvent) {
            nameBox.clear();
            dateBox.clear();
            earningsBox.clear();
            comBox.clear();
            deptvTogle.setSelected(false);
        }



}
