package com.grocery.app.dao;

import com.grocery.app.model.Cart;
import com.grocery.app.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartDao extends JpaRepository<Cart, String> {

    Cart findByCartIdAndUserId(String cartId, String userId);

}
