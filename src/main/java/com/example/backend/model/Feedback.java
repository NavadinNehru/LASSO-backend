package com.example.backend.model;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String comments;
    private int rating;

    @JsonBackReference("feedbackReference")
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;
}
