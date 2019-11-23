/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import model.schemas.House;

/**
 *
 * @author joses
 */
public class houseCrud {
    
    public void createHouse(House house){
        EntityManager manager = EMFBootstrapper.openEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            manager.persist(house);
            transaction.commit();
            System.out.printf("se ha anadido con exito");
        }
        catch(PersistenceException e) {
            transaction.rollback();
            throw e;
        }
        finally {
            manager.close();
        }
    }
    
     public void updateHouse(House house,House newHouse){
        EntityManager manager = EMFBootstrapper.openEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            manager.merge(house);
            clone(house,newHouse);
            manager.persist(house);
            transaction.commit();
            System.out.printf("se ha anadido con exito");
        }
        catch(PersistenceException e) {
            transaction.rollback();
            throw e;
        }
        finally {
            manager.close();
        }
    }
     
      public void deleteHouse(House house){
        EntityManager manager = EMFBootstrapper.openEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            manager.remove(house);
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
    private void clone(House house,House newHouse){
        house.setName(newHouse.getName());
    } 
}
