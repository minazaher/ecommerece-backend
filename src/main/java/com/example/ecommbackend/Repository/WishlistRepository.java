package com.example.ecommbackend.Repository;

import com.example.ecommbackend.Model.User;
import com.example.ecommbackend.Model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {
    @Query("select w from Wishlist w where w.user =:userid")
    Optional<Wishlist> findByUser(int userid);

}