package com.example.springbootrest.service;

import com.example.springbootrest.entity.Strategy;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StrategyService {

    Strategy findById(int theId);
    Strategy saveFile(MultipartFile file) throws IOException;
    void deleteAll();

}
