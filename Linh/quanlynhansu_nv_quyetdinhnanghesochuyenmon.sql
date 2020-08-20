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
-- Table structure for table `nv_quyetdinhnanghesochuyenmon`
--

DROP TABLE IF EXISTS `nv_quyetdinhnanghesochuyenmon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nv_quyetdinhnanghesochuyenmon` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ghichu` varchar(255) DEFAULT NULL,
  `ngayhieuluc` date NOT NULL,
  `ngayquyetdinh` date NOT NULL,
  `noidung` varchar(255) NOT NULL,
  `status` int NOT NULL,
  `tenquyetdinh` varchar(255) NOT NULL,
  `id_nhanvien` int NOT NULL,
  `id_trinhdo` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2svtw6koc1rx5o9d1v1m8nmxd` (`id_nhanvien`),
  KEY `FKlt7q47a9epnyey6qyt9o5bsel` (`id_trinhdo`),
  CONSTRAINT `FK2svtw6koc1rx5o9d1v1m8nmxd` FOREIGN KEY (`id_nhanvien`) REFERENCES `nv_nhanvien` (`id`),
  CONSTRAINT `FKlt7q47a9epnyey6qyt9o5bsel` FOREIGN KEY (`id_trinhdo`) REFERENCES `dm_trinhdo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nv_quyetdinhnanghesochuyenmon`
--

LOCK TABLES `nv_quyetdinhnanghesochuyenmon` WRITE;
/*!40000 ALTER TABLE `nv_quyetdinhnanghesochuyenmon` DISABLE KEYS */;
INSERT INTO `nv_quyetdinhnanghesochuyenmon` VALUES (1,'','2015-02-25','2020-07-12','Nâng hệ số chuyên môn cho nhân viên',1,'Quyết định nâng hệ số chuyên môn',3,1),(2,'','2015-02-25','2020-07-12','Nâng hệ số chuyên môn cho nhân viên',1,'Quyết định nâng hệ số chuyên môn',5,2);
/*!40000 ALTER TABLE `nv_quyetdinhnanghesochuyenmon` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-08-05 10:45:38
