package com.example.springbootrest.service;

import com.example.springbootrest.DAO.UserRepository;
import com.example.springbootrest.entity.User;
import com.example.springbootrest.entity.VerificationCode;
import com.example.springbootrest.DAO.VerificationCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;
import java.time.Duration;

@Service
@Transactional
public class VerificationCodeServiceImpl {

    private VerificationCodeRepository vcDAO;
    private UserRepository userDAO;

    @Autowired
    public VerificationCodeServiceImpl(VerificationCodeRepository vcDAO, UserRepository userDAO) {
        this.vcDAO = vcDAO;
        this.userDAO = userDAO;
    }

    public List<VerificationCode> getAllVerificationCodes() {
        return vcDAO.findAll();
    }

    public Optional<VerificationCode> getVerificationCodeById(Integer id) {
        return vcDAO.findById(id);
    }

    // create a verification code entry
    public VerificationCode saveVerificationCode(VerificationCode verificationCode) {
        return vcDAO.save(verificationCode);
    }

    public void deleteVerificationCode(Integer id) {
        vcDAO.deleteById(id);
    }

    public void deleteAllVerificationCodes() {
        vcDAO.deleteAll();
    }

    // verify the code by phone and code
    public boolean verifyByPhoneAndCode(String phone, String code) {
        List<VerificationCode> list = vcDAO.getVerificationCodesByUserPhone(phone);
        LocalDateTime now = LocalDateTime.now();
        
        for (VerificationCode vc : list) {
            if (vc.getCode().equals(code)) {
                Duration duration = Duration.between(vc.getTimeCreated(), now);
                if (duration.toMinutes() <= 10) {
                    return true;
                }
            }
        }
        return false;
    }

    public void deleteVerificationCodesByPhone(String phone) {
        List<VerificationCode> list = vcDAO.getVerificationCodesByUserPhone(phone);
        for (VerificationCode vc : list) {
            vcDAO.delete(vc);
        }
    }
}