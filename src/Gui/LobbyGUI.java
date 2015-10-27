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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author BlottoG
 */
public class LobbyGUI {
    
    Stage lobbyStage;
    Scene lobbyScene;
    
    HBox mainBox;
    
    VBox topMenuBox;
    
    VBox subMenuGame;
    VBox subMenuConnection;
    VBox subMenuParty;
    VBox subMenuHistory;
    
    // parameter object should be user! user used to get username to display
    public LobbyGUI(Object currentUser){
        
        mainBox = new HBox(topMenuBox, subMenuConnection);
        
        lobbyScene = new Scene(mainBox);
        
        
        
        lobbyStage.setScene(lobbyScene);
        lobbyStage.show();
        
    }
    
}
