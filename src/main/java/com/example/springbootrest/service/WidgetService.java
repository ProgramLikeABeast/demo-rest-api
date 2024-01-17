package com.example.springbootrest.service;

import com.example.springbootrest.entity.Strategy;
import com.example.springbootrest.entity.Widget;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface WidgetService {
    Widget findById(int theId);
    Widget saveWidget(Widget theWidget) throws IOException;
    void deleteAll();
    long getcount();
}
