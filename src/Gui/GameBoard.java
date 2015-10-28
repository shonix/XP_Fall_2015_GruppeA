/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Client.ClientConnection;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
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
    
    ClientConnection currentConnection;
    
    public GameBoard(ClientConnection currConnec){
        
        currentConnection = currConnec;
        
        Stage gameStage = new Stage();
        VBox gameBox = new VBox();
        
        TextArea chatTextArea = new TextArea();
        chatTextArea.setEditable(false);
        ScrollPane chatTextAreaScroll = new ScrollPane(chatTextArea);
        gameBox.getChildren().add(chatTextAreaScroll);
        
        
        
        TextField chat = new TextField();
        
        chat.setOnAction((ActionEvent chatEnter) -> {
            
            try {
                
                currentConnection.sendChatText(chat.getText());
                System.out.println(chat.getText());
                chat.clear();
            
            } catch (IOException ex) {
                Logger.getLogger(LobbyGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        });
        
        
        gameBox.getChildren().add(chat);
        
        
        
        
        
        
        Scene gameScene = new Scene(gameBox,100,100); 
        gameStage.setScene(gameScene);
        gameStage.show();
                
    }
}
