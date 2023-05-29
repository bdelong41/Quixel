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

public class CloseProgram {
    @FXML
    private Button closeCancelButton;

    private static String tf;
    public String BeginStart(){
        URL url = getClass().getResource("/com/example/inventorysystem/close_program.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        try {
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Save dialogue");
            Scene scene = new Scene(root);
            if(Styles.getTheme() == "Dark") {
                scene.getStylesheets().add(getClass().getResource("/com/example/inventorysystem/css/style.css").toExternalForm());
            }
            stage.setScene(scene);
            stage.showAndWait();
        }catch(Exception e){
            e.printStackTrace();
        }
        return tf;
    }

    public void apply_button_listener(){
        tf = "apply";
        Stage stage = (Stage) closeCancelButton.getScene().getWindow();
        stage.close();
    }

    public void cancel_button_listener(){
        tf = "cancel";
        Stage stage = (Stage) closeCancelButton.getScene().getWindow();
        stage.close();
    }
    public void dontApply_button_listener(){
        tf = "dont apply";
        Stage stage = (Stage) closeCancelButton.getScene().getWindow();
        stage.close();
    }
}
