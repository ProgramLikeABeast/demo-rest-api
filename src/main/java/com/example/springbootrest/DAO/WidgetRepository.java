package com.example.springbootrest.DAO;

import com.example.springbootrest.entity.Widget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WidgetRepository extends JpaRepository<Widget, Integer> {
}
