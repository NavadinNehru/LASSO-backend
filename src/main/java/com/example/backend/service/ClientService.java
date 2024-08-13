package com.example.backend.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.backend.model.Client;
import com.example.backend.model.Order;
import com.example.backend.repository.ClientRepository;
import com.example.backend.repository.FeedbackRepository;
import com.example.backend.repository.OrderRepository;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private FeedbackRepository feedbackRepository;

    public Client saveClient(Client client) {
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        return clientRepository.save(client);
        
    }
    public Client findByUsername(String username) {
        return clientRepository.findByUsername(username);
    }

    public Client updateClient(String username, Client updatedClient) {
        Client client = clientRepository.findByUsername(username);
        if (client != null) {
            client.setEmail(updatedClient.getEmail());
            client.setPassword(updatedClient.getPassword()); // Note: Handle password hashing separately
            //client.setCompanyName(updatedClient.getCompanyName());
            return clientRepository.save(client);
        }
        return null;
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    public Client authenticateClient(String email, String password) {
        Client client = clientRepository.findByEmail(email);
        if (client != null && client.getPassword().equals(password)) {
            return client;
        }
        return null;
    }

    public long getOrderCountByUsername(String username) {
        Client client = clientRepository.findByUsername(username);
        if (client != null) {
            return orderRepository.countOrdersByClientId(client.getId());
        }
        return 0;
    }
    public long getOrdersByUsername(String username) {
        Client client = clientRepository.findByUsername(username);
        if (client != null) {
            return orderRepository.countOrdersByClientId(client.getId());
        }
        return 0;
    }
    public List<Order> getOrdersByUser(String username) {
        Client client = clientRepository.findByUsername(username);
        if (client != null) {
            return orderRepository.findOrdersByClientId(client.getId());
        }
        else {
            return Collections.emptyList();
        }
        
    }

    public long getFeedbackCountByUsername(String username) {
        Client client = clientRepository.findByUsername(username);
        if (client != null) {
            return feedbackRepository.countFeedbacksByClientId(client.getId());
        }
        return 0;
    }
    public long findOrdersByClientId(String username) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findOrdersByClientId'");
    }
}
