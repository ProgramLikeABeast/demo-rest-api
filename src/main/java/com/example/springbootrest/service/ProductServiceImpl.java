package com.example.springbootrest.service;

import com.example.springbootrest.DAO.ProductRepository;
import com.example.springbootrest.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    @Autowired
    public ProductServiceImpl(ProductRepository theProductRepository) {
        productRepository = theProductRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(int theId) {
        Optional<Product> result = productRepository.findById(theId);

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
        return productRepository.save(theProduct);
    }

    @Override
    public void deleteAll() {
        productRepository.deleteAll();
    }

    @Override
    public long getcount() {
        return productRepository.count();
    }
}