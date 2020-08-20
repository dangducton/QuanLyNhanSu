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
-- Table structure for table `tl_bangluong`
--

DROP TABLE IF EXISTS `tl_bangluong`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tl_bangluong` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ghichu` varchar(255) DEFAULT NULL,
  `hesochucvu` float NOT NULL,
  `hesochuyenmon` float NOT NULL,
  `hesotrachnhiem` float NOT NULL,
  `mucluongcoban` double NOT NULL,
  `status` int NOT NULL,
  `tamung` double DEFAULT NULL,
  `thuclinh` double NOT NULL,
  `tienbaohiem` double NOT NULL,
  `tienphat` double DEFAULT NULL,
  `tienthuong` double DEFAULT NULL,
  `tienthuongle` double DEFAULT NULL,
  `tongheso` float NOT NULL,
  `id_nhanvien` int NOT NULL,
  `tienphucap` double DEFAULT NULL,
  `thang` int NOT NULL,
  `nam` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8qw2qlsbsb2gaxo22m3y1osa5` (`id_nhanvien`),
  CONSTRAINT `FK8qw2qlsbsb2gaxo22m3y1osa5` FOREIGN KEY (`id_nhanvien`) REFERENCES `nv_nhanvien` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tl_bangluong`
--

LOCK TABLES `tl_bangluong` WRITE;
/*!40000 ALTER TABLE `tl_bangluong` DISABLE KEYS */;
INSERT INTO `tl_bangluong` VALUES (10,'',0.6,0.8,0.5,7500000,1,600000,21507500.71927905,862500,179999.99597668648,1000000,0,1.9,1,400000,2,2015),(11,'',1,0.8,1,9500000,1,0,36207499.5470047,1092500,0,1000000,0,2.8,2,200000,2,2015),(12,'',0.3,0.4,0,4000000,1,0,4220000.193417072,460000,2119999.997317791,0,0,0.7,3,0,2,2015),(13,'',0.4,0.4,0.2,4500000,1,0,8482500,517500,0,0,0,1,4,0,2,2015),(14,'',0.6,0.4,0.5,7500000,1,0,18387500,862500,0,500000,0,1.5,5,0,2,2015),(15,'',0.6,0.4,0.5,7500000,1,0,17887500,862500,0,0,0,1.5,6,0,2,2015),(16,'',0.3,0.4,0,4000000,1,0,6340000.190734863,460000,0,0,0,0.7,7,0,2,2015),(17,'',0.3,0.6,0,4000000,1,0,7140000.381469727,460000,0,0,0,0.9,8,0,2,2015),(18,'',0.3,0.6,0,4000000,1,0,7140000.381469727,460000,0,0,0,0.9,9,0,2,2015),(19,'',0.3,0.6,0,4000000,1,0,7140000.381469727,460000,0,0,0,0.9,10,0,2,2015),(20,'',0.4,0.6,0.2,4500000,1,0,9382500.214576721,517500,0,0,0,1.2,11,0,2,2015),(21,'',0.3,0.3,0,4000000,1,0,5940000.095367432,460000,0,0,0,0.6,13,0,2,2015),(23,'',1,0.8,1,9500000,1,0,36007499.5470047,1092500,0,1000000,0,2.8,2,0,7,2015);
/*!40000 ALTER TABLE `tl_bangluong` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-08-05 10:45:40
