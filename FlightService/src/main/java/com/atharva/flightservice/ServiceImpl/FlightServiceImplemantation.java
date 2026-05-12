package com.atharva.flightservice.ServiceImpl;

import com.atharva.flightservice.DTO.*;
import com.atharva.flightservice.Entity.Aircraft;
import com.atharva.flightservice.Entity.Airline;
import com.atharva.flightservice.Entity.FlightSchedules;
import com.atharva.flightservice.Entity.Route;
import com.atharva.flightservice.Exceptions.DuplicateFlightException;
import com.atharva.flightservice.Exceptions.ResourceNotFoundException;
import com.atharva.flightservice.Repository.AircraftRepository;
import com.atharva.flightservice.Repository.AirlineRepository;
import com.atharva.flightservice.Repository.FlightSchedulesRepository;
import com.atharva.flightservice.Repository.RoutesRepository;
import com.atharva.flightservice.Service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightServiceImplemantation implements FlightService {

    private final FlightSchedulesRepository flightSchedulesRepository;
    private final AirlineRepository airlineRepository;
    private final RoutesRepository routeRepository;
    private final AircraftRepository aircraftRepository;

    @Override
    public void createFlightData(CreateFlightRequest createFlightRequest) {
               if(flightSchedulesRepository.existsByFlightNumberAndDepartureTime(
                       createFlightRequest.flightNumber(),
                       createFlightRequest.departureTime())) {
                   throw new DuplicateFlightException("Flight with the same number and departure time already exists.");
               }

        Airline airline = airlineRepository
                .findById(createFlightRequest.airlineId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Airline not found"
                        )
                );

        Aircraft aircraft = aircraftRepository
                .findById(createFlightRequest.aircraftId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Aircraft not found"
                        )
                );

        Route route = routeRepository
                .findById(createFlightRequest.routeId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Route not found"
                        )
                );

               FlightSchedules flightSchedules = FlightSchedules.builder()
                       .airline(airline)
                       .aircraft(aircraft)
                       .route(route)
                       .flight_number(createFlightRequest.flightNumber())
                       .departure_time(createFlightRequest.departureTime())
                       .arrival_time(createFlightRequest.arrivalTime())
                       .available_seats(createFlightRequest.availableSeats())
                       .basePrice(createFlightRequest.basePrice())
                       .status(FlightSchedules.FlightStatus.SCHEDULED)
                       .build();





    }

    @Override
    public FlightResponse getFlightById(long id) {
        FlightSchedules flightSchedules = flightSchedulesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Flight not found with id: " + id));

        return new FlightResponse(
                flightSchedules.getFlight_number(),
                flightSchedules.getAirline().getName(),
                flightSchedules.getRoute().getSource().getName(),
                flightSchedules.getRoute().getDestination().getName(),
                flightSchedules.getDeparture_time().toString(),
                flightSchedules.getArrival_time().toString(),
                flightSchedules.getAvailable_seats(),
                flightSchedules.getBasePrice().toString()
        );
    }

    @Override
    public List<FlightSearchResponse> searchFlights(SearchRequest searchRequest) {

        LocalDateTime start =
                searchRequest.date().atStartOfDay();

        LocalDateTime end =
                searchRequest.date()
                        .plusDays(1)
                        .atStartOfDay();

        List<FlightSearchResponse> flightSearchResponseList = flightSchedulesRepository.searchFlights(
                        searchRequest.source(),

                        searchRequest.destination(),

                        start,

                        end,

                        searchRequest.passengers()




                )
                .stream()
                .map(flightSchedules -> new FlightSearchResponse(

                        flightSchedules.getId(),
                        flightSchedules.getFlight_number(),
                        flightSchedules.getAirline().getName(),
                        flightSchedules.getDeparture_time(),
                        flightSchedules.getArrival_time(),
                        flightSchedules.getBasePrice().toString()
                ))
                .toList();
        return flightSearchResponseList;

    }

    @Override
    public void updateFlightStatus(long id, UpdateFlightStatusRequest updateFlightStatusRequest) {
        FlightSchedules flightSchedules = flightSchedulesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Flight not found with id: " + id));

        flightSchedules.setStatus(updateFlightStatusRequest.status());
        flightSchedulesRepository.save(flightSchedules);


    }

}
