package model.crud;

import model.bootstraper.EMFBootstrapper;
import model.schemas.Device;
import java.util.ArrayList;
import java.util.HashSet;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

public class DeviceCRUD {
    public void createDevice(Device device){
        EntityManager manager = EMFBootstrapper.openEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            manager.persist(device);
            transaction.commit();
            System.out.println("Se ha completado la transaccion");
        }catch (PersistenceException e){
            transaction.rollback();
            throw e;
        }finally {
            manager.close();
        }
    }

    public Device getDevice(String id){
        EntityManager manager = EMFBootstrapper.openEntityManager();
        Device device = new Device();
        try {
            device = (Device)manager.createQuery("from Device u where u.iddevice='" + id + "'").getSingleResult();
        }catch (PersistenceException e){
            throw e;
        }
        return device;
    }
    
    public ArrayList<Device> getDevices(){
        ArrayList<Device> devices;
        
        EntityManager manager = EMFBootstrapper.openEntityManager();
        try {
            devices = (ArrayList<Device>)manager.createQuery("FROM Device").getResultList();
        }catch (PersistenceException e){
            throw e; 
        }
        
        return devices;
    }

    public void deleteDevice(String id){
        
        String delims = "[,]";
        String[] tokens = id.split(delims);
        
        for(int i = 0; i < tokens.length; i++){
            Device device = getDevice(tokens[i]);
            EntityManager manager = EMFBootstrapper.openEntityManager();
            EntityTransaction transaction = manager.getTransaction();
            try {
                transaction.begin();
                manager.remove(device);
                transaction.commit();
                System.out.printf("se ha eliminado con exito");
            }
            catch(PersistenceException e) {
                transaction.rollback();
                throw e;
            }
            finally {
                manager.close();
            }
        }
    }
    
    public void offDevice(Device device){
        device.setState(false);
    }
    
    public void onDevice(Device device){
        device.setState(true);
    }

}
