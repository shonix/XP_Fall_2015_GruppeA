/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Client.ClientConnection;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
        
        
        //VBOX over chatarea
        
        HBox upperBox = new HBox();
        upperBox.setStyle("-fx-background-color: #336699;");
        
        GridPane ticTacLayout = new GridPane();
        
        
        VBox leftBox = new VBox();
        VBox middleBox = new VBox(ticTacLayout);
        VBox rightBox = new VBox();
        
        leftBox.setStyle("-fx-background-color: #331010;");
        middleBox.setStyle("-fx-background-color: #696969;");
        rightBox.setStyle("-fx-background-color: #205588;");
        

        //Size
        int prefWidth = 200;
        int prefHeight = 200;
        
        int ticTacButtonHeight = prefWidth / 3; 
        int ticTacButtonWidth = prefHeight / 3; 
        
        
        //TicTac Buttons
        Button TL = new Button();
        Button TM = new Button();
        Button TR = new Button();
        Button CL = new Button();
        Button CM = new Button();
        Button CR = new Button();
        Button BL = new Button();
        Button BM = new Button();
        Button BR = new Button();
        
        TL.setPrefSize(ticTacButtonWidth, ticTacButtonHeight);
        TM.setPrefSize(ticTacButtonWidth, ticTacButtonHeight);
        TR.setPrefSize(ticTacButtonWidth, ticTacButtonHeight);
        CL.setPrefSize(ticTacButtonWidth, ticTacButtonHeight);
        CM.setPrefSize(ticTacButtonWidth, ticTacButtonHeight);
        CR.setPrefSize(ticTacButtonWidth, ticTacButtonHeight);
        BL.setPrefSize(ticTacButtonWidth, ticTacButtonHeight);
        BM.setPrefSize(ticTacButtonWidth, ticTacButtonHeight);
        BR.setPrefSize(ticTacButtonWidth, ticTacButtonHeight);
        
        
        //Adding TicTacButtons
        ticTacLayout.add(TL, 0, 0);
        ticTacLayout.add(TM, 1, 0);
        ticTacLayout.add(TR, 2, 0);
        ticTacLayout.add(CL, 0, 1);
        ticTacLayout.add(CM, 1, 1);
        ticTacLayout.add(CR, 2, 1);
        ticTacLayout.add(BL, 0, 2);
        ticTacLayout.add(BM, 1, 2);
        ticTacLayout.add(BR, 2, 2);
        
        
        
        leftBox.setPrefSize(prefWidth, prefHeight);
        middleBox.setPrefSize(prefWidth, prefHeight);
        rightBox.setPrefSize(prefWidth, prefHeight);
        
        
        
        
        upperBox.getChildren().addAll(leftBox, middleBox, rightBox);
        
        //upperBox.getChildren().addAll(new Button("1"), new Button("2"), new Button("3"), new Button("4"), new Button("5"), new Button("6"), new Button("7"), new Button("8"), new Button("9"));
        
        gameBox.getChildren().add(upperBox);
        
        TextArea chatTextArea = new TextArea();
        chatTextArea.setEditable(false);
        ScrollPane chatTextAreaScroll = new ScrollPane(chatTextArea);
        gameBox.getChildren().add(chatTextAreaScroll);
        
        
        ChatNode.addChatList(chatTextArea);
        
        
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
        
        
        
        
        
        
        Scene gameScene = new Scene(gameBox,600,400); 
        gameStage.setScene(gameScene);
        gameStage.setResizable(false);
        gameStage.show();
                
    }
}
