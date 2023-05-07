package com.example.ecommbackend.Service;

import com.example.ecommbackend.Model.Order;
import com.example.ecommbackend.Model.Product;
import com.example.ecommbackend.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;
    public Order createOrderForUser(int userId, Set<Product> cart){
        Order order = new Order();
        int orderPrice = 0;
        order.setUserId(userId);

        for (Product product : cart) {
            productService.consumeProduct(product);
            order.getProducts().add(product);
            orderPrice += product.getPrice();
        }

        order.setPrice(orderPrice);
        saveOrder(order);
        return order;
    }
    public Order findOrderById(Long id){
        return orderRepository.findOrderById(id);
    }
    public void saveOrder(Order order){
        orderRepository.save(order);
    }
    public List<Order> findAllOrders(){
        return orderRepository.findAllWithProducts();
    }
    public List<Order> findOrderByUserId(Long id) {
        return orderRepository.findOrdersByUserId(id);
    }
}

