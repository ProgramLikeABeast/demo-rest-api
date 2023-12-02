package com.example.springbootrest.service;

import com.example.springbootrest.DAO.StrategyRepository;
import com.example.springbootrest.entity.Strategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class StrategyServiceImpl implements StrategyService{

    private StrategyRepository strategyRepository;
    @Autowired
    public StrategyServiceImpl(StrategyRepository theStrategyRepository) {
        strategyRepository = theStrategyRepository;
    }

    @Override
    public Strategy findById(int theId) {
        Optional<Strategy> result = strategyRepository.findById(theId);

        Strategy theStrategy = null;
        if(result.isPresent()) {
            theStrategy = result.get();
        }else {
            throw new RuntimeException("Cannot find the strategy");
        }

        return theStrategy;
    }

    @Override
    public Strategy saveFile(MultipartFile file) throws IOException {
        Strategy theStrategy = new Strategy();
        theStrategy.setFilename(file.getOriginalFilename());
        theStrategy.setContentType(file.getContentType());
        theStrategy.setFileData(file.getBytes());
        return strategyRepository.save(theStrategy);
    }

    @Override
    public void deleteAll() {
        strategyRepository.deleteAll();
    }
}
