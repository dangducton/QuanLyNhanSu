-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: quanlynhansu
-- ------------------------------------------------------
-- Server version	8.0.20

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
-- Table structure for table `dm_chitietthuongngayle`
--

DROP TABLE IF EXISTS `dm_chitietthuongngayle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dm_chitietthuongngayle` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ghichu` varchar(255) DEFAULT NULL,
  `mucthuong` float NOT NULL,
  `status` int NOT NULL,
  `id_chucdanh` int NOT NULL,
  `id_thuongngayle` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKg0rhoyoliew38esl58sdktlhl` (`id_chucdanh`),
  KEY `FKms6y7epy5yowheupn4kbt8leb` (`id_thuongngayle`),
  CONSTRAINT `FKg0rhoyoliew38esl58sdktlhl` FOREIGN KEY (`id_chucdanh`) REFERENCES `dm_chucdanh` (`id`),
  CONSTRAINT `FKms6y7epy5yowheupn4kbt8leb` FOREIGN KEY (`id_thuongngayle`) REFERENCES `dm_thuongngayle` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dm_chitietthuongngayle`
--

LOCK TABLES `dm_chitietthuongngayle` WRITE;
/*!40000 ALTER TABLE `dm_chitietthuongngayle` DISABLE KEYS */;
INSERT INTO `dm_chitietthuongngayle` VALUES (1,'',1,1,1,1),(2,'',1.5,1,2,1),(3,'',2,1,3,1),(4,'',2.5,1,4,1),(5,'',3,1,5,1),(6,'',3.5,1,6,1),(7,'',1,1,1,2),(8,'',1.5,1,2,2),(9,'',2,1,3,2),(10,'',2.5,1,4,2),(11,'',3,1,5,2),(12,'',3.5,1,6,2),(13,'',1,1,1,3),(14,'',1.5,1,2,3),(15,'',2,1,3,3),(16,'',2.5,1,4,3),(17,'',3,1,5,3),(18,'',3.5,1,6,3),(19,'',1,1,1,4),(20,'',1.5,1,2,4),(21,'',2,1,3,4),(22,'',2.5,1,4,4),(23,'',3,1,5,4),(24,'',3.5,1,6,4),(25,'',1,1,1,5),(26,'',1.5,1,2,5),(27,'',2,1,3,5),(28,'',2.5,1,4,5),(29,'',3,1,5,5),(30,'',3.5,1,6,5),(31,'',1,1,1,6),(32,'',1.5,1,2,6),(33,'',2,1,3,6),(34,'',2.5,1,4,6),(35,'',3,1,5,6),(36,'',3.5,1,6,6),(37,'tesstttt',1,0,1,1);
/*!40000 ALTER TABLE `dm_chitietthuongngayle` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-08-05 10:45:36
