/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Client.ClientConnection;
import java.io.IOException;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author Elinor-skole
 */
public class GameBoard {

    int tempX;
    int tempY;

    Label timerLabel;
    int fullTurn = 30;

    ClientConnection currentConnection;

    public GameBoard(ClientConnection currConnec) {

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

        Scene gameScene = new Scene(gameBox, 600, 400);
        gameStage.setScene(gameScene);
        gameStage.setResizable(false);
        gameStage.show();

    }

    //DENNE KONSTRUKTOR ER KUN TIL TEST, MEN DENS METODER ER DE NYESTE! SKRIV IKKE NOGEN STEDER UDEN AT SPØRGE HR. EMIL!
    public GameBoard() {

        Stage gameStage = new Stage();
        VBox gameBox = new VBox();

        //VBOX over chatarea
        HBox upperBox = new HBox();
        upperBox.setStyle("-fx-background-color: #336699;");

        GridPane ticTacLayout = new GridPane();

        VBox leftBox = new VBox();
        VBox middleBox = new VBox(ticTacLayout);
        VBox rightBox = new VBox();

        leftBox.setStyle("-fx-background-color: #FF1010;");
        middleBox.setStyle("-fx-background-color: #696969;");
        rightBox.setStyle("-fx-background-color: #205588;");

        //Size
        int prefWidthTicTac = 300;
        int prefHeightTicTac = 300;
        int prefWidthStandard = 200;
        int prefHeightStandard = 200;

        int ticTacButtonHeight = prefWidthTicTac / 3;
        int ticTacButtonWidth = prefHeightTicTac / 3;

        //TicTac Buttons
        Button TL = new Button("");
        Button TM = new Button("");
        Button TR = new Button("");
        Button CL = new Button("");
        Button CM = new Button("");
        Button CR = new Button("");
        Button BL = new Button("");
        Button BM = new Button("");
        Button BR = new Button("");

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

        leftBox.setPrefSize(prefWidthStandard, prefHeightStandard);
        middleBox.setPrefSize(prefWidthTicTac, prefHeightTicTac);
        rightBox.setPrefSize(prefWidthStandard, prefHeightStandard);

        //ButtonActions
        TL.setOnAction(event -> {
            tempX = 0;
            tempY = 0;
            System.out.println("Button pressed at [0,0]");
            System.out.println("Temp er sat til [" + tempX + "," + tempY + "]");

            setTic(TL);

        });

        TM.setOnAction(event -> {
            tempX = 0;
            tempY = 1;
            System.out.println("Button pressed at [0,1]");
            System.out.println("Temp er sat til [" + tempX + "," + tempY + "]");

            setTic(TM);
        });

        TR.setOnAction(event -> {
            tempX = 0;
            tempY = 2;
            System.out.println("Button pressed at [0,2]");
            System.out.println("Temp er sat til [" + tempX + "," + tempY + "]");

            setTic(TR);
        });

        CL.setOnAction(event -> {
            tempX = 1;
            tempY = 0;
            System.out.println("Button pressed at [1,0]");
            System.out.println("Temp er sat til [" + tempX + "," + tempY + "]");

            setTic(CL);
        });
        CM.setOnAction(event -> {
            tempX = 1;
            tempY = 1;
            System.out.println("Button pressed at [1,1]");
            System.out.println("Temp er sat til [" + tempX + "," + tempY + "]");

            setTic(CM);
        });
        CR.setOnAction(event -> {
            tempX = 1;
            tempY = 2;
            System.out.println("Button pressed at [1,2]");
            System.out.println("Temp er sat til [" + tempX + "," + tempY + "]");

            setTic(CR);
        });
        BL.setOnAction(event -> {
            tempX = 2;
            tempY = 0;
            System.out.println("Button pressed at [2,0]");
            System.out.println("Temp er sat til [" + tempX + "," + tempY + "]");

            setTic(BL);
        });
        BM.setOnAction(event -> {
            tempX = 2;
            tempY = 1;
            System.out.println("Button pressed at [2,1]");
            System.out.println("Temp er sat til [" + tempX + "," + tempY + "]");

            setTic(BM);
        });
        BR.setOnAction(event -> {
            tempX = 2;
            tempY = 2;
            System.out.println("Button pressed at [2,2]");
            System.out.println("Temp er sat til [" + tempX + "," + tempY + "]");

            setTic(BR);
        });

        //USERNAMES OG VS I LEFTBOX
        Label player1 = new Label("User.getName her");
        Label player2 = new Label("User.getName2 her");
        Label vs = new Label("VS");

        player1.setPrefHeight(prefHeightTicTac / 3);

        player2.setPrefHeight(prefHeightTicTac / 3);

        vs.setPrefHeight(prefHeightTicTac / 3);

        leftBox.getChildren().addAll(player1, vs, player2);

        //TimeBox og antal moves i RIGHTBOX
        timerLabel = new Label();
        timerLabel.setPrefHeight(prefHeightTicTac / 2);
        Button startGame = new Button("Start game!");
        startGame.setOnAction(event -> {
            startTurnTimer();
        });

        rightBox.getChildren().addAll(startGame, timerLabel);

        //Add alle topboxes til upperBox
        upperBox.getChildren().addAll(leftBox, middleBox, rightBox);

        //upperBox.getChildren().addAll(new Button("1"), new Button("2"), new Button("3"), new Button("4"), new Button("5"), new Button("6"), new Button("7"), new Button("8"), new Button("9"));
        gameBox.getChildren().add(upperBox);

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

        Scene gameScene = new Scene(gameBox, 600, 400);
        gameStage.setScene(gameScene);
        gameStage.setResizable(false);
        gameStage.show();

    }

    private void setTic(Button pressedButton) {

        if (pressedButton.getText() == "") {
            pressedButton.setText("X");
        } else if (pressedButton.getText() == "X") {
            pressedButton.setText("O");

        } else if (pressedButton.getText() == "O") {
            pressedButton.setText("");

        }

    }

    private void startTurnTimer() {
        Countdown startCountdown = new Countdown(timerLabel, fullTurn);

        startCountdown.run();
    }

}
