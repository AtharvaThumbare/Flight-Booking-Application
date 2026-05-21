package com.atharva.flightservice.ServiceImpl;

import com.atharva.flightservice.DTO.*;
import com.atharva.flightservice.Entity.Aircraft;
import com.atharva.flightservice.Entity.Airline;
import com.atharva.flightservice.Entity.FlightSchedules;
import com.atharva.flightservice.Entity.Route;
import com.atharva.flightservice.Exceptions.DuplicateFlightException;
import com.atharva.flightservice.Exceptions.NotEnoughSeatsException;
import com.atharva.flightservice.Exceptions.ResourceNotFoundException;
import com.atharva.flightservice.Repository.AircraftRepository;
import com.atharva.flightservice.Repository.AirlineRepository;
import com.atharva.flightservice.Repository.FlightSchedulesRepository;
import com.atharva.flightservice.Repository.RoutesRepository;
import com.atharva.flightservice.Service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FlightServiceImplementation implements FlightService {

    private final FlightSchedulesRepository flightSchedulesRepository;
    private final AirlineRepository airlineRepository;
    private final RoutesRepository routeRepository;
    private final AircraftRepository aircraftRepository;

    @Override
    public void createFlightData(CreateFlightRequest createFlightRequest) {
        Airline airline = airlineRepository
                .findAirlineByAirlineUUId(createFlightRequest.airlineUUId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Airline not found"
                        )
                );


           Integer highestNumber= flightSchedulesRepository.findHighestFlightNumberForAirline(airline.getCode());

             int number=(highestNumber!=null)?highestNumber+1:100;

             String generatedFightNumber=airline.getCode()+number;



               if(flightSchedulesRepository.existsByFlightNumberAndDepartureTime(
                       generatedFightNumber,
                       createFlightRequest.departureTime())) {
                   throw new DuplicateFlightException("Flight with the same number and departure time already exists.");
               }



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
                       .flightNumber(generatedFightNumber)
                       .departureTime(createFlightRequest.departureTime())
                       .arrivalTime(createFlightRequest.arrivalTime())
                       .availableSeats(aircraft.getTotalSeats())
                       .basePrice(createFlightRequest.basePrice())
                       .status(FlightSchedules.FlightStatus.SCHEDULED)
                       .build();

         flightSchedulesRepository.save(flightSchedules);

    }

    @Override
    public FlightResponse getFlightById(UUID uuid) {


        FlightSchedules flightSchedules = flightSchedulesRepository.findFlightSchedulesByFlightUUID(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("Flight not found with id: " + uuid));

        return new FlightResponse(
                flightSchedules.getFlightNumber(),
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

        return flightSchedulesRepository.searchFlights(
                        searchRequest.source(),

                        searchRequest.destination(),

                        start,

                        end,

                        searchRequest.passengers()




                )
                .stream()
                .map(flightSchedules -> new FlightSearchResponse(

                        flightSchedules.getFlightUUID(),
                        flightSchedules.getFlightNumber(),
                        flightSchedules.getAirline().getName(),
                        flightSchedules.getDepartureTime(),
                        flightSchedules.getArrivalTime(),
                        flightSchedules.getBasePrice().toString()
                ))
                .toList();

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
    public ReserveSeatsResponse reserveSeats(UUID uuid, ReserveSeatsRequest reservationRequest) {
        FlightSchedules flightSchedules = flightSchedulesRepository.findFlightSchedulesByFlightUUIDForUpdate(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("Flight not found with id: " + uuid));

        if (flightSchedules.getAvailableSeats() < reservationRequest.passengers()) {
            throw new NotEnoughSeatsException("Not enough available seats for reservation.");
        }



        flightSchedules.setAvailableSeats(flightSchedules.getAvailableSeats() - reservationRequest.passengers());
        flightSchedulesRepository.save(flightSchedules);

        return new ReserveSeatsResponse(flightSchedules.getBasePrice().multiply(BigDecimal.valueOf(reservationRequest.passengers())));
    }

    @Override
    @Transactional
    public void releaseFlight(UUID flightUUID,ReserveSeatsRequest reservationRequest) {
        FlightSchedules flightSchedules = flightSchedulesRepository.findFlightSchedulesByFlightUUIDForUpdate(flightUUID)
                .orElseThrow(() -> new ResourceNotFoundException("Flight not found with id: " + flightUUID));

        flightSchedules.setAvailableSeats(flightSchedules.getAvailableSeats() - reservationRequest.passengers());
        flightSchedulesRepository.save(flightSchedules);
    }


}
