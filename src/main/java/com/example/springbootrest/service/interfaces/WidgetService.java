package com.example.springbootrest.service.interfaces;

import com.example.springbootrest.entity.Widget;

import java.io.IOException;

public interface WidgetService {
    Widget findById(int theId);
    Widget saveWidget(Widget theWidget) throws IOException;
    void deleteAll();
    long getcount();
}
