/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.crud;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

import model.bootstraper.EMFBootstrapper;
import model.schemas.User;


/**
 *
 * @author joses
 */
public class UserCRUD {
    
    public void createUser(User user){
        EntityManager manager = EMFBootstrapper.openEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            manager.persist(user);
            transaction.commit();
            System.out.printf("se ha a�adido con exito");
        }
        catch(PersistenceException e) {
            transaction.rollback();
            throw e;
        }
        finally {
            manager.close();
        }
    }

    public User getUser(String email){
        EntityManager manager = EMFBootstrapper.openEntityManager();
        User user = new User();
        try {
            user = (User) manager.createQuery("from User u where u.Email='" + email + "'").getSingleResult();
        }
        catch(PersistenceException e) {
            throw e;
        }

        return user;
    }

    public void updateUser(User user, User newuser){
        EntityManager manager = EMFBootstrapper.openEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            manager.merge(user);
            clone(user, newuser);
            manager.persist(user);
            transaction.commit();
            System.out.printf("se ha actualizado con exito");
        }
        catch(PersistenceException e) {
            transaction.rollback();
            throw e;
        }
        finally {
            manager.close();
        }
    }
    
    public void deleteUser(User user){
        EntityManager manager = EMFBootstrapper.openEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            manager.remove(user);
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
    
    
    private void clone(User user,User newuser){
        user.setEmail(newuser.getEmail());
        user.setName(newuser.getName());
        user.setPassword(newuser.getPassword());
        
    } 
    
}
