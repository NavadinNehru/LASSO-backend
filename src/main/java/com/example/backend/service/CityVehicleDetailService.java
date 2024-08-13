package com.example.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.backend.repository.CityVehicleDetailRepository;

@Service
public class CityVehicleDetailService {

    @Autowired
    private CityVehicleDetailRepository cityVehicleDetailRepository;

    public int getTotalVehiclesByProviderId(Long providerId) {
        return cityVehicleDetailRepository.sumVehiclesByProviderId(providerId);
    }
    public long getCountOfServingCitiesByProviderId(Long providerId) {
        return cityVehicleDetailRepository.countDistinctCitiesByProviderId(providerId);
    }
}
