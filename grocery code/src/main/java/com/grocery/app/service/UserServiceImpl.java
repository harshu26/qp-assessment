package com.grocery.app.service;

import com.grocery.app.dao.UserDao;
import com.grocery.app.model.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserServiceImpl implements UserServiceInterface {

    @Autowired
    UserDao userDao;

    /*Admin will be adding new users for both customer as well admin roles*/
    @Override
    public User addUser(User user) {
        System.out.println("User added ! :" +user);
        return userDao.save(user);
    }

    /*Admin will be updating the existing user role*/
    @Override
    public User updateUserRole(String userId) {

        User user = userDao.findByUserId(userId);
        user.setUserRole("customer");
        userDao.save(user);
        System.out.println("User Role updated to Customer");
        return user;
    }
}
