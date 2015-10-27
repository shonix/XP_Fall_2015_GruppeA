/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import xp_fall_2015_gruppea.GameBoard;

/**
 *
 * @author Elinor-skole
 */
public class GuiMain extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        VBox logOnBox = new VBox(5);

        VBox userBox = new VBox();
        Label stringUser = new Label("User");
        TextField user = new TextField();
        userBox.getChildren().add(stringUser);
        userBox.getChildren().add(user);
        
        VBox passwordBox = new VBox();
        Label stringPasswordBox = new Label("Password");
        PasswordField password = new PasswordField();
        passwordBox.getChildren().add(stringPasswordBox);
        passwordBox.getChildren().add(password);
        
        logOnBox.getChildren().add(userBox);
        logOnBox.getChildren().add(passwordBox);
 
        
        Button logOn = new Button();
        logOn.setText("log on");
        logOnBox.getChildren().add(logOn);  
        logOn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                if(true){
                    GameBoard game = new GameBoard();
                }else{
                  Stage notFoundStage = new Stage();
                  notFoundStage.setTitle("Error");
                  Label errorMessage = new Label("Error");
                  HBox errorBox = new HBox();
                  errorBox.getChildren().add(errorMessage);
                  Scene notFoundScrene = new Scene(errorBox, 125, 100);
                  notFoundStage.setScene(notFoundScrene);
                  notFoundStage.show();
                }
                

            }
        });
        
      
        Scene scene = new Scene(logOnBox, 300, 250);
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
