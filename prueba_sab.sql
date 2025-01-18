/*
SQLyog Ultimate v11.11 (64 bit)
MySQL - 5.5.5-10.4.32-MariaDB : Database - prueba_sab
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`prueba_sab` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `prueba_sab`;

/*Table structure for table `tbl_boletos` */

DROP TABLE IF EXISTS `tbl_boletos`;

CREATE TABLE `tbl_boletos` (
  `Folio` int(11) NOT NULL AUTO_INCREMENT,
  `NumSocioOGS` varchar(100) NOT NULL,
  `Nombre` varchar(100) NOT NULL,
  `Sucursal` varchar(100) NOT NULL,
  `Invitado` int(11) NOT NULL,
  `Telefono` varchar(50) NOT NULL,
  `Correo` varchar(100) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `Zona` varchar(50) NOT NULL,
  `Mesa` varchar(50) NOT NULL,
  `Silla` varchar(50) NOT NULL,
  `Costo` float NOT NULL,
  `idEstatus` int(11) NOT NULL,
  `Fecha` date NOT NULL,
  PRIMARY KEY (`Folio`),
  KEY `idEstatus` (`idEstatus`),
  KEY `id_usuario` (`id_usuario`),
  CONSTRAINT `idEstatus` FOREIGN KEY (`idEstatus`) REFERENCES `tbl_estatus` (`idEstatus`),
  CONSTRAINT `id_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `tbl_usuarios` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `tbl_boletos` */

/*Table structure for table `tbl_estatus` */

DROP TABLE IF EXISTS `tbl_estatus`;

CREATE TABLE `tbl_estatus` (
  `idEstatus` int(11) NOT NULL AUTO_INCREMENT,
  `vchEstado` varchar(100) NOT NULL,
  PRIMARY KEY (`idEstatus`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `tbl_estatus` */

insert  into `tbl_estatus`(`idEstatus`,`vchEstado`) values (1,'Pagado'),(2,'Separado');

/*Table structure for table `tbl_mesas` */

DROP TABLE IF EXISTS `tbl_mesas`;

CREATE TABLE `tbl_mesas` (
  `idMesa` int(11) NOT NULL AUTO_INCREMENT,
  `DescMesa` varchar(100) NOT NULL,
  `Estatus` varchar(100) NOT NULL,
  PRIMARY KEY (`idMesa`),
  KEY `idEstadoMesa` (`Estatus`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `tbl_mesas` */

insert  into `tbl_mesas`(`idMesa`,`DescMesa`,`Estatus`) values (1,'Mesa1','Disponible'),(2,'Mesa2','Disponible'),(3,'Mesa3','Disponible'),(4,'Mesa4','Ocupado'),(5,'Mesa5','Ocupado'),(6,'Mesa6','Disponible'),(7,'Mesa7','Ocupado'),(8,'Mesa8','Ocupado'),(9,'Mesa9','Disponible'),(10,'Mesa10','Ocupado');

/*Table structure for table `tbl_perfil` */

DROP TABLE IF EXISTS `tbl_perfil`;

CREATE TABLE `tbl_perfil` (
  `id_perfil` int(11) NOT NULL AUTO_INCREMENT,
  `tipo_perfil` varchar(100) NOT NULL,
  PRIMARY KEY (`id_perfil`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `tbl_perfil` */

insert  into `tbl_perfil`(`id_perfil`,`tipo_perfil`) values (1,'Sistemas'),(2,'Operaciones'),(3,'Gerente'),(4,'Cajero');

/*Table structure for table `tbl_usuarios` */

DROP TABLE IF EXISTS `tbl_usuarios`;

CREATE TABLE `tbl_usuarios` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(100) NOT NULL,
  `APaterno` varchar(100) NOT NULL,
  `AMaterno` varchar(100) NOT NULL,
  `vchPass` varchar(100) NOT NULL,
  `vchSucursal` varchar(100) NOT NULL,
  `dtVigencia` date NOT NULL,
  `id_perfil` int(11) NOT NULL,
  PRIMARY KEY (`id_usuario`),
  KEY `id_perfil` (`id_perfil`),
  CONSTRAINT `id_perfil` FOREIGN KEY (`id_perfil`) REFERENCES `tbl_perfil` (`id_perfil`)
) ENGINE=InnoDB AUTO_INCREMENT=541 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `tbl_usuarios` */

insert  into `tbl_usuarios`(`id_usuario`,`Nombre`,`APaterno`,`AMaterno`,`vchPass`,`vchSucursal`,`dtVigencia`,`id_perfil`) values (12,'Eduardo','Hernandez','Hernandez','f83eb54af143e1c220d48cbff2675a4e','Suc-Indepe','2025-02-01',2),(539,'Eduardo','Dominguez','Librado','8d0b67f36735af8508e2d72d7aef96b9','Suc-Madero','2025-02-01',4),(540,'Perla','Rivera','Perez','a5258ae149a0acfbc9295dfa28a63cd6','Suc-SC','2025-02-01',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;