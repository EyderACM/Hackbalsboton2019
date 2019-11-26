
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
    public void nuevo_usuario(Register view)
    {
       User nuser = new User();
       
       nuser.setName(view.getNombre());
       nuser.setEmail(view.getCorreo());
       nuser.setPassword(view.getContrasena());
       
       UserCRUD crudnu = new UserCRUD();
       crudnu.createUser(nuser);
  
    }
    
}       
