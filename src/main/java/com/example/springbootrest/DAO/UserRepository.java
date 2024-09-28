package com.example.springbootrest.DAO;

import com.example.springbootrest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    // that's it ... no need to write any code
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserByPhone(String phone);
    Optional<User> findUsersByEmailAndPasswordAndVerified(String email, String password, boolean verified);
    List<User> findUsersByPhoneAndPassword(String email, String password);
    List<User> findUsersByPhoneAndVerified(String email, boolean verified);
}
