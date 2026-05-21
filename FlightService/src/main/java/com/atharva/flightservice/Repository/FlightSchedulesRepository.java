package com.atharva.flightservice.Repository;


import com.atharva.flightservice.Entity.FlightSchedules;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
        f.departureTime >= :start
        AND
        f.departureTime < :end
        AND
        f.availableSeats >= :passengers
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

    Optional<FlightSchedules> findFlightSchedulesByFlightUUID(
            UUID uuid
    );





    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
    SELECT f
    FROM FlightSchedules f
    WHERE f.flightUUID = :uuid
""")
    Optional<FlightSchedules> findFlightSchedulesByFlightUUIDForUpdate(
            UUID uuid
    );

    @Query(value = """
        SELECT MAX(CAST(SUBSTRING(flightNumber FROM LENGTH(:airlineCode) + 1) AS INTEGER))
        FROM flight_schedules
        WHERE flightNumber LIKE CONCAT(:airlineCode, '%')
        """, nativeQuery = true)
    Integer findHighestFlightNumberForAirline(@Param("airlineCode") String airlineCode);



}
