package com.example.ecommbackend.Service;

import com.example.ecommbackend.Model.User;
import com.example.ecommbackend.Model.Wishlist;
import com.example.ecommbackend.Model.Product;
import com.example.ecommbackend.Repository.ProductRepository;
import com.example.ecommbackend.Repository.UserRepository;
import com.example.ecommbackend.Repository.WishlistRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class WishlistService {

    private final WishlistRepository wishlistRepository;
    private final ProductRepository productRepository;
    private final JwtService jwtService;

    public Wishlist getUserWishlist(HttpServletRequest request){
        User user = jwtService.getUser(request);
        Wishlist wishlist =
                wishlistRepository.findByUserId(user.getId()).orElse(new Wishlist(user));
        return wishlist;
    }

    public ResponseEntity<?> addProductToWishlist(HttpServletRequest request, int productId) {
        Product product = productRepository.findById((long) productId).orElseThrow();
        Wishlist wishlist = getUserWishlist(request);
        if (wishlist.reachedMaxNumber()){
            return ResponseEntity.ok("Wishlist Has Reached its max");
        }
        wishlist.getProducts().add(product);
        wishlistRepository.save(wishlist);
        return ResponseEntity.ok(wishlist);
    }

    public ResponseEntity<?> deleteProductFromWishlist(HttpServletRequest request, int productId) {
        Product product = productRepository.findById((long) productId).orElseThrow();
        Wishlist wishlist = getUserWishlist(request);
        if (wishlist.reachedMinNumber()){
            return ResponseEntity.ok("Wishlist Has Reached its min");
        }
        wishlist.getProducts().remove(product);
        wishlistRepository.save(wishlist);
        return ResponseEntity.ok(wishlist);
    }


}
