package com.example.springbootrest.service;

import com.example.springbootrest.DAO.CategoryRepository;
import com.example.springbootrest.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{
    private CategoryRepository categoryDAO;
    @Autowired
    public CategoryServiceImpl(CategoryRepository theProductRepository) {
        categoryDAO = theProductRepository;
    }
    @Override
    public List<Category> findAll() {
        return categoryDAO.findAll();
    }

    @Override
    public List<Category> findAllRootCategories() {
        return categoryDAO.findByRoot("null");
    }

    @Override
    public List<Category> findAllCategoriesByRoot(String rootCategory) {
        return categoryDAO.findByRoot(rootCategory);
    }

    @Override
    public Category findByCid(int theCid) {
        Optional<Category> result = categoryDAO.findById(theCid);

        Category theCategory = null;
        if(result.isPresent()) {
            theCategory = result.get();
        }else {
            throw new RuntimeException("Did not find category id - " + theCid);
        }

        return theCategory;
    }

    @Override
    public Category saveCategory(Category theCategory){
        return categoryDAO.save(theCategory);
    }

    @Override
    public void deleteAll() {
        categoryDAO.deleteAll();
    }
}
