
package controller;

//recibe,valida y envia los parametros para agregar un usuario

import controller.exceptions.EmptyException;
import model.crud.UserCRUD;
import model.schemas.User;
import view.Register;

import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.swing.JOptionPane;

public class add_user 
{
    public void nuevo_usuario(Register view) throws EmptyException
    {
        UserCRUD model = new UserCRUD();
        User newUser = new User();

        Map<String,String> data = new HashMap<>();
        data.put("name", view.getNombre());
        data.put("email", view.getCorreo());
        data.put("password", view.getContrasena());
        data.put("password_confirm", view.getConfcontrasena());

        if(!validCompleteness(data)){
            throw new EmptyException();

        }else if(!data.get("password").equals(data.get("password_confirm"))){
            throw new EmptyException();

        }else{

            newUser.setName(data.get("name"));
            newUser.setEmail(data.get("email"));
            newUser.setPassword(data.get("password"));

            try{
                model.createUser(newUser);
            }
            catch(Exception ex){
                System.out.println("Salió mal");
            }
        }

        
    }

    public boolean validCompleteness(Map<String,String> data){
        boolean isComplete = false;
        Set<String> keys = data.keySet();
        for(String key: keys){
            if(!data.get(key).isEmpty()){ //Checks that there is no empty information
                isComplete = true;
            }
        }

        return isComplete;
    }

}
