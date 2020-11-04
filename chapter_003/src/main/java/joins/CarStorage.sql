-- tables creation
CREATE TABLE body (
    id serial PRIMARY KEY,
    name varchar(200)
);

CREATE TABLE engine (
    id serial PRIMARY KEY,
    name varchar(200)
);

CREATE TABLE transmission (
    id serial PRIMARY KEY,
    name varchar(200)
);

CREATE TABLE car (
    id serial PRIMARY KEY,
    name varchar(200),
    body_id int REFERENCES body (id),
    engine_id int REFERENCES engine (id),
    transmission_id int REFERENCES transmission (id)
);

-- data filling
INSERT INTO engine (name)
VALUES ('engine1'), ('engine2'), ('engine3'), ('experimental_engine');
INSERT INTO body (name)
VALUES ('body1'), ('body2');
INSERT INTO transmission (name)
VALUES ('transmission1'), ('deprecated_transmission');
INSERT INTO car (name, body_id, engine_id, transmission_id)
VALUES ('car1', 1, 1, 1), ('car2', 2, 2, 1), ('car3', 1, 3, 1);

-- select cars with all their details
SELECT
    car.name AS car,
    b.name AS body,
    e.name AS engine,
    t.name AS transmission
FROM car
         INNER JOIN body b
                    ON car.body_id = b.id
         INNER JOIN engine e
                    ON car.engine_id = e.id
         INNER JOIN transmission t
                    ON car.transmission_id = t.id;

-- select all unused details
SELECT
    b.name AS unused_bodies
FROM car
         RIGHT JOIN body b
                    ON car.body_id = b.id
WHERE car.id IS NULL;

SELECT
    e.name AS unused_engines
FROM car
         RIGHT JOIN engine e
                    ON car.engine_id = e.id
WHERE car.id IS NULL;

SELECT
    t.name AS unused_transmissions
FROM car
         RIGHT JOIN transmission t
                    ON car.transmission_id = t.id
WHERE car.id IS NULL;
