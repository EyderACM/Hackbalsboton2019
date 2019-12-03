/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
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
    
    //Enviar al modelo las areas a añadir una por una
    public void sendNewArea(ArrayList<String> toAdd){      
        for(int i = 0; i<toAdd.size(); i++){
            area = new Area();
            area.setName(toAdd.get(i));
            model.createArea(area);
        }
    }
    
    //Juntar los id's de las areas a eliminar en un string separadas por ','
    public void deleteExistingArea(ArrayList<String> toDelete){
        String deleted = "";
        String id;
        String joinId;
        
        for(int i= 0; i<toDelete.size(); i++){
            area = model.getAreaByName(toDelete.get(i));
            id = String.valueOf(area.getId());
            joinId = deleted.concat( id + "," );
            model.deleteArea(joinId);
        }
    }
    
    
    public HashMap<String, String> getDataFromController(){
        getDataFromModel();
        return areas;
    }
}
