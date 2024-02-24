package com.grocery.app.service;

import com.grocery.app.dao.UserDao;
import com.grocery.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginServiceInterface{

    @Autowired
    private UserDao userDao;

    /*Both customer and admin need to login to perform action*/
    @Override
    public User login(String userId, String password) {

     User user = userDao.findByUserIdAndPassword(userId, password);
     user.setIsUserLoggedIn(true);
     userDao.save(user);
     return user;
    }

    /*Both customer and admin can logout*/
    @Override
    public User logout(String userId) {

        User user = userDao.findByUserId(userId);
        user.setIsUserLoggedIn(false);
        userDao.save(user);
        return user;
    }

}
