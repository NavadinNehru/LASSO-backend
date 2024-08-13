package com.example.backend.service;


import com.example.backend.model.Client;
import com.example.backend.model.Notification;

import com.example.backend.repository.ClientRepository;
import com.example.backend.repository.NotificationRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private ClientRepository clientRepository;

    

    public void sendNotification(String message, String role) {
        Notification notification = new Notification();
        notification.setMessage(message);
        notification.setDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        notification.setRole(role);
        notificationRepository.save(notification);
    }
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }
}

   

    