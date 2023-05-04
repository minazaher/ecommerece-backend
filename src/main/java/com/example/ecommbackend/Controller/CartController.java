package com.example.ecommbackend.Controller;

import com.example.ecommbackend.Model.Product;
import com.example.ecommbackend.Model.Order;
import com.example.ecommbackend.Model.User;
import com.example.ecommbackend.Service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    @PostMapping("/create")
    public Set<Product> createCart(HttpSession session, HttpServletRequest request) {
       return cartService.createCart(session, request);
    }

    @PostMapping("/addProduct/{productId}")
    public ResponseEntity<Set<Product>> addProductToCart(HttpServletRequest request, @PathVariable String productId) {
        Set<Product> cart = cartService.addProductToCart(request, Integer.parseInt(productId));
        return ResponseEntity.ok(cart);
    }

    @GetMapping("/viewCart")
    public ResponseEntity<?> viewCart(HttpSession session){
        Set<Product> cart = cartService.getCart(session)    ;
        return ResponseEntity.ok(cart);
    }

    @GetMapping("/confirm")
    public ResponseEntity<String> confirmCart(HttpSession session) {
        String orderDetails = cartService.confirmOrder(session);
        return ResponseEntity.ok("Order created successfully and its details are : "
                + orderDetails);
    }


}
