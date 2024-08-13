package com.example.backend.repository;


import com.example.backend.model.UserNotification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserNotificationRepository extends JpaRepository<UserNotification, Long> {
    List<UserNotification> findByClientId(Long clientId);
}
