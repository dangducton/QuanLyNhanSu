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
-- Table structure for table `nv_quatrinhcongtac`
--

DROP TABLE IF EXISTS `nv_quatrinhcongtac`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nv_quatrinhcongtac` (
  `id` int NOT NULL AUTO_INCREMENT,
  `denngay` date NOT NULL,
  `ghichu` varchar(255) DEFAULT NULL,
  `status` int NOT NULL,
  `tungay` date NOT NULL,
  `id_chucvu` int NOT NULL,
  `id_nhanvien` int NOT NULL,
  `id_phongban` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4uxniq5yeo0lo99jkmi7obkh1` (`id_chucvu`),
  KEY `FK4gy73eukv8filmwjo0f4l1yrs` (`id_nhanvien`),
  KEY `FKokyc3wqtuif1llexdcn053rq1` (`id_phongban`),
  CONSTRAINT `FK4gy73eukv8filmwjo0f4l1yrs` FOREIGN KEY (`id_nhanvien`) REFERENCES `nv_nhanvien` (`id`),
  CONSTRAINT `FK4uxniq5yeo0lo99jkmi7obkh1` FOREIGN KEY (`id_chucvu`) REFERENCES `dm_chucdanh` (`id`),
  CONSTRAINT `FKokyc3wqtuif1llexdcn053rq1` FOREIGN KEY (`id_phongban`) REFERENCES `dm_phongban` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nv_quatrinhcongtac`
--

LOCK TABLES `nv_quatrinhcongtac` WRITE;
/*!40000 ALTER TABLE `nv_quatrinhcongtac` DISABLE KEYS */;
INSERT INTO `nv_quatrinhcongtac` VALUES (1,'2013-03-07','\r\n',1,'2010-03-07',3,1,3),(2,'2013-03-07',NULL,1,'2010-03-07',1,8,1),(3,'2015-05-02','',1,'2010-05-02',6,2,5);
/*!40000 ALTER TABLE `nv_quatrinhcongtac` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-08-05 10:45:45
