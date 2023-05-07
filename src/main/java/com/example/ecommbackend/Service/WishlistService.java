package com.example.ecommbackend.Service;

import com.example.ecommbackend.Model.User;
import com.example.ecommbackend.Model.Wishlist;
import com.example.ecommbackend.Model.Product;
import com.example.ecommbackend.Repository.ProductRepository;
import com.example.ecommbackend.Repository.UserRepository;
import com.example.ecommbackend.Repository.WishlistRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class WishlistService {

    private final WishlistRepository wishlistRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final JwtService jwtService;

    public Wishlist getUserWishlist(HttpServletRequest request){
        User user = jwtService.getUser(request);
        System.out.println(user);
        Wishlist wishlist =
                wishlistRepository.findByUserId(user.getId()).orElse(new Wishlist(user));
        return wishlist;
    }

    public Wishlist addProductToWishlist(HttpServletRequest request, int productId) {
        Product product = productRepository.findById((long) productId).orElseThrow();
        Wishlist wishlist = getUserWishlist(request);
        wishlist.getProducts().add(product);
        wishlistRepository.save(wishlist);
        return wishlist;
    }

    public Wishlist deleteProductFromWishlist(HttpServletRequest request, int productId) {
        Product product = productRepository.findById((long) productId).orElseThrow();
        Wishlist wishlist = getUserWishlist(request);
        wishlist.getProducts().remove(product);
        wishlistRepository.save(wishlist);
        return wishlist;
    }

}
