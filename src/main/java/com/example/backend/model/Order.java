package com.example.backend.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @JsonBackReference("clientReference")
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "transport_provider_id", nullable = false)
    private TransportProvider transportProvider;

    @Column(nullable = false)
    private String fromCity;

    @Column(nullable = false)
    private String toCity;

    @Column(nullable = false)
    private String status;

    private String additionalNotes;

    @Column(nullable = false)
    private double estimatedCost;
}
