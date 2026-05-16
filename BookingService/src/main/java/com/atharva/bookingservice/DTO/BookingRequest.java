package com.atharva.bookingservice.DTO;

import java.util.List;
import java.util.UUID;

public record BookingRequest(

        UUID flightUUID,

        List<PassengerRequest> passengers




) {
}
