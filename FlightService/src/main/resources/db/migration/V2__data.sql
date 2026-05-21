-- Assuming your table is named 'flights' based on your entity naming conventions
INSERT INTO flight_schedules (
    flightuuid,
    flight_number,
    airlines_id,
    aircraft_id,
    route_id,
    departure_time,
    arrival_time,
    available_seats,
    base_price,
    status
) VALUES

-- Flight 1: American Airlines (AA), JFK to LHR on a Boeing 777
(
    gen_random_uuid(),
    'AA100',
    (SELECT id FROM airlines WHERE code = 'AA'),
    (SELECT id FROM aircrafts WHERE model = 'Boeing 777-300ER' LIMIT 1),
(SELECT r.id FROM routes r
    JOIN airports src ON r.source_airports_id = src.id
    JOIN airports dest ON r.destination_id = dest.id
    WHERE src.code = 'JFK' AND dest.code = 'LHR'),
    '2026-06-15 08:30:00',
    '2026-06-15 20:40:00',
    304,
    550.00,
    'SCHEDULED'
    ),

-- Flight 2: Emirates (EK), LHR to DXB on an Airbus A380
(
    gen_random_uuid(),
    'EK002',
    (SELECT id FROM airlines WHERE code = 'EK'),
    (SELECT id FROM aircrafts WHERE model = 'Airbus A380-800' LIMIT 1),
    (SELECT r.id FROM routes r
        JOIN airports src ON r.source_airports_id = src.id
        JOIN airports dest ON r.destination_id = dest.id
        WHERE src.code = 'LHR' AND dest.code = 'DXB'),
    '2026-06-15 14:15:00',
    '2026-06-16 00:20:00',
    515,
    720.50,
    'BOARDING'
),

-- Flight 3: Emirates (EK), DXB to SYD on a Boeing 777
(
    gen_random_uuid(),
    'EK412',
    (SELECT id FROM airlines WHERE code = 'EK'),
    (SELECT id FROM aircrafts WHERE model = 'Boeing 777-200LR' LIMIT 1),
    (SELECT r.id FROM routes r
        JOIN airports src ON r.source_airports_id = src.id
        JOIN airports dest ON r.destination_id = dest.id
        WHERE src.code = 'DXB' AND dest.code = 'SYD'),
    '2026-06-16 10:15:00',
    '2026-06-17 06:00:00',
    210,
    1250.00,
    'SCHEDULED'
),

-- Flight 4: Singapore Airlines (SIA), SIN to HND on an Airbus A350
(
    gen_random_uuid(),
    'SQ636',
    (SELECT id FROM airlines WHERE code = 'SIA'),
    (SELECT id FROM aircrafts WHERE model = 'Airbus A350-900' LIMIT 1),
    (SELECT r.id FROM routes r
        JOIN airports src ON r.source_airports_id = src.id
        JOIN airports dest ON r.destination_id = dest.id
        WHERE src.code = 'SIN' AND dest.code = 'HND'),
    '2026-06-16 22:45:00',
    '2026-06-17 06:20:00',
    142,
    430.00,
    'DELAYED'
),

-- Flight 5: Delta Air Lines (DL), SFO to JFK on an Airbus A220
(
    gen_random_uuid(),
    'DL420',
    (SELECT id FROM airlines WHERE code = 'DL'),
    (SELECT id FROM aircrafts WHERE model = 'Airbus A220-100' LIMIT 1),
    (SELECT r.id FROM routes r
        JOIN airports src ON r.source_airports_id = src.id
        JOIN airports dest ON r.destination_id = dest.id
        WHERE src.code = 'SFO' AND dest.code = 'JFK'),
    '2026-05-18 10:00:00',
    '2026-05-18 18:30:00',
    0,
    299.99,
    'DEPARTED'
);