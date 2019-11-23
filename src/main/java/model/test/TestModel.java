package model.test;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.crud.HouseCRUD;
import model.schemas.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Eyder
 */
public class TestModel {
    public static void main(String[] args){
        House house = new House("Casa de Pepe");
        HouseCRUD hc = new HouseCRUD();
        hc.createHouse(house);
        test();
    }
        private static EntityManagerFactory emf;
        private static EntityManager manager;
    
    public static void test() {
        emf = Persistence.createEntityManagerFactory("DomoticaModel");
        manager = emf.createEntityManager();

        List<House> houses = (List<House>) manager.createQuery("FROM House").getResultList();
        System.out.println("hay un total de " + houses.size() + " casas");
        for (House house: houses) {
            System.out.println(house.toString());
        }
    }
}
