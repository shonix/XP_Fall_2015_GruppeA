/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.util.ArrayList;
import javafx.scene.control.TextArea;

/**
 *
 * @author BlottoG
 */
public class ChatNode {
    
    private static ArrayList<TextArea> chatList = new ArrayList<>();

    public static ArrayList<TextArea> getChatList() {
        return chatList;
    }

    public static void removeFromChatList(TextArea areaToRemove){
        chatList.remove(areaToRemove);
    }
    
    public static void addChatList(TextArea areaToAdd) {
        chatList.add(areaToAdd);
    }
    
    public static void updateChats(String userTexting, String message){
        
        for(TextArea chat : chatList){
            chat.appendText(userTexting + ": " + message);
        }
        
    }
    
}
