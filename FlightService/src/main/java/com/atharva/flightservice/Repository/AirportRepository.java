package com.atharva.flightservice.Repository;

import com.atharva.flightservice.Entity.Airports;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AirportRepository extends JpaRepository<Airports, Long> {

      Optional<Airports> findAirportsByAirportUUId(UUID AirportUUId);
}
