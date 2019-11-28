
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
  User nuser = new User();
  
  nuser.setName(view.getNombre());
  nuser.setEmail(view.getCorreo());
  nuser.setPassword(view.getContrasena());
  
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
     campos = 1;
    }
   }
  }
  catch(NullPointerException ex)
  {
   if (debounce == 0)
   {
    debounce = 1;
    JOptionPane.showMessageDialog(null,"Por favor, rellene todos los espacios");
   }
  }

  //verificacion de que es un correo
  int verif = 0;
  verif = data.get("correo").indexOf("@");
  
  if (verif != -1)
  {
   correo = 1;
  }
  else
  {
   if (debounce == 0)
   {
    debounce = 1;
    JOptionPane.showMessageDialog(null, "No se reconoce el correo, por favor intente de nuevo");
   }
  }
 
  //confirmacion de contrase?a
  if (data.get("contrasena").equals(data.get("conf_contrasena")))
  {
   contra = 1;
  }
  else
  {
   if (debounce == 0)
   {
    debounce = 1;
    JOptionPane.showMessageDialog(null,"Las contraseñas no coinciden");
   }
  }
      
  //envia la informacion si todo es correcto
  if(campos == 1 && correo == 1 && contra == 1 && debounce == 0) //asi es mas intuitivo
  {
   UserCRUD crudnu = new UserCRUD();
   crudnu.createUser(nuser);
  }
 }
}       
