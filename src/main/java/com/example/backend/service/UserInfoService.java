package com.example.backend.service;

// import com.example.dto.Product;
// import com.sample.entity.Products;
// import com.sample.entity.UserInfo;
// import com.sample.repository.ProductRepository;
// import com.sample.repository.UserInfoRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.backend.model.Client;
import com.example.backend.model.TransportProvider;
import com.example.backend.model.UserInfo;
import com.example.backend.repository.ClientRepository;
import com.example.backend.repository.TransportProviderRepository;
import com.example.backend.repository.UserInfoRepository;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.Optional;

@Service
public class UserInfoService {

    // @Autowired
    // private ProductRepository productRepository;

    @Autowired
    private UserInfoRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private TransportProviderRepository providerRepository;

    

   

    public String addUser(UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        repository.save(userInfo);
        if (userInfo.getRoles().equals("ROLE_CLIENT")) {
            Client client = new Client();
            client.setUsername(userInfo.getName());
            client.setEmail(userInfo.getEmail());
            client.setPassword(userInfo.getPassword());
            
            clientRepository.save(client);
        } else if (userInfo.getRoles().equals("ROLE_PROVIDER")) {
            TransportProvider provider = new TransportProvider();
            provider.setLogisticsName(userInfo.getName());
            provider.setEmail(userInfo.getEmail());
            provider.setPassword(userInfo.getPassword());
            providerRepository.save(provider);
        }
        return "user added to system ";
    }

    public List<UserInfo> getalluUserInfos() {
        return repository.findAll();
    }
    public String findRole(String name) {
        Optional<UserInfo> user = repository.findByName(name);
        return user.map(UserInfo::getRoles).orElse("Role not found");
    }
    
}

