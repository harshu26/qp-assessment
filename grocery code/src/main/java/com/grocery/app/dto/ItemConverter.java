package com.grocery.app.dto;

import com.google.gson.Gson;
import com.grocery.app.model.Item;

public class ItemConverter {

    static Gson gson = new Gson();
    public static String cartToJson(Item item){
        return gson.toJson(item);
    }

    public Item JsonToCart(String json){
        return gson.fromJson(json, Item.class);
    }
}
