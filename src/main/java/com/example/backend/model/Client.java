package com.example.backend.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    
    @Column(unique = true)
    private String username;

    private String email;

    private String password;

    //private String companyName;

    
    @JsonManagedReference("clientReference")
    @OneToMany(mappedBy = "client")
    private List<Order> orders;

    @JsonManagedReference("feedbackReference")
    @OneToMany(mappedBy = "client")
    private List<Feedback> feedback;
}