/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.HashMap;

/**
 *
 * @author raulrivadeneyra
 */
public class AreaCRUD {
    HashMap<String, String> areas;
    public AreaCRUD(){
        areas = new HashMap<>();
    }
    private void getDataFromModel(){
        for (int i = 0; i < 10; i++) {
            areas.put(""+i, "Area#"+i);
        }
    }
    public void sendDataToModel(HashMap<String, String> temp_areas){
        
    }
    public HashMap<String, String> getDataFromController(){
        getDataFromModel();
        return areas;
    }
}
