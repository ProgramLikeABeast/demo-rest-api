package com.example.springbootrest.service.interfaces;

import com.example.springbootrest.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {

    List<User> findAll();
    User findUserById(int theId) throws RuntimeException;
    User findUserByEmail(String theEmail) throws RuntimeException;
    User findUserByPhone(String theEmail) throws RuntimeException;
    User createUser(User theUser);
    void deleteById(int theId);
    void deleteAll();
    boolean checkUserExistence(String phone, String pwd);
    boolean checkUserVerified(String phone);
    void updateUserVerified(String phone, boolean verified);
    boolean updateUserPassword(String phone, String password);
    User update(User theUser);
}
