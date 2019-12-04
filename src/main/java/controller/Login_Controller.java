package controller;

import controller.exceptions.EmptyException;
import model.crud.UserCRUD;
import model.schemas.User;
import view.Login;

import javax.swing.*;
import java.sql.SQLException;
import java.util.*;
import view.Register;

public class Login_Controller {

    public void nuevo_usuario(Register view) throws EmptyException {
        User nuser = new User();

        nuser.setName(view.getNombre());
        nuser.setEmail(view.getCorreo());
        nuser.setPassword(view.getContrasena());
        nuser.setQuestion(view.getQuestion());
        nuser.setAnswer(view.getAnswer());

        Map<String, String> data = new HashMap<>();
        data.put("nombre", view.getNombre());
        data.put("correo", view.getCorreo());
        data.put("contrasena", view.getContrasena());
        data.put("conf_contrasena", view.getConfcontrasena());

        int campos = 0; //Verificacion campos
        int correo = 0; //Verificacion correo
        int contra = 0; //Verificacion contraseña

        int debounce = 0; //Mostrar solo un mensaje
        //Aunque no muestre el mensaje aun asi retorna la excepcion

        //confirmacion de todos los espacios fueron rellenados
        try {
            Set<String> llave = data.keySet();
            for (String key : llave) {
                if (data.get(key).isEmpty()) {
                    throw new NullPointerException();
                } else {
                    campos = 1;
                }
            }
        } catch (NullPointerException ex) {
            if (debounce == 0) {
                debounce = 1;
                JOptionPane.showMessageDialog(null, "Por favor, rellene todos los espacios");
            }
        }

        //verificacion de que es un correo
        int verif = 0;
        verif = data.get("correo").indexOf("@");

        if (verif != -1) {
            correo = 1;
        } else {
            if (debounce == 0) {
                debounce = 1;
                JOptionPane.showMessageDialog(null, "No se reconoce el correo, por favor intente de nuevo");
            }
        }

        //confirmacion de contrase?a
        if (data.get("contrasena").equals(data.get("conf_contrasena"))) {
            contra = 1;
        } else {
            if (debounce == 0) {
                debounce = 1;
                JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden");
            }
        }

        //envia la informacion si todo es correcto
        if (campos == 1 && correo == 1 && contra == 1 && debounce == 0) //asi es mas intuitivo
        {
            UserCRUD crudnu = new UserCRUD();
            crudnu.createUser(nuser);
        }
    }

    //Validates the input and send it to the model
    public boolean LoginUser(Login view) throws EmptyException {

        User user;
        UserCRUD model = new UserCRUD();

        Map<String, String> data = new HashMap<>();
        data.put("email", view.getEmailText());
        data.put("password", view.getPasswordText());

        if (!validCompleteness(data)) {
            throw new EmptyException();
        } else {
            try {
                System.out.println("1");
                user = model.getUser(data.get("email"));
                System.out.println("2");
                validateLogin(data, user, view);
                return true;
            } catch (Exception ex) {
                showError(ex, view);
            }

        }
        return false;
    }

    //Verify the password
    public void validateLogin(Map<String, String> data, User user, Login view) {
        if (user.getPassword().equals(data.get("password"))) {
            JOptionPane.showMessageDialog(
                    view, "Login success", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    //Display an OptionPane in the view with the error
    public void showError(Exception ex, Login view) {
        if (ex instanceof EmptyException) {
            JOptionPane.showMessageDialog(
                    view, "You must fill every text field", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else if (ex instanceof SQLException) {
            JOptionPane.showMessageDialog(
                    view, "Database error", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(
                    view, "Unexpected error", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Validate that there is no empty information
    public boolean validCompleteness(Map<String, String> data) {
        boolean isComplete = false;
        Set<String> keys = data.keySet();
        for (String key : keys) {
            if (!data.get(key).isEmpty()) { //Checks that there is no empty information
                isComplete = true;
            }
        }

        return isComplete;
    }
    
    UserCRUD crud = new UserCRUD();
    
    public boolean userExist(String email) {
        User user = null;

        user = crud.getUser(email);
        
        if (user == null) {
            return false;
        } else {
            return true;
        }
    }
    
    public boolean validateQuestion(String email, String answer, int question) {
        User user = crud.getUser(email);
        if (user.getAnswer().equals(answer) && user.getQuestion() == question) {
            return true;
        } else {
            return false;
        }
    }
    
    public void updatePassword(String email, String password){
        User user = crud.getUser(email);
        User newUser = crud.getUser(email);
        newUser.setPassword(password);
        
        crud.updateUser(user, newUser);
        
    }

}
