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
        ipField.setText("10.111.176.104");
        portField.setText("7777");
        user.setText("peter");
        password.setText("admin2");
        
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
            
            boolean connectionSuccess = false;
            
            ClientConnection connectToLogon = new ClientConnection();
            
            try {
                connectionSuccess = connectToLogon.makeConnection(user.getText(), password.getText(), ipField.getText(), Integer.parseInt(portField.getText()));
            } catch (Exception ex) {
                Logger.getLogger(GuiMain.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if(connectionSuccess){
                
                LobbyGUI lobby = new LobbyGUI(event, connectToLogon);
                
                primaryStage.close();
                
            }else{
                Stage notFoundStage = new Stage();
                notFoundStage.setTitle("Error");
                Label errorMessage = new Label("Error");
                HBox errorBox = new HBox();
                errorBox.getChildren().add(errorMessage);
                errorBox.setAlignment(Pos.CENTER);
                Scene notFoundScrene = new Scene(errorBox, 200, 100);
                notFoundStage.setScene(notFoundScrene);
                notFoundStage.show();
            }
        });
        
        
        //Offline mode button
        Button offMode = new Button();
        offMode.setText("OfflineMode");
        logOnBox.getChildren().add(offMode);  
        offMode.setOnAction((ActionEvent event) -> {
//            GameBoard test = new GameBoard();
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
