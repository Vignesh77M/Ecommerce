package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import com.example.demo.service.CartService; // Assuming you have a CartService to handle cart operations
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class OrderController {

    private final ProductService productService;
    private final CartService cartService;

    @Autowired
    public OrderController(ProductService productService, CartService cartService) {
        this.productService = productService;
        this.cartService = cartService;
    }

    // Method to handle 'Buy Now' button click
    @GetMapping("/buy-now/{productId}")
    public String buyNow(@PathVariable Long productId, Model model) {
        // Fetch the product by its ID
        Product product = productService.getProductById(productId);

        if (product != null) {
            // Redirect to the checkout page with the product details
            model.addAttribute("product", product);
            return "checkout"; // Displays checkout page
        } else {
            return "error"; // Handle product not found case
        }
    }

    // Method to handle 'Add to Cart' button click
    @PostMapping("/add-to-cart/{productId}")
    public String addToCart(@PathVariable Long productId) {
        Product product = productService.getProductById(productId);
        
        if (product != null) {
            // Add the product to the cart (CartService should handle this logic)
            cartService.addToCart(product);
            return "redirect:/cart"; // Redirect to cart page
        } else {
            return "error"; // Handle product not found case
        }
    }
}
