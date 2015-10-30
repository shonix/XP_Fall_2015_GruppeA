/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Elinor-skole
 */
public class TickTackLogic {
    private static ArrayList<Point[]> allWinCon = new ArrayList<>(); // all win condition
    private static String[][] board;// game board String to be replaced by user obj
    private static Point[] tempWinCon; // temp space to make win condition  
    private static int turnInTotal;// total turn in game
    private static int currentTurn;// currerent turn
    private static String winner;// winner of game, to be replaced by user obj
    public static void main (String []args){
        makeTTWinCon(3);
        System.out.println(makeMove(new Point(1,1), "else"));
        System.out.println(makeMove(new Point(1,2), "else"));
        System.out.println(makeMove(new Point(1,2), "bent"));
        System.out.println(testForWin());
        System.out.println(makeMove(new Point(1,0), "else"));
        System.out.println(testForWin());
        
        

    }
    public static void makeTTWinCon(int gameSize){
        turnInTotal = gameSize * gameSize;
        board = new String[gameSize][gameSize];
        for(int x = 0; x < gameSize; x++  ){
            tempWinCon = new Point[gameSize];// win con for vertical
            for(int y = 0 ; y < gameSize; y ++)
            {
                tempWinCon[y] = new Point(x,y);
            }
            allWinCon.add(tempWinCon);
        }
        for(int x = 0; x < gameSize; x++  ){
            tempWinCon = new Point[gameSize];// win con horizontal
            for(int y = 0 ; y < gameSize; y ++)
            {
                tempWinCon[y] = new Point(y,x);
            }
            allWinCon.add(tempWinCon); 
        }
        tempWinCon = new Point[gameSize];// win con for 0,0 -> gameSize-1, gameSize-1
        for(int x = 0 ; x < gameSize ; x++ ){
            tempWinCon[x] = new Point(x,x); 
        }
        allWinCon.add(tempWinCon);
        tempWinCon = new Point[gameSize];// win con for 0,gameSize-1 -> gameSize-1,0
        for(int x = 0 ; x < gameSize ; x++ ){
            tempWinCon[x] = new Point(x,gameSize-x-1);  
        }
        allWinCon.add(tempWinCon);
    }
    public static boolean makeMove(Point point, String name){//test if move is legit, string = user obj
        if(board [(int)point.getX()][(int)point.getY()] == null){// space is empty
            board [(int)point.getX()][(int)point.getY()] = name;
            currentTurn++;//turn count
            return true;
        }
        return false;
    }
    public static boolean testForWin(String name){// has the game end, string to be replaced by user obj
        if(turnInTotal < currentTurn){
           return false; 
        }

        outerloop:for(Point[] pointArray : allWinCon){// test win cindition BUG
            int winnerTest = 0;
            winner = null;
            for(Point testPoint : pointArray ){//BUg 
               if(winner == null || winner == board [(int)testPoint.getX()][(int)testPoint.getY()]){
                   winner = board [(int)testPoint.getX()][(int)testPoint.getY()];
                   System.out.println(winnerTest++);
                   
               } 
               else{
                   break outerloop;
               }
               if(winnerTest == 3){
                   System.out.println(winner);
                   return true;  
               }

            }
 
        }
        return false;
    }
}
