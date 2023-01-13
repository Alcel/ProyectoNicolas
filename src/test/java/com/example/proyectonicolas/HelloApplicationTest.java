package com.example.proyectonicolas;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.base.WindowMatchers;
import org.testfx.matcher.control.ListViewMatchers;
import org.testfx.matcher.control.TableViewMatchers;
import org.testfx.matcher.control.TextInputControlMatchers;

import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;

@ExtendWith(ApplicationExtension.class)
class HelloApplicationTest {
    FXMLLoader fxmlLoader;
    Scene mainstage;
    @Start
    public void start(Stage stage) throws IOException {
        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        mainstage = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(mainstage);
        stage.show();
        stage.setMinWidth(1000);
        stage.setMinHeight(413);

        stage.show();

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((screenBounds.getWidth() - stage.getWidth()) / 4);
        stage.setY((screenBounds.getHeight() - stage.getHeight()) / 4);
    }
    @Test
    void insertarCamposCorrectos(FxRobot robot){
        robot.clickOn("#botonMas"); //Pincha en la caja de texto
        robot.clickOn("#cajaNombre");
        robot.write("Nombre");
        robot.clickOn("#cajaAnno");
        robot.write("2000-12-12");
        robot.clickOn("#cajaBeneficios");
        robot.write("18.5");
        robot.clickOn("#cajaWeb");
        robot.write("No tiene web");
        robot.clickOn("#cajaSede");
        robot.write("Berchules");
        robot.clickOn("#cajaIsin");
        robot.write("18215445");
        robot.clickOn("#botonBool");
        robot.clickOn("#botonInsert");

        FxAssert.verifyThat("#tablaId", TableViewMatchers.hasNumRows(1));
        //FxAssert.verifyThat("#tablaId", TableViewMatchers.containsRowAtIndex(7,55,"Nombre",18.5f,"2000-12-12","Berchules","No tiene web",1,"18215445"));


        //Comprobar sin nombre

    }
    @Test
    void escribirCampoIncorrecto(FxRobot robot) {
        robot.clickOn("#botonMas"); //Pincha en la caja de texto
        robot.clickOn("#cajaAnno");
        robot.write("2000-12-12");
        robot.clickOn("#cajaBeneficios");
        robot.write("18.5");
        robot.clickOn("#cajaWeb");
        robot.write("No tiene web");
        robot.clickOn("#cajaSede");
        robot.write("Berchules");
        robot.clickOn("#cajaIsin");
        robot.write("18215445");
        robot.clickOn("#botonBool");
        robot.clickOn("#botonInsert");
        FxAssert.verifyThat(robot.window("Información"), WindowMatchers.isShowing());
    }
}