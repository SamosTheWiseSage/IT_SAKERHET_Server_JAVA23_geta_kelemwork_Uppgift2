package com.example.demo.service;

import com.example.demo.Model.TimeCapsule;
import com.example.demo.Model.User;
import com.example.demo.repository.TimeCapsuleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.EncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeCapsuleService {

    @Autowired
    private TimeCapsuleRepository timeCapsuleRepository; // Inject your TimeCapsuleRepository

    @Autowired
    private UserRepository userRepository; // Inject your UserRepository
    @Autowired
    private UserService userService;

    public void createTimeCapsule(Long userId, String encryptedMessage) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID must not be null");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found")); // Handle user not found

        TimeCapsule timeCapsule = new TimeCapsule();
        timeCapsule.setEncryptedMessage(encryptedMessage);
        timeCapsule.setUser(user);

        timeCapsuleRepository.save(timeCapsule); // Save the time capsule
    }

    public List<TimeCapsule> getTimeCapsules(Long userId) {
        // Implement your logic to get time capsules for the user
        return timeCapsuleRepository.findByUserId(userId); // Assume you have this method in your repository
    }
}