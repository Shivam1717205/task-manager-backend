package com.example.taskmanager.service;

import com.example.taskmanager.entity.User;
import com.example.taskmanager.enums.Role;
import com.example.taskmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User register(User user) {
        if(user.getRole() == null){
            user.setRole(Role.USER);
        }
        return userRepository.save(user);
    }

    public User login(String email, String password) {
        // 1. Find user or return null/throw custom error
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

        // 2. Check password
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }

        return user;

    }
}