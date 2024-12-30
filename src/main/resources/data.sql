CREATE TABLE drone (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    serial_number VARCHAR(100) NOT NULL UNIQUE,
    model VARCHAR(50) NOT NULL,
    weight_limit INT NOT NULL,
    battery_capacity INT NOT NULL,
    state VARCHAR(50) NOT NULL
);


CREATE TABLE drone_battery_audit_log (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    drone_id BIGINT NOT NULL,
    drone_serial_number VARCHAR(255) NOT NULL,
    battery_level INT NOT NULL,
    timestamp TIMESTAMP NOT NULL
);

CREATE TABLE medication (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL CHECK (name ~ '^[A-Za-z0-9-_]+'),
    weight INT NOT NULL,
    code VARCHAR(255) NOT NULL CHECK (code ~ '^[A-Z0-9_]+'),
    image BYTEA NOT NULL,
    drone_id BIGINT NOT NULL,
    CONSTRAINT fk_drone_id FOREIGN KEY (drone_id) REFERENCES drone(id) ON DELETE CASCADE
);

INSERT INTO drone (serial_number, model, weight_limit, battery_capacity, state)
VALUES
('DRONE001', 'LIGHTWEIGHT', 400, 100, 'IDLE'),      -- Available for loading
('DRONE002', 'MIDDLEWEIGHT', 500, 20, 'IDLE'),      -- Battery below 25%, cannot load
('DRONE003', 'HEAVYWEIGHT', 500, 80, 'LOADING'),    -- Currently in LOADING state
('DRONE004', 'CRUISERWEIGHT', 300, 50, 'DELIVERED'),-- Delivered state
('DRONE005', 'LIGHTWEIGHT', 300, 90, 'RETURNING');  -- Available for loading



INSERT INTO medication (name, weight, code, image, drone_id)
VALUES
('Paracetamol', 100, 'PARA_001', X'89504E470D0A1A0A0000000D49484452', 3), -- Loaded on DRONE003
('Ibuprofen', 200, 'IBU_002', X'89504E470D0A1A0A0000000D49484452', 3),    -- Loaded on DRONE003
('Aspirin', 150, 'ASPI_003', X'89504E470D0A1A0A0000000D49484452', 4);     -- Loaded on DRONE004



INSERT INTO drone_battery_audit_log (drone_id, drone_serial_number, battery_level, timestamp)
VALUES
(1,'DRONE001', 100, '2024-01-01T10:00:00'), -- DRONE001
(2,'DRONE002', 20, '2024-01-01T10:05:00'),  -- DRONE002
(3,'DRONE003', 80, '2024-01-01T10:10:00'),  -- DRONE003
(4,'DRONE004', 50, '2024-01-01T10:15:00'),  -- DRONE004
(5,'DRONE005', 90, '2024-01-01T10:20:00');  -- DRONE005




