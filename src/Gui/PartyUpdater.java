/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.util.ArrayList;
import javafx.scene.control.TableView;

/**
 *
 * @author BlottoG
 */
public class PartyUpdater {
    
    private static ArrayList<TableView> tableList = new ArrayList<>();

    public static ArrayList<TableView> getTableList() {
        return tableList;
    }

    public static void setTableList(TableView tabList) {
        PartyUpdater.tableList.add(tabList);
    }
    
    public static void updatePartyTable(){
        
        for(TableView partyT : tableList){
            
        }
        
    }
    
    
    
}
