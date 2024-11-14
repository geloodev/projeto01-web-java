DROP DATABASE IF EXISTS gym_db;
CREATE DATABASE gym_db;
USE gym_db; 

CREATE TABLE memberships (
    id CHAR(36) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price DECIMAL(10, 2) NOT NULL
);

CREATE TABLE discounts (
    id CHAR(36) PRIMARY KEY,
    duration INT NOT NULL,
    discount_percentage INT NOT NULL 
);

CREATE TABLE clients (
    id CHAR(36) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    phone CHAR(11),
    membership_id CHAR(36),
    discount_id CHAR(36),

    FOREIGN KEY (membership_id) REFERENCES memberships(id) ON DELETE SET NULL,
    FOREIGN KEY (discount_id) REFERENCES discounts(id) ON DELETE SET NULL
);

INSERT INTO memberships (id, name, price) VALUES
    (UUID(), 'Basic', 55.90),
    (UUID(), 'Silver', 129.90),
    (UUID(), 'Gold', 269.90);

INSERT INTO discounts (id, duration, discount_percentage) VALUES
    (UUID(), 1, 0),
    (UUID(), 3, 10),
    (UUID(), 6, 15),
    (UUID(), 12, 20);

INSERT INTO clients (id, name, email, phone, membership_id, discount_id) VALUES 
    (UUID(), 'João Teste', 'joao.teste@example.com', '11900000000',
        (SELECT id FROM memberships WHERE name = 'Basic'),
        (SELECT id FROM discounts WHERE duration = '1')),
    (UUID(), 'Lucas Teste', 'lucas@teste@example.com', '11900000000',
        (SELECT id FROM memberships WHERE name = 'Silver'),
        (SELECT id FROM discounts WHERE duration = '3')),
    (UUID(), 'Júlia Teste', 'julia.teste@example.com', '11900000000',
        (SELECT id FROM memberships WHERE name = 'Gold'),
        (SELECT id FROM discounts WHERE duration = '6'));
