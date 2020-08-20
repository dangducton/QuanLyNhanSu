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
-- Table structure for table `nv_nhanvien`
--

DROP TABLE IF EXISTS `nv_nhanvien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nv_nhanvien` (
  `id` int NOT NULL AUTO_INCREMENT,
  `diachihientai` varchar(255) NOT NULL,
  `diachithuongtru` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `ghichu` varchar(255) DEFAULT NULL,
  `gioitinh` int NOT NULL,
  `hinhanh` varchar(255) DEFAULT NULL,
  `hodem` varchar(255) NOT NULL,
  `luong` double NOT NULL,
  `id_chitiet` varchar(50) NOT NULL,
  `ngaysinh` date NOT NULL,
  `noicapcmnd` varchar(255) NOT NULL,
  `noisinh` varchar(255) NOT NULL,
  `sodienthoai` varchar(255) NOT NULL,
  `status` int NOT NULL,
  `ten` varchar(255) NOT NULL,
  `trangthaihopdong` int NOT NULL,
  `id_chucdanh` int NOT NULL,
  `id_chuyennganh` int NOT NULL,
  `id_dantoc` int NOT NULL,
  `id_phongban` int NOT NULL,
  `id_tinhtranghonnhan` int NOT NULL,
  `id_tongiao` int NOT NULL,
  `id_trinhdo` int NOT NULL,
  `quoctich` varchar(50) NOT NULL,
  `cmnd` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK11lpm8sc9yfcoityk5c7gadq4` (`id_chucdanh`),
  KEY `FKhcb1ovs1wna6x2pjdb006o1l` (`id_chuyennganh`),
  KEY `FK5oikfaa9xca1mp6fkvb7098w9` (`id_dantoc`),
  KEY `FKl4uhlmilaw9jya3w5htude1fs` (`id_phongban`),
  KEY `FKaegwd9deur5bxqhbif7olyvfu` (`id_tinhtranghonnhan`),
  KEY `FK1yibwamd2ptxyjyqqxcm43hcy` (`id_tongiao`),
  KEY `FKqm56f9kn19guxbdv5iscximdk` (`id_trinhdo`),
  CONSTRAINT `FK11lpm8sc9yfcoityk5c7gadq4` FOREIGN KEY (`id_chucdanh`) REFERENCES `dm_chucdanh` (`id`),
  CONSTRAINT `FK1yibwamd2ptxyjyqqxcm43hcy` FOREIGN KEY (`id_tongiao`) REFERENCES `dm_tongiao` (`id`),
  CONSTRAINT `FK5oikfaa9xca1mp6fkvb7098w9` FOREIGN KEY (`id_dantoc`) REFERENCES `dm_dantoc` (`id`),
  CONSTRAINT `FKaegwd9deur5bxqhbif7olyvfu` FOREIGN KEY (`id_tinhtranghonnhan`) REFERENCES `dm_tinhtranghonnhan` (`id`),
  CONSTRAINT `FKhcb1ovs1wna6x2pjdb006o1l` FOREIGN KEY (`id_chuyennganh`) REFERENCES `dm_chuyennganh` (`id`),
  CONSTRAINT `FKl4uhlmilaw9jya3w5htude1fs` FOREIGN KEY (`id_phongban`) REFERENCES `dm_phongban` (`id`),
  CONSTRAINT `FKqm56f9kn19guxbdv5iscximdk` FOREIGN KEY (`id_trinhdo`) REFERENCES `dm_trinhdo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nv_nhanvien`
--

LOCK TABLES `nv_nhanvien` WRITE;
/*!40000 ALTER TABLE `nv_nhanvien` DISABLE KEYS */;
INSERT INTO `nv_nhanvien` VALUES (1,'Cổ Nhuế,Bắc Từ Liêm,Hà Nội','Cổ Nhuế,Bắc Từ Liêm,Hà Nội','dangducton@gmail.com','',1,'anh the 3x4.jpg','Đặng Văn',2500000,'PP0001','1997-03-07','CA Hà Nội','Cổ Nhuế,Bắc Từ Liêm,Hà Nội','0988736559',1,'Tân',0,4,1,1,2,2,1,4,'Việt Nam','0635060999'),(2,'Cổ Nhuế,Bắc Từ Liêm,Hà Nội','Cổ Nhuế,Bắc Từ Liêm,Hà Nội','dangmailinh@gmail.com','',2,'81437752_1041601662855711_1141725538478456832_o.jpg','Đặng Mai',5000000,'GD0002','1997-08-26','CA Thanh Hóa','Thanh Hóa','036545455',1,'Linh',1,6,1,1,1,2,1,4,'Việt Nam','456789123'),(3,'Cổ Nhuế,Bắc Từ Liêm,Hà Nội','Cổ Nhuế,Bắc Từ Liêm,Hà Nội','dangmailinh@gmail.com','',1,'79206626_314123736424259_7608640758210022546_n.jpg','Lê Thị',1700000,'NV0003','1997-08-26','CA Thanh Hóa','Thanh Hóa','036545455',1,'Huyền',1,1,1,1,5,2,1,2,'Việt Nam','456789123'),(4,'Cổ Nhuế,Bắc Từ Liêm,Hà Nội','Cổ Nhuế,Bắc Từ Liêm,Hà Nội','dangmailinh@gmail.com','',2,'97441339_686861728557559_8050828286961385472_n.jpg','Trần Thu',2000000,'TT0004','1998-08-26','CA Thanh Hóa','Nam Định','036545455',1,'Phong',1,2,1,1,5,2,1,2,'Việt Nam','456789123'),(5,'Cổ Nhuế,Bắc Từ Liêm,Hà Nội','Cổ Nhuế,Bắc Từ Liêm,Hà Nội','dangmailinh@gmail.com','',1,'82095970_2699428053649111_3777637239656284160_n.jpg','Vũ Thế',3000000,'TP0005','1998-08-26','CA Thanh Hóa','Nam Định','036545455',1,'Kỷ',1,4,1,1,5,2,1,2,'Việt Nam','456789123'),(6,'Tây Tựu,Bắc Từ Liêm,Hà Nội','Tây Tựu,Bắc Từ Liêm,Hà Nội','dangmailinh@gmail.com','',1,'103099056_2802994099809174_147472594885978571_n.jpg','Đỗ Minh',3000000,'TP0006','1998-08-26','CA Thanh Hóa','Hà Nội','036545455',1,'Hải',1,4,1,1,5,2,1,2,'Việt Nam','456789123'),(7,'Tây Tựu,Bắc Từ Liêm,Hà Nội','Tây Tựu,Bắc Từ Liêm,Hà Nội','dangmailinh@gmail.com','',2,'61055783_451518808945149_3508405634158559232_o.jpg','Phạm Thị Trà',1700000,'NV0007','1998-08-26','CA Thanh Hóa','Hà Nội','036545455',1,'My',1,1,1,1,3,2,1,2,'Việt Nam','456789123'),(8,'Tây Tựu,Bắc Từ Liêm,Hà Nội','Tây Tựu,Bắc Từ Liêm,Hà Nội','dangmailinh@gmail.com','',2,'106222689_2604204819834751_932835887265316495_o.jpg','Văn Tiến',1700000,'NV0008','1998-08-26','CA Thanh Hóa','Hà Nội','036545455',1,'Thắng',1,1,1,1,3,2,1,3,'Việt Nam','456789123'),(9,'Tây Tựu,Bắc Từ Liêm,Hà Nội','Tây Tựu,Bắc Từ Liêm,Hà Nội','dangmailinh@gmail.com','',2,'105571678_1382156452116491_8848325838679366724_o.jpg','Phạm Diệu',1700000,'NV0009','1998-08-26','CA Thanh Hóa','Hà Nội','036545455',1,'Linh',1,1,1,1,2,2,1,3,'Việt Nam','456789123'),(10,'Tây Tựu,Bắc Từ Liêm,Hà Nội','Tây Tựu,Bắc Từ Liêm,Hà Nội','dangmailinh@gmail.com','',2,'104760098_2581422852109651_4567063815977964237_n.jpg','Phạm Diệu',1700000,'NV0010','1998-08-26','CA Thanh Hóa','Hà Nội','036545455',1,'Hương',1,1,1,1,4,2,1,3,'Việt Nam','456789123'),(11,'Tây Tựu,Bắc Từ Liêm,Hà Nội','Tây Tựu,Bắc Từ Liêm,Hà Nội','dangmailinh@gmail.com','',1,'92699612_1581315048690238_6193754895699410944_o.jpg','Phạm Duy',2000000,'TT0011','1998-08-26','CA Thanh Hóa','Hà Nội','036545455',1,'Đức',1,2,2,2,6,2,1,3,'Việt Nam','456789123'),(12,'Cổ Nhuế,Bắc Từ Liêm,Hà Nội','Cổ Nhuế,Bắc Từ Liêm,Hà Nội','dangducton@gmail.com','12313',1,'cay-duoi-cong-to.jpg','Trần Thu',4000000,'NV0012','1997-03-07','CA Thanh Hóa','Hà Nội','03164546',1,'test',0,1,2,2,1,2,1,1,'Việt Nam','03456469879'),(13,'Tây Tựu,Bắc Từ Liêm,Hà Nội','Cổ Nhuế,Bắc Từ Liêm,Hà Nội','123@gmail.com','',2,'10409146_407232502797437_912248228784820484_n.jpg','Trần Thu',4000000,'NV0013','1997-03-07','12321321','Hà Nội','123123123',1,'Huyền',0,1,2,2,1,2,1,1,'Việt Nam','12312321321'),(14,'Tây Tựu,Bắc Từ Liêm,Hà Nội','Cổ Nhuế,Bắc Từ Liêm,Hà Nội','123@gmail.com','',2,'10409146_407232502797437_912248228784820484_n.jpg','Trần Thu',4500000,'TT0014','1997-03-07','12321321','Hà Nội','123123123',1,'Huyền',1,2,2,2,1,2,1,1,'Việt Nam','12312321321'),(15,'Tây Tựu,Bắc Từ Liêm,Hà Nội','Cổ Nhuế,Bắc Từ Liêm,Hà Nội','123@gmail.com','',2,'10409146_407232502797437_912248228784820484_n.jpg','Trần Thu',6000000,'PP0015','1997-03-07','12321321','Hà Nội','123123123',1,'Huyền',1,3,2,2,1,2,1,1,'Việt Nam','12312321321'),(16,'Tây Tựu,Bắc Từ Liêm,Hà Nội','Cổ Nhuế,Bắc Từ Liêm,Hà Nội','123@gmail.com','',2,'10409146_407232502797437_912248228784820484_n.jpg','Trần Thu',7500000,'TP0016','1997-03-07','12321321','Hà Nội','123123123',1,'Huyền',1,4,2,2,1,2,1,1,'Việt Nam','12312321321'),(17,'Tây Tựu,Bắc Từ Liêm,Hà Nội','Cổ Nhuế,Bắc Từ Liêm,Hà Nội','123@gmail.com','',2,'10409146_407232502797437_912248228784820484_n.jpg','Trần Thu',12000000,'PGD0017','1997-03-07','12321321','Hà Nội','123123123',1,'Huyền',1,5,2,2,1,2,1,1,'Việt Nam','12312321321'),(18,'Tây Tựu,Bắc Từ Liêm,Hà Nội','Cổ Nhuế,Bắc Từ Liêm,Hà Nội','123@gmail.com','',1,'10409146_407232502797437_912248228784820484_n.jpg','Trần Thu',17000000,'GD0018','1997-03-07','12321321','Hà Nội','123123123',1,'Huyền',1,6,2,2,1,2,1,1,'Việt Nam','12312321321'),(19,'Cổ Nhuế,Bắc Từ Liêm,Hà Nội','Tây Tựu,Bắc Từ Liêm,Hà Nội','236@gmail.com','',2,'stock-photo-succulent-houseplant-crassula-ovata-in-a-pot-on-rustic-background-1445522252.jpg','Nguyen Thị',4000000,'NV0019','1997-07-22','CA Hà Nội','Hà Nội','035228997',1,'Yến',1,1,1,1,2,2,2,3,'Việt Nam','177663'),(20,'Cổ Nhuế,Bắc Từ Liêm,Hà Nội','Cổ Nhuế,Bắc Từ Liêm,Hà Nội','hoanganhduc@gmail.com','',1,'1231111111111.png','Trần Thu',4000000,'NV0020','1997-02-05','CA Thanh Hóa','Hà Nội','0988736559',1,'Thắng',0,1,1,1,1,1,1,2,'Việt Nam','0365069999');
/*!40000 ALTER TABLE `nv_nhanvien` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-08-05 10:45:44
