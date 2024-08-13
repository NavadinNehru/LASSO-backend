package com.example.backend.controller;


import com.example.backend.model.Notification;

import com.example.backend.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;
    

    @GetMapping
    public List<Notification> getNotifications() {
        return notificationService.getAllNotifications(); // Implement this method in NotificationService
    }

   
}
