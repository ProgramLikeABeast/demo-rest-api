package com.example.springbootrest.service;

import com.example.springbootrest.DAO.UserRepository;
import com.example.springbootrest.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository theDataDAO) {
        userRepository = theDataDAO;
    }
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(int theId) {

        Optional<User> result = userRepository.findById(theId);

        User theUser = null;
        if(result.isPresent()) {
            theUser = result.get();
        }else {
            throw new RuntimeException("Did not find user id - " + theId);
        }

        return theUser;
    }

    @Override
    public User save(User theUser) {
        return userRepository.save(theUser);
    }

    @Override
    public void deleteById(int theId) {
        userRepository.deleteById(theId);
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Override
    public boolean checkUserExistence(String email, String pwd) {
        List<User> retList = userRepository.findUsersByEmailAndPassword(email, pwd);
        return !retList.isEmpty();
    }
}
