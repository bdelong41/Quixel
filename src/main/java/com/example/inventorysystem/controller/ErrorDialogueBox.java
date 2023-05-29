package com.example.inventorysystem.controller;
import com.example.inventorysystem.models.Styles;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;

public class ErrorDialogueBox {
    @FXML
    private Label errorMessageLabel;
    @FXML
    private Button okButton;

    public void BeginStart(String errorMessage){
        URL url = getClass().getResource("/com/example/inventorysystem/error_dialogue_box.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        try {
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);

            //Creating ErrorDialogueBox instance
            ErrorDialogueBox errorDialogue = loader.getController();
            errorDialogue.set_error_message(errorMessage);
            stage.setTitle("Error");
            Scene scene = new Scene(root);
            if(Styles.getTheme() == "Dark") {
                scene.getStylesheets().add(getClass().getResource("/com/example/inventorysystem/css/style.css").toExternalForm());
            }
            stage.setScene(scene);
            stage.showAndWait();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void BeginStart(String message, String title){
        URL url = getClass().getResource("/com/example/inventorysystem/error_dialogue_box.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        try {
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);

            //Creating ErrorDialogueBox instance
            ErrorDialogueBox errorDialogue = loader.getController();
            errorDialogue.set_error_message(message);
            stage.setTitle(title);
            Scene scene = new Scene(root);
            if(Styles.getTheme() == "Dark") {
                scene.getStylesheets().add(getClass().getResource("/com/example/inventorysystem/css/style.css").toExternalForm());
            }
            stage.setScene(scene);
            stage.showAndWait();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void Ok_button_listener(){
        ((Stage) okButton.getScene().getWindow()).close();
    }

    public void set_error_message(String errorMessage){
        errorMessageLabel.setText(errorMessage);
        errorMessageLabel.setWrapText(true);
    }
}
