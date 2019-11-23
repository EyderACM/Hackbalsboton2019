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
        User user = new User("Axel", "Axel@lol3.com", "pass");
        UserCRUD uc = new UserCRUD();
        uc.createUser(user);
        test(uc);
    }
    private static EntityManagerFactory emf;
    private static EntityManager manager;

    public static void test(UserCRUD crud) {
        emf = Persistence.createEntityManagerFactory("DomoticaModel");
        manager = emf.createEntityManager();

        String email = "Axel@lol.com";

        User _user = (User) manager.createQuery("from User u where u.Email='" + email + "'").getSingleResult();
        System.out.println(_user.toString());

        User userResult = crud.getUser("Axel@lol.com");
        System.out.println(userResult.toString());
    }
}
