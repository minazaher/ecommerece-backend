package com.example.ecommbackend.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToMany(fetch =  FetchType.LAZY)
    @JoinTable(
            name = "wishlist_product",
            joinColumns =@JoinColumn(name = "wishlist_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Product> products = new HashSet<>();

    public Wishlist(User user) {
        this.user = user;
        this.products = new HashSet<>();
    }

    public boolean reachedMaxNumber(){
        int count = this.getProducts().size();
        return count > 10 ;
    }

    public boolean reachedMinNumber(){
        int count = this.getProducts().size();
        return count < 1 ;
    }

}
