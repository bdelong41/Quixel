package com.example.inventorysystem.controller;

import com.example.inventorysystem.data.DataConnection;
import com.example.inventorysystem.models.InHouse;
import com.example.inventorysystem.models.Outsourced;
import com.example.inventorysystem.models.Part;
import com.example.inventorysystem.models.Styles;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;

public class ConnectionController {
    //TextFields
    @FXML
    private PasswordField password;
    @FXML
    private TextField serverName;
    @FXML
    private TextField portNumber;
    @FXML
    private TextField dBName;
    @FXML
    private TextField userName;

    public void initialize(){
    }

    public void BeginStart(){
        URL url = getClass().getResource("/com/example/inventorysystem/connection.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        try {
            Parent root = loader.load();
            ConnectionController controller = loader.getController();
            //Setting id_textField to id.
            Stage stage = new Stage();
            stage.setTitle("Add Connetion");
            Scene scene = new Scene(root);
            if(Styles.getTheme() == "Dark") {
                scene.getStylesheets().add(getClass().getResource("/com/example/inventorysystem/css/style.css").toExternalForm());
            }
            stage.setScene(scene);
            //overloading default close operations
            stage.setOnCloseRequest(e->{
                e.consume();
                //Transferring closing protocols to Close_program()
                Close_program(stage);
            });
            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return boolean
     */
    public void save_button_listener(){
        if(validate()){
            DataConnection.setServerName(serverName.getText());
            DataConnection.setPort(portNumber.getText());
            DataConnection.setDatabaseName(dBName.getText());
            DataConnection.setUname(userName.getText());
            DataConnection.setPasswd(password.getText());
            Close_program((Stage) userName.getScene().getWindow());
        }
    }


    /**
     * Takes Stage instance as arg
     * This function takes a stage instance as argument and calls its close() function. This fixed an issue with calling
     * Close_program from within BeginStart() as a Stage instance with a value of null would be called instead.
     * @param stage Stage
     */
    public void Close_program(Stage stage){
        stage.close();
    }

    public void Cancel_Button_Listener(){
        Close_program((Stage) userName.getScene().getWindow());
    }

    public void Test_connection(){
        if(validate()){
            DataConnection.setServerName(serverName.getText());
            DataConnection.setPort(portNumber.getText());
            DataConnection.setDatabaseName(dBName.getText());
            DataConnection.setUname(userName.getText());
            DataConnection.setPasswd(password.getText());
            if(DataConnection.checkConnection() != null){
                ErrorDialogueBox successBox = new ErrorDialogueBox();
                successBox.BeginStart("Connection Succeeded!", "Success");
            }
            
//
//            try {
//                if (DataConnection.getDBName() != null) {
//                    ErrorDialogueBox errorDialogueBox = new ErrorDialogueBox();
//                    errorDialogueBox.BeginStart("Connection Succeeded!", "Connection Successfull");
//                }else{
//                    ErrorDialogueBox err = new ErrorDialogueBox();
//                    err.BeginStart("Error! Failed to connect to database");
//                }
//            }catch (Exception e){
//                ErrorDialogueBox err = new ErrorDialogueBox();
//                err.BeginStart("Error! Failed to connect to database");
//            }
//
//            //restoring previous state
//            DataConnection.setUname(uname);
//            DataConnection.setPasswd(passwd);
//            DataConnection.setServerName(server_Name);
//            DataConnection.setPort(port);
//            DataConnection.setDatabaseName(database_Name);
        }


    }
    public boolean validate(){
        if(serverName.getText() == null || serverName.getText().isEmpty()){
            genError("Error! 'Server' cannot be blank");
            serverName.setStyle("-fx-text-box-border: red");
        }

        else if(portNumber.getText() == null || portNumber.getText().isEmpty()){
            genError("Error! 'Port Number' cannot be blank");
            portNumber.setStyle("-fx-text-box-border: red");
            serverName.setStyle("");
        }

        else if(dBName.getText() == null || dBName.getText().isEmpty()){
            genError("Error! 'Database Name' cannot be blank");
            dBName.setStyle("-fx-text-box-border: red");
            serverName.setStyle("");
            portNumber.setStyle("");
        }

        else if(userName.getText() == null || userName.getText().isEmpty()){
            genError("Error! 'User Name' field cannot be blank");
            userName.setStyle("-fx-text-box-border: red");
            serverName.setStyle("");
            portNumber.setStyle("");
            dBName.setStyle("");
        }
        else {
            serverName.setStyle("");
            portNumber.setStyle("");
            dBName.setStyle("");
            userName.setStyle("");
            return true;
        }
        return false;
    }

    public void genError(String error){
        ErrorDialogueBox errorDialogueBox = new ErrorDialogueBox();
        errorDialogueBox.BeginStart(error);
    }


}
