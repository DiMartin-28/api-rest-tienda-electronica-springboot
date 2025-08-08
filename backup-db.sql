-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: localhost    Database: proy-fin-spring-boot_db
-- ------------------------------------------------------
-- Server version	8.0.42

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes` (
  `id_cliente` bigint NOT NULL AUTO_INCREMENT,
  `apellido` varchar(255) COLLATE utf8mb3_spanish_ci NOT NULL,
  `dni` varchar(255) COLLATE utf8mb3_spanish_ci NOT NULL,
  `nombre` varchar(255) COLLATE utf8mb3_spanish_ci NOT NULL,
  PRIMARY KEY (`id_cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (1,'valentino','28542568','salva'),(3,'Mineiro','130525565','Tulio'),(4,'valentino','28542111','Alejandro'),(5,'cingolani','30555784','matias'),(6,'herrera','31587416','walter'),(7,'farias','33587416','omar');
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productos` (
  `cantidad_disponible` int NOT NULL,
  `costo` double NOT NULL,
  `codigo_producto` bigint NOT NULL AUTO_INCREMENT,
  `marca` varchar(255) COLLATE utf8mb3_spanish_ci NOT NULL,
  `nombre` varchar(255) COLLATE utf8mb3_spanish_ci NOT NULL,
  PRIMARY KEY (`codigo_producto`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES (12,300000,2,'TLC','smart tv 40 pulgadas'),(15,80000,3,'samsung','monitor led 22 pulgadas'),(15,80000,4,'motorola','celular 3gb ram');
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ventas`
--

DROP TABLE IF EXISTS `ventas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ventas` (
  `fecha_venta` date DEFAULT NULL,
  `total` double NOT NULL,
  `codigo_venta` bigint NOT NULL AUTO_INCREMENT,
  `un_cliente_id_cliente` bigint NOT NULL,
  PRIMARY KEY (`codigo_venta`),
  KEY `FKm99c9fh8p26ql10cbd8c166o4` (`un_cliente_id_cliente`),
  CONSTRAINT `FKm99c9fh8p26ql10cbd8c166o4` FOREIGN KEY (`un_cliente_id_cliente`) REFERENCES `clientes` (`id_cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ventas`
--

LOCK TABLES `ventas` WRITE;
/*!40000 ALTER TABLE `ventas` DISABLE KEYS */;
INSERT INTO `ventas` VALUES ('2025-08-05',80000,1,1),('2025-08-04',900000,2,1),('2025-08-04',300000,3,1),('2025-08-04',160000,4,1),('2025-08-04',160000,5,1),('2025-08-04',80000,8,4),('2025-08-04',300000,10,3),('2025-08-05',0,11,3),('2025-08-05',600000,12,3),('2025-08-05',840000,13,3),('2025-08-05',840000,14,4),('2025-08-05',540000,15,4),('2025-08-05',240000,16,4),('2025-08-05',80000,17,4),('2025-08-06',300000,18,4),('2025-08-06',300000,19,4),('2025-08-06',300000,20,4),('2025-08-06',1500000,21,4),('2025-08-07',600000,22,4),('2025-08-07',600000,23,4);
/*!40000 ALTER TABLE `ventas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ventas_productos`
--

DROP TABLE IF EXISTS `ventas_productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ventas_productos` (
  `codigo_producto` bigint NOT NULL,
  `codigo_venta` bigint NOT NULL,
  KEY `FKsle9gdjwd9pxrioqlgiqyp82x` (`codigo_producto`),
  KEY `FKlqlcnbtq3u4vct1h0ntuluc00` (`codigo_venta`),
  CONSTRAINT `FKlqlcnbtq3u4vct1h0ntuluc00` FOREIGN KEY (`codigo_venta`) REFERENCES `ventas` (`codigo_venta`),
  CONSTRAINT `FKsle9gdjwd9pxrioqlgiqyp82x` FOREIGN KEY (`codigo_producto`) REFERENCES `productos` (`codigo_producto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ventas_productos`
--

LOCK TABLES `ventas_productos` WRITE;
/*!40000 ALTER TABLE `ventas_productos` DISABLE KEYS */;
INSERT INTO `ventas_productos` VALUES (2,2),(2,3),(2,3),(3,4),(3,4),(3,5),(3,5),(3,1),(3,8),(2,10),(2,10),(2,11),(2,11),(2,12),(2,12),(2,13),(2,13),(3,13),(3,13),(3,13),(2,14),(2,14),(3,14),(3,14),(3,14),(2,15),(2,15),(3,15),(3,15),(3,15),(2,16),(2,16),(3,16),(3,16),(3,16),(2,17),(2,17),(3,17),(3,17),(3,17),(2,18),(2,19),(2,20),(2,21),(2,22),(2,23);
/*!40000 ALTER TABLE `ventas_productos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-08-07  2:07:33
