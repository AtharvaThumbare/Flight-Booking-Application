package com.atharva.flightservice.Repository;

import com.atharva.flightservice.Entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoutesRepository extends JpaRepository<Route, Long> {
    
       Optional<Route> findRouteByRouteUUId(UUID routeUUId);
}
