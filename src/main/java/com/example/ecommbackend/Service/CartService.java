package com.example.ecommbackend.Service;

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
    public String ORDER_NOT_VALID_MSG = "Order Price Must be greater than or equal to 150 EGP";
    public String ORDER_VALID_MSG = "Order Placed!";
    public String CART_EMPTY = "Cart is Empty";

    public ResponseEntity<Set<Product>> createCart(HttpSession session, HttpServletRequest request) {
        String token = jwtService.extractTokenFromHeader(request);
        String email = jwtService.extractUsername(token);
        User user = userService.findUserIfExists(email);

        session.setAttribute("user",user);
        session.setAttribute("cart", new HashSet<Product>());

        Set<Product> cart = (Set<Product>) session.getAttribute("cart");
        return ResponseEntity.ok(cart);
    }
    public ResponseEntity<?> addProductToCart(HttpServletRequest request, int productId) {
        HttpSession session = request.getSession();

        if (session.getAttribute("user") == null){
            return ResponseEntity.status(403).body("You have to Login First");
        }

        Set<Product> cart = getSessionCart(session);
        if (cart == null) {
            cart = new HashSet<>();
            session.setAttribute("cart", cart);
        }
        Product product = productService.getProductsById(productId);
        cart.add(product);
        return ResponseEntity.ok(cart);
    }

    public ResponseEntity<?> confirmOrder(HttpSession session){
        Set<Product> cart = getSessionCart(session);
        if (cart == null || cart.isEmpty()) {
            return ResponseEntity.ok(CART_EMPTY);
        }
        if (isCartPriceValid(cart)){
            productService.consumeCartProducts(cart);
            saveCartAsOrder(session);
            clearCart(session);
            return ResponseEntity.ok(ORDER_VALID_MSG);
        }
        return ResponseEntity.ok(ORDER_NOT_VALID_MSG);
    }

    public void saveCartAsOrder(HttpSession session){
        User user = (User) session.getAttribute("user");
        Set<Product> cart = getSessionCart(session);
        orderService.createOrderForUser(user.getId(), cart, getCartPrice(cart));
    }

    public boolean isCartPriceValid(Set<Product> cart){
        int totalPrice = getCartPrice(cart);
        return totalPrice >= 150;
    }

    public int getCartPrice(Set<Product> cart){
        int totalPrice = 0;
        for (Product p :cart) {
            totalPrice += p.getPrice();
        }
        return totalPrice;
    }

    public Set<Product> getSessionCart(HttpSession session) {
        return (Set<Product>) session.getAttribute("cart");
    }
    public void clearCart(HttpSession session) {
        session.removeAttribute("cart");
    }
}
