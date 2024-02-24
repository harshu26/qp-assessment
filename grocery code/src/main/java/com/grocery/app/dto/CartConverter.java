package com.grocery.app.dto;

import com.google.gson.Gson;
import com.grocery.app.model.Cart;

public class CartConverter {

    static Gson gson = new Gson();
    public static String cartToJson(Cart cart){
        return gson.toJson(cart);
    }

    public Cart JsonToCart(String json){
        return gson.fromJson(json, Cart.class);
    }


}
