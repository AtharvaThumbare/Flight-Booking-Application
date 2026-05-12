package com.atharva.flightservice.Repository;

import com.atharva.flightservice.Entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoutesRepository extends JpaRepository<Route, Long> {
}
