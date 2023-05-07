package com.example.ecommbackend.Service;

import com.example.ecommbackend.Model.Order;
import com.example.ecommbackend.Model.Product;
import com.example.ecommbackend.Model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductService productService;
    private final JwtService jwtService;
    private final UserService userService;
    private final OrderService orderService;

    public ResponseEntity<Set<Product>> createCart(HttpSession session, HttpServletRequest request) {
        String token = jwtService.extractTokenFromHeader(request);
        String email = jwtService.extractUsername(token);
        User user = userService.findUserIfExists(email);
        session.setAttribute("user",user);
        session.setAttribute("cart", new HashSet<Product>());
        Set<Product> cart = (Set<Product>) session.getAttribute("cart");
        return ResponseEntity.ok(cart);
    }
    public ResponseEntity<Set<Product>> addProductToCart(HttpServletRequest request, int productId) {
        HttpSession session = request.getSession();
        Set<Product> cart = getSessionCart(session);
        if (cart == null) {
            cart = new HashSet<>();
            session.setAttribute("cart", cart);
        }
        Product product = productService.getProductsById(productId);
        cart.add(product);
        return ResponseEntity.ok(cart);
    }

    public ResponseEntity<String> confirmOrder(HttpSession session){
        Set<Product> cart = getSessionCart(session);
        if (cart == null || cart.isEmpty()) {
            return ResponseEntity.ok("Cart is empty");
        }
        clearCart(session);
        return ResponseEntity.ok("Order Confirmed Successfully");
    }

    public Order saveCartAsOrder(HttpSession session){
        User user = (User) session.getAttribute("user");
        Set<Product> cart = getSessionCart(session);
        return orderService.createOrderForUser(user.getId(), cart);
    }

    public Set<Product> getSessionCart(HttpSession session) {
        return (Set<Product>) session.getAttribute("cart");
    }

    public void clearCart(HttpSession session) {
        session.removeAttribute("cart");
    }
}
