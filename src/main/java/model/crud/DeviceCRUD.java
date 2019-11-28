package model.crud;

import model.bootstraper.EMFBootstrapper;
import model.schemas.Device;

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

    public Device getDevice(String field, String search){
        EntityManager manager = EMFBootstrapper.openEntityManager();
        Device device = new Device();
        try {
            device = (Device)manager.createQuery("FROM Device d WHERE d. + '"+field+"' + ='"+search+"'").getSingleResult();
        }catch (PersistenceException e){
            throw e;
        }
        return device;
    }

    public void updateDevice(Device device, Device newDevice){
        EntityManager manager = EMFBootstrapper.openEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            manager.merge(device);
            clone(device, newDevice);
            manager.persist(device);
            transaction.commit();
            System.out.println("Se ha actualizado el dispositivo");
        }catch(PersistenceException e) {
            transaction.rollback();
            throw e;
        } finally {
            manager.close();
        }
    }

    public void deleteDevice(Device device){
        EntityManager manager = EMFBootstrapper.openEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            manager.remove(device);
            transaction.commit();
            System.out.println("Se ha eliminado el dispositivo");
        }catch(PersistenceException e) {
            transaction.rollback();
            throw e;
        } finally {
            manager.close();
        }
    }

    private void clone(Device device, Device newDevice){
        device.setName(newDevice.getName());
        device.setType(newDevice.getType());
        device.setBrand(newDevice.getBrand());
        device.setModel(newDevice.getModel());
        device.setState(newDevice.getState());
        device.setGroup(newDevice.getGroup());
    }
}
