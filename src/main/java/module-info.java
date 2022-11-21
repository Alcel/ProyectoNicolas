module com.example.proyectonicolas {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.proyectonicolas to javafx.fxml;
    exports com.example.proyectonicolas;

    opens com.example.proyectonicolas.modelo to javafx.fxml;
    exports com.example.proyectonicolas.modelo;
}