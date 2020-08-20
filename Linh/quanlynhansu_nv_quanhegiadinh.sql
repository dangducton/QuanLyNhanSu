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
-- Table structure for table `nv_quanhegiadinh`
--

DROP TABLE IF EXISTS `nv_quanhegiadinh`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nv_quanhegiadinh` (
  `id` int NOT NULL AUTO_INCREMENT,
  `diachi` varchar(255) NOT NULL,
  `ghichu` varchar(255) DEFAULT NULL,
  `hoten` varchar(255) NOT NULL,
  `nghenghiep` varchar(255) NOT NULL,
  `status` int NOT NULL,
  `id_nhanvien` int NOT NULL,
  `id_quanhe` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKs7drlet4ymnevvmlhwano6pg8` (`id_nhanvien`),
  KEY `FKkocyf3l2vjxashmk2ispd7o7b` (`id_quanhe`),
  CONSTRAINT `FKkocyf3l2vjxashmk2ispd7o7b` FOREIGN KEY (`id_quanhe`) REFERENCES `dm_quanhe` (`id`),
  CONSTRAINT `FKs7drlet4ymnevvmlhwano6pg8` FOREIGN KEY (`id_nhanvien`) REFERENCES `nv_nhanvien` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nv_quanhegiadinh`
--

LOCK TABLES `nv_quanhegiadinh` WRITE;
/*!40000 ALTER TABLE `nv_quanhegiadinh` DISABLE KEYS */;
INSERT INTO `nv_quanhegiadinh` VALUES (1,'Lào Cai','','Đặng Đức Từ','Tự do',1,1,1),(2,'Lào Cai','','Phạm Thị Hoài','Tự do',1,1,2),(4,'Thanh Hóa','','Đặng Văn Thắng','Tự do',1,2,1),(5,'Thanh Hóa','','Đặng Thị Hoa','Tự do',1,2,2),(6,'Thanh Hóa','','Lê Văn Tuấn','Tự do',1,3,1),(7,'Thanh Hóa','','Hoàng Thị Thắm','Tự do',1,3,2),(8,'Thanh Hóa','','Đặng Đức Vũ','Tự do',1,2,3),(9,'Thanh Hóa','','Đặng Mộc Thảo','sinh viên',1,2,4);
/*!40000 ALTER TABLE `nv_quanhegiadinh` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-08-05 10:45:41
