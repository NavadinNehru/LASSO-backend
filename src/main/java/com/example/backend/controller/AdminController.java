package com.example.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.model.Client;
import com.example.backend.model.TransportProvider;
import com.example.backend.service.ClientService;
import com.example.backend.service.TransportProviderService;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private TransportProviderService providerService;

  
      
    @PostMapping("/createclients")
    public Client createClient(@RequestBody Client client) {
        return clientService.saveClient(client);
    }

    @GetMapping("/getallclients")
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/get/clients/{id}")
    public Client getClientById(@PathVariable Long id) {
        return clientService.getClientById(id);
    }

    @PutMapping("update/clients/{id}")
    public Client updateClient(@PathVariable Long id, @RequestBody Client clientDetails) {
        Client client = clientService.getClientById(id);
        if (client == null) {
            return null; 
        }
        client.setUsername(clientDetails.getUsername());
        client.setEmail(clientDetails.getEmail());
        return clientService.saveClient(client);
    }

    @DeleteMapping("delete/clients/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
    }

 

    @PostMapping("/createproviders")
    public TransportProvider createProvider(@RequestBody TransportProvider provider) {
        return providerService.saveProvider(provider);
    }

    @GetMapping("/get/allproviders")
    public List<TransportProvider> getAllProviders() {
        return providerService.getAllProviders();
    }

    @GetMapping("/get/providers/{id}")
    public TransportProvider getProviderById(@PathVariable Long id) {
        return providerService.getProviderById(id);
    }

    @PutMapping("/update/providers/{id}")
    public TransportProvider updateProvider(@PathVariable Long id, @RequestBody TransportProvider providerDetails) {
        TransportProvider provider = providerService.getProviderById(id);
        if (provider == null) {
            return null; 
        }
        provider.setLogisticsName(providerDetails.getLogisticsName());
        provider.setEmail(providerDetails.getEmail());
        provider.setPassword(providerDetails.getPassword());
        return providerService.saveProvider(provider);
    }

    @DeleteMapping("/delete/providers/{id}")
    public void deleteProvider(@PathVariable Long id) {
        providerService.deleteProvider(id);
    }
}
