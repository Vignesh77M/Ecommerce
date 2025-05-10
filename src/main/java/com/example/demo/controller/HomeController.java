package com.example.demo.controller;
import jakarta.servlet.http.HttpSession;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String indexPage(Model model, HttpSession session) {
        Object user = session.getAttribute("user");
        boolean isLoggedIn = (user != null);

        model.addAttribute("isLoggedIn", isLoggedIn);
        model.addAttribute("products", productService.getAllProducts());
        return "index";
    }


    @GetMapping("/home")
    public String homePage(Model model) {
        List<Product> products = productService.getAllProducts(); // Fetch all products
        model.addAttribute("products", products);
        return "home"; // This will return the home.html page
    }
}
