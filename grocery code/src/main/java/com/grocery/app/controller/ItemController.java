package com.grocery.app.controller;

import com.grocery.app.dao.UserDao;
import com.grocery.app.exceptions.InvalidUserException;
import com.grocery.app.model.Item;
import com.grocery.app.model.User;
import com.grocery.app.service.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {

    @Autowired
    private ItemServiceImpl itemService;

    @Autowired
    private UserDao userDao;


    @PostMapping(value="/api/add_item/{userId}")
    public ResponseEntity<List<Item>> addItem(@RequestBody List<Item> itemList, @PathVariable("userId") String userId)
    {
        User user = userDao.findByUserId(userId);
        if(user.getIsUserLoggedIn() && user.getUserRole().equals("ADMIN")){
            itemService.addItem(itemList);
        }
        else{
            throw new InvalidUserException("Action not allowed!!");
        }
        return new ResponseEntity<>(itemList, HttpStatus.OK);
    }

    @PutMapping(value = "/api/update_item/{itemId}/{userId}")
    public ResponseEntity<Item> updateItem(@PathVariable("itemId") String itemId, @PathVariable("userId") String userId, @RequestBody Item item)
    {
        User user = userDao.findByUserId(userId);
        if(user.getIsUserLoggedIn() && user.getUserRole().equals("ADMIN")){
            itemService.updateItem
                    (itemId, item.getItemName(),item.getItemPrice(),item.getItemLevel());
        }
        else{
            throw new InvalidUserException("Action not allowed!!");
        }
       return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @GetMapping(value="/api/fetchAllItem")
    public ResponseEntity<List<Item>> fetchAllItems(){
        return new ResponseEntity<>(itemService.fetchAllItems(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/delete_item/{itemId}/{userId}")
    public String deleteItem(@PathVariable("itemId") String itemId, @PathVariable("userId") String userId)
    {
        User user = userDao.findByUserId(userId);
        if(user.getIsUserLoggedIn() && user.getUserRole().equals("ADMIN")){
            itemService.deleteItem(itemId);
        }
        else{
            throw new InvalidUserException("Action not allowed!!");
        }
        return "Item deleted!";
    }
}


