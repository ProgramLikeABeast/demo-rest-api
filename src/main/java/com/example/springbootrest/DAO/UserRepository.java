package com.example.springbootrest.DAO;

import com.example.springbootrest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    // that's it ... no need to write any code
    List<User> findUsersByEmailAndPassword(String email, String password);
}
