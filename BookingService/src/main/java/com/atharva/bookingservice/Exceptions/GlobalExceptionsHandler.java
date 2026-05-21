package com.atharva.bookingservice.Exceptions;


import feign.FeignException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionsHandler {



    @ExceptionHandler(FeignException.class)
    public ResponseEntity<?> handleFeignException(FeignException e){


        return ResponseEntity
                .status(e.status())
                .body(e.contentUTF8());
    }

    @ExceptionHandler(BookingNotFoundException.class)
    public ResponseEntity<?> handleBookingNotFoundException(BookingNotFoundException e){

        return ResponseEntity
                .status(404)
                .body(e.getMessage());

    }



}
