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

/*Table structure for table `tbl_estado_sillas` */

DROP TABLE IF EXISTS `tbl_estado_sillas`;

CREATE TABLE `tbl_estado_sillas` (
  `idEstado` int(11) NOT NULL AUTO_INCREMENT,
  `EstadoSilla` varchar(100) NOT NULL,
  PRIMARY KEY (`idEstado`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `tbl_estado_sillas` */

LOCK TABLES `tbl_estado_sillas` WRITE;

insert  into `tbl_estado_sillas`(`idEstado`,`EstadoSilla`) values (1,'Disponible'),(2,'Separado'),(3,'Comprado');

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

insert  into `tbl_mesas`(`idMesa`,`DescMesa`,`Estatus`,`idZona`) values (1,'Mesa1','Ocupado',1),(2,'Mesa2','Disponible',1),(3,'Mesa3','Disponible',1),(4,'Mesa4','Disponible',1),(5,'Mesa5','Disponible',1),(6,'Mesa6','Disponible',1),(7,'Mesa7','Disponible',1),(8,'Mesa8','Disponible',1),(9,'Mesa9','Disponible',1),(10,'Mesa10','Disponible',2),(11,'Mesa11','Disponible',2),(12,'Mesa12','Disponible',2),(13,'Mesa13','Disponible',2),(14,'Mesa14','Disponible',2),(15,'Mesa15','Disponible',2),(16,'Mesa16','Disponible',2),(17,'Mesa17','Disponible',2),(18,'Mesa18','Disponible',2),(19,'Mesa19','Disponible',3),(20,'Mesa20','Disponible',3),(21,'Mesa21','Disponible',3),(22,'Mesa22','Disponible',3),(23,'Mesa23','Disponible',3),(24,'Mesa24','Disponible',3),(25,'Mesa25','Disponible',3),(26,'Mesa26','Disponible',3),(27,'Mesa27','Disponible',3),(28,'Mesa28','Disponible',3),(29,'Mesa29','Disponible',3),(30,'Mesa30','Disponible',3),(31,'Mesa31','Disponible',3),(32,'Mesa32','Disponible',3),(33,'Mesa33','Disponible',3),(34,'Mesa34','Disponible',3),(35,'Mesa35','Disponible',3),(36,'Mesa36','Disponible',3),(37,'Mesa37','Disponible',3),(38,'Mesa38','Disponible',3),(39,'Mesa39','Disponible',4),(40,'Mesa40','Disponible',4),(41,'Mesa41','Disponible',4),(42,'Mesa42','Disponible',4),(43,'Mesa43','Disponible',4),(44,'Mesa44','Disponible',4),(45,'Mesa45','Disponible',4),(46,'Mesa46','Disponible',4),(47,'Mesa47','Disponible',4),(48,'Mesa48','Disponible',4),(49,'Mesa49','Disponible',4),(50,'Mesa50','Disponible',4),(51,'Mesa51','Disponible',5),(52,'Mesa52','Disponible',5),(53,'Mesa53','Disponible',5),(54,'Mesa54','Disponible',5),(55,'Mesa55','Disponible',5),(56,'Mesa56','Disponible',5),(57,'Mesa57','Disponible',5),(58,'Mesa58','Disponible',5),(59,'Mesa59','Disponible',5),(60,'Mesa60','Disponible',5),(61,'Mesa61','Disponible',5),(62,'Mesa62','Disponible',5),(63,'Mesa63','Disponible',5),(64,'Mesa64','Disponible',5),(65,'Mesa65','Disponible',5),(66,'Mesa66','Disponible',5),(67,'Mesa67','Disponible',6),(68,'Mesa68','Disponible',6),(69,'Mesa69','Disponible',6),(70,'Mesa70','Disponible',6),(71,'Mesa71','Disponible',6),(72,'Mesa72','Disponible',6),(73,'Mesa73','Disponible',6),(74,'Mesa74','Disponible',6),(75,'Mesa75','Disponible',6),(76,'Mesa76','Disponible',6),(77,'Mesa77','Disponible',6),(78,'Mesa78','Disponible',6),(79,'Mesa79','Disponible',6),(80,'Mesa80','Disponible',6);

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
  `idEstado` int(11) NOT NULL,
  `idMesa` int(11) NOT NULL,
  PRIMARY KEY (`idSilla`),
  KEY `idMesa` (`idMesa`),
  KEY `idEstado` (`idEstado`),
  CONSTRAINT `idEstado` FOREIGN KEY (`idEstado`) REFERENCES `tbl_estado_sillas` (`idEstado`),
  CONSTRAINT `idMesa` FOREIGN KEY (`idMesa`) REFERENCES `tbl_mesas` (`idMesa`)
) ENGINE=InnoDB AUTO_INCREMENT=803 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `tbl_sillas` */

LOCK TABLES `tbl_sillas` WRITE;

insert  into `tbl_sillas`(`idSilla`,`vchDescripcion`,`idEstado`,`idMesa`) values (1,'Silla 1',1,1),(2,'Silla 2',1,1),(3,'Silla 3',1,1),(4,'Silla 4',1,1),(5,'Silla 5',1,1),(6,'Silla 6',1,1),(7,'Silla 7',1,1),(8,'Silla 8',1,1),(9,'Silla 9',1,1),(10,'Silla 10',1,1),(11,'Silla 11',1,2),(12,'Silla 12',1,2),(13,'Silla 13',1,2),(14,'Silla 14',2,2),(15,'Silla 15',1,2),(16,'Silla 16',1,2),(17,'Silla 17',2,2),(18,'Silla 18',3,2),(19,'Silla 19',1,2),(20,'Silla 20',1,2),(21,'Silla 21',1,3),(22,'Silla 22',1,3),(23,'Silla 23',1,3),(24,'Silla 24',1,3),(25,'Silla 25',1,3),(26,'Silla 26',1,3),(27,'Silla 27',1,3),(28,'Silla 28',1,3),(29,'Silla 29',1,3),(30,'Silla 30',1,3),(31,'Silla 31',1,4),(32,'Silla 32',1,4),(33,'Silla 33',1,4),(34,'Silla 34',1,4),(35,'Silla 35',1,4),(36,'Silla 36',1,4),(37,'Silla 37',1,4),(38,'Silla 38',1,4),(39,'Silla 39',1,4),(40,'Silla 40',1,4),(41,'Silla 41',1,5),(42,'Silla 42',1,5),(43,'Silla 43',1,5),(44,'Silla 44',1,5),(45,'Silla 45',1,5),(46,'Silla 46',1,5),(47,'Silla 47',1,5),(48,'Silla 48',1,5),(49,'Silla 49',1,5),(50,'Silla 50',1,5),(52,'Silla 51',1,6),(53,'Silla 52',1,6),(54,'Silla 53',1,6),(55,'Silla 54',1,6),(56,'Silla 55',1,6),(57,'Silla 56',1,6),(58,'Silla 57',1,6),(59,'Silla 58',1,6),(60,'Silla 59',1,6),(61,'Silla 60',1,6),(62,'Silla 61',1,7),(63,'Silla 62',1,7),(64,'Silla 63',1,7),(65,'Silla 64',1,7),(66,'Silla 65',1,7),(67,'Silla 66',1,7),(68,'Silla 67',1,7),(69,'Silla 68',1,7),(70,'Silla 69',1,7),(71,'Silla 70',1,7),(72,'Silla 71',1,8),(73,'Silla 72',1,8),(74,'Silla 73',1,8),(75,'Silla 74',1,8),(76,'Silla 75',1,8),(77,'Silla 76',1,8),(78,'Silla 77',1,8),(79,'Silla 78',1,8),(80,'Silla 79',1,8),(81,'Silla 80',1,8),(82,'Silla 81',1,9),(83,'Silla 82',1,9),(84,'Silla 83',1,9),(85,'Silla 84',1,9),(86,'Silla 85',1,9),(87,'Silla 86',1,9),(88,'Silla 87',1,9),(89,'Silla 88',1,9),(90,'Silla 89',1,9),(91,'Silla 90',1,9),(92,'Silla 91',1,10),(93,'Silla 92',1,10),(94,'Silla 93',1,10),(95,'Silla 94',1,10),(96,'Silla 95',1,10),(97,'Silla 96',1,10),(98,'Silla 97',1,10),(99,'Silla 98',1,10),(100,'Silla 99',1,10),(101,'Silla 100',1,10),(102,'Silla 101',1,11),(103,'Silla 102',1,11),(104,'Silla 103',1,11),(105,'Silla 104',1,11),(106,'Silla 105',1,11),(107,'Silla 106',1,11),(108,'Silla 107',1,11),(109,'Silla 108',1,11),(110,'Silla 109',1,11),(111,'Silla 110',1,11),(112,'Silla 111',1,12),(113,'Silla 112',1,12),(114,'Silla 113',1,12),(115,'Silla 114',1,12),(116,'Silla 115',1,12),(117,'Silla 116',1,12),(118,'Silla 117',1,12),(119,'Silla 118',1,12),(120,'Silla 119',1,12),(121,'Silla 120',1,12),(122,'Silla 121',1,13),(123,'Silla 122',1,13),(124,'Silla 123',1,13),(125,'Silla 124',1,13),(126,'Silla 125',1,13),(127,'Silla 126',1,13),(128,'Silla 127',1,13),(129,'Silla 128',1,13),(130,'Silla 129',1,13),(131,'Silla 130',1,13),(132,'Silla 131',1,14),(133,'Silla 132',1,14),(134,'Silla 133',1,14),(135,'Silla 134',1,14),(136,'Silla 135',1,14),(137,'Silla 136',1,14),(138,'Silla 137',1,14),(139,'Silla 138',1,14),(140,'Silla 139',1,14),(141,'Silla 140',1,14),(142,'Silla 141',1,15),(143,'Silla 142',1,15),(144,'Silla 143',1,15),(145,'Silla 144',1,15),(146,'Silla 145',1,15),(147,'Silla 146',1,15),(148,'Silla 147',1,15),(149,'Silla 148',1,15),(150,'Silla 149',1,15),(151,'Silla 150',1,15),(152,'Silla 151',1,16),(153,'Silla 152',1,16),(154,'Silla 153',1,16),(155,'Silla 154',1,16),(156,'Silla 155',1,16),(157,'Silla 156',1,16),(158,'Silla 157',1,16),(159,'Silla 158',1,16),(160,'Silla 159',1,16),(161,'Silla 160',1,16),(162,'Silla 161',1,17),(163,'Silla 162',1,17),(164,'Silla 163',1,17),(165,'Silla 164',1,17),(166,'Silla 165',1,17),(167,'Silla 166',1,17),(168,'Silla 167',1,17),(169,'Silla 168',1,17),(170,'Silla 169',1,17),(171,'Silla 170',1,17),(172,'Silla 171',1,18),(173,'Silla 172',1,18),(174,'Silla 173',1,18),(175,'Silla 174',1,18),(176,'Silla 175',1,18),(177,'Silla 176',1,18),(178,'Silla 177',1,18),(179,'Silla 178',1,18),(180,'Silla 179',1,18),(181,'Silla 180',1,18),(182,'Silla 181',1,19),(183,'Silla 182',1,19),(184,'Silla 183',1,19),(185,'Silla 184',1,19),(186,'Silla 185',1,19),(187,'Silla 186',1,19),(188,'Silla 187',1,19),(189,'Silla 188',1,19),(190,'Silla 189',1,19),(191,'Silla 190',1,19),(192,'Silla 191',1,20),(193,'Silla 192',1,20),(194,'Silla 193',1,20),(195,'Silla 194',1,20),(196,'Silla 195',1,20),(197,'Silla 196',1,20),(198,'Silla 197',1,20),(199,'Silla 198',1,20),(200,'Silla 199',1,20),(201,'Silla 200',1,20),(202,'Silla 201',1,21),(203,'Silla 202',1,21),(204,'Silla 203',1,21),(205,'Silla 204',1,21),(206,'Silla 205',1,21),(207,'Silla 206',1,21),(208,'Silla 207',1,21),(209,'Silla 208',1,21),(210,'Silla 209',1,21),(211,'Silla 210',1,21),(212,'Silla 211',1,22),(213,'Silla 212',1,22),(214,'Silla 213',1,22),(215,'Silla 214',1,22),(216,'Silla 215',1,22),(217,'Silla 216',1,22),(218,'Silla 217',1,22),(219,'Silla 218',1,22),(220,'Silla 219',1,22),(221,'Silla 220',1,22),(222,'Silla 221',1,23),(223,'Silla 222',1,23),(224,'Silla 223',1,23),(225,'Silla 224',1,23),(226,'Silla 225',1,23),(227,'Silla 226',1,23),(228,'Silla 227',1,23),(229,'Silla 228',1,23),(230,'Silla 229',1,23),(231,'Silla 230',1,23),(232,'Silla 231',1,24),(233,'Silla 232',1,24),(234,'Silla 233',1,24),(235,'Silla 234',1,24),(236,'Silla 235',1,24),(237,'Silla 236',1,24),(238,'Silla 237',1,24),(239,'Silla 238',1,24),(240,'Silla 239',1,24),(241,'Silla 240',1,24),(242,'Silla 241',1,25),(243,'Silla 242',1,25),(244,'Silla 243',1,25),(245,'Silla 244',1,25),(246,'Silla 245',1,25),(247,'Silla 246',1,25),(248,'Silla 247',1,25),(249,'Silla 248',1,25),(250,'Silla 249',1,25),(251,'Silla 250',1,25),(252,'Silla 251',1,26),(253,'Silla 252',1,26),(254,'Silla 253',1,26),(255,'Silla 254',1,26),(256,'Silla 255',1,26),(257,'Silla 256',1,26),(258,'Silla 257',1,26),(259,'Silla 258',1,26),(260,'Silla 259',1,26),(261,'Silla 260',1,26),(262,'Silla 261',1,27),(263,'Silla 262',1,27),(264,'Silla 263',1,27),(265,'Silla 264',1,27),(266,'Silla 265',1,27),(267,'Silla 266',1,27),(268,'Silla 267',1,27),(269,'Silla 268',1,27),(270,'Silla 269',1,27),(271,'Silla 270',1,27),(272,'Silla 271',1,28),(273,'Silla 272',1,28),(274,'Silla 273',1,28),(275,'Silla 274',1,28),(276,'Silla 275',1,28),(277,'Silla 276',1,28),(278,'Silla 277',1,28),(279,'Silla 278',1,28),(280,'Silla 279',1,28),(281,'Silla 280',1,28),(282,'Silla 281',1,29),(283,'Silla 282',1,29),(284,'Silla 283',1,29),(285,'Silla 284',1,29),(286,'Silla 285',1,29),(287,'Silla 286',1,29),(288,'Silla 287',1,29),(289,'Silla 288',1,29),(290,'Silla 289',1,29),(291,'Silla 290',1,29),(292,'Silla 291',1,30),(293,'Silla 292',1,30),(294,'Silla 293',1,30),(295,'Silla 294',1,30),(296,'Silla 295',1,30),(297,'Silla 296',1,30),(298,'Silla 297',1,30),(299,'Silla 298',1,30),(300,'Silla 299',1,30),(301,'Silla 300',1,30),(302,'Silla 301',1,31),(303,'Silla 302',1,31),(304,'Silla 303',1,31),(305,'Silla 304',1,31),(306,'Silla 305',1,31),(307,'Silla 306',1,31),(308,'Silla 307',1,31),(309,'Silla 308',1,31),(310,'Silla 309',1,31),(311,'Silla 310',1,31),(312,'Silla 311',1,32),(313,'Silla 312',1,32),(314,'Silla 313',1,32),(315,'Silla 314',1,32),(316,'Silla 315',1,32),(317,'Silla 316',1,32),(318,'Silla 317',1,32),(319,'Silla 318',1,32),(320,'Silla 319',1,32),(321,'Silla 320',1,32),(322,'Silla 321',1,33),(323,'Silla 322',1,33),(324,'Silla 323',1,33),(325,'Silla 324',1,33),(326,'Silla 325',1,33),(327,'Silla 326',1,33),(328,'Silla 327',1,33),(329,'Silla 328',1,33),(330,'Silla 329',1,33),(331,'Silla 330',1,33),(332,'Silla 331',1,34),(333,'Silla 332',1,34),(334,'Silla 333',1,34),(335,'Silla 334',1,34),(336,'Silla 335',1,34),(337,'Silla 336',1,34),(338,'Silla 337',1,34),(339,'Silla 338',1,34),(340,'Silla 339',1,34),(341,'Silla 340',1,34),(342,'Silla 341',1,35),(343,'Silla 342',1,35),(344,'Silla 343',1,35),(345,'Silla 344',1,35),(346,'Silla 345',1,35),(347,'Silla 346',1,35),(348,'Silla 347',1,35),(349,'Silla 348',1,35),(350,'Silla 349',1,35),(351,'Silla 350',1,35),(352,'Silla 351',1,36),(353,'Silla 352',1,36),(354,'Silla 353',1,36),(355,'Silla 354',1,36),(356,'Silla 355',1,36),(357,'Silla 356',1,36),(358,'Silla 357',1,36),(359,'Silla 358',1,36),(360,'Silla 359',1,36),(361,'Silla 360',1,36),(362,'Silla 361',1,37),(363,'Silla 362',1,37),(364,'Silla 363',1,37),(365,'Silla 364',1,37),(366,'Silla 365',1,37),(367,'Silla 366',1,37),(368,'Silla 367',1,37),(369,'Silla 368',1,37),(370,'Silla 369',1,37),(371,'Silla 370',1,37),(372,'Silla 371',1,38),(373,'Silla 372',1,38),(374,'Silla 373',1,38),(375,'Silla 374',1,38),(376,'Silla 375',1,38),(377,'Silla 376',1,38),(378,'Silla 377',1,38),(379,'Silla 378',1,38),(380,'Silla 379',1,38),(381,'Silla 380',1,38),(382,'Silla 381',1,39),(383,'Silla 382',1,39),(384,'Silla 383',1,39),(385,'Silla 384',1,39),(386,'Silla 385',1,39),(387,'Silla 386',1,39),(388,'Silla 387',1,39),(389,'Silla 388',1,39),(390,'Silla 389',1,39),(391,'Silla 390',1,39),(392,'Silla 391',1,40),(393,'Silla 392',1,40),(394,'Silla 393',1,40),(395,'Silla 394',1,40),(396,'Silla 395',1,40),(397,'Silla 396',1,40),(398,'Silla 397',1,40),(399,'Silla 398',1,40),(400,'Silla 399',1,40),(401,'Silla 400',1,40),(402,'Silla 401',1,41),(403,'Silla 402',1,41),(404,'Silla 403',1,41),(405,'Silla 404',1,41),(406,'Silla 405',1,41),(407,'Silla 406',1,41),(408,'Silla 407',1,41),(409,'Silla 408',1,41),(410,'Silla 409',1,41),(411,'Silla 410',1,41),(412,'Silla 411',1,42),(413,'Silla 412',1,42),(414,'Silla 413',1,42),(415,'Silla 414',1,42),(416,'Silla 415',1,42),(417,'Silla 416',1,42),(418,'Silla 417',1,42),(419,'Silla 418',1,42),(420,'Silla 419',1,42),(421,'Silla 420',1,42),(422,'Silla 421',1,43),(423,'Silla 422',1,43),(424,'Silla 423',1,43),(425,'Silla 424',1,43),(426,'Silla 425',1,43),(427,'Silla 426',1,43),(428,'Silla 427',1,43),(429,'Silla 428',1,43),(430,'Silla 429',1,43),(431,'Silla 430',1,43),(432,'Silla 431',1,44),(433,'Silla 432',1,44),(434,'Silla 433',1,44),(435,'Silla 434',1,44),(436,'Silla 435',1,44),(437,'Silla 436',1,44),(438,'Silla 437',1,44),(439,'Silla 438',1,44),(440,'Silla 439',1,44),(441,'Silla 440',1,44),(442,'Silla 441',1,45),(443,'Silla 442',1,45),(444,'Silla 443',1,45),(445,'Silla 444',1,45),(446,'Silla 445',1,45),(447,'Silla 446',1,45),(448,'Silla 447',1,45),(449,'Silla 448',1,45),(450,'Silla 449',1,45),(451,'Silla 450',1,45),(452,'Silla 451',1,46),(453,'Silla 452',1,46),(454,'Silla 453',1,46),(455,'Silla 454',1,46),(456,'Silla 455',1,46),(457,'Silla 456',1,46),(458,'Silla 457',1,46),(459,'Silla 458',1,46),(460,'Silla 459',1,46),(461,'Silla 460',1,46),(462,'Silla 461',1,47),(463,'Silla 462',1,47),(464,'Silla 463',1,47),(465,'Silla 464',1,47),(466,'Silla 465',1,47),(467,'Silla 466',1,47),(468,'Silla 467',1,47),(469,'Silla 468',1,47),(470,'Silla 469',1,47),(471,'Silla 470',1,47),(472,'Silla 471',1,48),(473,'Silla 472',1,48),(474,'Silla 473',1,48),(475,'Silla 474',1,48),(476,'Silla 475',1,48),(477,'Silla 476',1,48),(478,'Silla 477',1,48),(479,'Silla 478',1,48),(480,'Silla 479',1,48),(481,'Silla 480',1,48),(482,'Silla 481',1,49),(483,'Silla 482',1,49),(484,'Silla 483',1,49),(485,'Silla 484',1,49),(486,'Silla 485',1,49),(487,'Silla 486',1,49),(488,'Silla 487',1,49),(489,'Silla 488',1,49),(490,'Silla 489',1,49),(491,'Silla 490',1,49),(492,'Silla 491',1,50),(493,'Silla 492',1,50),(494,'Silla 493',1,50),(495,'Silla 494',1,50),(496,'Silla 495',1,50),(497,'Silla 496',1,50),(498,'Silla 497',1,50),(499,'Silla 498',1,50),(500,'Silla 499',1,50),(501,'Silla 500',1,50),(502,'Silla 501',1,51),(503,'Silla 502',1,51),(504,'Silla 503',1,51),(505,'Silla 504',1,51),(506,'Silla 505',1,51),(507,'Silla 506',1,51),(508,'Silla 507',1,51),(509,'Silla 508',1,51),(510,'Silla 509',1,51),(511,'Silla 510',1,51),(512,'Silla 511',1,52),(513,'Silla 512',1,52),(514,'Silla 513',1,52),(515,'Silla 514',1,52),(516,'Silla 515',1,52),(517,'Silla 516',1,52),(518,'Silla 517',1,52),(519,'Silla 518',1,52),(520,'Silla 519',1,52),(521,'Silla 520',1,52),(522,'Silla 521',1,53),(523,'Silla 522',1,53),(524,'Silla 523',1,53),(525,'Silla 524',1,53),(526,'Silla 525',1,53),(527,'Silla 526',1,53),(528,'Silla 527',1,53),(529,'Silla 528',1,53),(530,'Silla 529',1,53),(531,'Silla 530',1,53),(532,'Silla 531',1,54),(533,'Silla 532',1,54),(534,'Silla 533',1,54),(535,'Silla 534',1,54),(536,'Silla 535',1,54),(537,'Silla 536',1,54),(538,'Silla 537',1,54),(539,'Silla 538',1,54),(540,'Silla 539',1,54),(541,'Silla 540',1,54),(542,'Silla 541',1,55),(543,'Silla 542',1,55),(544,'Silla 543',1,55),(545,'Silla 544',1,55),(546,'Silla 545',1,55),(547,'Silla 546',1,55),(548,'Silla 547',1,55),(549,'Silla 548',1,55),(550,'Silla 549',1,55),(551,'Silla 550',1,55),(552,'Silla 551',1,56),(553,'Silla 552',1,56),(554,'Silla 553',1,56),(555,'Silla 554',1,56),(556,'Silla 555',1,56),(557,'Silla 556',1,56),(558,'Silla 557',1,56),(559,'Silla 558',1,56),(560,'Silla 559',1,56),(561,'Silla 560',1,56),(562,'Silla 561',1,57),(563,'Silla 562',1,57),(564,'Silla 563',1,57),(565,'Silla 564',1,57),(566,'Silla 565',1,57),(567,'Silla 566',1,57),(568,'Silla 567',1,57),(569,'Silla 568',1,57),(570,'Silla 569',1,57),(571,'Silla 570',1,57),(572,'Silla 571',1,58),(573,'Silla 572',1,58),(574,'Silla 573',1,58),(575,'Silla 574',1,58),(576,'Silla 575',1,58),(577,'Silla 576',1,58),(578,'Silla 577',1,58),(579,'Silla 578',1,58),(580,'Silla 579',1,58),(581,'Silla 580',1,58),(582,'Silla 581',1,59),(583,'Silla 582',1,59),(584,'Silla 583',1,59),(585,'Silla 584',1,59),(586,'Silla 585',1,59),(587,'Silla 586',1,59),(588,'Silla 587',1,59),(589,'Silla 588',1,59),(590,'Silla 589',1,59),(591,'Silla 590',1,59),(592,'Silla 591',1,60),(593,'Silla 592',1,60),(594,'Silla 593',1,60),(595,'Silla 594',1,60),(596,'Silla 595',1,60),(597,'Silla 596',1,60),(598,'Silla 597',1,60),(599,'Silla 598',1,60),(600,'Silla 599',1,60),(601,'Silla 600',1,60),(602,'Silla 601',1,61),(603,'Silla 602',1,61),(604,'Silla 603',1,61),(605,'Silla 604',1,61),(606,'Silla 605',1,61),(607,'Silla 606',1,61),(608,'Silla 607',1,61),(609,'Silla 608',1,61),(610,'Silla 609',1,61),(611,'Silla 610',1,61),(612,'Silla 611',1,62),(613,'Silla 612',1,62),(614,'Silla 613',1,62),(615,'Silla 614',1,62),(616,'Silla 615',1,62),(617,'Silla 616',1,62),(618,'Silla 617',1,62),(619,'Silla 618',1,62),(620,'Silla 619',1,62),(621,'Silla 620',1,62),(622,'Silla 621',1,63),(623,'Silla 622',1,63),(624,'Silla 623',1,63),(625,'Silla 624',1,63),(626,'Silla 625',1,63),(627,'Silla 626',1,63),(628,'Silla 627',1,63),(629,'Silla 628',1,63),(630,'Silla 629',1,63),(631,'Silla 630',1,63),(632,'Silla 631',1,64),(633,'Silla 632',1,64),(634,'Silla 633',1,64),(635,'Silla 634',1,64),(636,'Silla 635',1,64),(637,'Silla 636',1,64),(638,'Silla 637',1,64),(639,'Silla 638',1,64),(640,'Silla 639',1,64),(641,'Silla 640',1,64),(642,'Silla 641',1,65),(643,'Silla 642',1,65),(644,'Silla 643',1,65),(645,'Silla 644',1,65),(646,'Silla 645',1,65),(647,'Silla 646',1,65),(648,'Silla 647',1,65),(649,'Silla 648',1,65),(650,'Silla 649',1,65),(651,'Silla 650',1,65),(652,'Silla 651',1,66),(653,'Silla 652',1,66),(654,'Silla 653',1,66),(655,'Silla 654',1,66),(656,'Silla 655',1,66),(657,'Silla 656',1,66),(658,'Silla 657',1,66),(659,'Silla 658',1,66),(660,'Silla 659',1,66),(661,'Silla 660',1,66),(662,'Silla 661',1,67),(663,'Silla 662',1,67),(664,'Silla 663',1,67),(665,'Silla 664',1,67),(666,'Silla 665',1,67),(667,'Silla 666',1,67),(668,'Silla 667',1,67),(669,'Silla 668',1,67),(670,'Silla 669',1,67),(671,'Silla 670',1,67),(672,'Silla 671',1,68),(673,'Silla 672',1,68),(674,'Silla 673',1,68),(675,'Silla 674',1,68),(676,'Silla 675',1,68),(677,'Silla 676',1,68),(678,'Silla 677',1,68),(679,'Silla 678',1,68),(680,'Silla 679',1,68),(681,'Silla 680',1,68),(682,'Silla 681',1,69),(683,'Silla 682',1,69),(684,'Silla 683',1,69),(685,'Silla 684',1,69),(686,'Silla 685',1,69),(687,'Silla 686',1,69),(688,'Silla 687',1,69),(689,'Silla 688',1,69),(690,'Silla 689',1,69),(691,'Silla 690',1,69),(692,'Silla 691',1,70),(693,'Silla 692',1,70),(694,'Silla 693',1,70),(695,'Silla 694',1,70),(696,'Silla 695',1,70),(697,'Silla 696',1,70),(698,'Silla 697',1,70),(699,'Silla 698',1,70),(700,'Silla 699',1,70),(701,'Silla 700',1,70),(702,'Silla 701',1,71),(703,'Silla 702',1,71),(704,'Silla 703',1,71),(705,'Silla 704',1,71),(706,'Silla 705',1,71),(707,'Silla 706',1,71),(708,'Silla 707',1,71),(709,'Silla 708',1,71),(710,'Silla 709',1,71),(711,'Silla 710',1,71),(712,'Silla 711',1,72),(713,'Silla 712',1,72),(714,'Silla 713',1,72),(715,'Silla 714',1,72),(716,'Silla 715',1,72),(717,'Silla 716',1,72),(718,'Silla 717',1,72),(719,'Silla 718',1,72),(720,'Silla 719',1,72),(721,'Silla 720',1,72),(722,'Silla 721',1,73),(723,'Silla 722',1,73),(724,'Silla 723',1,73),(725,'Silla 724',1,73),(726,'Silla 725',1,73),(727,'Silla 726',1,73),(728,'Silla 727',1,73),(729,'Silla 728',1,73),(730,'Silla 729',1,73),(731,'Silla 730',1,73),(732,'Silla 731',1,74),(733,'Silla 732',1,74),(734,'Silla 733',1,74),(735,'Silla 734',1,74),(736,'Silla 735',1,74),(737,'Silla 736',1,74),(738,'Silla 737',1,74),(739,'Silla 738',1,74),(740,'Silla 739',1,74),(741,'Silla 740',1,74),(742,'Silla 741',1,75),(743,'Silla 742',1,75),(744,'Silla 743',1,75),(745,'Silla 744',1,75),(746,'Silla 745',1,75),(747,'Silla 746',1,75),(748,'Silla 747',1,75),(749,'Silla 748',1,75),(750,'Silla 749',1,75),(751,'Silla 750',1,75),(752,'Silla 751',1,76),(753,'Silla 752',1,76),(754,'Silla 753',1,76),(755,'Silla 754',1,76),(756,'Silla 755',1,76),(757,'Silla 756',1,76),(758,'Silla 757',1,76),(759,'Silla 758',1,76),(760,'Silla 759',1,76),(761,'Silla 760',1,76),(762,'Silla 761',1,77),(763,'Silla 762',1,77),(764,'Silla 763',1,77),(765,'Silla 764',1,77),(766,'Silla 765',1,77),(767,'Silla 766',1,77),(768,'Silla 767',1,77),(769,'Silla 768',1,77),(770,'Silla 769',1,77),(771,'Silla 770',1,77),(772,'Silla 771',1,78),(773,'Silla 772',1,78),(774,'Silla 773',1,78),(775,'Silla 774',1,78),(776,'Silla 775',1,78),(777,'Silla 776',1,78),(778,'Silla 777',1,78),(779,'Silla 778',1,78),(780,'Silla 779',1,78),(781,'Silla 780',1,78),(782,'Silla 781',1,79),(783,'Silla 782',1,79),(784,'Silla 783',1,79),(785,'Silla 784',1,79),(786,'Silla 785',1,79),(787,'Silla 786',1,79),(788,'Silla 787',1,79),(789,'Silla 788',1,79),(790,'Silla 789',1,79),(791,'Silla 790',1,79),(792,'Silla 791',1,80),(793,'Silla 792',1,80),(794,'Silla 793',1,80),(795,'Silla 794',1,80),(796,'Silla 795',1,80),(797,'Silla 796',1,80),(798,'Silla 797',1,80),(799,'Silla 798',1,80),(800,'Silla 799',1,80),(801,'Silla 800',1,80);

UNLOCK TABLES;

/*Table structure for table `tbl_usuarios` */

DROP TABLE IF EXISTS `tbl_usuarios`;

CREATE TABLE `tbl_usuarios` (
  `id_usuario` int(11) NOT NULL,
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `tbl_usuarios` */

LOCK TABLES `tbl_usuarios` WRITE;

insert  into `tbl_usuarios`(`id_usuario`,`Nombre`,`APaterno`,`AMaterno`,`vchPass`,`vchSucursal`,`dtVigencia`,`id_perfil`) values (12,'Eduardo','Hernandez','Hernandez','f83eb54af143e1c220d48cbff2675a4e','Suc-Indepe','2025-02-01',2),(539,'Eduardo','Dominguez','Librado','8d0b67f36735af8508e2d72d7aef96b9','Suc-Madero','2025-02-01',4),(540,'Perla','Rivera','Perez','a5258ae149a0acfbc9295dfa28a63cd6','Suc-SC','2025-02-01',1),(541,'Rogelio','Hernandez','Hernandez','f35e1564a7ae27439f47569bd9fa4d36','cambio','2025-01-21',4),(542,'asdasd','jdbi','kbkhb','8d0b67f36735af8508e2d72d7aef96b9','cambio','2025-01-21',4),(543,'dfghdbfjhj','jhbjgvb','hgvhgvhv','8d0b67f36735af8508e2d72d7aef96b9','cambio','2025-01-21',1),(544,'jsfjsh','uvieru','pokpwoekv','8d0b67f36735af8508e2d72d7aef96b9','cambio','2025-01-21',1),(600,'Usuario','Usuario','Usuario','8d0b67f36735af8508e2d72d7aef96b9','cambio','2025-01-21',1);

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
