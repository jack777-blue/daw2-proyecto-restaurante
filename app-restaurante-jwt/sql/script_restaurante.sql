DROP DATABASE IF EXISTS bdrestaurante;
CREATE DATABASE bdrestaurante CHARACTER SET utf8mb4;
USE bdrestaurante;

CREATE TABLE rol (
    idrol   INT AUTO_INCREMENT PRIMARY KEY,
    nomrol  VARCHAR(30) NOT NULL UNIQUE
);

CREATE TABLE usuario (
    idusuario  INT AUTO_INCREMENT PRIMARY KEY,
    nomusuario VARCHAR(50)  NOT NULL UNIQUE,
    email      VARCHAR(100) NOT NULL UNIQUE,
    password   VARCHAR(100) NOT NULL,          -- hash BCrypt (60 caracteres)
    nombres    VARCHAR(80),
    apellidos  VARCHAR(80),
    activo     TINYINT(1) NOT NULL DEFAULT 1
);

CREATE TABLE usuario_rol (
    idusuario INT NOT NULL,
    idrol     INT NOT NULL,
    PRIMARY KEY (idusuario, idrol),
    FOREIGN KEY (idusuario) REFERENCES usuario(idusuario),
    FOREIGN KEY (idrol)     REFERENCES rol(idrol)
);

CREATE TABLE plato (
    idplato     INT AUTO_INCREMENT PRIMARY KEY,
    nombre      VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255),
    precio      DECIMAL(8,2) NOT NULL,
    categoria   VARCHAR(50),
    disponible  TINYINT(1) NOT NULL DEFAULT 1
);

-- Roles, usuarios (con password cifrado con BCrypt) y algunos platos
-- los crea automaticamente la clase DataInitializer al iniciar la app.
