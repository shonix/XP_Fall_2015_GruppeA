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
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
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
import javax.swing.JOptionPane;

/**
 *
 * @author Elinor-skole
 */
public class GameBoard {

    int tempX;
    int tempY;

    ClientConnection currentConnection;

    static char xoro;

    Button TL;
    Button TM;
    Button TR;
    Button CL;
    Button CM;
    Button CR;
    Button BM;
    Button BL;
    Button BR;

    public GameBoard(ClientConnection currConnec, char faction) {

        System.out.println("Player has chosen " + faction);
        currentConnection = currConnec;

        xoro = faction;

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
        TL = new Button("");
        TM = new Button("");
        TR = new Button("");
        CL = new Button("");
        CM = new Button("");
        CR = new Button("");
        BL = new Button("");
        BM = new Button("");
        BR = new Button("");

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
            if (TL.getText().equals("")) {
                tempX = 0;
                tempY = 0;
                System.out.println("Button pressed at [0,0]");
                System.out.println("Temp er sat til [" + tempX + "," + tempY + "]");

                currConnec.makeMove(tempX, tempY, this);
            } else {
                JOptionPane.showMessageDialog(null, "Feltet er ikke tomt!");
            }

        });

        TM.setOnAction(event -> {
            if (TM.getText().equals("")) {
                tempX = 0;
                tempY = 1;
                System.out.println("Button pressed at [0,1]");
                System.out.println("Temp er sat til [" + tempX + "," + tempY + "]");

                currConnec.makeMove(tempX, tempY, this);
            } else {
                JOptionPane.showMessageDialog(null, "Feltet er ikke tomt!");
            }
        });

        TR.setOnAction(event -> {
            if (TR.getText().equals("")) {
                tempX = 0;
                tempY = 2;
                System.out.println("Button pressed at [0,2]");
                System.out.println("Temp er sat til [" + tempX + "," + tempY + "]");

                currConnec.makeMove(tempX, tempY, this);
            } else {
                JOptionPane.showMessageDialog(null, "Feltet er ikke tomt!");
            }
        });

        CL.setOnAction(event -> {
            if (CL.getText().equals("")) {

                tempX = 1;
                tempY = 0;
                System.out.println("Button pressed at [1,0]");
                System.out.println("Temp er sat til [" + tempX + "," + tempY + "]");

                currConnec.makeMove(tempX, tempY, this);
            } else {
                JOptionPane.showMessageDialog(null, "Feltet er ikke tomt!");
            }
        });
        CM.setOnAction(event -> {
            if (CM.getText().equals("")) {

                tempX = 1;
                tempY = 1;
                System.out.println("Button pressed at [1,1]");
                System.out.println("Temp er sat til [" + tempX + "," + tempY + "]");

                currConnec.makeMove(tempX, tempY, this);
            } else {
                JOptionPane.showMessageDialog(null, "Feltet er ikke tomt!");
            }
        });
        CR.setOnAction(event -> {
            if (CR.getText().equals("")) {

                tempX = 1;
                tempY = 2;
                System.out.println("Button pressed at [1,2]");
                System.out.println("Temp er sat til [" + tempX + "," + tempY + "]");

                currConnec.makeMove(tempX, tempY, this);
            } else {
                JOptionPane.showMessageDialog(null, "Feltet er ikke tomt!");
            }
        });
        BL.setOnAction(event -> {
            if (BL.getText().equals("")) {

                tempX = 2;
                tempY = 0;
                System.out.println("Button pressed at [2,0]");
                System.out.println("Temp er sat til [" + tempX + "," + tempY + "]");

                currConnec.makeMove(tempX, tempY, this);
            } else {
                JOptionPane.showMessageDialog(null, "Feltet er ikke tomt!");
            }
        });
        BM.setOnAction(event -> {
            if (BM.getText().equals("")) {

                tempX = 2;
                tempY = 1;
                System.out.println("Button pressed at [2,1]");
                System.out.println("Temp er sat til [" + tempX + "," + tempY + "]");

                currConnec.makeMove(tempX, tempY, this);
            } else {
                JOptionPane.showMessageDialog(null, "Feltet er ikke tomt!");
            }
        });
        BR.setOnAction(event -> {
            if (BR.getText().equals("")) {

                tempX = 2;
                tempY = 2;
                System.out.println("Button pressed at [2,2]");
                System.out.println("Temp er sat til [" + tempX + "," + tempY + "]");

                currConnec.makeMove(tempX, tempY, this);
            } else {
                JOptionPane.showMessageDialog(null, "Feltet er ikke tomt!");
            }
        });

        //USERNAMES OG VS I LEFTBOX
        Label player1 = new Label(currConnec.getUsername());
        Label player2 = new Label("User.getName2 her");
        Label vs = new Label("VS");

        player1.setPrefHeight(prefHeightTicTac / 3);

        player2.setPrefHeight(prefHeightTicTac / 3);

        vs.setPrefHeight(prefHeightTicTac / 3);

        leftBox.getChildren().addAll(player1, vs, player2);

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

                String text = chat.getText();
                if (InputCheck.isValid("Message", text)) {
                    currentConnection.sendChatText(text);
                    System.out.println(text);
                    chat.clear();

                }

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

    public void setTic(String coords, boolean isLegit) {
        String mark = "T";
        String coordmark = coords;

        if (xoro == 'o') {
            mark = "O";
        } else if (xoro == 'x') {
            mark = "X";
        } else {
            JOptionPane.showMessageDialog(null, "Ingen faction valgt, luk programmet og start forfra.");
        }

        if (isLegit == true) {
            System.out.println("hep1");
             final String finalMark = mark;
            Platform.runLater(() -> {
                switch (coordmark) {
                    case "00":
                        TL.setText(finalMark);
                        break;
                    case "01":
                        TM.setText(finalMark);
                        break;
                    case "02":
                        TR.setText(finalMark);
                        break;
                    case "10":
                        CL.setText(finalMark);
                        break;
                    case "11":
                        CM.setText(finalMark);
                        break;
                    case "12":
                        CR.setText(finalMark);
                        break;
                    case "20":
                        BL.setText(finalMark);
                        break;
                    case "21":
                        BM.setText(finalMark);
                        break;
                    case "22":
                        BR.setText(finalMark);
                        break;

                }
                System.out.println("hep");
            });

        } else {
            JOptionPane.showMessageDialog(null, "Det kan du ikke!");
        }

    }

}
