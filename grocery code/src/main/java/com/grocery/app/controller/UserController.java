package com.grocery.app.controller;

import com.grocery.app.dao.UserDao;
import com.grocery.app.exceptions.InvalidUserException;
import com.grocery.app.model.User;
import com.grocery.app.service.LoginServiceImpl;
import com.grocery.app.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private LoginServiceImpl loginService;

    @Autowired
    private UserDao userDao;


    @PostMapping(value = "/login/{userId}/{password}")
    public ResponseEntity<String>  userLogin (@PathVariable("userId") String userId, @PathVariable("password") String password)
    {
        loginService.login(userId, password);
        return new ResponseEntity<>("User successfully logged in!", HttpStatus.OK);
    }

    @PostMapping(value = "/logout/{userId}")
    public ResponseEntity<String>  userLogout (@PathVariable("userId") String userId)
    {
       loginService.logout(userId);
       return new ResponseEntity<>("User logged out!", HttpStatus.OK);
    }

    @PostMapping(value = "/api/add_user/{userId}")
    public ResponseEntity<User> addUser(@RequestBody User user, @PathVariable("userId") String userId)
    {
        User user1 = userDao.findByUserId(userId);
        if(user1.getUserRole().equals("ADMIN") && user1.getIsUserLoggedIn()) {
          userService.addUser(user);
        }
        else{
            throw new InvalidUserException("Action not allowed!!");
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping(value = "/api/update_user/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable("userId") String userId)
    {
       User updateUser = userDao.findByUserId(userId);
       if(updateUser.getIsUserLoggedIn()){
           userService.updateUserRole(userId);
       }
       else{
           throw new InvalidUserException("Action not allowed!!");
       }
       return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

 }
