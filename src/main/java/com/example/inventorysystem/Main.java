package com.example.inventorysystem;

import com.example.inventorysystem.controller.ConnectionController;
import com.example.inventorysystem.controller.InventoryManagement;
import com.example.inventorysystem.data.DataConnection;
import com.example.inventorysystem.data.InHouseDAOImpl;
import com.example.inventorysystem.models.Outsourced;
import com.example.inventorysystem.models.Part;
import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

////class modules
//import Part.java;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{

        //InventoryManagementController
        InventoryManagement inventoryController = new InventoryManagement();
        inventoryController.BeginStart();
    }
    public static void main(String[] args) {
        launch(args);
    }
}