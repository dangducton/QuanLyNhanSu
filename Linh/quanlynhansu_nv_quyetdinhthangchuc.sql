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
-- Table structure for table `nv_quyetdinhthangchuc`
--

DROP TABLE IF EXISTS `nv_quyetdinhthangchuc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nv_quyetdinhthangchuc` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ghichu` varchar(255) DEFAULT NULL,
  `ngayhieuluc` date NOT NULL,
  `ngayquyetdinh` date NOT NULL,
  `noidung` varchar(255) NOT NULL,
  `status` int NOT NULL,
  `tenquyetdinh` varchar(255) NOT NULL,
  `id_chucdanh` int NOT NULL,
  `id_nhanvien` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKaxxd1970qvy8patooal1d7bb` (`id_chucdanh`),
  KEY `FKewi4yc3qq53x2xknvhhjyx050` (`id_nhanvien`),
  CONSTRAINT `FKaxxd1970qvy8patooal1d7bb` FOREIGN KEY (`id_chucdanh`) REFERENCES `dm_chucdanh` (`id`),
  CONSTRAINT `FKewi4yc3qq53x2xknvhhjyx050` FOREIGN KEY (`id_nhanvien`) REFERENCES `nv_nhanvien` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nv_quyetdinhthangchuc`
--

LOCK TABLES `nv_quyetdinhthangchuc` WRITE;
/*!40000 ALTER TABLE `nv_quyetdinhthangchuc` DISABLE KEYS */;
INSERT INTO `nv_quyetdinhthangchuc` VALUES (1,'','2015-02-25','2020-07-12','Quyết định thăng chức cho nhân viên',1,'Quyết định thăng chức',3,1);
/*!40000 ALTER TABLE `nv_quyetdinhthangchuc` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-08-05 10:45:43
