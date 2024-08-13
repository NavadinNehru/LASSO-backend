package com.example.backend.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.backend.model.TransportProvider;
import com.example.backend.repository.TransportProviderRepository;
import com.example.backend.model.CityVehicleDetail;
import com.example.backend.service.CityVehicleDetailService;
import com.example.backend.service.TransportProviderService;

@RestController
@RequestMapping("/providers")
//@PreAuthorize("hasAuthority('ROLE_PROVIDER')")
public class TransportProviderController {

    @Autowired
    private TransportProviderService providerService;
    @Autowired
    private TransportProviderRepository providerRepository;
    @Autowired
    private CityVehicleDetailService cityVehicleDetailService;

    // @PostMapping
    // public TransportProvider createProvider(@RequestBody TransportProvider provider) {
    //     return providerService.saveProvider(provider);
    // }

    // @GetMapping
    // public List<TransportProvider> getAllProviders() {
    //     return providerService.getAllProviders();
    // }

    @GetMapping("/findbycities")
    public List<String> getProvidersByCities(@RequestParam String fromCity, @RequestParam String toCity) {
        List<TransportProvider> allProviders = providerService.getAllProviders();
        return allProviders.stream()
                .filter(provider -> provider.getCityVehicleDetails().stream()
                        .anyMatch(detail -> detail.getCity().equals(fromCity)) &&
                        provider.getCityVehicleDetails().stream()
                        .anyMatch(detail -> detail.getCity().equals(toCity)))
                .map(TransportProvider::getLogisticsName)
                .collect(Collectors.toList());
    }

    @PostMapping("/cityvehicledetail")
    public CityVehicleDetail createCityVehicleDetail(@RequestBody CityVehicleDetail detail) {
        return providerService.saveCityVehicleDetail(detail);
    }

    @GetMapping("/cityvehicledetails")
    public List<CityVehicleDetail> getCityVehicleDetailsByProvider(@RequestParam Long providerId) {
        return providerService.getCityVehicleDetailsByProvider(providerId);
    }

    @CrossOrigin(origins = "http://localhost:5001")
    @GetMapping("/checkavailability")
public ResponseEntity<List<String>> checkVehicleAvailability(
        @RequestParam String logisticsName,
        @RequestParam String city) {
    List<String> availableVehicleTypes = providerService.checkVehicleAvailability(logisticsName, city);
    if (availableVehicleTypes.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(availableVehicleTypes, HttpStatus.OK);
}

    @CrossOrigin(origins = "http://localhost:5001")
    @GetMapping("/findbyname")
    public TransportProvider getProviderByName(@RequestParam String name) {
        return providerRepository.findByLogisticsName(name);
    }

    @CrossOrigin(origins = "http://localhost:5001")
    @GetMapping("/totalvehicles")
    public ResponseEntity<Integer> getTotalVehiclesByProviderId(@RequestParam Long providerId) {
        int totalVehicles = cityVehicleDetailService.getTotalVehiclesByProviderId(providerId);
        return new ResponseEntity<>(totalVehicles, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:5001")
    @GetMapping("/countofcities")
    public ResponseEntity<Long> getCountOfServingCities(@RequestParam Long providerId) {
        long count = cityVehicleDetailService.getCountOfServingCitiesByProviderId(providerId);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    // @PostMapping("/signin")
    // public ResponseEntity<String> signIn(@RequestBody SignInRequest signInRequest) {
    //     TransportProvider transportProvider = providerService.authenticateProvider(signInRequest.getEmail(), signInRequest.getPassword());
    //     if (transportProvider != null) {
    //         return ResponseEntity.ok("Sign in successful");
    //     } else {
    //         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Sign in failed: Invalid email or password");
    //     }
    // }
}
