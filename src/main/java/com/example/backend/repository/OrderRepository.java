package com.example.backend.repository;

import com.example.backend.model.Order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT COUNT(o) FROM Order o WHERE o.client.id = :clientId")
    long countOrdersByClientId(@Param("clientId") Long clientId);
    @Query("SELECT o FROM Order o WHERE o.client.id = :clientId")
    List<Order> findOrdersByClientId(@Param("clientId") Long clientId);

    Long countByTransportProviderId(Long providerId);

    
    List<Order> findByTransportProviderId(Long providerId);
    
}

