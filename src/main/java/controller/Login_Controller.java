package controller;

import controller.exceptions.EmptyException;
import controller.exceptions.TooLongException;
import model.crud.UserCRUD;
import model.schemas.User;
import view.Login;

import javax.swing.*;
import java.util.*;

public class Login_Controller {

    UserCRUD model = new UserCRUD();

    public void LoginUser(Login view) throws EmptyException, TooLongException {

        User user = new User();

        Map<String, String> data = new HashMap<>();
        System.out.println(view.getEmailText());
        System.out.println(view.getPasswordText());
        data.put("user", view.getEmailText());
        //data.put("password", view.getPassword_Input());  //Vi estas dos propiedades en la presentacion del View sobre sus avances.
        data.put("email", view.getEmailText());
        data.put("password", view.getPasswordText());

        if (!validCompleteness(data))
        {
            throw new EmptyException();
        }
        else if (data.get("user").length() > 45)
        {
            throw new TooLongException();
        }
        else
        {
            try{
                user = model.getUser(data.get("email"));
                validateLogin(user, view);
            }catch(Exception e){
                JOptionPane.showMessageDialog(
                        view, "Login Error" , "ERROR", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    public void validateLogin(User user, Login view){
        if(user.getPassword() == view.getPasswordText()){
            JOptionPane.showMessageDialog(
                    view, "Login success" , "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    //Validate that there is no empty information
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


