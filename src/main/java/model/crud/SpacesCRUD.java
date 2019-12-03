package model.crud;

import model.bootstraper.EMFBootstrapper;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import model.schemas.Area;
import model.schemas.Room;

public class SpacesCRUD {
    public void createArea(Area area){
        EntityManager manager = EMFBootstrapper.openEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            manager.persist(area);
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

    public void deleteArea(String id){

        String delims = "[,]";
        String[] tokens = id.split(delims);


        for(int i = 0; i < tokens.length; i++){
            Area area = getArea(tokens[i]);
            EntityManager manager = EMFBootstrapper.openEntityManager();
            EntityTransaction transaction = manager.getTransaction();
            try {
                transaction.begin();
                manager.remove(area);
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

    public Area getArea(String id){
        EntityManager manager = EMFBootstrapper.openEntityManager();
        Area area = new Area();
        try {
            area = (Area) manager.createQuery("from Area u where u.idarea='" + id + "'").getSingleResult();
        }
        catch(PersistenceException e) {
            throw e;
        }

        return area;
    }

    public ArrayList<Room> getRooms(String id){
        ArrayList<Room> rooms;
        EntityManager manager = EMFBootstrapper.openEntityManager();
        try {
            rooms = ( ArrayList<Room>) manager.createQuery("from Room u where u.idarea='" + id + "'").getSingleResult();
        }
        catch(PersistenceException e) {
            throw e;
        }
        return rooms;
    }

    
    

    public Area getAreaByName(String name){
        EntityManager manager = EMFBootstrapper.openEntityManager();
        Area area = new Area();
        try {
            area = (Area) manager.createQuery("from Area u where u.name='" + name + "'").getSingleResult();
        }
        catch(PersistenceException e) {
            throw e;
        }

        return area;
    }

}
