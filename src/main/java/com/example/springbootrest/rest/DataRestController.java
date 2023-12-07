package com.example.springbootrest.rest;

import com.example.springbootrest.entity.Strategy;
import com.example.springbootrest.entity.User;
import com.example.springbootrest.service.StrategyService;
import com.example.springbootrest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
public class DataRestController {
    // expose "/user" and return a list of Users
    private UserService userService;
    private StrategyService strategyService;
    // inject user dao
    @Autowired
    public DataRestController(UserService theUserService, StrategyService theStrategyService) {
        userService = theUserService;
        strategyService = theStrategyService;
    }

    @GetMapping("/")
    public String welcome() {
        return "Hello World!";
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
    @GetMapping("/strategies/{theId}")
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
    }

    @GetMapping("/strategies/getJson/{theId}")
    public Strategy saveStrategyJsonLocally(@PathVariable int theId) {
        Strategy theStrategy = strategyService.findById(theId);
        return theStrategy;
    }

    // upload one
    @PostMapping(value="/strategies", consumes = {"multipart/form-data"})
    public ResponseEntity<String> strategyUpload(@RequestParam(value="any_file", required=false) MultipartFile file) {
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
}
