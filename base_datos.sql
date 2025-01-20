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
  CONSTRAINT `id_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `tbl_usuarios` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `tbl_boletos` */

LOCK TABLES `tbl_boletos` WRITE;

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

/*Table structure for table `tbl_mesas` */

DROP TABLE IF EXISTS `tbl_mesas`;

CREATE TABLE `tbl_mesas` (
  `idMesa` int(11) NOT NULL AUTO_INCREMENT,
  `DescMesa` varchar(100) NOT NULL,
  `Estatus` varchar(100) NOT NULL,
  `idZona` int(11) NOT NULL,
  PRIMARY KEY (`idMesa`),
  KEY `idEstadoMesa` (`Estatus`),
  KEY `idZona` (`idZona`),
  CONSTRAINT `idZona` FOREIGN KEY (`idZona`) REFERENCES `tbl_zonas` (`idZona`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `tbl_mesas` */

LOCK TABLES `tbl_mesas` WRITE;

insert  into `tbl_mesas`(`idMesa`,`DescMesa`,`Estatus`,`idZona`) values (1,'Mesa1','Disponible',1),(2,'Mesa2','Disponible',1),(3,'Mesa3','Disponible',1),(4,'Mesa4','Disponible',1),(5,'Mesa5','Disponible',1),(6,'Mesa6','Disponible',1),(7,'Mesa7','Disponible',1),(8,'Mesa8','Disponible',1),(9,'Mesa9','Disponible',1),(10,'Mesa10','Disponible',2),(11,'Mesa11','Disponible',2),(12,'Mesa12','Disponible',2),(13,'Mesa13','Disponible',2),(14,'Mesa14','Disponible',2),(15,'Mesa15','Disponible',2),(16,'Mesa16','Disponible',2),(17,'Mesa17','Disponible',2),(18,'Mesa18','Disponible',2),(19,'Mesa19','Disponible',3),(20,'Mesa20','Disponible',3),(21,'Mesa21','Disponible',3),(22,'Mesa22','Disponible',3),(23,'Mesa23','Disponible',3),(24,'Mesa24','Disponible',3),(25,'Mesa25','Disponible',3),(26,'Mesa26','Disponible',3),(27,'Mesa27','Disponible',3),(28,'Mesa28','Disponible',3),(29,'Mesa29','Disponible',3),(30,'Mesa30','Disponible',3),(31,'Mesa31','Disponible',3),(32,'Mesa32','Disponible',3),(33,'Mesa33','Disponible',3),(34,'Mesa34','Disponible',3),(35,'Mesa35','Disponible',3),(36,'Mesa36','Disponible',3),(37,'Mesa37','Disponible',3),(38,'Mesa38','Disponible',3),(39,'Mesa39','Disponible',4),(40,'Mesa40','Disponible',4),(41,'Mesa41','Disponible',4),(42,'Mesa42','Disponible',4),(43,'Mesa43','Disponible',4),(44,'Mesa44','Disponible',4),(45,'Mesa45','Disponible',4),(46,'Mesa46','Disponible',4),(47,'Mesa47','Disponible',4),(48,'Mesa48','Disponible',4),(49,'Mesa49','Disponible',4),(50,'Mesa50','Disponible',4),(51,'Mesa51','Disponible',5),(52,'Mesa52','Disponible',5),(53,'Mesa53','Disponible',5),(54,'Mesa54','Disponible',5),(55,'Mesa55','Disponible',5),(56,'Mesa56','Disponible',5),(57,'Mesa57','Disponible',5),(58,'Mesa58','Disponible',5),(59,'Mesa59','Disponible',5),(60,'Mesa60','Disponible',5),(61,'Mesa61','Disponible',5),(62,'Mesa62','Disponible',5),(63,'Mesa63','Disponible',5),(64,'Mesa64','Disponible',5),(65,'Mesa65','Disponible',5),(66,'Mesa66','Disponible',5),(67,'Mesa67','Disponible',6),(68,'Mesa68','Disponible',6),(69,'Mesa69','Disponible',6),(70,'Mesa70','Disponible',6),(71,'Mesa71','Disponible',6),(72,'Mesa72','Disponible',6),(73,'Mesa73','Disponible',6),(74,'Mesa74','Disponible',6),(75,'Mesa75','Disponible',6),(76,'Mesa76','Disponible',6),(77,'Mesa77','Disponible',6),(78,'Mesa78','Disponible',6),(79,'Mesa79','Disponible',6),(80,'Mesa80','Disponible',6);

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

/*Table structure for table `tbl_sillas` */

DROP TABLE IF EXISTS `tbl_sillas`;

CREATE TABLE `tbl_sillas` (
  `idSilla` int(11) NOT NULL AUTO_INCREMENT,
  `vchDescripcion` varchar(100) NOT NULL,
  `vchEstado` varchar(100) NOT NULL,
  `idMesa` int(11) NOT NULL,
  PRIMARY KEY (`idSilla`),
  KEY `idMesa` (`idMesa`),
  CONSTRAINT `idMesa` FOREIGN KEY (`idMesa`) REFERENCES `tbl_mesas` (`idMesa`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `tbl_sillas` */

LOCK TABLES `tbl_sillas` WRITE;

insert  into `tbl_sillas`(`idSilla`,`vchDescripcion`,`vchEstado`,`idMesa`) values (1,'Silla 1','Disponible',1),(3,'Silla 2','Disponible',1),(4,'Silla 3','Disponible',1),(5,'Silla 4','Disponible',1),(6,'Silla 5','Disponible',1),(7,'Silla 6','Disponible',1),(8,'Silla 7','Disponible',1),(9,'Silla 8','Disponible',1),(10,'Silla 9','Disponible',1),(11,'Silla 10','Disponible',1),(12,'Silla 11','Disponible',2),(13,'Silla 12','Disponible',2);

UNLOCK TABLES;

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

LOCK TABLES `tbl_usuarios` WRITE;

insert  into `tbl_usuarios`(`id_usuario`,`Nombre`,`APaterno`,`AMaterno`,`vchPass`,`vchSucursal`,`dtVigencia`,`id_perfil`) values (12,'Eduardo','Hernandez','Hernandez','f83eb54af143e1c220d48cbff2675a4e','Suc-Indepe','2025-02-01',2),(539,'Eduardo','Dominguez','Librado','8d0b67f36735af8508e2d72d7aef96b9','Suc-Madero','2025-02-01',4),(540,'Perla','Rivera','Perez','a5258ae149a0acfbc9295dfa28a63cd6','Suc-SC','2025-02-01',1);

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

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
