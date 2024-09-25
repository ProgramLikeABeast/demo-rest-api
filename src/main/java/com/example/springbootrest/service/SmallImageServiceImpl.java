package com.example.springbootrest.service;

import com.example.springbootrest.DAO.SmallImageRepository;
import com.example.springbootrest.entity.SmallImage;
import com.example.springbootrest.service.interfaces.SmallImageService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class SmallImageServiceImpl implements SmallImageService {
    private SmallImageRepository smallImageRepository;

    public SmallImageServiceImpl(SmallImageRepository theRepository) {
        this.smallImageRepository = theRepository;
    }

    @Override
    public SmallImage findById(int theId) {
        Optional<SmallImage> result = smallImageRepository.findById(theId);

        SmallImage theImage = null;
        if(result.isPresent()) {
            theImage = result.get();
        }else {
            throw new RuntimeException("Load image failed");
        }

        return theImage;
    }

    @Override
    public SmallImage saveImage(SmallImage theImage) throws IOException {
        return smallImageRepository.save(theImage);
    }

    @Override
    public void deleteAll() {
        smallImageRepository.deleteAll();
    }

    @Override
    public long getcount() {
        return smallImageRepository.count();
    }
}
