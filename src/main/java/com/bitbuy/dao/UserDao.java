package com.bitbuy.dao;

import com.bitbuy.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

@Repository
public class UserDao {

    private EntityManager entityManager;

    public User saveUser(User user){
        entityManager.persist(user);
        return user;
    }

    public User getUserByUserName(String userName){
        try{
            return entityManager
                    .createNamedQuery("userByUserName",User.class)
                    .setParameter("userName",userName)
                    .getSingleResult();
        }catch (NoResultException noResultException){
            return null;
        }
    }

    public User getUserById(String uuid){
        try{
            return entityManager
                    .createNamedQuery("userById",User.class)
                    .setParameter("id",uuid)
                    .getSingleResult();
        }catch(Exception e){
            return null;
        }
    }

    public User updateUser(User user){
        entityManager.merge(user);
        return user;
    }
}
