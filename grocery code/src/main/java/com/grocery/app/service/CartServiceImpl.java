package com.grocery.app.service;

import com.grocery.app.dao.CartDao;
import com.grocery.app.dao.ItemDao;
import com.grocery.app.dao.UserDao;
import com.grocery.app.exceptions.InvalidDetailsException;
import com.grocery.app.model.Cart;
import com.grocery.app.model.Item;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class CartServiceImpl implements CartServiceInterface{

    @Autowired
    CartDao cartDao;

    @Autowired
    ItemDao itemDao;

    @Autowired
    UserDao userDao;

    /*Customers can add multiple items to cart based on their userId and ItemId of the item which is passed as a key(string in map)*/
    @Override
    public Cart addItemToCart( String userId, Map<String,Integer> cartItems) {

        if(userId != null) {

            Cart cart = new Cart();
            cart.setUserId(userId);
            for(Map.Entry<String, Integer> entry: cartItems.entrySet()) {
                Item item = itemDao.findByItemId(entry.getKey());
                if (item == null)
                    throw new InvalidDetailsException("Item details are invalid!, Please enter correct details");
                int quantity = entry.getValue();
                if(quantity > item.getItemLevel() || quantity < 1)
                    throw new InvalidDetailsException("Item quantity incorrect. Please enter valid Item quantity");
                System.out.println("Item: "+ item.getItemName()+ " added to cart");
            }
            cart.setCartItems(cartItems);
            cartDao.save(cart);
            return cart;
        } else {
            throw new InvalidDetailsException("User details are invalid!, Please enter correct User details");
        }


    }

    /*Customers can view all the items they have added to the cart*/
    @Override
    public List<Cart> fetchAllCartItem() {
        return cartDao.findAll();
    }

    /*Customers can view their cart status*/
    @Override
    public Cart fetchCart(String cartId){
        Optional<Cart> cartObj = cartDao.findById(cartId);
        if(cartObj.isEmpty())
            throw new InvalidDetailsException("No such cartID exists");
        else
            return cartObj.get();
    }

    /*Customers can modify items in their cart*/
    @Override
    public Cart updateCart(String cartId, String userId, String itemId, int quantity) {

        Cart cart = cartDao.findByCartIdAndUserId(cartId, userId);
        if(cart != null) {
            Item item = itemDao.findByItemId(itemId);
            if (item == null)
                throw new InvalidDetailsException("Item details are invalid!, Please enter correct details");
            cart.getCartItems().put(itemId,quantity);
            cartDao.save(cart);
        }
        return cart;
    }

    /*Once finalised, customer can proceed for checkout, this will update the item level as well in the store*/
    @Override
    public void checkoutCart(String cartId, String userId) {
        Cart cart = cartDao.findByCartIdAndUserId(cartId, userId);
        Map<String, Integer> cartMap = cart.getCartItems();
        for(Map.Entry<String,Integer> entry : cartMap.entrySet())
        {
           Optional<Item> itemObj =  itemDao.findById(entry.getKey());
           if (itemObj.isEmpty())
               throw new InvalidDetailsException("Cannot proceed as Item details are invalid!, Please enter correct details");
           Item item = itemObj.get();
           item.setItemLevel( item.getItemLevel() - entry.getValue());
           itemDao.save(item);
        }
        System.out.println("Cart checked out successfully....");
    }
}
