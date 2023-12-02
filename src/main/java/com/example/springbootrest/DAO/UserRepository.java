package com.example.springbootrest.DAO;

import com.example.springbootrest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    // that's it ... no need to write any code
}
