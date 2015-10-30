/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Client.ClientConnection;
import java.io.IOException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.stage.WindowEvent;

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
    Button logOutButton;

    VBox subMenuGame;
    char selectedGameVariable = '0';
    char teamToPlay = 't';

    VBox subMenuParty;
    TableView partyTable;
    TextField partyTypeField;
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

        //Stage
        lobbyStage = new Stage();
        lobbyStage.setTitle("Lobby");
        lobbyStage.setScene(lobbyScene);
        lobbyStage.setResizable(false);
        lobbyStage.show();

        lobbyStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                currentConnection.closeConnection();
                System.exit(0);
            }
        });

    }

    private void subMenuGameCreate() {

        
        subMenuGame = new VBox();
        HBox xoroBox = new HBox();

        //Tictactoe button
        Button ticTacToeButton = new Button("Start Tic-Tac-Toe");

        ticTacToeButton.setOnAction((ActionEvent event) -> {

            selectedGameVariable = 't';

            GameBoard testytest = new GameBoard(currentConnection, teamToPlay);

            System.out.println(event.getSource() + " pressed");
        });

        //x team button
        Button xTeamButton = new Button("Play as x");

        xTeamButton.setOnAction((ActionEvent event) -> {

            teamToPlay = 'x';

        });
        //o team button
        Button oTeamButton = new Button("Play as o");

        oTeamButton.setOnAction((ActionEvent event) -> {

            teamToPlay = 'o';

        });

        //add to xoroBox
        xoroBox.setAlignment(Pos.CENTER);
        xoroBox.setPrefWidth(lobbySceneWidth / 2);
        xoroBox.getChildren().add(xTeamButton);
        xoroBox.getChildren().add(oTeamButton);

        //submenu layout
        subMenuGame.setAlignment(Pos.CENTER);
        subMenuGame.setSpacing(30);
        subMenuGame.setPrefSize(lobbySceneWidth / 2, lobbySceneHeight / 2);

        //add nodes to submenu
        subMenuGame.getChildren().add(ticTacToeButton);
        subMenuGame.getChildren().add(xoroBox);

    }

    private void subMenuPartyCreate() {

        subMenuParty = new VBox();

        HBox inviteBox = new HBox();

        //Invite stuff
        Button inviteButton = new Button("invite");

        inviteButton.setOnAction((ActionEvent event) -> {
            currentConnection.requestParty(partyTypeField.getText());
            partyTypeField.clear();
        });

        partyTable = new TableView();
        partyTable.setPrefHeight(lobbySceneHeight / 4);
        //invite type field
        partyTypeField = new TextField();
        partyTypeField.setOnAction((ActionEvent partyEnter) -> {

            currentConnection.requestParty(partyTypeField.getText());
            partyTypeField.clear();

        });

        inviteBox.getChildren().add(partyTypeField);
        inviteBox.getChildren().add(inviteButton);

        //Chat del i partyframe 
        TextArea partyTextArea = new TextArea();
        partyTextArea.setEditable(false);

        ChatNode.addChatList(partyTextArea);

        ScrollPane partyTextAreaScroll = new ScrollPane(partyTextArea);

        typingField = new TextField();

        typingField.setOnAction((ActionEvent chatEnter) -> {

            try {
                if(InputCheck.isValid("Message", typingField.getText())){
                currentConnection.sendChatText(typingField.getText());
                typingField.clear();
                } 
                    
                
            } catch (IOException ex) {
                System.out.println("sendChatText() fail");
            }

        });

        //Size of subMenu
        subMenuParty.setPrefSize(lobbySceneWidth / 2, lobbySceneHeight / 2);
        subMenuParty.setAlignment(Pos.CENTER);
        subMenuParty.setSpacing(5);

        subMenuParty.getChildren().add(partyTable);
        subMenuParty.getChildren().add(inviteBox);
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
        Label nameDisplay = new Label(currentConnection.getUsername());

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

        //LogOutbutton
        logOutButton = new Button("Exit");

        logOutButton.setOnAction((ActionEvent event) -> {
            currentConnection.closeConnection();
            lobbyStage.close();
            System.exit(0);

        });

        //topmenu settings
        topMenuBox.setSpacing(50);
        topMenuBox.setPrefSize(lobbySceneWidth / 2, lobbySceneHeight / 2);
        topMenuBox.setAlignment(Pos.CENTER);

        //Add nodes to submenu
        topMenuBox.getChildren().add(nameDisplay);
        topMenuBox.getChildren().add(gamesButton);
        topMenuBox.getChildren().add(partyButton);
        topMenuBox.getChildren().add(historyButton);
        topMenuBox.getChildren().add(logOutButton);

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
