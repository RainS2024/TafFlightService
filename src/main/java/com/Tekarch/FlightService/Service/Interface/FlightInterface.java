package com.Tekarch.FlightService.Service.Interface;

import com.Tekarch.FlightService.DTO.FlightDto;

import java.time.LocalDate;
import java.util.List;

public interface FlightInterface {
    List<FlightDto> getAllFlights();
    FlightDto getFlightById(Long flightId);
    FlightDto createFlight(FlightDto flightDto);
    FlightDto updateFlight(Long flightId, FlightDto flightDto);
    String deleteFlight(Long flightId);
   // Page<FlightDto> findFlights(String departure, String arrival, LocalDate departureTime, LocalDate arrivalTime, int page, int size);
}
