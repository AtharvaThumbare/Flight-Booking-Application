package com.atharva.flightservice.Service;

import com.atharva.flightservice.DTO.*;

import java.util.List;
import java.util.UUID;

public interface FlightService {

       void createFlightData(CreateFlightRequest createFlightRequest);
       FlightResponse getFlightById(UUID id);
      List< FlightSearchResponse> searchFlights(SearchRequest searchRequest);

       void updateFlightStatus(UUID uuid, UpdateFlightStatusRequest updateFlightStatusRequest);

               void updateFlightPrice(UUID uuid,  UpdateFlightPrice updateFlightPrice);

               void updateAircraft(UUID uuid,  UpdateAircraft updateAircraftRequest);

                void updateFlightSchedule(UUID uuid,  UpdateFlightSchedule updateFlightSchedule);

                void cancelFlight(UUID uuid);

                ReserveSeatsResponse reserveSeats(UUID uuid, ReserveSeatsRequest reservationRequest);

                        void releaseFlight(UUID uuid,ReserveSeatsRequest releaseSeatsRequest);
}
