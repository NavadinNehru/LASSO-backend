package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.backend.model.CityVehicleDetail;

@Repository
public interface CityVehicleDetailRepository extends JpaRepository<CityVehicleDetail, Long> {
    @Query("SELECT SUM(cvd.small + cvd.medium + cvd.large) FROM CityVehicleDetail cvd WHERE cvd.transportProvider.id = :providerId")
    int sumVehiclesByProviderId(@Param("providerId") Long providerId);

    @Query("SELECT COUNT(DISTINCT cvd.city) FROM CityVehicleDetail cvd WHERE cvd.transportProvider.id = :providerId")
    long countDistinctCitiesByProviderId(@Param("providerId") Long providerId);
}

