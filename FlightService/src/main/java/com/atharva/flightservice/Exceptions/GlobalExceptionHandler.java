package com.atharva.flightservice.Exceptions;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateFlightException.class)
    public ResponseEntity<?> handleException(DuplicateFlightException exception) {

        System.err.println("An error occurred: " + exception.getMessage());
        // Return a generic error response
        return ResponseEntity.status(409 ).body(exception.getMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleException(ResourceNotFoundException exception) {

        return ResponseEntity.status(404).body(exception.getMessage());

    }

    @ExceptionHandler(NotEnoughSeatsException.class)
    public ResponseEntity<?> handleException(NotEnoughSeatsException exception) {

        return ResponseEntity.status(409).body(exception.getMessage());
    }

}
