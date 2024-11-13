DROP DATABASE IF EXISTS gym_db;
CREATE DATABASE gym_db;
USE gym_db;

CREATE TABLE clients (
    id CHAR(36) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    phone CHAR(11)
);

INSERT INTO clients (id, name, email, phone) 
    VALUES (UUID(), 'João Teste', 'joao.teste@example.com', '11900000000');
INSERT INTO clients (id, name, email, phone) 
    VALUES (UUID(), 'Lucas Teste', 'lucas@teste@example.com', '11900000000');
INSERT INTO clients (id, name, email, phone) 
    VALUES (UUID(), 'Júlia Teste', 'julia.teste@example.com', '11900000000');
