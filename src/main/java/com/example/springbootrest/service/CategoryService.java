package com.example.springbootrest.service;

import com.example.springbootrest.entity.Category;

import java.io.IOException;
import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    List<Category> findAllRootCategories();
    List<Category> findAllCategoriesByRoot(String rootCategory);
    Category findByCid(int theCid);
    Category saveCategory(Category theCategory) throws IOException;
    void deleteAll();
}
