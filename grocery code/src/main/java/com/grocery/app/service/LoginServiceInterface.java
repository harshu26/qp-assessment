package com.grocery.app.service;

import com.grocery.app.model.User;

public interface LoginServiceInterface {

    User login(String userId, String password);
    User logout(String userId);


}
