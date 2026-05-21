package com.atharva.bookingservice.DTO;

import jakarta.validation.constraints.Positive;

public record ReserveSeatsRequest(

        @Positive
        Integer noOfPassengers



) {
    public ReserveSeatsRequest(Integer noOfPassengers) {
        this.noOfPassengers = noOfPassengers;
    }
}
