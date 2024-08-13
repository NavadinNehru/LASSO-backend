package com.example.backend.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Column;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransportProvider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String logisticsName;

    private String email;

    private String password;

    @JsonManagedReference
    @OneToMany(mappedBy = "transportProvider")
    private List<CityVehicleDetail> cityVehicleDetails;

    @JsonManagedReference
    @OneToMany(mappedBy = "transportProvider")
    private List<Order> orders;
}
