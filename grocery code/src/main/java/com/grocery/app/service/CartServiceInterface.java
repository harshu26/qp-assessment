package com.grocery.app.service;

import com.grocery.app.model.Cart;

import java.util.List;
import java.util.Map;

public interface CartServiceInterface {


    Cart addItemToCart( String userId, Map<String,Integer> ItemList);
    List<Cart> fetchAllCartItem();

    Cart fetchCart(String cartId);

    Cart updateCart(String cartId, String userId, String itemId, int quantity);

    void checkoutCart(String cartId, String userId);
}
