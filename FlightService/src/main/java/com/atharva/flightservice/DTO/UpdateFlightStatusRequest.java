package com.atharva.flightservice.DTO;

import com.atharva.flightservice.Entity.FlightSchedules;

public record UpdateFlightStatusRequest(


        FlightSchedules.FlightStatus status
) {
}
