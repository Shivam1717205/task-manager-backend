package com.example.taskmanager.controller;
import com.example.taskmanager.entity.User;
import com.example.taskmanager.security.JwtUtil;
import com.example.taskmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin()
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.register(user);
    }
    @GetMapping ("/admin/test")
        public String adminOnly(){
        return "Only admin can access this resource";
        }
    @PostMapping("/login")
    public String login(@RequestBody User user) {
        try {
            User u = userService.login(user.getEmail(), user.getPassword());
            return jwtUtil.generateToken(u.getEmail());
        } catch (Exception e) {
            return "Error: " + e.getMessage(); // This will show "User not found" in Postman
        }
    }
}
