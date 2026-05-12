package com.atharva.flightservice.Service;

import com.atharva.flightservice.DTO.*;
import jakarta.validation.Valid;

import java.util.List;

public interface FlightService {

       void createFlightData(CreateFlightRequest createFlightRequest);
       FlightResponse getFlightById(long id);
      List< FlightSearchResponse> searchFlights(SearchRequest searchRequest);

       void updateFlightStatus(long id,@Valid UpdateFlightStatusRequest updateFlightStatusRequest);
}
