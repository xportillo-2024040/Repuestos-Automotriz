DROP DATABASE IF EXISTS DBProyectoAutomotriz;
CREATE DATABASE DBProyectoAutomotriz;
USE DBProyectoAutomotriz;

-- TABLA CLIENTES
CREATE TABLE cliente (
    id_cliente INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    telefono VARCHAR(15),
    email VARCHAR(100) UNIQUE,
    PRIMARY KEY (id_cliente)
);

-- TABLA VEHICULOS
CREATE TABLE vehiculo (
    id_vehiculo INT NOT NULL AUTO_INCREMENT,
    marca VARCHAR(50) NOT NULL,
    modelo VARCHAR(50) NOT NULL,
    año INT NOT NULL,
    id_cliente INT,
    PRIMARY KEY (id_vehiculo),
    FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente)
);

-- TABLA REPUESTOS
CREATE TABLE repuesto (
    id_repuesto INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(200),
    precio DECIMAL(10,2) NOT NULL,
    stock INT NOT NULL,
    PRIMARY KEY (id_repuesto)
);

-- TABLA PEDIDOS
CREATE TABLE pedido (
    id_pedido INT NOT NULL AUTO_INCREMENT,
    id_cliente INT NOT NULL,
    id_repuesto INT NOT NULL,
    cantidad INT NOT NULL,
    fecha DATE NOT NULL,
    PRIMARY KEY (id_pedido),
    FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente),
    FOREIGN KEY (id_repuesto) REFERENCES repuesto(id_repuesto)
);



INSERT INTO cliente (nombre, apellido, telefono, email) VALUES
('Xavier', 'Portillo', '50123456', 'xavier@gmail.com'),
('Rodrigo', 'Mendez', '40234567', 'rodrigo@gmail.com'),
('Milton', 'Lara', '55112233', 'milton@gmail.com'),
('Mauricio', 'Morales', '66123456', 'mauricio@gmail.com'),
('Diego', 'Molina', '77112233', 'diego@gmail.com'),
('Elizabeth', 'Juarez', '50112233', 'elizabeth@gmail.com'),
('Sophia', 'Pinelo', '42123456', 'sophia@gmail.com'),
('Pablo', 'Calderon', '66112233', 'pablo@gmail.com'),
('Karin', 'Estrada', '55123456', 'karin@gmail.com'),
('Rolando', 'Guzman', '77123456', 'rolando@gmail.com');

INSERT INTO vehiculo (marca, modelo, año, id_cliente) VALUES
('Toyota', 'Corolla', 2018, 1),
('Honda', 'Civic', 2020, 2),
('Nissan', 'Altima', 2017, 3),
('Ford', 'Focus', 2019, 4),
('Chevrolet', 'Cruze', 2016, 5),
('Mazda', '3', 2021, 6),
('Hyundai', 'Elantra', 2015, 7),
('Kia', 'Rio', 2019, 8),
('Volkswagen', 'Jetta', 2018, 9),
('Subaru', 'Impreza', 2020, 10);

INSERT INTO repuesto (nombre, descripcion, precio, stock) VALUES
('Filtro de aceite', 'Filtro de aceite sintetico', 120.50, 20),
('Pastillas de freno', 'Juego de pastillas delanteras', 350.00, 15),
('Bujia', 'Bujia de iridio', 85.75, 50),
('Bateria', 'Bateria 12V 60Ah', 950.00, 10),
('Amortiguador', 'Amortiguador delantero derecho', 800.00, 8),
('Aceite sintetico', 'Aceite 5W-30', 250.00, 30),
('Filtro de aire', 'Filtro para motor 1.8L', 150.00, 25),
('Radiador', 'Radiador aluminio', 1200.00, 5),
('Disco de freno', 'Disco ventilado 280mm', 600.00, 12),
('Correa de distribucion', 'Correa dentada reforzada', 700.00, 7);

INSERT INTO pedido (id_cliente, id_repuesto, cantidad, fecha) VALUES
(1, 1, 2, '2025-01-10'),
(2, 3, 4, '2025-01-12'),
(3, 2, 1, '2025-01-15'),
(4, 5, 2, '2025-01-18'),
(5, 4, 1, '2025-01-20'),
(6, 6, 3, '2025-01-22'),
(7, 7, 2, '2025-01-25'),
(8, 9, 1, '2025-01-28'),
(9, 8, 1, '2025-01-30'),
(10, 10, 2, '2025-02-02');


SELECT * FROM cliente;
SELECT * FROM vehiculo;
SELECT * FROM repuesto;
SELECT * FROM pedido;
