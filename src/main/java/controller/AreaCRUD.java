/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.HashMap;
import java.util.Set;
import model.crud.SpacesCRUD;
import model.schemas.Area;

/**
 *
 * @author raulrivadeneyra
 */
public class AreaCRUD {
    
    HashMap<String, String> areas;
    SpacesCRUD model;
    Area area;
    
    public AreaCRUD(){
        areas = new HashMap<>();
        model = new SpacesCRUD();
    }
    
    private void getDataFromModel(){
        for (int i = 0; i < 10; i++) {
            areas.put(""+i, "Area#"+i);
        }
    }
    
    
    public void sendDataToModel(HashMap<String, String> temp_areas){      
        Set<String> keys = temp_areas.keySet();
        
        for(String key : keys){
            area = new Area();
            area.setName(temp_areas.get(key));
            model.createArea(area);
        }
    }
    
    
    public HashMap<String, String> getDataFromController(){
        getDataFromModel();
        return areas;
    }
}
