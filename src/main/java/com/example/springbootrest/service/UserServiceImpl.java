package com.example.springbootrest.service;

import com.example.springbootrest.DAO.UserRepository;
import com.example.springbootrest.entity.User;
import com.example.springbootrest.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userDAO;

    @Autowired
    public UserServiceImpl(UserRepository theDataDAO) {
        userDAO = theDataDAO;
    }

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public User findUserById(int theId) {
        Optional<User> result = userDAO.findById(theId);
        User theUser = null;

        if(result.isPresent()) {
            theUser = result.get();
        }else {
            throw new RuntimeException("Did not find user id - " + theId);
        }

        return theUser;
    }

    @Override
    public User findUserByEmail(String theEmail) {
        Optional<User> result = userDAO.findUserByEmail(theEmail);
        User theUser = null;

        if(result.isPresent()) {
            theUser = result.get();
        }else {
            throw new RuntimeException("Did not find user with email - " + theEmail);
        }

        return theUser;
    }

    @Override
    public User findVerifiedUsersByEmailAndPassword(String theEmail, String thePassword, boolean verified) throws RuntimeException {
        Optional<User> result = userDAO.findUsersByEmailAndPasswordAndVerified(theEmail, thePassword,  true);
        User theUser = null;

        if(result.isPresent()) {
            theUser = result.get();
        }else {
            throw new RuntimeException("Did not find user with email - " + theEmail);
        }

        return theUser;
    }

    @Override
    public User findUserByPhone(String phone) {
        Optional<User> result = userDAO.findUserByPhone(phone);
        User theUser = null;

        if(result.isPresent()) {
            theUser = result.get();
        }else {
            throw new RuntimeException("Did not find user with phone - " + phone);
        }

        return theUser;
    }

    @Override
    public User createUser(User theUser) {
        try {
            return userDAO.save(theUser);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while saving the user.", e);
        }
    }

    @Override
    public void deleteById(int theId) {
        try {
            userDAO.deleteById(theId);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while deleting the user.", e);
        }
    }

    @Override
    public void deleteAll() {
        userDAO.deleteAll();
    }

    @Override
    public boolean checkUserExistence(String phone, String pwd) {
        List<User> retList = userDAO.findUsersByPhoneAndPassword(phone, pwd);
        return !retList.isEmpty();
    }

    @Override
    public boolean checkUserVerified(String phone){
        List<User> retList = userDAO.findUsersByPhoneAndVerified(phone, true);
        return !retList.isEmpty();
    }

    @Override
    public void updateUserVerified(String phone, boolean verified) {
        Optional<User> retVal = userDAO.findUserByPhone(phone);
        if(retVal.isPresent()){
            User user = retVal.get();
            user.setVerified(verified);
            userDAO.save(user);
        }
    }

    @Override
    public boolean updateUserPassword(String phone, String password) {
        Optional<User> retVal = userDAO.findUserByPhone(phone);
        if(retVal.isPresent()){
            User user = retVal.get();
            user.setPassword(password);
            userDAO.save(user);
            return true;
        }
        return false;
    }

    @Override
    public User update(User theUser) {
        try {
            return userDAO.save(theUser);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while updating the user.", e);
        }
    }
}
