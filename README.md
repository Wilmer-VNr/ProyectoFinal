CREATE DATABASE aulaEsfot;
USE aulaEsfot;


CREATE TABLE usuarios (
  userid int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  nombre varchar(50) NOT NULL,
  apellido varchar(50) NOT NULL,
  username varchar(20) NOT NULL,
  password varchar(20) NOT NULL,
  perfil varchar(20) NOT NULL
) 

INSERT INTO usuarios (userid, nombre, apellido, username, password, perfil) VALUES
(1, 'Juan ', 'Pascal', 'admin', '2024', 'Administrador'),
(2, 'Maria ', 'Torres', 'profesor', '2024', 'Profesor'),
(3, 'Klens ', 'Silva', 'estudiante', '2024', 'Estudiante');

CREATE TABLE aulas (
    idAula VARCHAR(10) PRIMARY KEY,
    reservadoPor VARCHAR(100),
    totalAulas INT,
    capacidad INT,
    disponibles INT
);

CREATE TABLE laboratorios (
    idLab VARCHAR(10) PRIMARY KEY,
    reservadoPor VARCHAR(100),
    totalLab INT,
    capacidad INT,
    disponibles INT,
FechaReserva DATE,
    FOREIGN KEY (idAula) REFERENCES aulas(idAula)
);
