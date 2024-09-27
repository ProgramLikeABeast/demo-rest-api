package com.example.springbootrest.rest;

import com.example.springbootrest.entity.User;
import com.example.springbootrest.entity.VerificationCode;
import com.example.springbootrest.service.SmsService;
import com.example.springbootrest.service.VerificationCodeServiceImpl;
import com.example.springbootrest.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@RestController
public class UserController {

    private final UserService userService;
    private final SmsService smsService;
    private final VerificationCodeServiceImpl verificationCodeService;

    @Autowired
    public UserController(UserService userService, SmsService smsService, VerificationCodeServiceImpl verificationCodeService) {
        this.userService = userService;
        this.smsService = smsService;
        this.verificationCodeService = verificationCodeService;
    }

    // select all
    @GetMapping("/users")
    public List<User> findAllUsers() {
        return userService.findAll();
    }

    // select one
    @GetMapping("/users/{userId}")
    public User getUser(@PathVariable int userId) {
        User theUser = userService.findUserById(userId);

        if(theUser == null) {
            throw new RuntimeException("User id not found - " + userId);
        }
        return theUser;
    }

    @GetMapping("/users/byEmail/{userEmail}")
    public User getUser(@PathVariable String userEmail) {
        return userService.findUserByEmail(userEmail);
    }

    // create a new user
    @PostMapping("/users")
    public User addUser(@RequestBody User theUser) {
        // in case they pass an id on JSON ... set id to 0
        // is to force a save of a new item ... instead of update
        theUser.setId(0);
        return userService.createUser(theUser);
    }

    // Update one
    @PutMapping("/users")
    public User updateUser(@RequestBody User theUser) {
        return userService.update(theUser);
    }

    // Delete one
    @DeleteMapping("/users/{userId}")
    public String deleteUser(@PathVariable int userId) {

        User tempUser = userService.findUserById(userId);
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

    @GetMapping("/login")
    public boolean loginUser(@RequestParam String phone, @RequestParam String password) {
        if(!userService.checkUserExistence(phone, password)) return false;
        return userService.checkUserVerified(phone);
    }

    @GetMapping("check-user")
    public boolean checkPhoneExists(@RequestParam String phone) {
        return userService.checkUserVerified(phone);
    }

    @PostMapping("change-password")
    public boolean changePassword(@RequestParam String phone, @RequestParam String password) {
        return userService.updateUserPassword(phone, password);
    }

    @GetMapping("/vcs")
    public List<VerificationCode> getVerificationCodes() {
        return verificationCodeService.getAllVerificationCodes();
    }

    @DeleteMapping("/vcs")
    public void deleteAllVerificationCodes() {
        verificationCodeService.deleteAllVerificationCodes();
    }

    @GetMapping("/verify")
    public ResponseEntity<String> sendSms(@RequestParam String toPhoneNumber) {
        String code = codeGenerator();
        VerificationCode vc = new VerificationCode();
        vc.setUserPhone(toPhoneNumber);
        vc.setCode(code);
        vc.setTimeCreated(LocalDateTime.now());
        verificationCodeService.saveVerificationCode(vc);

        String message = "[Momo Security] Your verification code is " + code + ". The code will expire in 10 minutes.";
        String result = smsService.sendSms(toPhoneNumber, message);

        if (result.startsWith("SMS sent successfully")) {
            return ResponseEntity.ok("Verification code sent successfully");
        } else {
            // Log the error for debugging purposes
            System.err.println("SMS sending failed: " + result);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Failed to send verification code. Please try again later.");
        }
    }

    @PostMapping("/verify")
    public boolean verifyCode(@RequestParam String phone, @RequestParam String code) {
        boolean res = verificationCodeService.verifyByPhoneAndCode(phone, code);
        if(res) userService.updateUserVerified(phone, true);
        return res;
    }

    private String codeGenerator() {
        Random rand = new Random();
        int num = rand.nextInt(1000000); // Generate a number from 0 to 999999
        return String.format("%06d", num); // Format as a 6-digit string with leading zeros
    }
}
