package com.atharva.bookingservice.Service;

import com.atharva.bookingservice.DTO.BookingRequest;

import java.util.UUID;

public interface BookingService {


           void bookFlight(UUID userUUID,UUID flightUUID, BookingRequest bookingRequest);


               void cancelFLight(UUID bookingUUID);
}
