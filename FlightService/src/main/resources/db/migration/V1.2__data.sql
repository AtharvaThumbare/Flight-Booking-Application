

INSERT INTO airports (airportuuid, code, name, city, country, timezone) VALUES
                                                               (gen_random_uuid(), 'NYC-01', 'New York Headquarters', 'New York', 'USA', 'America/New_York'),
                                                               (gen_random_uuid(), 'LON-02', 'London Branch', 'London', 'United Kingdom', 'Europe/London'),
                                                               (gen_random_uuid(), 'BOM-01', 'Mumbai Regional Office', 'Mumbai', 'India', 'Asia/Kolkata'),
                                                               (gen_random_uuid(), 'SYD-01', 'Sydney Hub', 'Sydney', 'Australia', 'Australia/Sydney'),
                                                               (gen_random_uuid(), 'TOK-03', 'Tokyo Innovation Center', 'Tokyo', 'Japan', 'Asia/Tokyo'),
                                                               (gen_random_uuid(), 'SFO-01', 'San Francisco Engineering', 'San Francisco', 'USA', 'America/Los_Angeles'),
                                                               (gen_random_uuid(), 'BER-02', 'Berlin Data Center', 'Berlin', 'Germany', 'Europe/Berlin'),
                                                               (gen_random_uuid(), 'DXB-01', 'Dubai Operations', 'Dubai', 'United Arab Emirates', 'Asia/Dubai'),
                                                               (gen_random_uuid(), 'GRU-01', 'São Paulo Logistics', 'São Paulo', 'Brazil', 'America/Sao_Paulo'),
                                                               (gen_random_uuid(), 'SIN-01', 'Singapore APAC HQ', 'Singapore', 'Singapore', 'Asia/Singapore'),
                                                               (gen_random_uuid(), 'JFK', 'John F. Kennedy International Airport', 'New York', 'USA', 'America/New_York'),
                                                               (gen_random_uuid(), 'LHR', 'Heathrow Airport', 'London', 'United Kingdom', 'Europe/London'),
                                                               (gen_random_uuid(), 'BOM', 'Chhatrapati Shivaji Maharaj International', 'Mumbai', 'India', 'Asia/Kolkata'),
                                                               (gen_random_uuid(), 'SYD', 'Sydney Kingsford Smith Airport', 'Sydney', 'Australia', 'Australia/Sydney'),
                                                               (gen_random_uuid(), 'HND', 'Tokyo Haneda Airport', 'Tokyo', 'Japan', 'Asia/Tokyo'),
                                                               (gen_random_uuid(), 'SFO', 'San Francisco International Airport', 'San Francisco', 'USA', 'America/Los_Angeles'),
                                                               (gen_random_uuid(), 'BER', 'Berlin Brandenburg Airport', 'Berlin', 'Germany', 'Europe/Berlin'),
                                                               (gen_random_uuid(), 'DXB', 'Dubai International Airport', 'Dubai', 'United Arab Emirates', 'Asia/Dubai'),
                                                               (gen_random_uuid(), 'GRU', 'São Paulo/Guarulhos International Airport', 'São Paulo', 'Brazil', 'America/Sao_Paulo'),
                                                               (gen_random_uuid(), 'SIN', 'Changi Airport', 'Singapore', 'Singapore', 'Asia/Singapore');



INSERT INTO airlines (airlineuuid, code, name) VALUES
                                     (gen_random_uuid(), 'AA', 'American Airlines'),
                                     (gen_random_uuid(), 'DL', 'Delta Air Lines'),
                                     (gen_random_uuid(), 'EK', 'Emirates'),
                                     (gen_random_uuid(), 'BA', 'British Airways'),
                                     (gen_random_uuid(), 'SIA', 'Singapore Airlines');



INSERT INTO aircrafts (aircraftuuid, airlines_id, model, total_seats) VALUES
                                                           (gen_random_uuid(), (SELECT id FROM airlines WHERE code = 'AA'), 'Boeing 737-800', 160),
                                                           (gen_random_uuid(), (SELECT id FROM airlines WHERE code = 'AA'), 'Boeing 777-300ER', 304),

                                                           (gen_random_uuid(), (SELECT id FROM airlines WHERE code = 'DL'), 'Airbus A350-900', 306),
                                                           (gen_random_uuid(), (SELECT id FROM airlines WHERE code = 'DL'), 'Airbus A220-100', 109),

                                                           (gen_random_uuid(), (SELECT id FROM airlines WHERE code = 'EK'), 'Airbus A380-800', 515),
                                                           (gen_random_uuid(), (SELECT id FROM airlines WHERE code = 'EK'), 'Boeing 777-200LR', 302),

                                                           (gen_random_uuid(), (SELECT id FROM airlines WHERE code = 'BA'), 'Boeing 787-9 Dreamliner', 216),
                                                           (gen_random_uuid(), (SELECT id FROM airlines WHERE code = 'BA'), 'Airbus A320neo', 180),

                                                           (gen_random_uuid(), (SELECT id FROM airlines WHERE code = 'SIA'), 'Airbus A350-900', 253);

INSERT INTO routes (routeuuid, source_airports_id, destination_id, distance) VALUES
-- New York (JFK) to London (LHR)
(gen_random_uuid(), (SELECT id FROM airports WHERE code = 'JFK'), (SELECT id FROM airports WHERE code = 'LHR'), 5540),

-- London (LHR) to Dubai (DXB)
(gen_random_uuid(), (SELECT id FROM airports WHERE code = 'LHR'), (SELECT id FROM airports WHERE code = 'DXB'), 5470),

-- Dubai (DXB) to Sydney (SYD)
(gen_random_uuid(), (SELECT id FROM airports WHERE code = 'DXB'), (SELECT id FROM airports WHERE code = 'SYD'), 12040),

-- Singapore (SIN) to Tokyo (HND)
(gen_random_uuid(), (SELECT id FROM airports WHERE code = 'SIN'), (SELECT id FROM airports WHERE code = 'HND'), 5300),

-- San Francisco (SFO) to Singapore (SIN)
(gen_random_uuid(), (SELECT id FROM airports WHERE code = 'SFO'), (SELECT id FROM airports WHERE code = 'SIN'), 13580),

-- San Francisco (SFO) to New York (JFK)
(gen_random_uuid(), (SELECT id FROM airports WHERE code = 'SFO'), (SELECT id FROM airports WHERE code = 'JFK'), 4150);
