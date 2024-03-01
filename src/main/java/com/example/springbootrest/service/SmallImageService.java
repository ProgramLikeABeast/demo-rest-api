package com.example.springbootrest.service;

import com.example.springbootrest.entity.SmallImage;

import java.io.IOException;

public interface SmallImageService {
    SmallImage findById(int theId);
    SmallImage saveImage(SmallImage theImage) throws IOException;
    void deleteAll();
    long getcount();
}
