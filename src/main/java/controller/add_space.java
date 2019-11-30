/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import model.schemas.Group;
import view.AddSpace;
/**
 *
 * @author Josué
 */
public class add_space 
{
    public void add_space(AddSpace view)
    {
        String gname;
        Map<String, String> data = new HashMap<>();
        
        data.put("gname", view.getNombre());
        
        gname = data.get("gname");
        int size = gname.length();
        
        if(data.keySet().isEmpty())
        {
          JOptionPane.showMessageDialog(null, "por favor rellene el espacio");
        } 
        else 
        {
            if (size > 45)
            {
                JOptionPane.showMessageDialog(null, "El nombre debe tener menos de 45 caracteres");
                        
            }
            else
            {
                SpaceCRUD nuevo = new SpaceCRUD();
                Group grupo = new Group();         
                
                grupo.setName(gname);
                try
                {
                    nuevo.createspace(grupo);
                }
                catch (Exception e)
                {
                    JOptionPane.showMessageDialog(null, "error");
                }
            }    
        }
    }
}