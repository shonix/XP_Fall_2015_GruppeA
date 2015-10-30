/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Client.ClientConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Elinor-skole
 */
public class GuiMain extends Application {

    @Override
    public void start(Stage primaryStage) {

        VBox logOnBox = new VBox(5);

        //IP field and label
        VBox ipBox = new VBox();
        Label stringip = new Label("IP");
        TextField ipField = new TextField();
        ipBox.getChildren().add(stringip);
        ipBox.getChildren().add(ipField);

        //Port field and label
        VBox portBox = new VBox();
        Label stringPort = new Label("Port");
        TextField portField = new TextField();
        portBox.getChildren().add(stringPort);
        portBox.getChildren().add(portField);

        //User field and label
        VBox userBox = new VBox();
        Label stringUser = new Label("User");
        TextField user = new TextField();
        userBox.getChildren().add(stringUser);
        userBox.getChildren().add(user);

        //Password field and label
        VBox passwordBox = new VBox();
        Label stringPasswordBox = new Label("Password");
        PasswordField password = new PasswordField();
        passwordBox.getChildren().add(stringPasswordBox);
        passwordBox.getChildren().add(password);

        //Inserts standard field text
        ipField.setText("10.111.176.122");
        portField.setText("7777");
        user.setText("peter");
        password.setText("admin123");

        //adds fields and labels
        logOnBox.getChildren().add(ipBox);
        logOnBox.getChildren().add(portBox);
        logOnBox.getChildren().add(userBox);
        logOnBox.getChildren().add(passwordBox);

        //log on butotn
        Button logOn = new Button();
        logOn.setText("log on");
        logOnBox.getChildren().add(logOn);
        logOn.setOnAction((ActionEvent event) -> {
           
            boolean checkBoolean = false;
            
            if(InputCheck.isValid("IP", ipField.getText()) && 
                    InputCheck.isValid("Port", portField.getText()) &&
                    InputCheck.isValid("User", user.getText()) &&
                    InputCheck.isValid("Password", password.getText())){
                checkBoolean = true;
            }
            
            if (checkBoolean) {
                boolean connectionSuccess = false;

                ClientConnection connectToLogon = new ClientConnection();

                try {
                    connectionSuccess = connectToLogon.makeConnection(user.getText(), password.getText(), ipField.getText(), Integer.parseInt(portField.getText()));
                } catch (Exception ex) {
                    Logger.getLogger(GuiMain.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (connectionSuccess) {

                    LobbyGUI lobby = new LobbyGUI(event, connectToLogon);

                    primaryStage.close();

                } else {
                    Stage notFoundStage = new Stage();
                    notFoundStage.setTitle("Error");
                    Label errorMessage = new Label("Connection Error");
                    HBox errorBox = new HBox();
                    errorBox.getChildren().add(errorMessage);
                    errorBox.setAlignment(Pos.CENTER);
                    Scene notFoundScrene = new Scene(errorBox, 200, 100);
                    notFoundStage.setScene(notFoundScrene);
                    notFoundStage.show();
                }
            } else {
                Stage notFoundStage = new Stage();
                    notFoundStage.setTitle("Error");
                    Label errorMessage = new Label("Error");
                    
                    
                    
                    if(!InputCheck.isValid("IP", ipField.getText())){
                        errorMessage.setText("You fucked up IP"
                                + "\nIP must follow the IPv4 standard format. (eg. 192.168.1.1)");
                    }else if (!InputCheck.isValid("Port", portField.getText())){
                        errorMessage.setText("You fucked up Port"
                                + "\nPort number must be between 2000 - 65000");
                    } else if (!InputCheck.isValid("User", user.getText())){
                        errorMessage.setText("You fucked up User"
                                + "\nUser name must be between 4 - 18 characters long, and can't contain special characters.");
                    } else if (!InputCheck.isValid("Password", password.getText())){
                        errorMessage.setText("You fucked up Password"
                                + "\nPassword must contain both letters and numbers, and must be between 8 - 20 characters long.");
                    } else {
                        errorMessage.setText("Someone fucked up somewhere unknown");
                    }
                    
                    HBox errorBox = new HBox();
                    errorBox.getChildren().add(errorMessage);
                    errorBox.setAlignment(Pos.CENTER);
                    Scene notFoundScrene = new Scene(errorBox);
                    notFoundStage.setScene(notFoundScrene);
                    notFoundStage.show();
            }
        });

        
        Scene scene = new Scene(logOnBox);
        primaryStage.setTitle("log on");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
