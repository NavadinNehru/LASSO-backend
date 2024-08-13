package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.backend.model.Client;
import java.util.List;
import java.util.Optional;


@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByEmail(String email);
    Optional<Client> findById(Long id);

    Client findByUsername(String username);
    
}
