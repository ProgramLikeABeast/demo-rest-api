package com.example.springbootrest.service;

import com.example.springbootrest.DAO.ProductRepository;
import com.example.springbootrest.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productDAO;
    @Autowired
    public ProductServiceImpl(ProductRepository theProductRepository) {
        productDAO = theProductRepository;
    }

    @Override
    public List<Product> findAll() {
        return productDAO.findAll();
    }

    @Override
    public Product findById(int theId) {
        Optional<Product> result = productDAO.findById(theId);

        Product theProduct = null;
        if(result.isPresent()) {
            theProduct = result.get();
        }else {
            throw new RuntimeException("Cannot find the strategy");
        }

        return theProduct;
    }

    @Override
    public Product saveProduct(Product theProduct) throws IOException {
        return productDAO.save(theProduct);
    }

    @Override
    public void deleteAll() {
        productDAO.deleteAll();
    }

    @Override
    public long getcount() {
        return productDAO.count();
    }

    @Override
    public List<Product> findByCategory(int theCid) {
        return productDAO.findByCid(theCid);
    }

    @Override
    public List<Product> find4ByCategory(int theCid) {
        return productDAO.findTop4ByCidOrderByPid(theCid);
    }
}