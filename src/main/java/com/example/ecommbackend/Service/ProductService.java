package com.example.ecommbackend.Service;

import com.example.ecommbackend.Model.Product;
import com.example.ecommbackend.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAllProducts();
    }

    public List<Product> getProductsPage(int page) {
        return productRepository.findProductsByPage(PageRequest.of(page-1, 9));
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

    public void consumeCartProducts(Set<Product> cart) {
        for (Product product : cart) {
            product.consume();
            productRepository.save(product);
        }
    }

    public Product getProductsById(int productId) {
        return productRepository.findProductById(productId);
    }
}
