//amaury morales cerecedo
//10:55am-11:17am

package controller;

//Recibe,valida y envia los parametros para agregar dispositivo

//import controller.exceptions.EmptyException;
import model.crud.DeviceCRUD;
import model.schemas.Device;
import view.Devices;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.swing.JOptionPane;

public class add_device
{
 public void nuevo_dispositivo(Devices view) //throws EmptyException
 {
  int nombre = 0; //Para validar nombre
  int tipo = 0; //Para validar tipo
  int campos = 0; //Para validar campos

  int debounce = 0; //Para mostrar solo un error a la vez.

  //Las variables de arriba necesitan resetearse y por eso la funcion nuevo_dispositivo debe ser llamada cada vez que el view
  //presiona el boton de enviar dispositivo porque si solo se activa por cambios entonces no funcionara el codigo

  Device ndispositivo = new Device();
  
  Map<String, String> data = new HashMap<>();
  //data.put("name", view.getNombre()); //Supongo que esto lo saco del view. Favor de descomentar y cambiar por los string necesarios
  //data.put("type", view.getType()); //Y esto tambien.
  
  //Validar informacion//

  //Validar campos llenos

  //Aqui si quieren pongan un try y catch pero yo no lo pondre por ahora
  Set<String> llave = data.keySet();
  for (String key : llave) 
  {
   if (data.get(key).isEmpty())
   {
    if (debounce == 0)
    {
     debounce = 1;
     JOptionPane.showMessageDialog(null, "Por favor, rellene todos los espacios"); //Espero que esto funcione sin necesitar el try y catch
    }
   } 
   else 
   {
    campos = 1;
   }
  }

  //Validar nombre correcto
  if (data.get("name").length() > 42) //Creo que el maximo es 42 caracteres
  {
   if (debounce == 0)
   {
    debounce = 1;
    JOptionPane.showMessageDialog(null, "Por favor, escriba un nombre con menos de 42 caracteres");
   }
  }
  else
  {
   nombre = 1;
  }

  //Validar tipo correcto
  if (data.get("type").trim().isEmpty())
  {
   if (debounce == 0)
   {
    debounce = 1;
    JOptionPane.showMessageDialog(null, "Por favor, seleccione un tipo de dispositivo");
   }
  }
  else
  {
   tipo = 1;
  }

  //Llenar informacion del dispositivo y enviar al crud
  if (campos == 1 && nombre == 1 & tipo == 1 && debounce == 0) //No tiene que haber ningun error
  {
   //Descomentar y cambiar strings por favor antes de crear dispositivo
   //ndispositivo.setName(view.getNombre()); //Cambien los metodos que no existen por los String que me deben de enviar desdde el view
   //ndispositivo.setType(view.getTipo()); //Cambien los metodos que no existen por los String que me deben de enviar desdde el view

   DeviceCRUD crudnd = new DeviceCRUD();
   crudnd.createDevice(ndispositivo);
  }
 }
}
