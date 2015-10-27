/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

/**
 *
 * @author Elinor-skole
 */
public class GameBoard {
    public GameBoard(){
        Stage gameStage = new Stage();
        VBox gameBox = new VBox();
        
        TextArea chatTextArea = new TextArea();
        chatTextArea.setEditable(false);
        ScrollPane chatTextAreaScroll = new ScrollPane(chatTextArea);
        gameBox.getChildren().add(chatTextAreaScroll);
        
        TextField chat = new TextField();
        gameBox.getChildren().add(chat);
        
        
        
        
        
        
        Scene gameScene = new Scene(gameBox,100,100); 
        gameStage.setScene(gameScene);
        gameStage.show();
                
    }
}
