
package controller;

//recibe,valida y envia los parametros para agregar un usuario

import controller.exceptions.EmptyException;
import model.crud.UserCRUD;
import model.schemas.User;
import view.Register;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.swing.JOptionPane;

public class add_user 
{
    public void nuevo_usuario(Register view) throws EmptyException
    {
        int flag=0;
        
       User nuser = new User();
       
       nuser.setName(view.getNombre());
       nuser.setEmail(view.getCorreo());
       nuser.setPassword(view.getContrasena());
       
        Map<String, String> data = new HashMap<>();
        data.put("nombre", view.getNombre());
        data.put("correo", view.getCorreo());
        data.put("contrasena", view.getContrasena());
        data.put("conf_contrasena", view.getConfcontrasena());
        
        //confirmacion de todos los espacios fueron rellenados
        try
        {
            Set<String> llave = data.keySet();
            for (String key : llave) 
            {
                if(data.get(key).isEmpty())
                {
                    throw new NullPointerException();
                } 
                else 
                {
                   flag +=1;
                }
            }
        }
        catch(NullPointerException ex)
        {
            JOptionPane.showMessageDialog(null,"rellene todos los espacios por favor");
        }
        
        //confirmacion de contrase?a
        if(data.get("contrasena").equals(data.get("conf_contrasena")))
        {
            flag+=1;
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Las contrase?as no coinciden");
        }
        
        //verificacion de que es un correo
        int verifi=0;
        verifi=data.get("correo").indexOf("@");
        
        if (verifi!=-1)
        {
            flag+=1;
        }
        else
        {
            JOptionPane.showMessageDialog(null, "no se reconoce el correo intente de nuevo");
        }
        
        //envia la informacion si todo es correcto
        if(flag==3)
        {
            UserCRUD crudnu = new UserCRUD();
            crudnu.createUser(nuser);
        }
        
        flag=0;
    }
   
}       
