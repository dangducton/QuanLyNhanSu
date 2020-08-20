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
-- Table structure for table `nv_quyetdinhchamduthopdong`
--

DROP TABLE IF EXISTS `nv_quyetdinhchamduthopdong`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nv_quyetdinhchamduthopdong` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ghichu` varchar(255) DEFAULT NULL,
  `ngayhieuluc` date NOT NULL,
  `ngayquyetdinh` date NOT NULL,
  `noidung` varchar(255) NOT NULL,
  `status` int NOT NULL,
  `tenquyetdinh` varchar(255) NOT NULL,
  `id_nhanvien` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKavluqrpetl51m727crxib9lnh` (`id_nhanvien`),
  CONSTRAINT `FKavluqrpetl51m727crxib9lnh` FOREIGN KEY (`id_nhanvien`) REFERENCES `nv_nhanvien` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nv_quyetdinhchamduthopdong`
--

LOCK TABLES `nv_quyetdinhchamduthopdong` WRITE;
/*!40000 ALTER TABLE `nv_quyetdinhchamduthopdong` DISABLE KEYS */;
INSERT INTO `nv_quyetdinhchamduthopdong` VALUES (2,'','2014-04-07','2020-07-12','Do đại dịch cô - vít 19 công ty cần cắt giảm nhân sự để tiết kiệm chi phí hết sức có thể',1,'Quyết định thôi việc',12),(3,'','2014-03-07','2020-07-12','Do đại dịch cô - vít 19 công ty cần cắt giảm nhân sự để tiết kiệm chi phí hết sức có thể',1,'Quyết định thôi việc',13),(6,'','2020-07-13','2020-07-13','Do đại dịch cô - vít 19 công ty cần cắt giảm nhân sự để tiết kiệm chi phí hết sức có thể',1,'Quyết định thôi việc',1),(7,'121','2015-02-06','2020-07-26','Do đại dịch cô - vít 19 công ty cần cắt giảm nhân sự để tiết kiệm chi phí hết sức có thể',1,'Quyết định thôi việc',20);
/*!40000 ALTER TABLE `nv_quyetdinhchamduthopdong` ENABLE KEYS */;
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
