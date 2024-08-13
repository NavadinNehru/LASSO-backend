package com.example.backend.service;


import com.example.backend.model.UserNotification;
import com.example.backend.repository.UserNotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserNotificationService {

    @Autowired
    private UserNotificationRepository notificationRepository;

    public List<UserNotification> getNotificationsByClientId(Long clientId) {
        return notificationRepository.findByClientId(clientId);
    }

    public UserNotification createNotification(UserNotification notification) {
        return notificationRepository.save(notification);
    }
}
