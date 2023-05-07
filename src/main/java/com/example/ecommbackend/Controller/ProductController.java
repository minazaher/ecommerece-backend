package com.example.ecommbackend.Controller;

import com.example.ecommbackend.Model.Product;
import com.example.ecommbackend.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class ProductController {

    private final ProductService productService;
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping("/admin/addProduct")
    public ResponseEntity<String> addProduct(@RequestBody Product product){
        productService.saveProduct(product);
        return ResponseEntity.ok("Product Added!");
    }
}
