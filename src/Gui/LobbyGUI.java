/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Client.ClientConnection;
import Server.User;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author BlottoG
 */
public class LobbyGUI {

    //Fields
    ClientConnection currentConnection;
    
    Stage lobbyStage;

    Scene lobbyScene;
    int lobbySceneWidth = 500;
    int lobbySceneHeight = 350;

    HBox mainBox;

    VBox topMenuBox;
    Button gamesButton;
    Button partyButton;
    Button historyButton;

    VBox subMenuGame;
    char selectedGameVariable = '0';

    VBox subMenuParty;
    TextField typingField;

    VBox subMenuHistory;
    TableView<Object> matchTable;

    // parameter object should be user! user used to get username to display
    public LobbyGUI(Object currentUser, ClientConnection currConnect) {
        
        //Creates the connection with the ClientConnection
        currentConnection = currConnect;
        
        //Creates different boxes
        topMenuBoxCreate();

        subMenuGameCreate();
        subMenuPartyCreate();
        subMenuHistoryCreate();

        mainBox = new HBox(topMenuBox, subMenuGame);

        lobbyScene = new Scene(mainBox, lobbySceneWidth, lobbySceneHeight);

        //topmenu (maybe move to topMenuBoxCreate() ???
        topMenuBox.setSpacing(50);
        topMenuBox.setPrefSize(lobbySceneWidth / 2, lobbySceneHeight / 2);
        topMenuBox.setAlignment(Pos.CENTER);

        //Stage
        lobbyStage = new Stage();
        lobbyStage.setTitle("Lobby");
        lobbyStage.setScene(lobbyScene);
        lobbyStage.setResizable(false);
        lobbyStage.show();

    }

    private void subMenuGameCreate() {

        subMenuGame = new VBox();

        //Tictactoe button
        Button ticTacToeButton = new Button("Tic-Tac-Toe");

        ticTacToeButton.setOnAction((ActionEvent event) -> {

            selectedGameVariable = 't';
            
            GameBoard testytest = new GameBoard(currentConnection);

            System.out.println(event.getSource() + " pressed");
        });

        //Temp game2 button
        Button game2Button = new Button("Game2");

        game2Button.setOnAction((ActionEvent event) -> {
            System.out.println(event.getSource() + " pressed");
        });

        //submenu layout
        subMenuGame.setAlignment(Pos.CENTER);
        subMenuGame.setSpacing(30);
        subMenuGame.setPrefSize(lobbySceneWidth / 2, lobbySceneHeight / 2);

        //add nodes to submenu
        subMenuGame.getChildren().add(ticTacToeButton);
        subMenuGame.getChildren().add(game2Button);

    }

    private void subMenuPartyCreate() {

        subMenuParty = new VBox();

        //Invite stuff
        Button inviteButton = new Button("invite");

        inviteButton.setOnAction((ActionEvent event) -> {
            System.out.println(event.getSource() + " pressed");
        });

        //Chat del i partyframe 
        TextArea partyTextArea = new TextArea();
        partyTextArea.setEditable(false);
        
        ChatNode.addChatList(partyTextArea);
        
        ScrollPane partyTextAreaScroll = new ScrollPane(partyTextArea);

        typingField = new TextField();

        typingField.setOnAction((ActionEvent chatEnter) -> {
            
            try {
                
                currentConnection.sendChatText(typingField.getText());
                System.out.println(typingField.getText());
                typingField.clear();
            
            } catch (IOException ex) {
                Logger.getLogger(LobbyGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        });

        //Size of subMenu
        subMenuParty.setPrefSize(lobbySceneWidth / 2, lobbySceneHeight / 2);
        subMenuParty.setAlignment(Pos.CENTER);

        subMenuParty.getChildren().add(inviteButton);
        subMenuParty.getChildren().add(partyTextAreaScroll);
        subMenuParty.getChildren().add(typingField);

    }

    private void subMenuHistoryCreate() {

        subMenuHistory = new VBox();

        //Table over matchhistory
        matchTable = new TableView<>(null);

        //Size of submenu
        subMenuHistory.setPrefSize(lobbySceneWidth / 2, lobbySceneHeight / 2);
        subMenuHistory.getChildren().add(matchTable);

    }

    private void topMenuBoxCreate() {

        topMenuBox = new VBox();

        //Displayname
        Label nameDisplay = new Label("user.Getname goes here!");

        //Games button
        gamesButton = new Button("Games");

        gamesButton.setOnAction((ActionEvent event) -> {
            changeSubMenu(event);
        });

        //Partybutton
        partyButton = new Button("Party");

        partyButton.setOnAction((ActionEvent event) -> {
            changeSubMenu(event);
        });

        //Historybutton
        historyButton = new Button("Match History");

        historyButton.setOnAction((ActionEvent event) -> {
            changeSubMenu(event);
        });

        //Add nodes to submenu
        topMenuBox.getChildren().add(nameDisplay);
        topMenuBox.getChildren().add(gamesButton);
        topMenuBox.getChildren().add(partyButton);
        topMenuBox.getChildren().add(historyButton);

    }

    //Post: changed submenu depending on button pressed
    private void changeSubMenu(ActionEvent event) {

        //remove ccurrent submenu (must be #1)
        mainBox.getChildren().remove(1);

        //Adds new submenu
        if (event.getSource().equals(gamesButton)) {

            mainBox.getChildren().add(subMenuGame);

        } else if (event.getSource().equals(partyButton)) {

            mainBox.getChildren().add(subMenuParty);

        } else if (event.getSource().equals(historyButton)) {

            mainBox.getChildren().add(subMenuHistory);

        }
    }

}
