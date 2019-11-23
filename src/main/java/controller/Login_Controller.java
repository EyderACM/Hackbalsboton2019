package controller;

import controller.exceptions.EmptyException;
import controller.exceptions.TooLongException;
import view.Login;

import java.util.*;

public class Login_Controller {

    public void LoginUser(Login view) throws EmptyException, TooLongException {
        this.model = new Model();
        Map<String, String> data = new HashMap<>();

        data.put("user", view.getEmailText());
        //data.put("password", view.getPassword_Input());  //Vi estas dos propiedades en la presentacion del View sobre sus avances.

        if (!validCompleteness(data)) // Esta funcion de Alex
        {
            throw new EmptyException();
        }
        else if (data.get("user").length() > 45) // Supongo que lo mismo que aplica para espacios aplica para usuarios
        {
            throw new TooLongException();
        }
        else
        {
            model.login(data); // Supongo que el Pseudo API del model tiene esto por algun lado.
            // Aqui podria tenerse una funcion async para saber si el login salio bien
            // O podriamos tener una funcion aparte que el model utilize en nosotros para
            // avisar al view
        }
    }

    //Validate that there is no empty information
    public boolean validCompleteness(Map<String,String> data){
        boolean isComplete = false;
        Set<String> keys = data.keySet();
        for(String key: keys){
            if(!data.get(key).isBlank()){ //Checks that there is no empty information
                isComplete = true;
            }
        }

        return isComplete;
    }
}

}
