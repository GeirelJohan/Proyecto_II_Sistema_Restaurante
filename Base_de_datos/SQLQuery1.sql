USE RestauranteDB;
GO

CREATE TABLE Cliente(
    idCliente INT IDENTITY(1,1) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    telefono VARCHAR(20),
    correo VARCHAR(100)
);

CREATE TABLE Empleado(
    idEmpleado INT IDENTITY(1,1) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    cargo VARCHAR(50)
);

CREATE TABLE Producto(
    idProducto INT IDENTITY(1,1) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    stock INT NOT NULL
);

CREATE TABLE Pedido(
    idPedido INT IDENTITY(1,1) PRIMARY KEY,
    idCliente INT NOT NULL,
    idEmpleado INT NOT NULL,
    fecha DATE NOT NULL,
    estado VARCHAR(30) NOT NULL,

    FOREIGN KEY(idCliente) REFERENCES Cliente(idCliente),
    FOREIGN KEY(idEmpleado) REFERENCES Empleado(idEmpleado)
);

CREATE TABLE DetallePedido(
    idDetalle INT IDENTITY(1,1) PRIMARY KEY,
    idPedido INT NOT NULL,
    idProducto INT NOT NULL,
    cantidad INT NOT NULL,
    precio DECIMAL(10,2) NOT NULL,

    FOREIGN KEY(idPedido) REFERENCES Pedido(idPedido),
    FOREIGN KEY(idProducto) REFERENCES Producto(idProducto)
);

SELECT idCliente
FROM Cliente;

SELECT * FROM Empleado;

SELECT idEmpleado
FROM Empleado;

INSERT INTO Empleado(nombre, cargo)
VALUES ('Administrador', 'Encargado');

INSERT INTO Empleado (nombre, cargo)
VALUES
('Juan Pérez', 'Mesero'),
('María Gómez', 'Cajera'),
('Carlos Rodríguez', 'Administrador');

DROP TABLE Empleado;

SELECT * 
FROM INFORMATION_SCHEMA.TABLES
WHERE TABLE_NAME = 'Empleado';

SELECT COLUMN_NAME
FROM INFORMATION_SCHEMA.COLUMNS
WHERE TABLE_NAME = 'Empleado';