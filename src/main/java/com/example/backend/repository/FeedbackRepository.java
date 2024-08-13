package com.example.backend.repository;

import com.example.backend.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    @Query("SELECT COUNT(f) FROM Feedback f WHERE f.client.id = :clientId")
    long countFeedbacksByClientId(@Param("clientId") Long clientId);
}
