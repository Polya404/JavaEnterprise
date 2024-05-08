CREATE TABLE if not exists users
(
    id       BIGSERIAL PRIMARY KEY,
    name     VARCHAR(255) NOT NULL,
    email    VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
    );

CREATE TABLE if not exists roles
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
    );

CREATE TABLE if not exists users_roles
(
    user_id BIGINT REFERENCES users (id),
    role_id BIGINT REFERENCES roles (id),
    PRIMARY KEY (user_id, role_id)
    );

CREATE TABLE track (
            id BIGSERIAL PRIMARY KEY
);

CREATE TABLE track_coordinates (
            id BIGSERIAL PRIMARY KEY,
            latitude DOUBLE PRECISION,
            longitude DOUBLE PRECISION,
            track_id BIGINT,
            created_at TIMESTAMP NOT NULL,
            FOREIGN KEY (track_id) REFERENCES track(id)
);

INSERT INTO users (id, name, email, password)
VALUES (1, 'admin', 'admin@mail.com', '$2a$10$RcVgIhKNcmiKVr7EDDFW6OEENmSVYyvRxiCtFYadcXCtwOZOsX./.');

INSERT INTO roles (name)
VALUES ('ROLE_USER'),
       ('ROLE_ADMIN');

INSERT INTO users_roles (user_id, role_id)
VALUES (1, 2);

