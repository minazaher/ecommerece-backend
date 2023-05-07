package com.example.ecommbackend.Controller;

import com.example.ecommbackend.Model.Product;

import com.example.ecommbackend.Service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    @PostMapping("/create")
    public ResponseEntity<Set<Product>> createCart(HttpSession session, HttpServletRequest request) {
       return cartService.createCart(session, request);
    }

    @PutMapping("/addProduct")
    public ResponseEntity<Set<Product>> addProductToCart(HttpServletRequest request,
                                                         @RequestParam("productId") int productId) {
        return cartService.addProductToCart(request, productId);
    }

    @GetMapping("/viewCart")
    public ResponseEntity<?> viewCart(HttpSession session){
        Set<Product> cart = cartService.getSessionCart(session)    ;
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/confirm")
    public ResponseEntity<String> confirmCart(HttpSession session) {
        return cartService.confirmOrder(session);
    }


}
