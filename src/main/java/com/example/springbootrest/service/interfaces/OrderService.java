package com.example.springbootrest.service.interfaces;

import com.example.springbootrest.entity.Order;

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