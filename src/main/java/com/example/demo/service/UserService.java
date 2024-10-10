package com.example.demo.service;

import com.example.demo.Model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.JwtUtil;
import com.example.demo.util.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Constructor without Spring dependency injection
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new PasswordEncoder(); // Create a new instance
    }

    public String registerUser(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));

        userRepository.save(user);
        return "User registered successfully!";
    }
    public User findById(Long userId) {
        return userRepository.findById(userId).orElse(null); // Adjust based on your repository
    }
    public String loginUser(String email, String password) {
        try {
            Optional<User> userOpt = userRepository.findByEmail(email);
            if (userOpt.isPresent()) {
                User user = userOpt.get();
                if (passwordEncoder.matches(password, user.getPassword())) {
                    return JwtUtil.generateToken(user.getEmail());
                }
            }
        } catch (Exception e) {
            // Log the error
            System.err.println("Login error: " + e.getMessage());
        }
        return null; // Or throw a custom exception if needed
    }
}
