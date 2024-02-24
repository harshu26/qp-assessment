package com.grocery.app.service;

import com.grocery.app.dao.ItemDao;
import com.grocery.app.model.Item;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ItemServiceImpl implements ItemServiceInterface{

    @Autowired
    ItemDao itemDao;

    /*Admin will be adding items to the inventory*/
    @Override
    public List<Item> addItem(List<Item> itemList) {
        System.out.println("Adding Items to the inventory");
        return itemDao.saveAll(itemList);
    }

    /*Admin will be updating item details*/
    @Override
    public Item updateItem(String itemId, String name, double price, int level) {
        Item item = itemDao.findByItemId(itemId);
        item.setItemDesc("Daily Needs");
        item.setItemName(name);
        item.setItemLevel(level);
        item.setItemPrice(price);
        itemDao.save(item);
        System.out.println("Item details successfully updated!!");
        return item;
    }


    /*Both admin and users can see all the available items*/
    @Override
    public List<Item> fetchAllItems() {
       return itemDao.findAll();
    }

    /*Admin will be deleting item from the inventory*/
    @Override
    public void deleteItem(String itemId) {
        Item item = itemDao.findByItemId(itemId);
        itemDao.delete(item);
        System.out.println("Item deleted!"+ itemId);
    }
}
