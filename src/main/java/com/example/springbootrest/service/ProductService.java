package com.example.springbootrest.service;

import com.example.springbootrest.entity.Product;
import com.example.springbootrest.entity.User;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product findById(int theId);
    Product saveProduct(Product theWidget) throws IOException;
    void deleteAll();
    long getcount();
}
