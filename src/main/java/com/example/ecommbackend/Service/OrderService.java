package com.example.ecommbackend.Service;

import com.example.ecommbackend.Model.Order;
import com.example.ecommbackend.Model.Product;
import com.example.ecommbackend.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public void createOrderForUser(int userId, Set<Product> cart, int price){
                Order order = Order.builder()
                .userId(userId)
                .price(price)
                .products(cart)
                .build();
        saveOrder(order);
    }
    public Order findOrderById(int id){
        return orderRepository.findOrderById(id);
    }
    public void saveOrder(Order order){
            orderRepository.save(order);
    }
    public List<Order> findAllOrders(){
        return orderRepository.findAllWithProducts();
    }
    public List<Order> findOrdersByUserId(int id) {
        return orderRepository.findOrdersByUserId(id);
    }
}

