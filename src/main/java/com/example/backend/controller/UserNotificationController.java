package com.example.backend.controller;



import com.example.backend.model.UserNotification;
import com.example.backend.service.UserNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class UserNotificationController {

    @Autowired
    private UserNotificationService notificationService;

    @CrossOrigin(origins = "http://localhost:5001")
    @GetMapping("/byclient")
    public List<UserNotification> getNotificationsByClientId(@RequestParam Long clientId) {
        return notificationService.getNotificationsByClientId(clientId);
    }

    @CrossOrigin(origins = "http://localhost:5001")
    @PostMapping
    public UserNotification createNotification(@RequestBody UserNotification notification) {
        return notificationService.createNotification(notification);
    }
}

