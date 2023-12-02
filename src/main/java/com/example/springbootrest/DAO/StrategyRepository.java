package com.example.springbootrest.DAO;

import com.example.springbootrest.entity.Strategy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StrategyRepository extends JpaRepository<Strategy, Integer> {
}
