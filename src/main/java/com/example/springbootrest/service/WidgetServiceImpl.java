package com.example.springbootrest.service;

import com.example.springbootrest.DAO.WidgetRepository;
import com.example.springbootrest.entity.Widget;
import com.example.springbootrest.service.interfaces.WidgetService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class WidgetServiceImpl implements WidgetService {
    private WidgetRepository widgetRepository;

    public WidgetServiceImpl(WidgetRepository widgetRepository) {
        this.widgetRepository = widgetRepository;
    }

    @Override
    public Widget findById(int theId) {
        Optional<Widget> result = widgetRepository.findById(theId);

        Widget theWidget = null;
        if(result.isPresent()) {
            theWidget = result.get();
        }else {
            throw new RuntimeException("Cannot find the widget");
        }

        return theWidget;
    }

    @Override
    public Widget saveWidget(Widget theWidget) throws IOException {
//        Widget theWidget = new Widget();
//        theWidget.setWidget_name(name);
//        theWidget.setBg_color(bg_color);
//        theWidget.setText_description(desc);
//        // store avatar image
//        theWidget.setAvatar_image_filename(avatar_file.getOriginalFilename());
//        theWidget.setAvatar_image_content_type(avatar_file.getContentType());
//        theWidget.setAvatar_image_data(avatar_file.getBytes());
//        // store large image
//        theWidget.setLarge_image_filename(large_file.getOriginalFilename());
//        theWidget.setLarge_image_content_type(large_file.getContentType());
//        theWidget.setLarge_image_data(large_file.getBytes());
        return widgetRepository.save(theWidget);
    }

    @Override
    public void deleteAll() {
        widgetRepository.deleteAll();
    }

    @Override
    public long getcount() {
        return widgetRepository.count();
    }
}
