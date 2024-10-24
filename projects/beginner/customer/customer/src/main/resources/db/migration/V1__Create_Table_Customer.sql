CREATE TABLE IF NOT EXISTS customer (
    id SERIAL PRIMARY KEY,
    birth_date DATE,
    email VARCHAR(150) NOT NULL UNIQUE,
    first_name VARCHAR(15) NOT NULL,
    gender VARCHAR(1),
    last_name VARCHAR(15),
    phone_number VARCHAR(255)
);