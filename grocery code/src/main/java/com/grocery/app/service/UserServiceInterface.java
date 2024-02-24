package com.grocery.app.service;

import com.grocery.app.model.User;

public interface UserServiceInterface {

    User addUser(User user);
    User updateUserRole(String userId);
    
    
}
