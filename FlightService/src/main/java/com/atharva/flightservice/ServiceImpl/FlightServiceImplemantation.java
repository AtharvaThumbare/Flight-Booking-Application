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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

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
                .findAirlineByAirlineUUId(createFlightRequest.airlineUUId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Airline not found"
                        )
                );

        Aircraft aircraft = aircraftRepository
                .findAircraftByAircraftUUId(createFlightRequest.aircraftUUId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Aircraft not found"
                        )
                );

        Route route = routeRepository
                .findRouteByRouteUUId(createFlightRequest.routeUUId())
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
                       .departureTime(createFlightRequest.departureTime())
                       .arrivalTime(createFlightRequest.arrivalTime())
                       .availableSeats(createFlightRequest.availableSeats())
                       .basePrice(createFlightRequest.basePrice())
                       .status(FlightSchedules.FlightStatus.SCHEDULED)
                       .build();





    }

    @Override
    public FlightResponse getFlightById(UUID uuid) {
        FlightSchedules flightSchedules = flightSchedulesRepository.findFlightSchedulesByFlightUUID(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("Flight not found with id: " + uuid));

        return new FlightResponse(
                flightSchedules.getFlight_number(),
                flightSchedules.getAirline().getName(),
                flightSchedules.getRoute().getSource().getName(),
                flightSchedules.getRoute().getDestination().getName(),
                flightSchedules.getDepartureTime().toString(),
                flightSchedules.getArrivalTime().toString(),
                flightSchedules.getAvailableSeats(),
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
                        flightSchedules.getDepartureTime(),
                        flightSchedules.getArrivalTime(),
                        flightSchedules.getBasePrice().toString()
                ))
                .toList();
        return flightSearchResponseList;

    }

    @Override
    public void updateFlightStatus(UUID uuid, UpdateFlightStatusRequest updateFlightStatusRequest) {
        FlightSchedules flightSchedules = flightSchedulesRepository.findFlightSchedulesByFlightUUID(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("Flight not found with id: " + uuid));

        flightSchedules.setStatus(updateFlightStatusRequest.status());
        flightSchedulesRepository.save(flightSchedules);


    }

    @Override
    public void updateFlightPrice(UUID uuid, UpdateFlightPrice updateFlightPrice) {
        FlightSchedules flightSchedules = flightSchedulesRepository.findFlightSchedulesByFlightUUID(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("Flight not found with id: " + uuid));

        flightSchedules.setBasePrice(updateFlightPrice.basePrice());
        flightSchedulesRepository.save(flightSchedules);
    }

    @Override
    public void updateAircraft(UUID uuid, UpdateAircraft updateAircraftRequest) {
        FlightSchedules flightSchedules = flightSchedulesRepository.findFlightSchedulesByFlightUUID(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("Flight not found with id: " + uuid));

        Aircraft aircraft = aircraftRepository
                .findById(updateAircraftRequest.aircraft_id())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Aircraft not found"
                        )
                );

        flightSchedules.setAircraft(aircraft);
        flightSchedulesRepository.save(flightSchedules);
    }

    @Override
    public void updateFlightSchedule(UUID uuid, UpdateFlightSchedule updateFlightSchedule) {
        FlightSchedules flightSchedules = flightSchedulesRepository.findFlightSchedulesByFlightUUID(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("Flight not found with id: " + uuid));

        flightSchedules.setDepartureTime(updateFlightSchedule.departure_time());
        flightSchedules.setArrivalTime(updateFlightSchedule.arrival_time());
        flightSchedulesRepository.save(flightSchedules);
    }

    @Override
    public void cancelFlight(UUID uuid) {
        FlightSchedules flightSchedules = flightSchedulesRepository.findFlightSchedulesByFlightUUID(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("Flight not found with id: " + uuid));

        flightSchedules.setStatus(FlightSchedules.FlightStatus.CANCELLED);
        flightSchedulesRepository.save(flightSchedules);
    }

    @Override
    @Transactional
    public void reserveSeats(UUID uuid, ReserveSeatsRequest reservationRequest) {
        FlightSchedules flightSchedules = flightSchedulesRepository.findFlightSchedulesByFlightUUID(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("Flight not found with id: " + uuid));

        if (flightSchedules.getAvailableSeats() < reservationRequest.passengers()) {
            throw new IllegalArgumentException("Not enough available seats for reservation.");
        }

        flightSchedules.setAvailableSeats(flightSchedules.getAvailableSeats() - reservationRequest.passengers());
        flightSchedulesRepository.save(flightSchedules);
    }

    @Override
    public void releaseFlight(UUID uuid) {

    }


}
