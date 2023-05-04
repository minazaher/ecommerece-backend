package com.example.ecommbackend.Service;

import com.example.ecommbackend.Model.Product;
import com.example.ecommbackend.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAllProducts();
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void consumeProduct(Product product) {
        int currentStock = product.getStock();
        if(currentStock == 0){
            throw new IllegalStateException("Product out of stock");
        }
        product.setStock(product.getStock()-1);
        productRepository.save(product);
    }
    public Product getProductsById(int productId) {
        return productRepository.findProductById(productId);
    }
}
