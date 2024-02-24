package com.grocery.app.dao;

import com.grocery.app.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemDao extends JpaRepository<Item, String> {

    Item findByItemId(String itemId);
}
