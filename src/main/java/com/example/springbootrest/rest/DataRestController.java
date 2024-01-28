package com.example.springbootrest.rest;

import com.example.springbootrest.entity.Strategy;
import com.example.springbootrest.entity.User;
import com.example.springbootrest.entity.Widget;
import com.example.springbootrest.entity.WidgetInfo;
import com.example.springbootrest.service.StrategyService;
import com.example.springbootrest.service.UserService;
import com.example.springbootrest.service.WidgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class DataRestController {
    // expose "/user" and return a list of Users
    private UserService userService;
    private StrategyService strategyService;
    private WidgetService widgetService;
    // inject user dao
    @Autowired
    public DataRestController(
            UserService theUserService,
            StrategyService theStrategyService,
            WidgetService theWidgetService
            ) {
        userService = theUserService;
        strategyService = theStrategyService;
        widgetService = theWidgetService;
    }

    @GetMapping("/")
    public String welcome() {
        return "Hello World! <a href=\"/login\">Login</a> <a href=\"/logout\">Logout</a> <a href=\"/user_profile\">user</a>";
    }

    // select all
    @GetMapping("/users")
    public List<User> findAll() {
        return userService.findAll();
    }

    // select one
    @GetMapping("/users/{userId}")
    public User getUser(@PathVariable int userId) {
        User theUser = userService.findById(userId);

        if(theUser == null) {
            throw new RuntimeException("User id not found - " + userId);
        }
        return theUser;
    }

    // create new
    @PostMapping("/users")
    public User addUser(@RequestBody User theUser) {
        // in case they pass an id on JSON ... set id to 0
        // is to force a save of a new item ... instead of update
        theUser.setId(0);
        User dbUser = userService.save(theUser);
        return dbUser;
    }

    // Update one
    @PutMapping("/users")
    public User updateUser(@RequestBody User theUser) {

        User dbUser = userService.save(theUser);
        return dbUser;
    }

    // Delete one
    @DeleteMapping("/users/{userId}")
    public String deleteUser(@PathVariable int userId) {

        User tempUser = userService.findById(userId);
        if(tempUser == null) {
            throw new RuntimeException("User id not found - " + userId);
        }

        userService.deleteById(userId);
        return "Deleted user id - " + userId;
    }

    // Delete all
    @DeleteMapping("/users")
    public String deleteAllUsers() {
        userService.deleteAll();
        return "Deleted all users.";
    }

    // Strategy Section

    // select one
    // This method saves the file data to a local file
    /*@GetMapping("/strategies/{theId}")
    public ResponseEntity<String> saveStrategiesLocally(@PathVariable int theId) {
        Strategy fileEntity = strategyService.findById(theId);

        if (fileEntity != null) {
            try {
                String filePath = "C:\\Users\\xuyan\\OneDrive\\Desktop\\receive folder\\" + fileEntity.getFilename();
                FileOutputStream fos = new FileOutputStream(filePath);
                fos.write(fileEntity.getFileData());
                fos.close();
                return ResponseEntity.ok("File saved locally: " + filePath);
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save the file locally");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File not found");
        }
    }*/

    @GetMapping("/strategies/{theId}")
    public ResponseEntity<byte []> getStrategyFile(@PathVariable int theId) {
        Strategy theStrategy = strategyService.findById(theId);

        HttpHeaders headers = new HttpHeaders();
        headers.add("file-name", theStrategy.getFilename());
        headers.add("content-type", theStrategy.getContentType());

        return ResponseEntity.ok()
                .headers(headers)
                .body(theStrategy.getFileData());
    }

    @GetMapping("/strategies/getJson/{theId}")
    public Strategy saveStrategyJsonLocally(@PathVariable int theId) {
        Strategy theStrategy = strategyService.findById(theId);
        return theStrategy;
    }

    // upload one
    @PostMapping(value="/strategies", consumes = {"multipart/form-data"})
    public ResponseEntity<String> uploadStrategy(@RequestParam(value="any_file", required=false) MultipartFile file) {
        try {
            Strategy theStrategyFile = strategyService.saveFile(file);
            return ResponseEntity.ok("File uploaded successfully. File ID: " + theStrategyFile.getId());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload the file");
        }
    }

    @DeleteMapping("/strategies")
    public String deleteAllStrategies() {
        strategyService.deleteAll();
        return "Deleted all strategies.";
    }

    @GetMapping("/widgets/count")
    public long getWidgetCount() {
        return widgetService.getcount();
    }
    // load a widget
    @GetMapping("/widgets/{theId}")
    public WidgetInfo getWidget(@PathVariable int theId) {
        Widget theWidget = widgetService.findById(theId);

        return new WidgetInfo(theWidget.getWidget_name(),
                          theWidget.getText_description(),
                          theWidget.getBg_color()
                          );
    }

    // load a widget pict
    @GetMapping("/widgets/pict/{theId}")
    public ResponseEntity<byte []> getWidgetPict(@PathVariable int theId) {
        Widget theWidget = widgetService.findById(theId);

        HttpHeaders headers = new HttpHeaders();
        headers.add("file-name", theWidget.getLarge_image_filename());
        headers.add("content-type", theWidget.getLarge_image_content_type());

        return ResponseEntity.ok()
                .headers(headers)
                .body(theWidget.getLarge_image_data());
    }

    // upload a widget
    @PostMapping(value="/widgets", consumes = {"multipart/form-data"})
    public ResponseEntity<String> uploadWidget(@RequestParam(value="avatar_image", required=false) MultipartFile avatar_image,
                                               @RequestParam(value="large_image", required=false) MultipartFile large_image,
                                               @RequestParam String widget_name,
                                               @RequestParam String description,
                                               @RequestParam String bg_color
                                               ) throws IOException {
        Widget theWidget = new Widget(
                widget_name,
                description,
                bg_color,
                avatar_image.getContentType(),
                avatar_image.getOriginalFilename(),
                avatar_image.getBytes(),
                large_image.getContentType(),
                large_image.getOriginalFilename(),
                large_image.getBytes()
                );
        try {
            widgetService.saveWidget(theWidget);
            return ResponseEntity.ok("Widget uploaded successfully. File ID: " + theWidget.getId());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload the widget");
        }
    }

    // Delete all widgets
    @DeleteMapping("/widgets")
    public String deleteAllWidgets() {
        widgetService.deleteAll();
        return "Deleted all widgets.";
    }
}
