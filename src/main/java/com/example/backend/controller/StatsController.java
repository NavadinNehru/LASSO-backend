package com.example.backend.controller;


import com.example.backend.repository.ClientRepository;
import com.example.backend.repository.OrderRepository;
import com.example.backend.repository.TransportProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class StatsController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private TransportProviderRepository transportProviderRepository;

    @Autowired
    private OrderRepository orderRepository;

    @CrossOrigin(origins = "http://localhost:5001")
    @GetMapping("/api/stats")
    public Map<String, Long> getStats() {
        Map<String, Long> stats = new HashMap<>();
        stats.put("totalClients", clientRepository.count());
        stats.put("totalTransportProviders", transportProviderRepository.count());
        stats.put("totalOrders", orderRepository.count());
        return stats;
    }
}

