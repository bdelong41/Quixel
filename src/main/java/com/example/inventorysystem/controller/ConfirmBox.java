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

public class ConfirmBox {
    @FXML
    private Button confirmButton;
    @FXML
    private Button cancelButton;

    //By default tf is false
    private static boolean truefalse = false;

    public boolean BeginStart(){
        URL url = getClass().getResource("/com/example/inventorysystem/confirm_box.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        try{
            Parent root = loader.load();

            Stage stage = new Stage();
            ConfirmBox confirmBox = loader.getController();
            stage.setTitle("Confirm Action");
            Scene scene = new Scene(root);
            if(Styles.getTheme() == "Dark") {
                scene.getStylesheets().add(getClass().getResource("/com/example/inventorysystem/css/style.css").toExternalForm());
            }
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        }catch(Exception e){
            e.printStackTrace();
        }
        return truefalse;
    }

    public void confirm_button_listener(){
        truefalse = true;

        ((Stage) confirmButton.getScene().getWindow()).close();
    }
    public void cancel_button_listener(){
        truefalse = false;

        ((Stage) cancelButton.getScene().getWindow()).close();
    }
}
