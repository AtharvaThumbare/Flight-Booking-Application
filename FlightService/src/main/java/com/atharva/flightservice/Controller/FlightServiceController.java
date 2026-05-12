package com.atharva.flightservice.Controller;


import com.atharva.flightservice.DTO.CreateFlightRequest;
import com.atharva.flightservice.DTO.SearchRequest;
import com.atharva.flightservice.DTO.UpdateFlightStatusRequest;
import com.atharva.flightservice.Service.FlightService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/flights")

public class FlightServiceController {

    private FlightService flightService;

      @PostMapping
      public ResponseEntity<?> createFlight(@RequestBody @Valid CreateFlightRequest flightRequest) {
            flightService.createFlightData(flightRequest);
            return new  ResponseEntity<>(HttpStatus.CREATED);
      }

      @GetMapping("/{id}")
       public ResponseEntity<?> getFlightById(@PathVariable long id) {

              return new ResponseEntity<>(flightService.getFlightById(id), HttpStatus.OK);

      }

      @GetMapping
       public ResponseEntity<?> searchFlights(@RequestBody @Valid SearchRequest searchRequest) {

              return new ResponseEntity<>(flightService.searchFlights(searchRequest), HttpStatus.OK);


      }

      @PatchMapping("/{id}/status")
    public ResponseEntity<?> updateFlightStatus(@RequestBody @Valid UpdateFlightStatusRequest updateFlightStatusRequest, @PathVariable long id)
       {

              flightService.updateFlightStatus(id,updateFlightStatusRequest);
              return new ResponseEntity<>(HttpStatus.OK);

      }

      @PatchMapping("{id}/price")
      public ResponseEntity<>




}






