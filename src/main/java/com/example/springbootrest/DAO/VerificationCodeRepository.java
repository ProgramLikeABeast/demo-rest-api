package com.example.springbootrest.DAO;

import com.example.springbootrest.entity.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Integer> {

    List<VerificationCode> getVerificationCodesByUserPhone(String phone);
    List<VerificationCode> deleteVerificationCodesByUserPhone(String phone);
}