package com.example.ecommbackend.Controller;

import com.example.ecommbackend.Model.Product;
import com.example.ecommbackend.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @GetMapping("/products/all")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
    @GetMapping("/products")
    public List<Product> getProductsPage(@RequestParam(name = "page", defaultValue = "1")int page){
        return productService.getProductsPage(page);
    }
    @PostMapping("/admin/addProduct")
    public ResponseEntity<String> addProduct(@RequestBody Product product){
        productService.saveProduct(product);
        return ResponseEntity.ok("Product Added!");
    }

}
