module com.example.proyectonicolas {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jasperreports;
    requires java.desktop;


    opens com.example.proyectonicolas to javafx.fxml;
    exports com.example.proyectonicolas;

    opens com.example.proyectonicolas.modelo to javafx.fxml;
    exports com.example.proyectonicolas.modelo;
    exports com.example.proyectonicolas.controllers;
    opens com.example.proyectonicolas.controllers to javafx.fxml;
}