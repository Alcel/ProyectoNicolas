module com.example.proyectonicolas {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.proyectonicolas to javafx.fxml;
    exports com.example.proyectonicolas;
}