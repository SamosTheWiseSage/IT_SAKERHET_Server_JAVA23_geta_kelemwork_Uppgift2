package com.example.demo.controller;

import com.example.demo.Model.TimeCapsule;
import com.example.demo.Model.User;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.TimeCapsuleRequest;
import com.example.demo.service.TimeCapsuleService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private TimeCapsuleService timeCapsuleService;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        // Extract email and password from the user object
        String email = user.getEmail();
        String password = user.getPassword();
        System.out.println(user.getEmail());
        userService.registerUser(email, password);
        return "User registered successfully!";
    }
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        System.out.println(loginRequest.getEmail());
        return userService.loginUser(loginRequest.getEmail(), loginRequest.getPassword());
    }


    @PostMapping("/timecapsules")
    public String createTimeCapsule(@RequestBody TimeCapsuleRequest timeCapsuleRequest) {
        timeCapsuleService.createTimeCapsule(timeCapsuleRequest.getUserId(), timeCapsuleRequest.getMessage());
        return "Time capsule created!";
    }

    @GetMapping("/timecapsules")
    public List<TimeCapsule> getTimeCapsules(@RequestParam Long userId) {
        return timeCapsuleService.getTimeCapsules(userId);
    }
    @GetMapping("/api")
    public Resource Homepage(){
        return new ClassPathResource("templates/index.html");
    }
}