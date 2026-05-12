package com.atharva.flightservice.DTO;

public record FlightResponse(

        String flightNumber,
        String airlineName,
        String sourceAirport,
        String destinationAirport,
        String departureTime,
        String arrivalTime,
        Integer availableSeats,
        String basePrice


) {
}
