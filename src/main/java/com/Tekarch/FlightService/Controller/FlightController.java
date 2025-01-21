package com.Tekarch.FlightService.Controller;

import com.Tekarch.FlightService.DTO.FlightDto;
import com.Tekarch.FlightService.Service.FlightService;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightController {
    private static final Logger logger = LogManager.getLogger(FlightController.class);
    @Autowired
    private FlightService flightService;

    @GetMapping
    public ResponseEntity<List<FlightDto>> getAllFlights() {
        List<FlightDto> flights = flightService.getAllFlights();
        return ResponseEntity.ok(flights);
    }

    @GetMapping("/{flightId}")
    public ResponseEntity<FlightDto> getFlightById(@PathVariable Long flightId) {
        FlightDto flight = flightService.getFlightById(flightId);
        return ResponseEntity.ok(flight);
    }

    @PostMapping
    public ResponseEntity<FlightDto> createFlight(@Valid @RequestBody FlightDto flightDto) {
        FlightDto createdFlight = flightService.createFlight(flightDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFlight);
    }

    @PutMapping("/{flightId}")
    public ResponseEntity<FlightDto> updateFlight(@PathVariable Long flightId, @RequestBody FlightDto flightDto) {
        FlightDto updatedFlight = flightService.updateFlight(flightId, flightDto);
        return ResponseEntity.ok(updatedFlight);
    }

    @DeleteMapping("/{flightId}")
    public ResponseEntity<?> deleteFlight(@PathVariable Long flightId) {
        if(flightService.getFlightById(flightId) != null) {
            flightService.deleteFlight(flightId);
            return ResponseEntity.ok("FlightId " + flightId + " deleted successfully.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("FlightId " + flightId + " not found.");
    }

    @ExceptionHandler
    public ResponseEntity<String> responseWithError(Exception e) {
        logger.error("Exception Occured.Details : {}", e.getMessage());
        return new ResponseEntity<>("Exception Occured.More Info :"
                + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
