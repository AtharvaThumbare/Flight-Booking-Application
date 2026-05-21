package com.atharva.flightservice.Controller;


import com.atharva.flightservice.DTO.*;
import com.atharva.flightservice.Service.FlightService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/api/flights")
@RequiredArgsConstructor
public class FlightServiceController {

    private final FlightService flightService;

       @PostMapping
      public ResponseEntity<?> createFlight(@RequestBody @Valid CreateFlightRequest flightRequest) {
            flightService.createFlightData(flightRequest);
            return new  ResponseEntity<>(HttpStatus.CREATED);
      }

       @GetMapping("/{uuid}")
       public ResponseEntity<?> getFlightById( @PathVariable  UUID uuid) {

              return ResponseEntity.ok(flightService.getFlightById(uuid));

      }

       @PostMapping("/search")
       public ResponseEntity<?> searchFlights( @RequestBody @Valid SearchRequest searchRequest) {

            return ResponseEntity.ok(flightService.searchFlights(searchRequest));


      }

      @PatchMapping("/{uuid}/status")
    public ResponseEntity<?> updateFlightStatus(@RequestBody @Valid UpdateFlightStatusRequest updateFlightStatusRequest, @PathVariable UUID uuid)
       {

              flightService.updateFlightStatus(uuid,updateFlightStatusRequest);
              return new ResponseEntity<>(HttpStatus.OK);

      }

      @PatchMapping("{uuid}/price")
      public ResponseEntity<?> updateFlightPrice(@RequestBody @Valid UpdateFlightPrice updateFlightPrice, @PathVariable UUID uuid)
      {
             flightService.updateFlightPrice( uuid,updateFlightPrice);
             return new ResponseEntity<>(HttpStatus.OK);
      }

      @PatchMapping("{uuid}/aircraft")
    public ResponseEntity<?> updateAircraft(@RequestBody @Valid UpdateAircraft updateAircraftRequest, @PathVariable UUID uuid)
      {
               flightService.updateAircraft(uuid,updateAircraftRequest);
             return new ResponseEntity<>(HttpStatus.OK);
      }

      @PatchMapping("/{uuid}/schedule")
      public ResponseEntity<?> updateFlightSchedule(@RequestBody @Valid UpdateFlightSchedule updateFlightSchedule, @PathVariable UUID uuid)
      {
              flightService.updateFlightSchedule(uuid,updateFlightSchedule);
             return new ResponseEntity<>(HttpStatus.OK);
      }

    @PatchMapping("/{uuid}/cancel")
    public ResponseEntity<?> cancelFlight(@PathVariable UUID uuid) {
        flightService.cancelFlight(uuid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{uuid}/reserve")
    public ResponseEntity<?> reserveFlight(@PathVariable UUID uuid,@Valid @RequestBody ReserveSeatsRequest reserveSeatsRequest) {


        return ResponseEntity.ok(
                flightService.reserveSeats(
                        uuid,
                        reserveSeatsRequest
                )
        );

    }

    @PostMapping("/{uuid}/release")
    public ResponseEntity<?> releaseFlight(@PathVariable UUID uuid ,@RequestBody ReserveSeatsRequest releaseSeatsRequest) {
          flightService.releaseFlight(uuid,releaseSeatsRequest);
          return new ResponseEntity<>(HttpStatus.OK);
    }










}






