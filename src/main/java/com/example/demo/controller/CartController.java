package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import com.example.demo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CartController {

    private final CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/cart")
    public String showCartPage(Model model) {
        model.addAttribute("cart", cartService.getCart());
        return "cart";
    }

    @GetMapping("/checkout")
    public String showCheckoutPage(@RequestParam(name = "productId", required = false) Long productId, Model model) {
        if (productId == null) {
            model.addAttribute("product", null);
            return "checkout";
        }
        Product product = productService.getProductById(productId);
        if (product == null) {
            return "redirect:/error-page";
        }
        model.addAttribute("product", product);
        return "checkout";
    }


    @PostMapping("/process-checkout")
    public String processCheckout(@RequestParam String name,
                                  @RequestParam String address,
                                  @RequestParam String paymentMethod) {
        System.out.println("Name: " + name);
        System.out.println("Address: " + address);
        System.out.println("Payment Method: " + paymentMethod);
        return "redirect:/";
    }
}
