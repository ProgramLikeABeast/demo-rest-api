package com.example.springbootrest.DAO;

import com.example.springbootrest.entity.Category;
import com.example.springbootrest.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByCid(int cid);
    List<Product> findTop4ByCidOrderByPid(int cid);
}
