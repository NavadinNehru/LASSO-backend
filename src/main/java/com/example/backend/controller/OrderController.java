package com.example.backend.controller;

import com.example.backend.model.Order;
import com.example.backend.repository.OrderRepository;
import com.example.backend.service.NotificationService;
import com.example.backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
//@PreAuthorize("hasAuthority('ROLE_CLIENT')")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private NotificationService notificationService;


    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.findAll();
    }
    @CrossOrigin(origins = "http://localhost:5001")
    @GetMapping("/recent-orders")
    public List<Order> getRecentOrders() {
        // Fetch the 5 most recent orders sorted by the ID in descending order
        return orderRepository.findAll(PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "id"))).getContent();
    }

    @GetMapping("get/{id}")
    public Optional<Order> getOrderById(@PathVariable Long id) {
        return orderService.findById(id);
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.save(order);
    }

    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable Long id, @RequestBody Order order) {
        order.setId(id);
        return orderService.save(order);
    }

    @CrossOrigin(origins = "http://localhost:5001")
    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteById(id);
    }

    @CrossOrigin(origins = "http://localhost:5001")
    @GetMapping("/countbyprovider")
    public Long getOrderCountByProviderId(@RequestParam Long providerId) {
        return orderService.getOrderCountByProviderId(providerId);
    }

    @CrossOrigin(origins = "http://localhost:5001")
    @PutMapping("/updateStatus")
    public ResponseEntity<String> updateOrderStatus(@RequestParam Long orderId, @RequestParam String status) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);

        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            order.setStatus(status);
            orderRepository.save(order);

            
            return ResponseEntity.ok("Order status updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found");
        }
    }
@CrossOrigin(origins = "http://localhost:5001")
@GetMapping("/byprovider")
    public ResponseEntity<List<Order>> getOrdersByProvider(@RequestParam Long providerId) {
        List<Order> orders = orderRepository.findByTransportProviderId(providerId);
        return ResponseEntity.ok(orders);
    }

}
