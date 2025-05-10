package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth") // Handles login and authentication
public class AuthController {

    @Autowired
    private UserService userService;
    
    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // Return the login.html page from templates
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String email, @RequestParam String password) {
        User user = userService.getUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            // Assuming successful login, redirect to the home page
            return "redirect:/home"; 
        } else {
            // If login fails, redirect back to the login page
            return "redirect:/auth/login"; 
        }
    }
}
