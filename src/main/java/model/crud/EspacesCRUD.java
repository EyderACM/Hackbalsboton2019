package model.crud;

import model.bootstraper.EMFBootstrapper;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import model.schemas.Area;

public class EspacesCRUD {
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

    private void clone(Area area,Area newArea){
        area.setName(newArea.getName());
    }

//    public String getRooms(Group group){
//
//    }



}
