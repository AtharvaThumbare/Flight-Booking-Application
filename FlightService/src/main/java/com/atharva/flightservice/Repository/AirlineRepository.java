package com.atharva.flightservice.Repository;

import com.atharva.flightservice.Entity.Airline;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AirlineRepository extends JpaRepository<Airline, Long> {

        Optional<Airline> findAirlineByAirlineUUId(UUID airlineUUId);
}
