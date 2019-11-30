package model.test;

import model.crud.HouseCRUD;
import model.crud.UserCRUD;
import model.schemas.House;
import model.schemas.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class TestUser {

    public static void main(String[] args){
//        User user1 = new User("Jorge", "Axel@lol.com", "pass"); //debería fallar
//        User user2 = new User("Mario", "hola2.com", "pass"); //se debería agrear y tener ID 18
//        UserCRUD uc = new UserCRUD();
//        uc.createUser(user1);
//        uc.createUser(user2);
        test();
    }
    private static EntityManagerFactory emf;
    private static EntityManager manager;

    public static void test() {
        emf = Persistence.createEntityManagerFactory("DomoticaModel");
        manager = emf.createEntityManager();


        List<User> userList = manager.createQuery("from User").getResultList();
        for (User u:userList) {
            System.out.println(u.toString());
        }

    }
}
