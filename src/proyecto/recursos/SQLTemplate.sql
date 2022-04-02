CREATE DATABASE `PrograIII` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;


CREATE TABLE `Atracciones` (
  `idAtracciones` int NOT NULL,
  `nombreAtraccion` varchar(255) NOT NULL,
  `fechaInstalacion` datetime(6) NOT NULL,
  `capacidad` int NOT NULL,
  `seccion` varchar(1) NOT NULL,
  `edadMinima` int NOT NULL,
  `edadMaxima` int NOT NULL,
  `precioNormal` decimal(5,2) NOT NULL,
  `activo` tinyint NOT NULL,
  PRIMARY KEY (`idAtracciones`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `Bookeo` (
  `ticket` int NOT NULL,
  `cedula` int NOT NULL,
  `fechaCompra` datetime(6) NOT NULL,
  `fechaVisita` datetime(6) NOT NULL,
  `totalVenta` decimal(6,2) NOT NULL,
  `paseEspecial` tinyint NOT NULL,
  PRIMARY KEY (`ticket`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `BookeoAtracciones` (
  `orderId` int NOT NULL,
  `ticket` int NOT NULL,
  `idenAtrac` int NOT NULL,
  PRIMARY KEY (`orderId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `BookeoPersona` (
  `orderId` int NOT NULL,
  `cedula` int NOT NULL,
  `ticket` int NOT NULL,
  PRIMARY KEY (`orderId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `Login` (
  `idLogin` int NOT NULL,
  `cedula` int NOT NULL,
  `numTickets` int NOT NULL,
  `fechaLogin` datetime(6) DEFAULT NULL,
  `fechaLogoff` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`idLogin`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `Mantenimiento` (
  `idMantenimiento` varchar(45) NOT NULL,
  `idAtracciones` int NOT NULL,
  `cedula` int NOT NULL,
  `fechaRevision` datetime NOT NULL,
  `error` tinyint NOT NULL,
  `descripcion` varchar(128) NOT NULL,
  `solucion` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`idMantenimiento`),
  UNIQUE KEY `idMantenimiento_UNIQUE` (`idMantenimiento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `Precio` (
  `idPrecio` int NOT NULL,
  `idAtraccion` int NOT NULL,
  `descripcion` varchar(45) NOT NULL,
  `precio` int NOT NULL,
  `activo` tinyint NOT NULL,
  `edadMin` int NOT NULL,
  `edadMax` int NOT NULL,
  PRIMARY KEY (`idPrecio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `Usuarios` (
  `cedula` int NOT NULL,
  `password` varchar(32) NOT NULL,
  `correo` varchar(255) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `apellido1` varchar(45) NOT NULL,
  `appellido2` varchar(45) NOT NULL,
  `fechaNacimiento` datetime NOT NULL,
  `tipoUsuario` int NOT NULL,
  PRIMARY KEY (`cedula`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



