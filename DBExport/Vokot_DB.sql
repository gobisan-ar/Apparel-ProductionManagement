CREATE DATABASE  IF NOT EXISTS `vokot` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `vokot`;
-- MySQL dump 10.13  Distrib 8.0.24, for Win64 (x86_64)
--
-- Host: localhost    Database: vokot
-- ------------------------------------------------------
-- Server version	8.0.24

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
-- Table structure for table `finalproducts`
--

DROP TABLE IF EXISTS `finalproducts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `finalproducts` (
  `FID` varchar(20) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `stock` int DEFAULT NULL,
  `size` varchar(10) DEFAULT NULL,
  `priceperone` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`FID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `finalproducts`
--

LOCK TABLES `finalproducts` WRITE;
/*!40000 ALTER TABLE `finalproducts` DISABLE KEYS */;
/*!40000 ALTER TABLE `finalproducts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gatepassedproduct`
--

DROP TABLE IF EXISTS `gatepassedproduct`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gatepassedproduct` (
  `noticeID` int NOT NULL,
  `productionID` int DEFAULT NULL,
  `productID` int DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `gatepass_date` datetime DEFAULT NULL,
  PRIMARY KEY (`noticeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gatepassedproduct`
--

LOCK TABLES `gatepassedproduct` WRITE;
/*!40000 ALTER TABLE `gatepassedproduct` DISABLE KEYS */;
INSERT INTO `gatepassedproduct` VALUES (2010,3005,1009,100,NULL);
/*!40000 ALTER TABLE `gatepassedproduct` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lineunits`
--

DROP TABLE IF EXISTS `lineunits`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lineunits` (
  `lineID` int NOT NULL,
  `unitID` int NOT NULL,
  `unitName` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`lineID`,`unitID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lineunits`
--

LOCK TABLES `lineunits` WRITE;
/*!40000 ALTER TABLE `lineunits` DISABLE KEYS */;
INSERT INTO `lineunits` VALUES (5000,1,'cutting'),(5000,2,'printing'),(5000,3,'sewing'),(5000,4,'washing'),(5000,5,'pressing'),(5000,6,'packaging'),(5001,2,'printing'),(5001,4,'washing'),(5001,5,'pressing'),(5001,6,'packaging'),(5002,4,'washing'),(5002,5,'pressing'),(5002,6,'packaging');
/*!40000 ALTER TABLE `lineunits` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `login` (
  `login_username` varchar(30) NOT NULL,
  `login_password` varchar(30) NOT NULL,
  `login_module` varchar(30) NOT NULL,
  PRIMARY KEY (`login_username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES ('ProductionManager','cm9vdDEyMw==','Production'),('ProductManager','cm9vdDEyMw==','Product');
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manufacturingproduct`
--

DROP TABLE IF EXISTS `manufacturingproduct`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `manufacturingproduct` (
  `mfgProduct_id` int NOT NULL AUTO_INCREMENT,
  `notice_id` int DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  `inspection` varchar(30) DEFAULT (_utf8mb4'pending'),
  `mfgProduct_color` varchar(10) DEFAULT NULL,
  `mfgProduct_size` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`mfgProduct_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manufacturingproduct`
--

LOCK TABLES `manufacturingproduct` WRITE;
/*!40000 ALTER TABLE `manufacturingproduct` DISABLE KEYS */;
INSERT INTO `manufacturingproduct` VALUES (1,1000,1,'pending','#004080','M'),(2,1001,2001,'pending','#ff80ff','S'),(3,1002,2002,'pending','#303c6c','M'),(4,1003,2003,'pending','#303c6c','M'),(5,1004,2004,'pending','#004040','M'),(6,1005,2005,'pending','#ff8080','M'),(7,1006,2006,'pending','#800000','M'),(8,1007,2007,'pending','#8080ff','M'),(9,1008,2008,'pending','#a30125','M'),(10,1009,1,'pending','#408080','S'),(11,1009,2010,'pending','#408080','M');
/*!40000 ALTER TABLE `manufacturingproduct` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `product_id` int NOT NULL AUTO_INCREMENT,
  `product_title` varchar(50) DEFAULT NULL,
  `product_type` int DEFAULT NULL,
  `product_collection` int DEFAULT NULL,
  `product_image` varchar(50) DEFAULT 'defaultProduct.png',
  `product_unitCost` float DEFAULT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1011 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1000,'Vokot Men\'s CLR T-Shirt',0,2,'1633931280684MTS1.jpg',449),(1001,'Vokot Womens CLR T-Shirt',0,2,'1633931297009WTS1.webp',449),(1002,'Vokot Mens CNK T-Shirt',0,2,'1633931321914MTS2.jpg',119),(1003,'Vokot Women\'s CNK T-Shirt',1,2,'1633931342811WTS2.jpg',149),(1004,'Vokot Men\'s HSV Shirt',0,1,'1633931374082MCS.webp',599),(1005,'Vokot Women\'s HSV Shirt',1,1,'1633931394435WCS.webp',599),(1006,'Vokot Men\'s FSV Shirt',0,1,'1633931413220MFS.jpg',799),(1007,'Vokot Women\'s FSV Shirt',1,1,'1633931444346WFS.webp',799),(1008,'Vokot Men\'s Hoodie',0,3,'1633931467342MHD.jpg',999),(1009,'Vokot Women\'s Hoodie',1,3,'1633931482070WHD.webp',1199);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productcollection`
--

DROP TABLE IF EXISTS `productcollection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productcollection` (
  `productCollection_id` int NOT NULL,
  `productCollection_Name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`productCollection_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productcollection`
--

LOCK TABLES `productcollection` WRITE;
/*!40000 ALTER TABLE `productcollection` DISABLE KEYS */;
INSERT INTO `productcollection` VALUES (1,'Shirt'),(2,'T-Shirt'),(3,'Hoodie');
/*!40000 ALTER TABLE `productcollection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productcolor`
--

DROP TABLE IF EXISTS `productcolor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productcolor` (
  `product_id` int NOT NULL,
  `product_color` varchar(50) NOT NULL,
  PRIMARY KEY (`product_id`,`product_color`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productcolor`
--

LOCK TABLES `productcolor` WRITE;
/*!40000 ALTER TABLE `productcolor` DISABLE KEYS */;
INSERT INTO `productcolor` VALUES (1000,'#004080'),(1000,'#008040'),(1000,'#ff8040'),(1001,'#0080ff'),(1001,'#80ff80'),(1001,'#ff80ff'),(1002,'#272729'),(1002,'#303c6c'),(1002,'#808080'),(1003,'#008040'),(1003,'#303c6c'),(1003,'#ff0000'),(1004,'#000040'),(1004,'#004040'),(1004,'#800040'),(1005,'#408080'),(1005,'#8080ff'),(1005,'#ff8080'),(1006,'#000040'),(1006,'#004200'),(1006,'#800000'),(1007,'#3eff3e'),(1007,'#8080ff'),(1007,'#ff3c3c'),(1008,'#026632'),(1008,'#303c6c'),(1008,'#a30125'),(1009,'#3e6780'),(1009,'#408080'),(1009,'#ff7d7d'),(1010,'#000080'),(1010,'#303c6c');
/*!40000 ALTER TABLE `productcolor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `production`
--

DROP TABLE IF EXISTS `production`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `production` (
  `productionID` int NOT NULL AUTO_INCREMENT,
  `productionStatus` varchar(30) DEFAULT (_utf8mb4'Scheduled'),
  `noticeID` int DEFAULT NULL,
  `supervisor` int DEFAULT NULL,
  `inspection` varchar(30) DEFAULT (_utf8mb4'pending'),
  PRIMARY KEY (`productionID`)
) ENGINE=InnoDB AUTO_INCREMENT=3006 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `production`
--

LOCK TABLES `production` WRITE;
/*!40000 ALTER TABLE `production` DISABLE KEYS */;
INSERT INTO `production` VALUES (3000,'Scheduled',2000,796774,'pending'),(3001,'COMPLETED',2001,954123,'pass'),(3002,'Scheduled',2002,796774,'pending'),(3003,'COMPLETED',2003,954123,'fail'),(3004,'RUNNING',2004,954123,'pending'),(3005,'GATE-PASSED',2010,796774,'pass');
/*!40000 ALTER TABLE `production` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productionline`
--

DROP TABLE IF EXISTS `productionline`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productionline` (
  `lineID` int NOT NULL AUTO_INCREMENT,
  `line_type` char(1) DEFAULT NULL,
  `automation` int DEFAULT NULL,
  `line_status` int DEFAULT NULL,
  PRIMARY KEY (`lineID`)
) ENGINE=InnoDB AUTO_INCREMENT=5006 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productionline`
--

LOCK TABLES `productionline` WRITE;
/*!40000 ALTER TABLE `productionline` DISABLE KEYS */;
INSERT INTO `productionline` VALUES (5000,'A',1,1),(5001,'B',0,0),(5002,'C',-1,0),(5003,'A',1,1),(5004,'B',0,0),(5005,'C',-1,1);
/*!40000 ALTER TABLE `productionline` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productionroute`
--

DROP TABLE IF EXISTS `productionroute`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productionroute` (
  `routeID` int NOT NULL AUTO_INCREMENT,
  `productionID` int DEFAULT NULL,
  `lineID` int DEFAULT NULL,
  `cutting` int DEFAULT NULL,
  `printing` int DEFAULT NULL,
  `sewing` int DEFAULT NULL,
  `washing` int DEFAULT NULL,
  `pressing` int DEFAULT NULL,
  `packaging` int DEFAULT NULL,
  PRIMARY KEY (`routeID`)
) ENGINE=InnoDB AUTO_INCREMENT=3506 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productionroute`
--

LOCK TABLES `productionroute` WRITE;
/*!40000 ALTER TABLE `productionroute` DISABLE KEYS */;
INSERT INTO `productionroute` VALUES (3500,3000,5000,1,1,1,1,0,1),(3501,3001,5001,0,1,0,0,0,1),(3502,3002,5003,1,0,1,0,0,1),(3503,3003,5003,0,1,0,0,0,1),(3504,3004,5003,1,0,1,0,0,1),(3505,3005,5001,1,1,1,1,0,1);
/*!40000 ALTER TABLE `productionroute` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productionschedule`
--

DROP TABLE IF EXISTS `productionschedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productionschedule` (
  `sheduleID` int NOT NULL AUTO_INCREMENT,
  `productionID` int DEFAULT NULL,
  `startDate` date DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  `cutting` int DEFAULT NULL,
  `printing` int DEFAULT NULL,
  `sewing` int DEFAULT NULL,
  `washing` int DEFAULT NULL,
  `pressing` int DEFAULT NULL,
  `packaging` int DEFAULT NULL,
  PRIMARY KEY (`sheduleID`)
) ENGINE=InnoDB AUTO_INCREMENT=3256 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productionschedule`
--

LOCK TABLES `productionschedule` WRITE;
/*!40000 ALTER TABLE `productionschedule` DISABLE KEYS */;
INSERT INTO `productionschedule` VALUES (3250,3000,'2021-10-09','2021-12-14',11,11,11,11,11,11),(3251,3001,'2021-10-09','2021-10-30',0,7,0,0,7,7),(3252,3002,'2021-10-10','2021-11-15',9,0,9,0,9,9),(3253,3003,'2021-10-09','2021-10-21',0,6,0,0,0,6),(3254,3004,'2021-10-09','2021-10-30',7,0,7,0,0,7),(3255,3005,'2021-10-11','2021-12-23',10,10,10,10,10,23);
/*!40000 ALTER TABLE `productionschedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productnotice`
--

DROP TABLE IF EXISTS `productnotice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productnotice` (
  `notice_id` int NOT NULL AUTO_INCREMENT,
  `notice_title` varchar(50) DEFAULT NULL,
  `notice_origin` varchar(30) DEFAULT NULL,
  `notice_quantity` int DEFAULT NULL,
  `estBudget` float DEFAULT NULL,
  `notice_completion` date DEFAULT NULL,
  `notice_status` int DEFAULT '0',
  `referenceNumber` int DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2011 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productnotice`
--

LOCK TABLES `productnotice` WRITE;
/*!40000 ALTER TABLE `productnotice` DISABLE KEYS */;
INSERT INTO `productnotice` VALUES (2000,'Vokot Men\'s CLR T-Shirt - 1000','Inhouse',100,45000,'2021-10-30',1,1000,1000),(2001,'Vokot Womens CLR T-Shirt - 1001','Inhouse',100,50000,'2021-10-25',1,1001,1001),(2002,'Vokot Mens CNK T-Shirt - 1002','Inhouse',100,12000,'2021-10-30',1,1002,1002),(2003,'Vokot Women\'s CNK T-Shirt - 1003','Inhouse',100,15000,'2021-10-27',1,1003,1003),(2004,'Vokot Men\'s HSV Shirt - 1004','Inhouse',100,60000,'2021-10-29',1,1004,1004),(2005,'Vokot Women\'s HSV Shirt - 1005','Inhouse',100,60000,'2021-10-28',0,1005,1005),(2006,'Vokot Men\'s FSV Shirt - 1006','Inhouse',100,80000,'2021-10-28',0,1006,1006),(2007,'Vokot Women\'s FSV Shirt - 1007','Inhouse',100,80000,'2021-10-29',0,1007,1007),(2008,'Vokot Men\'s Hoodie - 1008','Inhouse',100,100000,'2021-10-25',-1,1008,1008),(2009,'Vokot Women\'s Hoodie - 1009','Inhouse',100,120000,'2021-10-27',0,1009,1009),(2010,'Vokot Women\'s Hoodie - 1009','Inhouse',100,500000,'2021-10-13',1,1009,1009);
/*!40000 ALTER TABLE `productnotice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productsize`
--

DROP TABLE IF EXISTS `productsize`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productsize` (
  `product_id` int NOT NULL,
  `product_size` varchar(3) NOT NULL,
  PRIMARY KEY (`product_id`,`product_size`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productsize`
--

LOCK TABLES `productsize` WRITE;
/*!40000 ALTER TABLE `productsize` DISABLE KEYS */;
INSERT INTO `productsize` VALUES (1000,'l'),(1000,'m'),(1000,'s'),(1001,'l'),(1001,'m'),(1001,'s'),(1002,'l'),(1002,'m'),(1002,'s'),(1003,'l'),(1003,'m'),(1003,'s'),(1004,'l'),(1004,'m'),(1004,'s'),(1005,'l'),(1005,'m'),(1005,'s'),(1006,'l'),(1006,'m'),(1006,'s'),(1007,'l'),(1007,'m'),(1007,'s'),(1008,'l'),(1008,'m'),(1008,'s'),(1009,'l'),(1009,'m'),(1009,'s'),(1010,'l'),(1010,'m'),(1010,'s');
/*!40000 ALTER TABLE `productsize` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-03 11:24:32
