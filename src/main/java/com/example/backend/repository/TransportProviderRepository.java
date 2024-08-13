package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.backend.model.TransportProvider;

@Repository
public interface TransportProviderRepository extends JpaRepository<TransportProvider, Long> {

    TransportProvider findByLogisticsName(String logisticsName);
    TransportProvider findByEmail(String email);
}
