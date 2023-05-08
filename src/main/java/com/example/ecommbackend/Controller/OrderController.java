package com.example.ecommbackend.Controller;


import com.example.ecommbackend.Model.Order;
import com.example.ecommbackend.Repository.OrderRepository;
import com.example.ecommbackend.Service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderRepository orderRepository;

    @GetMapping("/orders")
    public Order getOrderById(@RequestParam(name = "orderId", required = false)  Long id){
        return orderService.findOrderById(id);
    }
    @GetMapping("/orders/user")
    public List<Order> getOrderByUserId(@RequestParam(name = "userId", required = false)  Long id){
        return orderService.findOrderByUserId(id);
    }
    @GetMapping("/admin/orders/all")
    public List<Order> getAllOrders(){
        return orderService.findAllOrders();
    }
    @PostMapping("/orders/save")
    public void saveOrder(@RequestBody Order order){
        orderRepository.save(order);
    }
}

