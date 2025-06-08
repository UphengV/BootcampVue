CREATE TABLE dealerships (
    dealership_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50),
    address VARCHAR(50),
    phone VARCHAR(12)
);

CREATE TABLE vehicles (
    VIN VARCHAR(17) PRIMARY KEY,
    make VARCHAR(30),
    model VARCHAR(30),
    year INT,
    color VARCHAR(20),
    sold BOOLEAN
);

CREATE TABLE inventory (
    dealership_id INT,
    VIN VARCHAR(17),
    FOREIGN KEY (dealership_id) REFERENCES dealerships(dealership_id),
    FOREIGN KEY (VIN) REFERENCES vehicles(VIN)
);

CREATE TABLE sales_contracts (
    contract_id INT AUTO_INCREMENT PRIMARY KEY,
    VIN VARCHAR(17),
    sale_price DECIMAL(10, 2),
    sale_date DATE,
    dealership_id INT,
    FOREIGN KEY (VIN) REFERENCES vehicles(VIN),
    FOREIGN KEY (dealership_id) REFERENCES dealerships(dealership_id)
);

INSERT INTO dealerships (name, address, phone) VALUES
('Fast Cars', '123 Main St', '123-456-7890'),
('Auto World', '456 Elm St', '234-567-8901');

INSERT INTO vehicles (VIN, make, model, year, color, sold) VALUES
('1HGCM82633A123456', 'Honda', 'Civic', 2020, 'Red', FALSE),
('2C3KA63H76H123456', 'Chrysler', '300', 2022, 'Black', TRUE),
('3FADP4EJ0DM123456', 'Ford', 'Fiesta', 2019, 'Blue', FALSE);

INSERT INTO inventory (dealership_id, VIN) VALUES
(1, '1HGCM82633A123456'),
(1, '2C3KA63H76H123456'),
(2, '3FADP4EJ0DM123456');

INSERT INTO sales_contracts (VIN, sale_price, sale_date, dealership_id) VALUES
('2C3KA63H76H123456', 25000.00, '2024-05-10', 1);

/*1. Get all dealerships*/
SELECT * FROM dealerships;

/*2. Find all vehicles for a specific dealership*/
SELECT v.*
FROM vehicles v
JOIN inventory i ON v.VIN = i.VIN
WHERE i.dealership_id = 1;

/*Find a car by VIN*/
SELECT * FROM vehicles
WHERE VIN = '1HGCM82633A123456'; 

/*Find the dealership where a certain car is located, by VIN*/
SELECT d.*
FROM dealerships d
JOIN inventory i ON d.dealership_id = i.dealership_id
WHERE i.VIN = '2C3KA63H76H123456';

/*Find all Dealerships that have a certain car type (i.e. Red Ford Mustang)*/
SELECT DISTINCT d.*
FROM dealerships d
JOIN inventory i ON d.dealership_id = i.dealership_id
JOIN vehicles v ON v.VIN = i.VIN
WHERE v.make = 'Ford' AND v.model = 'Fiesta' AND v.color = 'Blue';

/*. Get all sales information for a specific dealer for a specific date range*/
SELECT *
FROM sales_contracts
WHERE dealership_id = 1 AND sale_date BETWEEN '2024-01-01' AND '2024-12-31';



