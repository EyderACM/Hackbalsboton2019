/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import view.setupAreas;
/**
 *
 * @author Josué
 */
public class add_space 
{
    public void validSpace(setupAreas view) throws EmptyException, TooLongException
    {
        String gname;
        Map<String, String> data = new HashMap<>();
        
        data.put("gname", view.getAreaText());
        
        gname = data.get("gname");
        int size = gname.length();
        
        if(data.keySet().isEmpty())
        {
          throw new EmptyException();
        } 
        else 
        {
            if (size > 45)
            {
               throw new TooLongException();             
            }    
        }
    }
    
    public void showExceptions(Exception ex, setupAreas view){
        if(ex instanceof EmptyException){
            JOptionPane.showMessageDialog(null, "por favor rellene el espacio");
        }else if(ex instanceof TooLongException){
            JOptionPane.showMessageDialog(null, "El nombre debe tener menos de 45 caracteres");
        }
    }
}