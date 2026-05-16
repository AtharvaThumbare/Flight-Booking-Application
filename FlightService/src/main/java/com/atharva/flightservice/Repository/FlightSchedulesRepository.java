package com.atharva.flightservice.Repository;

import com.atharva.flightservice.DTO.SearchRequest;
import com.atharva.flightservice.Entity.FlightSchedules;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FlightSchedulesRepository extends JpaRepository<FlightSchedules, Long> {



    boolean existsByFlightNumberAndDepartureTime(
            String flightNumber,
            LocalDateTime departureTime
    );

    @Query("""
    SELECT f
    FROM FlightSchedules f
    WHERE
        f.route.source.code = :source
        AND
        f.route.destination.code = :destination
        AND
        f.departure_time >= :start
        AND
        f.departure_time < :end
        AND
        f.available_seats >= :passengers
        AND
        f.status = 'SCHEDULED'
""")
    List<FlightSchedules> searchFlights(
        String source,
        String destination,
        LocalDateTime start,
        LocalDateTime end,
        int passengers
    );





    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
    SELECT f
    FROM FlightSchedules f
    WHERE f.flightUUID = :uuid
""")
    Optional<FlightSchedules> findFlightSchedulesByFlightUUID(
            UUID uuid
    );


}
