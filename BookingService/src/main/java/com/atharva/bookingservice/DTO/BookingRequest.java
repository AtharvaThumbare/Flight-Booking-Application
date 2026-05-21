package com.atharva.bookingservice.DTO;




import java.util.List;


public record BookingRequest(




        List<PassengerRequest> passengers




) {
}
