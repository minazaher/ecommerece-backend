package com.example.ecommbackend.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    private int price;
    private int stock;
    private String brand;
    private String category;
    private String thumbnail;
    public boolean isAvailable(){
        return this.stock >0;
    }

    public void consume() {
        if (this.isAvailable())
            this.stock--;
        else
            throw new RuntimeException("Product is not available");
    }
}
