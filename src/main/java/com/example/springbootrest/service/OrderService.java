package com.example.springbootrest.service;

import com.example.springbootrest.entity.Category;
import com.example.springbootrest.entity.Order;
import com.example.springbootrest.entity.User;

import java.io.IOException;
import java.util.List;

public interface OrderService {
    List<Order> findAll();
    List<Order> findByUid(int uid);
    List<Order> findAllUnprocessedOrders(int status);
    Order findByOid(int theId);
    Order save(Order theOrder);
    void deleteByOid(int theOid);
    void deleteAll();
}