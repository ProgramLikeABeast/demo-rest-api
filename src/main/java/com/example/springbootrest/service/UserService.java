package com.example.springbootrest.service;

import com.example.springbootrest.entity.User;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserService {

    List<User> findAll();
    User findById(int theId);
    User save(User theUser);
    void deleteById(int theId);
    void deleteAll();
    boolean checkUserExistence(String email, String pwd);
}
