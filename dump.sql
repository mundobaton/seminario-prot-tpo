-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: 127.0.0.1    Database: semprotdb
-- ------------------------------------------------------
-- Server version	5.7.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `dosis`
--

DROP TABLE IF EXISTS `dosis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dosis` (
  `dosis_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `aplicada` bit(1) DEFAULT NULL,
  `fecha_aplicacion_efectiva` datetime(6) DEFAULT NULL,
  `fecha_aplicacion_prevista` datetime(6) DEFAULT NULL,
  `enfermero_id` bigint(20) DEFAULT NULL,
  `item_indicacion_id` bigint(20) DEFAULT NULL,
  `paciente_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`dosis_id`),
  KEY `FKdrrp2oraac5bd1dh46avlyrql` (`enfermero_id`),
  KEY `FK47x7woktf2sylcq9rgc8wd9ox` (`item_indicacion_id`),
  KEY `FKkxm4wb9o5aaquoh2wg9k6kkqr` (`paciente_id`),
  CONSTRAINT `FK47x7woktf2sylcq9rgc8wd9ox` FOREIGN KEY (`item_indicacion_id`) REFERENCES `item_indicaciones` (`item_indicacion_id`),
  CONSTRAINT `FKdrrp2oraac5bd1dh46avlyrql` FOREIGN KEY (`enfermero_id`) REFERENCES `usuarios` (`usuario_id`),
  CONSTRAINT `FKkxm4wb9o5aaquoh2wg9k6kkqr` FOREIGN KEY (`paciente_id`) REFERENCES `pacientes` (`paciente_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dosis`
--

LOCK TABLES `dosis` WRITE;
/*!40000 ALTER TABLE `dosis` DISABLE KEYS */;
/*!40000 ALTER TABLE `dosis` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `indicaciones`
--

DROP TABLE IF EXISTS `indicaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `indicaciones` (
  `indicacion_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `diagnostico` varchar(255) DEFAULT NULL,
  `estado` int(11) DEFAULT NULL,
  `fecha_creacion` datetime(6) DEFAULT NULL,
  `fecha_recepcion` datetime(6) DEFAULT NULL,
  `fecha_validacion` datetime(6) DEFAULT NULL,
  `observaciones` varchar(255) DEFAULT NULL,
  `enfermero_id` bigint(20) DEFAULT NULL,
  `farmaceutico_id` bigint(20) DEFAULT NULL,
  `medico_id` bigint(20) DEFAULT NULL,
  `paciente_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`indicacion_id`),
  KEY `FKshto785bt5n3ofn964ouiote3` (`enfermero_id`),
  KEY `FKtdrvl23qjx816847upo98x26w` (`farmaceutico_id`),
  KEY `FKsvbc734wca46xtcgqn56amdju` (`medico_id`),
  KEY `FKknc745p6955sk4g4y82tp6fkc` (`paciente_id`),
  CONSTRAINT `FKknc745p6955sk4g4y82tp6fkc` FOREIGN KEY (`paciente_id`) REFERENCES `pacientes` (`paciente_id`),
  CONSTRAINT `FKshto785bt5n3ofn964ouiote3` FOREIGN KEY (`enfermero_id`) REFERENCES `usuarios` (`usuario_id`),
  CONSTRAINT `FKsvbc734wca46xtcgqn56amdju` FOREIGN KEY (`medico_id`) REFERENCES `usuarios` (`usuario_id`),
  CONSTRAINT `FKtdrvl23qjx816847upo98x26w` FOREIGN KEY (`farmaceutico_id`) REFERENCES `usuarios` (`usuario_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `indicaciones`
--

LOCK TABLES `indicaciones` WRITE;
/*!40000 ALTER TABLE `indicaciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `indicaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_indicaciones`
--

DROP TABLE IF EXISTS `item_indicaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_indicaciones` (
  `item_indicacion_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cantidad` int(11) DEFAULT NULL,
  `fecha_recepcion` datetime(6) DEFAULT NULL,
  `frecuencia` double DEFAULT NULL,
  `indicacion_id` bigint(20) DEFAULT NULL,
  `medicamento_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`item_indicacion_id`),
  KEY `FK6g8s2jlb087nlrri6ieeli3y2` (`indicacion_id`),
  KEY `FKnvkjpbax4mh3c4fy9og5j5tqe` (`medicamento_id`),
  CONSTRAINT `FK6g8s2jlb087nlrri6ieeli3y2` FOREIGN KEY (`indicacion_id`) REFERENCES `indicaciones` (`indicacion_id`),
  CONSTRAINT `FKnvkjpbax4mh3c4fy9og5j5tqe` FOREIGN KEY (`medicamento_id`) REFERENCES `medicamentos` (`medicamento_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_indicaciones`
--

LOCK TABLES `item_indicaciones` WRITE;
/*!40000 ALTER TABLE `item_indicaciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `item_indicaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medicamentos`
--

DROP TABLE IF EXISTS `medicamentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medicamentos` (
  `medicamento_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `stock_compra` int(11) DEFAULT NULL,
  PRIMARY KEY (`medicamento_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicamentos`
--

LOCK TABLES `medicamentos` WRITE;
/*!40000 ALTER TABLE `medicamentos` DISABLE KEYS */;
INSERT INTO `medicamentos` VALUES (1,'Mejoralito',500,500),(2,'Ibupirac',350,350),(3,'Curitas',250,250);
/*!40000 ALTER TABLE `medicamentos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pacientes`
--

DROP TABLE IF EXISTS `pacientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pacientes` (
  `paciente_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `apellido` varchar(255) DEFAULT NULL,
  `dni` varchar(255) DEFAULT NULL,
  `fecha_nacimiento` datetime(6) DEFAULT NULL,
  `internado` bit(1) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `num_afiliado` bigint(20) DEFAULT NULL,
  `obra_social` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`paciente_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pacientes`
--

LOCK TABLES `pacientes` WRITE;
/*!40000 ALTER TABLE `pacientes` DISABLE KEYS */;
INSERT INTO `pacientes` VALUES (1,'Perez','12345','1990-03-20 00:00:00.000000','','Susana',13123123,'Osde'),(2,'Gomez','432123','1985-11-04 00:00:00.000000','\0','Carlos',432234,'Osde'),(3,'Nieve','5433453','1981-01-13 00:00:00.000000','','Juan',883234,'The Watch Medical Assistant');
/*!40000 ALTER TABLE `pacientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios` (
  `usuario_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `apellido` varchar(255) DEFAULT NULL,
  `dni` bigint(20) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `estado` char(1) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `rol` int(11) DEFAULT NULL,
  PRIMARY KEY (`usuario_id`),
  UNIQUE KEY `UK_kfsp0s1tflm1cwlj8idhqsad0` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'Fulano',123312,'me@dico.com','A','Agustin','12345',1),(2,'Gomez',31231,'farma@ceutico.com','A','Jose','12345',0),(3,'Perez',123123,'enfer@mero.com','A','Claudia','qwerty',2);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-07-17 20:54:21
