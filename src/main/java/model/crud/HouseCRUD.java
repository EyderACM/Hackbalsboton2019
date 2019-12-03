/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.crud;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import model.schemas.House;
import model.schemas.Area;
import model.bootstraper.EMFBootstrapper;
import java.util.ArrayList;

/**
 *
 * @author joses
 */
public class HouseCRUD {
    private EntityManager manager;

    public HouseCRUD(EntityManager manager) {
        this.manager = manager;
    }

    public void createHouse(House house){
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
    private void clone(House house,House newHouse){
        house.setName(newHouse.getName());
        
    } 
    
    public ArrayList<Area> getAreas(String id){
        ArrayList<Area> areas;
        EntityManager manager = EMFBootstrapper.openEntityManager();
        try {
            areas = ( ArrayList<Area>) manager.createQuery("from Area u where u.idhouses='" + id + "'");
        }
        catch(PersistenceException e) {
            throw e;
        }
        return areas;
    }
}
