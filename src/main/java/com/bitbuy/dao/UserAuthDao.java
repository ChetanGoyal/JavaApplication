package com.bitbuy.dao;

import com.bitbuy.entity.UserAuth;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

@Repository
public class UserAuthDao {

    private EntityManager entityManager;

    public UserAuth getCustomerAuthByToken(String accessToken) {
        try {
            return entityManager
                    .createNamedQuery("userAuthByToken", UserAuth.class)
                    .setParameter("accessToken", accessToken)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }
}
