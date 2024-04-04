CREATE TABLE IF NOT EXISTS users (
                       id SERIAL PRIMARY KEY,
                       full_name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS tasks (
                       id SERIAL PRIMARY KEY,
                       user_id INT,
                       name VARCHAR(255),
                       status VARCHAR(50),
                       description TEXT,
                       deadline DATE,
                       priority INT,
                       FOREIGN KEY (user_id) REFERENCES users(id)
);

