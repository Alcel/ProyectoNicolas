package com.example.proyectonicolas.controllers;

import com.example.proyectonicolas.dao.BrandDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

/**
 * This class controles the window in wich we add new brands
 */
public class AddController {
    @javafx.fxml.FXML
    private TextField webBox;
    @javafx.fxml.FXML
    private TextField headquartersBox;
    @javafx.fxml.FXML
    private TextField earningsBox;
    @javafx.fxml.FXML
    private TextField nameBox;
    @javafx.fxml.FXML
    private TextField dateBox;
    @javafx.fxml.FXML
    private TextField isinBox;
    private Integer buleano = 0;
    @javafx.fxml.FXML
    private Button addNewButton;
    @FXML
    private ToggleButton deptvTogle;
    @FXML
    private Button cancelButton;
    @FXML
    private Button resetButton;

    private HelloController hc = new HelloController();

    /** This method purpose is to add a new Brand into the database using the values we have entered
     * @param actionEvent  Click action
     */
    @FXML
    public void addNew(ActionEvent actionEvent) {
        BrandDAO brandDAO = new BrandDAO();
        String nombreS = nameBox.getText();
        String sedeS = headquartersBox.getText();
        String beneficiosS = earningsBox.getText();
        Float beneficiosF = 0F;
        String fechaS = dateBox.getText();
        String webS = webBox.getText();

        String isinS = isinBox.getText();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (beneficiosS.isEmpty() || fechaS.isEmpty() || nombreS.isEmpty() || sedeS.isEmpty() || isinS.isEmpty()) {
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
            } else if (!webS.isEmpty()) {
                beneficiosF = Float.parseFloat(beneficiosS);
                brandDAO.insert(nombreS, beneficiosF, fechaS, sedeS, webS, buleano, isinS);
                Stage stage = (Stage) addNewButton.getScene().getWindow();
                stage.close();
                hc.cargarDatosTabla();
            } else {
                beneficiosF = Float.parseFloat(beneficiosS);
                brandDAO.insert(nombreS, beneficiosF, fechaS, sedeS, webS, 0, isinS);
                Stage stage = (Stage) addNewButton.getScene().getWindow();
                stage.close();
                hc.cargarDatosTabla();
            }
        }
    }

    /** This method chages the toogle button value
     * @param actionEvent Click action
     */
    @FXML
    public void cambioDep(ActionEvent actionEvent) {

        if (deptvTogle.isSelected()) {
            buleano = 1;
            deptvTogle.setText("Si");
        } else {
            buleano = 0;
            deptvTogle.setText("No");
        }
    }

    /** Closes window
     * @param actionEvent Click action
     */
    @FXML
    public void closeWindow(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    /**Clears or resets every field in window
     * @param actionEvent Click action
     */
    @FXML
    public void reset(ActionEvent actionEvent) {
        nameBox.clear();
        dateBox.clear();
        earningsBox.clear();
        isinBox.clear();
        webBox.clear();
        deptvTogle.setSelected(false);
    }
}