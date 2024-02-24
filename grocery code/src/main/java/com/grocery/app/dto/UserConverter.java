package com.grocery.app.dto;

import com.google.gson.Gson;
import com.grocery.app.model.User;

public class UserConverter {

    static Gson gson = new Gson();
    public static String userToJson(User user){
        return gson.toJson(user);
    }

    public User JsonToUser(String json){
        return gson.fromJson(json, User.class);
    }
}
