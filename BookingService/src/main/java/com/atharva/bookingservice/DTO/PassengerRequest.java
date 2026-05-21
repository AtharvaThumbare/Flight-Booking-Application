package com.atharva.bookingservice.DTO;


import com.atharva.bookingservice.Enums.Gender;

public record PassengerRequest(

        String firstName,

        String lastName,

        Integer age,

        Gender gender



) {


}
