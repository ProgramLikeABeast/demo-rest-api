package com.example.springbootrest.service;

import com.example.springbootrest.entity.Product;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product findById(int theId);
    List<Product> findByCategory(int theCid);
    List<Product> find4ByCategory(int theCid);
    Product findByProductName(String name);
    Product saveProduct(Product theWidget) throws IOException;
    void deleteAll();
    long getcount();
}
