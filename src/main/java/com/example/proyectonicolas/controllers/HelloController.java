package com.example.proyectonicolas.controllers;

import com.example.proyectonicolas.HelloApplication;
import com.example.proyectonicolas.ReportBrands;
import com.example.proyectonicolas.dao.BrandDAO;
import com.example.proyectonicolas.modelo.Brand;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Main controller of the app
 */
public class HelloController {

    private ObservableList<Brand> marcas;
    private ObservableList<Brand> marcasAux;

    private BrandDAO brandDAO = new BrandDAO();
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

    private Executor exec = new Executor() {
        @Override
        public void execute(Runnable command) {

        }
    };
    @FXML
    private GridPane gridRoot;
    @FXML
    private GridPane gridFiltros;
    @FXML
    private Label idLabel;
    @FXML
    private Label dateLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label questionLabel;
    @FXML
    private Button reload;
    @FXML
    private HBox buttonPanel;
    @FXML
    private Button informe;
    @FXML
    private Button informeDetalleButton;

    /**
     * Loads the table containing every brand
     */
    public void initialize() {
        brandNumberC.setCellValueFactory(new PropertyValueFactory<Brand, Integer>("brandNumber"));
        brandNameC.setCellValueFactory(new PropertyValueFactory<Brand, String>("brandName"));
        earningsC.setCellValueFactory(new PropertyValueFactory<Brand, Float>("earnings"));
        fundationC.setCellValueFactory(new PropertyValueFactory<Brand, Date>("fundation"));

        headquartersC.setCellValueFactory(new PropertyValueFactory<Brand, String>("headquarters"));
        webC.setCellValueFactory(new PropertyValueFactory<Brand, String>("web"));

        isSportyC.setCellValueFactory(new PropertyValueFactory<Brand, Integer>("isSporty"));
        isinC.setCellValueFactory(new PropertyValueFactory<Brand, String>("isin"));




        brandAux = new Brand(0, "", 0f, fecha, "", "", 0, "");
        cargarGestorDobleCLick();

        exec = Executors.newCachedThreadPool((runnable) -> {
            Thread t = new Thread (runnable);
            t.setDaemon(true);
            return t;
        });

        cargarDatosTabla();
    }

    /**
     * Sets every brand on the table
     */
    public void cargarDatosTabla() {
        Task<List<Brand>> task = new Task<List<Brand>>(){
            @Override
            public ObservableList<Brand> call() throws Exception{
                return brandDAO.obtenerBrands();
            }
        };
        task.setOnFailed(e-> task.getException().printStackTrace());
        task.setOnSucceeded(e-> tvBrands.setItems((ObservableList<Brand>) task.getValue()));
        exec.execute(task);

    }

    /**This method helps us filter through the table
     * @param actionEvent
     */
    @FXML
    public void search(ActionEvent actionEvent) {
        String idSearchBoxS = idSearchBox.getText();
        String nameSearchBoxS = nameSearchBox.getText();
        String dateSearchInitialS = dateSearchInitial.getText();
        String dateSearchFinalS = dateSearchFinal.getText();


        Alert alert = new Alert(Alert.AlertType.INFORMATION);


        if (!idSearchBoxS.isEmpty() && !idSearchBoxS.matches("[0-9]*")) {
            //Informamos al usuario del error
            alert.setTitle("Información");
            alert.setHeaderText("En el campo id solo se han de introducir numeros enteros");
            alert.showAndWait().ifPresent(rs -> {
            });
        } else {
            if (!dateSearchInitialS.isEmpty() && !dateSearchInitialS.matches("((18|19|20)[0-9]{2}[\\-.](0[13578]|1[02])[\\-.](0[1-9]|[12][0-9]|3[01]))|(18|19|20)[0-9]{2}[\\-.](0[469]|11)[\\-.](0[1-9]|[12][0-9]|30)|(18|19|20)[0-9]{2}[\\-.](02)[\\-.](0[1-9]|1[0-9]|2[0-8])|(((18|19|20)(04|08|[2468][048]|[13579][26]))|2000)[\\-.](02)[\\-.]29")) {
                alert.setTitle("Información");
                alert.setHeaderText("Tanto el campo fecha inicio como fecha final el formato a usar es el de Año-Mes-Dia");
                alert.setContentText("Como ejemplo: 2000-04-01");
                alert.showAndWait().ifPresent(rs -> {
                });
            } else if (!dateSearchFinalS.isEmpty() && !dateSearchFinalS.matches("((18|19|20)[0-9]{2}[\\-.](0[13578]|1[02])[\\-.](0[1-9]|[12][0-9]|3[01]))|(18|19|20)[0-9]{2}[\\-.](0[469]|11)[\\-.](0[1-9]|[12][0-9]|30)|(18|19|20)[0-9]{2}[\\-.](02)[\\-.](0[1-9]|1[0-9]|2[0-8])|(((18|19|20)(04|08|[2468][048]|[13579][26]))|2000)[\\-.](02)[\\-.]29")) {
                alert.setTitle("Información");
                alert.setHeaderText("Tanto el campo fecha inicio como fecha final el formato a usar es el de Año-Mes-Dia");
                alert.setContentText("Como ejemplo: 2000-04-01");
                alert.showAndWait().ifPresent(rs -> {
                });
                System.out.println(dateSearchFinalS.isEmpty());
            }
            if (dateSearchInitialS.isEmpty()) {
                dateSearchInitialS = "0000-00-00";
                System.out.println(dateSearchInitialS);
            }
            if (dateSearchFinalS.isEmpty()) {
                dateSearchFinalS = "9999-12-30";
            }


        }
        marcasAux = brandDAO.obtenerBrandsBusqueda(idSearchBoxS, nameSearchBoxS, dateSearchInitialS, dateSearchFinalS, buleano);

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


    /**When clicking in the button to check if it's sporty or not what we are looking for, this method will chage the button's value
     * @param actionEvent
     */
    @FXML
    public void cambioDep(ActionEvent actionEvent) {

        if (deptv.isSelected()) {
            buleano = 1;

            deptv.setText("Si");
        } else {
            buleano = 0;
            deptv.setText("No");
        }
    }

    /**Opens window to add a new brand
     * @param actionEvent
     */
    @FXML
    public void add(ActionEvent actionEvent) {
        try {

            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("add-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 413);
            stage.setScene(scene);
            stage.setTitle("IWear - Añadir");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setMinWidth(720);
            stage.setMinHeight(413);
            stage.centerOnScreen();
            stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
            stage.setResizable(false);


            stage.showAndWait();
            cargarDatosTabla();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**Opens a window to edit an existing brand
     * @param actionEvent
     */
    @FXML
    public void openEdit(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("mod-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 413);
            ModController modcont = (ModController) fxmlLoader.getController();
            modcont.initialize((Brand) tvBrands.getSelectionModel().getSelectedItem());

            stage.setTitle("IWear - Edición");
            stage.setScene(scene);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setMinWidth(720);
            stage.setMinHeight(413);
            stage.setResizable(false);
            stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
            stage.showAndWait();
            cargarDatosTabla();


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


    /**Deletes the selected brand
     * @param actionEvent
     */
    @FXML
    public void deleteRow(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        Brand marca = (Brand) tvBrands.getSelectionModel().getSelectedItem();
        Alert eleccion = new Alert(Alert.AlertType.CONFIRMATION);
        eleccion.setTitle("Alerta");
        eleccion.setHeaderText("¿Esta seguro de que quiere eliminar?");
        try{
        eleccion.showAndWait().ifPresent(type -> {
            if(type==ButtonType.OK){
            brandDAO.delete(marca.getBrandNumber());
                cargarDatosTabla();}
        });}
        catch(NullPointerException npe){
            alert.setTitle("Información");
            alert.setHeaderText("Ha de elegir una fila a eliminar");
            alert.showAndWait().ifPresent(rs -> {
            });

        }
    }

    /**
     * When double clicking will open a window with the brand's garment
     */
    private void cargarGestorDobleCLick() {

        tvBrands.setRowFactory(tv -> {
            TableRow<Brand> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    brandAux.setBrandNumber(row.getItem().getBrandNumber());
                    brandAux.setBrandName(row.getItem().getBrandName());
                    brandAux.setEarnings(row.getItem().getEarnings());
                    brandAux.setFundation(row.getItem().getFundation());
                    brandAux.setHeadquarters(row.getItem().getHeadquarters());
                    final int bul = row.getItem().getIsSporty();
                    if (bul == 0) {
                        brandAux.setSporty(false);
                    } else {
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

    /**Opens a window with garment
     * @param num
     */
    public void openDet(int num) {
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
        stage.setTitle("IWear - Ropa");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setMinWidth(720);
        stage.setMinHeight(413);
        stage.show();
    }

    @FXML
    public void cargarDatosTabla(ActionEvent actionEvent) {
    }

    @FXML
    public void informe(ActionEvent actionEvent) {
        try {
            // --- Show Jasper Report on click-----
            new ReportBrands().showReportSimple();
        } catch (ClassNotFoundException | JRException | SQLException e1) {
            e1.printStackTrace();
        }
    }

    @FXML
    public void informeDetalle(ActionEvent actionEvent) {
        try {
            // --- Show Jasper Report on click-----
            new ReportBrands().showReportConSubreport();
        } catch (ClassNotFoundException | JRException | SQLException e1) {
            e1.printStackTrace();
        }
    }
}