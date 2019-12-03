package model.crud;

import model.bootstraper.EMFBootstrapper;
import model.schemas.Room;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

public class RoomCRUD {
    public void createRoom(Room room){
        EntityManager manager = EMFBootstrapper.openEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            manager.persist(room);
            transaction.commit();
            System.out.printf("se ha añadido con exito");
        }
        catch(PersistenceException e) {
            transaction.rollback();
            throw e;
        }
        finally {
            manager.close();
        }
    }

    public Room getRoom(String id){
        EntityManager manager = EMFBootstrapper.openEntityManager();
        Room room = new Room();
        try {
            room = (Room) manager.createQuery("FROM ROOM u WHERE u.idroom='"+id+"'").getSingleResult();
        }catch(PersistenceException e) {
            throw e;
        }
        return room;
    }

    public void deleteRoom(String id){
        String delims = "[,]";
        String[] tokens = id.split(delims);


        for(int i = 0; i < tokens.length; i++){
            Room room = getRoom(tokens[i]);
            EntityManager manager = EMFBootstrapper.openEntityManager();
            EntityTransaction transaction = manager.getTransaction();
            try {
                transaction.begin();
                manager.remove(room);
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
}
