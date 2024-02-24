package com.grocery.app.service;

import com.grocery.app.model.Item;

import java.util.List;

public interface ItemServiceInterface {

    List<Item> addItem(List<Item> itemList);

    Item updateItem(String itemId, String name, double price, int level);

    List<Item> fetchAllItems();

    void deleteItem(String itemId);
}
