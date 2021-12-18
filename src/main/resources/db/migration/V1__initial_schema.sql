-- drop all the tables while testing
DROP TABLE IF EXISTS teams;

CREATE TABLE teams
(
    id   serial,
    name VARCHAR(100) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);

INSERT INTO teams (name) VALUES ('unassigned');