package com.grocery.app.controller;

import com.grocery.app.dao.UserDao;
import com.grocery.app.exceptions.InvalidUserException;
import com.grocery.app.model.Cart;
import com.grocery.app.model.User;
import com.grocery.app.service.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class CartController {

    @Autowired
    private CartServiceImpl cartService;

    @Autowired
    private UserDao userDao;


    @PostMapping(value = "/api/add_item_to_cart/{userId}")
    public ResponseEntity<Cart> addItemToCart(@PathVariable("userId")String userId, @RequestBody Map<String, Integer> itemDetails)
    {
        Cart cart;
        User user = userDao.findByUserId(userId);
        if(user.getIsUserLoggedIn()){
            cart = cartService.addItemToCart(userId,itemDetails);
        }
        else{
            throw new InvalidUserException("Please login to add item cart!!");
        }
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PostMapping(value = "/api/checkout_cart/{userId}/{cartId}")
    public ResponseEntity<String> checkoutCart(@PathVariable("userId") String userId, @PathVariable("cartId") String cartId)
    {
        User user = userDao.findByUserId(userId);
        if(user.getIsUserLoggedIn()){
            cartService.checkoutCart(cartId, userId);
        }
        else{
            throw new InvalidUserException("User not logged in!!");
        }
        return new ResponseEntity<>("Checkout successful",HttpStatus.OK);
    }

    @GetMapping(value = "/api/fetchAllCartItem/{userId}")
    public ResponseEntity<List<Cart>> fetchAllCartItem(@PathVariable("userId") String userId)
    {
        List<Cart> carts;
        User user = userDao.findByUserId(userId);
        if(user.getIsUserLoggedIn()){
            carts = cartService.fetchAllCartItem();
        }
        else{
            throw new InvalidUserException("User not logged in!!");
        }
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }

    @GetMapping(value = "/api/fetchCart/{cartId}/{userId}")
    public ResponseEntity<Cart> fetchCart(@PathVariable("cartId") String cartId, @PathVariable("userId") String userId)
    {
        Cart cart;
        User user = userDao.findByUserId(userId);
        if(user.getIsUserLoggedIn()){
            cart = cartService.fetchCart(cartId);
        }
        else{
            throw new InvalidUserException("User not logged in!!");
        }
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PutMapping(value = "/api/update_cart/{cartId}/{userId}/{itemId}/{quantity}")
    public ResponseEntity<Cart> updateCart(@PathVariable("cartId") String cartId, @PathVariable("userId") String userId,
                                           @PathVariable("itemId") String itemId, @PathVariable("quantity") int quantity)
    {
        Cart cart;
        User user = userDao.findByUserId(userId);
        if(user.getIsUserLoggedIn()){
            cart = cartService.updateCart(cartId,userId,itemId,quantity);
        }
        else{
            throw new InvalidUserException("User not logged in!!");
        }
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }


}
