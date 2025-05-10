package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller // Change from @RestController to @Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Serve the signup form (HTML)
    @GetMapping("/signup")
    public String showSignupForm() {
        return "signup"; // Return the signup.html page from templates
    }

    // Handle form submission
    @PostMapping("/register")
    public String registerUser(@RequestParam String name,
                               @RequestParam String email,
                               @RequestParam String password) {
        // Create the User object from the form fields
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        
        // Register the user
        userService.registerUser(user);
        
        // Redirect to login page after successful registration
        return "redirect:/auth/login"; // Or wherever you want to redirect after signup
    }
}
