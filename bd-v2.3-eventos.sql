/*
SQLyog Ultimate v11.11 (64 bit)
MySQL - 5.5.5-10.4.27-MariaDB : Database - eventos
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`eventos` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `eventos`;

/*Table structure for table `fechalimite` */

DROP TABLE IF EXISTS `fechalimite`;

CREATE TABLE `fechalimite` (
  `idFecha` int(11) NOT NULL AUTO_INCREMENT,
  `fechaLimite` date NOT NULL,
  PRIMARY KEY (`idFecha`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `fechalimite` */

LOCK TABLES `fechalimite` WRITE;

insert  into `fechalimite`(`idFecha`,`fechaLimite`) values (1,'2025-04-30');

UNLOCK TABLES;

/*Table structure for table `saldosocio` */

DROP TABLE IF EXISTS `saldosocio`;

CREATE TABLE `saldosocio` (
  `idSaldo` int(11) NOT NULL AUTO_INCREMENT,
  `Origen` int(11) NOT NULL,
  `Grupo` int(11) NOT NULL,
  `Socio` int(11) NOT NULL,
  `Saldo` double NOT NULL,
  PRIMARY KEY (`idSaldo`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `saldosocio` */

LOCK TABLES `saldosocio` WRITE;

insert  into `saldosocio`(`idSaldo`,`Origen`,`Grupo`,`Socio`,`Saldo`) values (8,30422,10,3674,560),(9,30412,10,1658,1120),(10,30412,10,1655,560),(11,30402,10,6501,500);

UNLOCK TABLES;

/*Table structure for table `tbl_bit_accion_usuarios` */

DROP TABLE IF EXISTS `tbl_bit_accion_usuarios`;

CREATE TABLE `tbl_bit_accion_usuarios` (
  `id_reg` int(11) NOT NULL AUTO_INCREMENT,
  `Fecha` datetime NOT NULL,
  `Sucursal` varchar(100) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `Nombre` varchar(100) NOT NULL,
  `id_perfil` int(11) NOT NULL,
  `Descripcion` text NOT NULL,
  PRIMARY KEY (`id_reg`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `tbl_bit_accion_usuarios` */

LOCK TABLES `tbl_bit_accion_usuarios` WRITE;

insert  into `tbl_bit_accion_usuarios`(`id_reg`,`Fecha`,`Sucursal`,`id_usuario`,`Nombre`,`id_perfil`,`Descripcion`) values (17,'2025-03-03 15:19:44','Sucursal Independencia',701,'Eduardo Hernandez Hernandez',1,'Usuario: 701 Actualizo al usuario: 697'),(18,'2025-03-03 15:19:44','Corporativo Cerro de la Silla',697,'ADAIR GUTIERREZ VITE',3,'Actualización de datos'),(19,'2025-03-03 16:43:13','Sucursal Independencia',701,'Eduardo Hernandez Hernandez',1,'Usuario: 701 Restablecio contraseña a usuario: 630'),(20,'2025-03-03 16:43:13','Sucursal Independencia',630,'MARIA ELENA ALVAREZ RAMOS',1,'Actualización de datos'),(21,'2025-03-03 16:44:59','Sucursal Independencia',701,'Eduardo Hernandez Hernandez',1,'Usuario: 701 Restablecio contraseña a usuario: 540'),(22,'2025-03-03 16:44:59','Sucursal Muzquiz',540,'Perla',1,'Restableciminto de contraseña'),(23,'2025-03-03 16:46:40','Sucursal Independencia',701,'Eduardo Hernandez Hernandez',1,'Usuario: 701 Restablecio contraseña a usuario: 113'),(24,'2025-03-03 16:46:40','Sucursal Muzquiz',113,'Jose Cecilio Gomez Galdamez',1,'Restableciminto de contraseña'),(26,'2025-03-04 10:18:08','Sucursal Sabinas',709,'MONICA VALLEJO MARTINEZ',1,'Usuario actualizó datos'),(27,'2025-03-04 10:21:28','Sucursal Independencia',701,'Eduardo Hernandez Hernandez',1,'Usuario: 701 Actualizó al usuario: 709'),(28,'2025-03-04 10:21:28','Sucursal Sabinas',709,'MONICA VALLEJO MARTINEZ',4,'Usuario actualizó datos'),(29,'2025-03-04 10:41:40','Sucursal Independencia',701,'Eduardo Hernandez Hernandez',1,'Usuario: 701 creó al usuario: 697'),(30,'2025-03-04 10:41:40','Corporativo Cerro de la Silla',697,'ADAIR GUTIERREZ VITE',2,'Nuevo usuario creado.'),(31,'2025-03-04 14:38:00','Sucursal Independencia',701,'Eduardo Hernandez Hernandez',1,'Usuario: 701 Restablecio la contraseña al usuario: 540'),(32,'2025-03-04 14:38:00','Sucursal Muzquiz',540,'Perla',1,'Restablecio su contraseña'),(33,'2025-03-04 15:57:39','Sucursal Independencia',701,'Eduardo Hernandez Hernandez',1,'Usuario: 701 Elimino a usuario: 540'),(34,'2025-03-04 15:57:54','Sucursal Independencia',701,'Eduardo Hernandez Hernandez',1,'Usuario: 701 creó al usuario: 562'),(35,'2025-03-04 15:57:54','Sucursal Nueva Rosita',562,'Omar Alejandro Flores Reyes',1,'Nuevo usuario creado.'),(36,'2025-03-04 15:57:57','Sucursal Independencia',701,'Eduardo Hernandez Hernandez',1,'Usuario: 701 Elimino a usuario: 562'),(37,'2025-03-04 16:01:02','Sucursal Independencia',701,'Eduardo Hernandez Hernandez',1,'Usuario: 701 creó al usuario: 569'),(38,'2025-03-04 16:01:02','Sucursal Palau',569,'Maria Yadira Camacho Vazquez',1,'Nuevo usuario creado.'),(39,'2025-03-04 16:01:05','Sucursal Independencia',701,'Eduardo Hernandez Hernandez',1,'Usuario: 701 Elimino a usuario: 569'),(40,'2025-03-04 16:10:00','Sucursal Independencia',701,'Eduardo Hernandez Hernandez',1,'Usuario: 701 creó al usuario: 499'),(41,'2025-03-04 16:10:00','Corporativo Cerro de la Silla',499,'FRANCISCO JAVIER FLORES GONZALEZ',3,'Nuevo usuario creado.'),(42,'2025-03-04 16:10:04','Sucursal Independencia',701,'Eduardo Hernandez Hernandez',1,'Usuario: 701 Elimino a usuario: 499'),(43,'2025-03-04 16:10:04','Corporativo Cerro de la Silla',499,'FRANCISCO JAVIER FLORES GONZALEZ',3,'Eliminado del sistema'),(44,'2025-03-06 16:56:13','Sucursal Nueva Rosita',539,'Eduardo Dominguez',1,'Usuario: 539 creó al usuario: 540'),(45,'2025-03-06 16:56:13','Sucursal Muzquiz',540,'Maria del Rosario Gonzalez Vas',1,'Nuevo usuario creado.'),(46,'2025-03-06 16:56:26','Sucursal Nueva Rosita',539,'Eduardo Dominguez',1,'Usuario: 539 Actualizó al usuario: 539'),(47,'2025-03-06 16:56:26','Sucursal Nueva Rosita',539,'Eduardo Dominguez',4,'Usuario actualizó datos'),(48,'2025-03-08 11:51:21','Sucursal Independencia',701,'Eduardo Hernandez Hernandez',1,'Usuario: 701 Restablecio la contraseña al usuario: 709'),(49,'2025-03-08 11:51:21','Sucursal Sabinas',709,'MONICA VALLEJO MARTINEZ',2,'Restablecio su contraseña');

UNLOCK TABLES;

/*Table structure for table `tbl_costo` */

DROP TABLE IF EXISTS `tbl_costo`;

CREATE TABLE `tbl_costo` (
  `idCosto` int(11) NOT NULL AUTO_INCREMENT,
  `Costo` double NOT NULL,
  PRIMARY KEY (`idCosto`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `tbl_costo` */

LOCK TABLES `tbl_costo` WRITE;

insert  into `tbl_costo`(`idCosto`,`Costo`) values (1,560),(2,460),(3,360);

UNLOCK TABLES;

/*Table structure for table `tbl_estado_sillas` */

DROP TABLE IF EXISTS `tbl_estado_sillas`;

CREATE TABLE `tbl_estado_sillas` (
  `idEstado` int(11) NOT NULL AUTO_INCREMENT,
  `EstadoSilla` varchar(100) NOT NULL,
  PRIMARY KEY (`idEstado`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `tbl_estado_sillas` */

LOCK TABLES `tbl_estado_sillas` WRITE;

insert  into `tbl_estado_sillas`(`idEstado`,`EstadoSilla`) values (1,'Disponible'),(2,'Separado'),(3,'Pagado');

UNLOCK TABLES;

/*Table structure for table `tbl_perfil` */

DROP TABLE IF EXISTS `tbl_perfil`;

CREATE TABLE `tbl_perfil` (
  `id_perfil` int(11) NOT NULL AUTO_INCREMENT,
  `tipo_perfil` varchar(100) NOT NULL,
  PRIMARY KEY (`id_perfil`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `tbl_perfil` */

LOCK TABLES `tbl_perfil` WRITE;

insert  into `tbl_perfil`(`id_perfil`,`tipo_perfil`) values (1,'Sistemas'),(2,'Operaciones'),(3,'Gerente'),(4,'Cajero');

UNLOCK TABLES;

/*Table structure for table `tbl_usuarios` */

DROP TABLE IF EXISTS `tbl_usuarios`;

CREATE TABLE `tbl_usuarios` (
  `id_usuario` int(11) NOT NULL,
  `Nombre` varchar(200) NOT NULL,
  `vchPass` varchar(100) NOT NULL,
  `dtVigencia` date NOT NULL,
  `id_perfil` int(11) NOT NULL,
  `estado` varchar(50) NOT NULL,
  PRIMARY KEY (`id_usuario`),
  KEY `id_perfil` (`id_perfil`),
  CONSTRAINT `id_perfil` FOREIGN KEY (`id_perfil`) REFERENCES `tbl_perfil` (`id_perfil`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `tbl_usuarios` */

LOCK TABLES `tbl_usuarios` WRITE;

insert  into `tbl_usuarios`(`id_usuario`,`Nombre`,`vchPass`,`dtVigencia`,`id_perfil`,`estado`) values (113,'Jose Cecilio Gomez Galdamez','c65aea2c82a552d83ef8f02d8845ab2d','2025-04-03',1,'activo'),(539,'Eduardo Dominguez','8e09099f4cb6ccf3ca4ba8cb4c3bae7e','2025-03-26',4,'activo'),(540,'Maria del Rosario Gonzalez Vas','8e09099f4cb6ccf3ca4ba8cb4c3bae7e','2025-04-06',2,'activo'),(630,'MARIA ELENA ALVAREZ RAMOS','c65aea2c82a552d83ef8f02d8845ab2d','2025-04-03',1,'activo'),(697,'ADAIR GUTIERREZ VITE','f83eb54af143e1c220d48cbff2675a4e','2025-04-04',2,'activo'),(701,'Eduardo Hernandez Hernandez','f83eb54af143e1c220d48cbff2675a4e','2025-03-27',2,'activo'),(709,'MONICA VALLEJO MARTINEZ','b623e6e36421e60f6bb902e2d30a379f','2025-04-08',2,'activo');

UNLOCK TABLES;

/*Table structure for table `tbl_zonas` */

DROP TABLE IF EXISTS `tbl_zonas`;

CREATE TABLE `tbl_zonas` (
  `idZona` int(11) NOT NULL AUTO_INCREMENT,
  `Zona` varchar(10) NOT NULL,
  `idCosto` int(11) NOT NULL,
  PRIMARY KEY (`idZona`),
  KEY `idCosto` (`idCosto`),
  CONSTRAINT `idCosto` FOREIGN KEY (`idCosto`) REFERENCES `tbl_costo` (`idCosto`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `tbl_zonas` */

LOCK TABLES `tbl_zonas` WRITE;

insert  into `tbl_zonas`(`idZona`,`Zona`,`idCosto`) values (1,'A1',1),(2,'A2',1),(3,'B1',2),(4,'B2',2),(5,'C1',3),(6,'C2',3);

UNLOCK TABLES;

/* Procedure structure for procedure `deleteUsersXBitacXAccionDeleteUser` */

/*!50003 DROP PROCEDURE IF EXISTS  `deleteUsersXBitacXAccionDeleteUser` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteUsersXBitacXAccionDeleteUser`(
    IN p_id_usuario INT,
    IN p_sucursalNuevoUsuario VARCHAR(255),
    IN p_sucursal_sistema VARCHAR(255),
    IN p_id_usuario_sistema INT, 
    IN p_nombre_usuario_sistema VARCHAR(255), 
    IN p_perfil_usuario_sistema INT
)
BEGIN
    -- 1. Declarar una variable en donde se guardará el nombre y el perfil del usuario.
    DECLARE p_id_perfil_usuario INT;
    DECLARE p_nombre_usuario VARCHAR(255);
    
    -- 2. Con ayuda del id_usuario, recuperamos el nombre y el perfil del usuario.
    SELECT id_perfil, Nombre INTO p_id_perfil_usuario, p_nombre_usuario
    FROM tbl_usuarios
    WHERE id_usuario = p_id_usuario;
    -- 3. Insertar en la bitácora la acción del usuario que realizó la eliminación.
    INSERT INTO tbl_bit_accion_usuarios (Fecha, Sucursal, id_usuario, Nombre, id_perfil, Descripcion)
    VALUES (NOW(), p_sucursal_sistema, p_id_usuario_sistema, 
            p_nombre_usuario_sistema, p_perfil_usuario_sistema, 
            CONCAT('Usuario: ', p_id_usuario_sistema, ' Elimino a usuario: ', p_id_usuario));
    
    -- 4. Insertar en la bitácora la acción de eliminación del usuario.
    INSERT INTO tbl_bit_accion_usuarios (Fecha, Sucursal, id_usuario, Nombre, id_perfil, Descripcion)
    VALUES (NOW(), p_sucursalNuevoUsuario, p_id_usuario, p_nombre_usuario, p_id_perfil_usuario, 'Eliminado del sistema');
    -- 5. Eliminar el usuario de la tabla tbl_usuarios.
    DELETE FROM tbl_usuarios WHERE id_usuario = p_id_usuario;
END */$$
DELIMITER ;

/* Procedure structure for procedure `insertXUsuariosXBitacXAccionInsert` */

/*!50003 DROP PROCEDURE IF EXISTS  `insertXUsuariosXBitacXAccionInsert` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `insertXUsuariosXBitacXAccionInsert`(
    IN p_id_usuario INT,
    IN p_nombre_usuario VARCHAR(255),
    IN p_contrasenia VARCHAR(255),
    IN p_dtvigencia DATE,
    IN p_id_perfil INT,
    IN p_sucursalNuevoUsuario VARCHAR(255),
    
    IN p_id_usuario_sistema INT, 
    IN p_nombre_usuario_sistema VARCHAR(255), 
    IN p_perfil_usuario_sistema INT,
    IN p_sucursal_sistema VARCHAR(255)
)
BEGIN
    -- Variable para el estado del usuario
    DECLARE p_estado VARCHAR(10) DEFAULT 'activo';
    -- 1. Insertar el nuevo usuario con la contraseña encriptada usando MD5
    INSERT INTO tbl_usuarios (id_usuario, Nombre, vchPass, dtVigencia, id_perfil, estado)
    VALUES (p_id_usuario, p_nombre_usuario, MD5(p_contrasenia), p_dtvigencia, p_id_perfil, p_estado);
    
    -- 2. Insertar en la bitácora la acción de creación del nuevo usuario
    INSERT INTO tbl_bit_accion_usuarios (Fecha, Sucursal, id_usuario, Nombre, id_perfil, Descripcion)
    VALUES (NOW(), p_sucursal_sistema, p_id_usuario_sistema, 
            p_nombre_usuario_sistema, p_perfil_usuario_sistema, 
            CONCAT('Usuario: ', p_id_usuario_sistema, ' creó al usuario: ', p_id_usuario));
            
    -- 3. Insertar en la bitácora la acción del usuario que se acaba de crear
    INSERT INTO tbl_bit_accion_usuarios (Fecha, Sucursal, id_usuario, Nombre, id_perfil, Descripcion)
    VALUES (NOW(), p_sucursalNuevoUsuario, p_id_usuario, p_nombre_usuario, p_id_perfil, 'Nuevo usuario creado.');
    
END */$$
DELIMITER ;

/* Procedure structure for procedure `resetPasswXUsuariosXBitacXAccionReset` */

/*!50003 DROP PROCEDURE IF EXISTS  `resetPasswXUsuariosXBitacXAccionReset` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `resetPasswXUsuariosXBitacXAccionReset`(
    IN p_contraseniaEncriptada VARCHAR(255),
    IN p_fechaVigencia VARCHAR(255),
    IN p_id_usuario INT,
    IN p_nombre_usuario VARCHAR(255),
    IN p_sucursalNuevoUsuario VARCHAR(255),
    
    IN p_sucursal_sistema VARCHAR(255),
    IN p_id_usuario_sistema INT, 
    IN p_nombre_usuario_sistema VARCHAR(255), 
    IN p_perfil_usuario_sistema INT
)
BEGIN
    -- 1. Declarar una variable en donde se guardará el id_perfil del usuario
    DECLARE p_id_perfil_usuario INT;
    -- 2. Restablecer la contraseña del usuario en la tabla tbl_usuarios y marcarlo como "activo"
    UPDATE tbl_usuarios 
    SET vchPass = p_contraseniaEncriptada, 
        dtVigencia = p_fechaVigencia,
        activo = 'activo' -- Se marca el usuario como "activo" (texto) al restablecer la contraseña
    WHERE id_usuario = p_id_usuario;
    
    -- 3. Con ayuda del id_usuario que se restableció en el paso 2, recuperamos el id_perfil y lo almacenamos en la variable p_id_perfil_usuario
    SELECT id_perfil INTO p_id_perfil_usuario
    FROM tbl_usuarios
    WHERE id_usuario = p_id_usuario;
    
    -- 4. Insertar en la bitácora la acción del usuario que realizó el restablecimiento de contraseña
    INSERT INTO tbl_bit_accion_usuarios (Fecha, Sucursal, id_usuario, Nombre, id_perfil, Descripcion)
    VALUES (NOW(), p_sucursal_sistema, p_id_usuario_sistema, 
            p_nombre_usuario_sistema, p_perfil_usuario_sistema, 
            CONCAT('Usuario: ', p_id_usuario_sistema, ' Restableció la contraseña al usuario: ', p_id_usuario));
            
    -- 5. Insertar en la bitácora la acción del restablecimiento de la contraseña del usuario.
    INSERT INTO tbl_bit_accion_usuarios (Fecha, Sucursal, id_usuario, Nombre, id_perfil, Descripcion)
    VALUES (NOW(), p_sucursalNuevoUsuario, p_id_usuario, p_nombre_usuario, p_id_perfil_usuario, 'Restableció su contraseña y su estado ahora es activo');
    
END */$$
DELIMITER ;

/* Procedure structure for procedure `updateXUsuariosXBitacXAccionUpdate` */

/*!50003 DROP PROCEDURE IF EXISTS  `updateXUsuariosXBitacXAccionUpdate` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `updateXUsuariosXBitacXAccionUpdate`(
    IN p_id_perfil INT, 
    IN p_id_usuario INT,
    IN p_sucursalNuevoUsuario VARCHAR(255),
    IN p_sucursal_sistema VARCHAR(255),
    
    IN p_id_usuario_sistema INT, 
    IN p_nombre_usuario_sistema VARCHAR(255), 
    IN p_perfil_usuario_sistema INT
)
BEGIN
    -- 1. Declarar una variable en donde se guardará el nombre del usuario.
    DECLARE nombre_usuario VARCHAR(255);
    
    -- 2. Actualizar el perfil del usuario en la tabla tbl_usuarios.
    UPDATE tbl_usuarios 
    SET id_perfil = p_id_perfil 
    WHERE id_usuario = p_id_usuario;
    
    -- 3. Con ayuda del id_usuario que se actualizó en el paso 1, mandar a traer el Nombre del usuario y almacenarlo en la variable nombre_usuario creada en el paso 1.
    SELECT Nombre INTO nombre_usuario
    FROM tbl_usuarios
    WHERE id_usuario = p_id_usuario;
    
    -- 4. Insertar en la bitácora la acción del usuario que realizó la actualización.
    INSERT INTO tbl_bit_accion_usuarios (Fecha, Sucursal, id_usuario, Nombre, id_perfil, Descripcion)
    VALUES (NOW(), p_sucursal_sistema, p_id_usuario_sistema, 
            p_nombre_usuario_sistema, p_perfil_usuario_sistema, 
            CONCAT('Usuario: ', p_id_usuario_sistema, ' Actualizó al usuario: ', p_id_usuario));
            
    -- 5. Insertar en la bitácora la acción de la actualización del usuario.
    INSERT INTO tbl_bit_accion_usuarios (Fecha, Sucursal, id_usuario, Nombre, id_perfil, Descripcion)
    VALUES (NOW(), p_sucursalNuevoUsuario, p_id_usuario, nombre_usuario, p_id_perfil, 'Usuario actualizó datos');
    
END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
