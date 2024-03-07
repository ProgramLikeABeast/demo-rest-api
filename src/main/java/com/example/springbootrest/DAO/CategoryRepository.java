package com.example.springbootrest.DAO;

import com.example.springbootrest.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findByRoot(String root);
    //TODO need to be fixed
    List<Category> findByRootIsNull();
}
