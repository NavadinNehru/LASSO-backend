package com.example.backend.controller;

// import com.sample.dto.AuthRequest;
// import com.sample.dto.Product;
// import com.sample.entity.Products;
// import com.sample.entity.UserInfo;
// import com.sample.service.JwtService;
// import com.sample.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.backend.dto.AuthRequest;
import com.example.backend.dto.AuthResponse;
import com.example.backend.model.UserInfo;
import com.example.backend.service.JwtService;
import com.example.backend.service.NotificationService;
import com.example.backend.service.UserInfoService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserInfoController {

   
    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private NotificationService notificationService;
    

    

    @CrossOrigin(origins = "http://localhost:5001")
    @PostMapping("/newuser")
    public String addNewUser(@RequestBody UserInfo userInfo) {
        // Save the new user
        userInfoService.addUser(userInfo);

        // Send notification to admin
        notificationService.sendNotification("New user registered: " + userInfo.getName(), "Admin");

        return "User added successfully";
    }

    @CrossOrigin(origins = "http://localhost:5001")
    @PostMapping("/authenticate")
    public AuthResponse authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
            System.out.println("Testing");
        if (authentication.isAuthenticated()) {
           AuthResponse authResponse = new AuthResponse();
            authResponse.setUsername(authRequest.getUsername());
            String role=userInfoService.findRole(authRequest.getUsername());
            authResponse.setRole(role);
            authResponse.setToken(jwtService.generateToken(authRequest.getUsername()));
            //authResponse.setToken(jwtService.generateToken(authRequest.getUsername()));
            //authResponse.setRole("Organizer");
            return authResponse;
        } else {
            System.out.println("Testing3");
            throw new UsernameNotFoundException("invalid user request !");
        }
    }
    
    @CrossOrigin(origins = "http://localhost:5001")
    @GetMapping("/validate")
    public ResponseEntity<Map<String, String>> validateToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7); // Remove "Bearer " prefix
        if (jwtService.validateToken(token, null)) {
            String username = jwtService.extractUsername(token);
            String role = userInfoService.findRole(username);
            Map<String, String> response = new HashMap<>();
            response.put("role", role);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    

    @CrossOrigin(origins = "http://localhost:5001")
    @GetMapping("/allusers")
    public List<UserInfo> getalluUserInfos() {
        return userInfoService.getalluUserInfos();
    }

}

