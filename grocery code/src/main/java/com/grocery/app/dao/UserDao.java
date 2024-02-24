package com.grocery.app.dao;

import com.grocery.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, String> {

     User findByUserId(String id);

     User findByUserIdAndPassword(String userId, String password);
}
