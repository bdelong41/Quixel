package com.example.inventorysystem.controller;

import com.example.inventorysystem.models.Styles;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;

public class PartPicker {
    @FXML
    private Button outsourcedButton;
    @FXML
    private Button inhouseButton;
    @FXML
    private Button closeCancelButton;
    private static int choice;
    public int BeginStart(){
        URL url = getClass().getResource("/com/example/inventorysystem/part_picker.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        try {
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("PartPicker dialogue");
            Scene scene = new Scene(root);
            if(Styles.getTheme() == "Dark") {
                scene.getStylesheets().add(getClass().getResource("/com/example/inventorysystem/css/style.css").toExternalForm());
            }
            stage.setScene(scene);
            stage.showAndWait();
        }catch(Exception e){
            e.printStackTrace();
        }
        return choice;
    }

    public void outsourced_button_listener(){
        choice = 1;
        Stage stage = (Stage) closeCancelButton.getScene().getWindow();
        stage.close();
    }

    public void inhouse_button_listener(){
        choice = 0;
        Stage stage = (Stage) closeCancelButton.getScene().getWindow();
        stage.close();
    }
    public void dontApply_button_listener(){
        choice = -1;
        Stage stage = (Stage) closeCancelButton.getScene().getWindow();
        stage.close();
    }
}
