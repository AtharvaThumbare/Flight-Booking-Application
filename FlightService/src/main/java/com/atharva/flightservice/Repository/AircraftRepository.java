package com.atharva.flightservice.Repository;

import com.atharva.flightservice.Entity.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AircraftRepository extends JpaRepository<Aircraft, Long> {

    Optional<Aircraft> findAircraftByAircraftUUId(UUID aircraftUUId);
}
