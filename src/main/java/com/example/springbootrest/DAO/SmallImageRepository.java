package com.example.springbootrest.DAO;

import com.example.springbootrest.entity.SmallImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SmallImageRepository extends JpaRepository<SmallImage, Integer> {

}
