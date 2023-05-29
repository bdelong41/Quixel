module com.example.inventorysystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.inventorysystem to javafx.fxml;
    exports com.example.inventorysystem;
    exports com.example.inventorysystem.controller;
    opens com.example.inventorysystem.controller to javafx.fxml;
    exports com.example.inventorysystem.models;
    opens com.example.inventorysystem.models to javafx.fxml;
}