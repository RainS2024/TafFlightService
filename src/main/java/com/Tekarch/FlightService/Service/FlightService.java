package com.Tekarch.FlightService.Service;

import com.Tekarch.FlightService.DTO.FlightDto;
import com.Tekarch.FlightService.Service.Interface.FlightInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FlightService implements FlightInterface {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${db.service.url}")
    private String dataStoreServiceUrl;


    @Override
    // Get All Flights
    public List<FlightDto> getAllFlights() {
        try {
            String url = dataStoreServiceUrl + "/flights";
            ResponseEntity<List<FlightDto>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<FlightDto>>() {}
            );
            return response.getBody();
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch all flights: " + e.getMessage(), e);
        }
    }
@Override
    // Get Flight By ID
    public FlightDto getFlightById(Long flightId) {
        try {
            String url = dataStoreServiceUrl + "/flights/" + flightId;
            return restTemplate.getForObject(url, FlightDto.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch flight with ID " + flightId + ": " + e.getMessage(), e);
        }
    }
@Override
    // Create Flight
    public FlightDto createFlight(FlightDto flightDto) {
        try {
            String url = dataStoreServiceUrl + "/flights";
            return restTemplate.postForObject(url, flightDto, FlightDto.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create flight: " + e.getMessage(), e);
        }
    }
@Override
    // Update Flight
    public FlightDto updateFlight(Long flightId, FlightDto flightDto) {
        try {
            String url = dataStoreServiceUrl + "/flights/" + flightId;
            restTemplate.put(url, flightDto);
            return getFlightById(flightId);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update flight with ID " + flightId + ": " + e.getMessage(), e);
        }
    }
@Override
    // Delete Flight
    public String deleteFlight(Long flightId) {
        try {
            String url = dataStoreServiceUrl + "/flights/" + flightId;
            restTemplate.delete(url);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete flight with ID " + flightId + ": " + e.getMessage(), e);
        }
    String response =("Flight Service " + flightId + " is successfully deleted");
    return response;
    }
}
