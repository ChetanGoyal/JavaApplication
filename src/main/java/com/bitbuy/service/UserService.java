package com.bitbuy.service;

import com.bitbuy.dao.UserAuthDao;
import com.bitbuy.dao.UserDao;
import com.bitbuy.entity.User;
import com.bitbuy.entity.UserAuth;
import com.bitbuy.exception.AuthenticationFailedException;
import com.bitbuy.exception.UserRestrictedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;

@Service
public class UserService {

    private UserDao userDao;
    private UserAuthDao userAuthDao;

    public UserService(UserDao userDao,UserAuthDao userAuthDao) {
        this.userDao = userDao;
        this.userAuthDao = userAuthDao;
    }

    @Transactional
    public User saveUser(User user) throws UserRestrictedException {

        User existingUser = userDao.getUserByUserName(user.getUserName());

        if(existingUser != null){
            throw new UserRestrictedException("USR-003","User already exists with provided User name");
        }

        return userDao.saveUser(user);
    }

    public User authenticate(String userName,String password) throws AuthenticationFailedException {
        User user = userDao.getUserByUserName(userName);

        if(null == user){
            throw new AuthenticationFailedException("AFE-001","User Name not registered");
        }

        if(!password.equals(user.getPassword())){
            throw new AuthenticationFailedException("AFE-002","Invalid credentials");
        }

        return user;
    }

    public User getUser(String accessToken) throws AuthenticationFailedException{

        UserAuth userAuth = userAuthDao.getCustomerAuthByToken(accessToken);

        if (userAuth == null) {
            throw new AuthenticationFailedException("ATHR-001", "Customer is not Logged in.");
        }

        if (userAuth.getLogoutAt() != null) {
            throw new AuthenticationFailedException("ATHR-002", "Customer is logged out. Log in again to access this endpoint.");
        }

        final ZonedDateTime now = ZonedDateTime.now();

        if (userAuth.getExpiresAt().compareTo(now) <= 0) {//Checking accessToken is Expired.
            throw new AuthenticationFailedException("ATHR-003", "Your session is expired. Log in again to access this endpoint.");
        }
        return userAuth.getUser();
    }

    public User getUserById(String uuid){
        User userById = userDao.getUserById(uuid);

        return userById;
    }

    @Transactional
    public User updateUser(User user){
        return userDao.updateUser(user);
    }
}
