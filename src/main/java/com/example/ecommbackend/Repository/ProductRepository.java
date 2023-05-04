package com.example.ecommbackend.Repository;

import com.example.ecommbackend.Model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findProductById(@Param(("id")) int id);

    List<Product> findByCategory(@Param("categoryName") String categoryName);

    List<Product> findByBrand(@Param("brandName") String brandName);

    @Query("select p from products p order by p.price DESC ")
    List<Product> OrderByPriceDesc();

    @Query("SELECT p FROM products p")
    List<Product> findAllProducts();

    @Query(value = "Select DISTINCT category from products")
    List<String> findCategories();

    @Query("Select DISTINCT brand from products")
    List<String> findBrands();

    @Query("SELECT p FROM products p ORDER BY p.id")
    List<Product> findProductsByPage(Pageable pageable);


}
