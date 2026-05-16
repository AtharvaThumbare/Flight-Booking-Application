package com.atharva.bookingservice.DTO;

public record PassengerRequest(

        String firstName,

        String lastName,

        Integer age,

        String gender

) {
}
