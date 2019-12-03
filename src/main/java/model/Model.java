package model;

import model.bootstraper.EMFBootstrapper;
import model.crud.HouseCRUD;

import javax.persistence.EntityManager;

public final class Model {
    private EntityManager manager = EMFBootstrapper.openEntityManager();;

    public static HouseCRUD startHouseCRUD(EntityManager manager){
        HouseCRUD crud = new HouseCRUD(manager);
        return crud;
    }

    public static DeviceCRUD startDeviceCRUD(EntityManager manager){
        HouseCRUD crud = new HouseCRUD(manager);
        return crud;
    }
}
