CREATE TABLE IF NOT EXISTS users (
                                     id SERIAL PRIMARY KEY,
                                     full_name VARCHAR(255)
    );

CREATE TABLE IF NOT EXISTS tasks (
    id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES users(id),
    name VARCHAR(255),
    status VARCHAR(50),
    description TEXT,
    deadline DATE,
    priority INTEGER
    );

CREATE TABLE user_tasks (
                            user_id INTEGER REFERENCES users(id),
                            task_id INTEGER REFERENCES tasks(id),
                            PRIMARY KEY (user_id, task_id)
);