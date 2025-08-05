CREATE TABLE users (

    id BIGINT PRIMARY KEY,
    name VARCHAR(256),
    email VARCHAR(256)

);

CREATE TABLE administrator (

    id BIGINT PRIMARY KEY,
    name VARCHAR(256),
    email VARCHAR(256),
    password VARCHAR(256)

);

CREATE TABLE product (

    id BIGINT PRIMARY KEY,
    name VARCHAR(256),
    description TEXT,
    price NUMERIC,
    quantity INTEGER,
    imgUrl VARCHAR(256)

);

