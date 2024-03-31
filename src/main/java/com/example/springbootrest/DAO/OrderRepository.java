package com.example.springbootrest.DAO;

import com.example.springbootrest.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByStatusOrderByOid(int status);
    List<Order> findByUidEquals(int uid);
}
