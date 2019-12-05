-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: localhost    Database: springbootcourse
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 
CREATE TABLE `address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `complement` varchar(255) DEFAULT NULL,
  `district` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `postal_code` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `cityid` int(11) DEFAULT NULL,
  `customerid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdj1v3hu7905u6i519uhi6o43u` (`cityid`),
  KEY `FK839a9l0sktylcf94y74cw48qg` (`customerid`),
  CONSTRAINT `FK839a9l0sktylcf94y74cw48qg` FOREIGN KEY (`customerid`) REFERENCES `customer` (`id`),
  CONSTRAINT `FKdj1v3hu7905u6i519uhi6o43u` FOREIGN KEY (`cityid`) REFERENCES `city` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'Apto 303','Jardim','300','38220834','Rua Flores',1,1),(2,'Sala 800','Centro','105','38777012','Avenida Matos',2,1),(3,NULL,'Centro','2106','281777012','Avenida Floriano',2,2);
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Informática'),(2,'Escritório'),(3,'Cama mesa e banho'),(4,'Eletrônicos'),(5,'Jardinagem'),(6,'Decoração'),(7,'Perfumaria');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 
CREATE TABLE `city` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `provinceid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKewkitlhd136owyn0qwjo2fr40` (`provinceid`),
  CONSTRAINT `FKewkitlhd136owyn0qwjo2fr40` FOREIGN KEY (`provinceid`) REFERENCES `province` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` VALUES (1,'Uberlândia',1),(2,'São Paulo',2),(3,'Campinas',2);
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 
CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_type` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `financial_code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,1,'jeanbr86@gmail.com','36378912377','Maria Silva','$2a$10$mRogNkoXBL3mgy3ewO8efelXnBw7c.3lL6Qa5b5WKNMbQQq8iX6LK'),(2,1,'jeanb@softexpert.com','31628382740','Ana Costa','$2a$10$9dvoUPSvO.frF1Xu75DpuuBxYb94UMrYD//5UbJjMf0l2MLiJ7nJq');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 
CREATE TABLE `payment` (
  `requestid` int(11) NOT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`requestid`),
  CONSTRAINT `FKpm38k51ysm2lirnchc0miqqd3` FOREIGN KEY (`requestid`) REFERENCES `request` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (1,2),(2,1);
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_with_boleto`
--

DROP TABLE IF EXISTS `payment_with_boleto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 
CREATE TABLE `payment_with_boleto` (
  `due_date` datetime DEFAULT NULL,
  `payment_date` datetime DEFAULT NULL,
  `requestid` int(11) NOT NULL,
  PRIMARY KEY (`requestid`),
  CONSTRAINT `FKdaivu9vtuhq2lihos6hb38sbk` FOREIGN KEY (`requestid`) REFERENCES `payment` (`requestid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_with_boleto`
--

LOCK TABLES `payment_with_boleto` WRITE;
/*!40000 ALTER TABLE `payment_with_boleto` DISABLE KEYS */;
INSERT INTO `payment_with_boleto` VALUES ('2017-10-20 02:00:00',NULL,2);
/*!40000 ALTER TABLE `payment_with_boleto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_with_card`
--

DROP TABLE IF EXISTS `payment_with_card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 
CREATE TABLE `payment_with_card` (
  `number_of_parcelas` int(11) DEFAULT NULL,
  `requestid` int(11) NOT NULL,
  PRIMARY KEY (`requestid`),
  CONSTRAINT `FKssn4x928o78orogsk95o8qck` FOREIGN KEY (`requestid`) REFERENCES `payment` (`requestid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_with_card`
--

LOCK TABLES `payment_with_card` WRITE;
/*!40000 ALTER TABLE `payment_with_card` DISABLE KEYS */;
INSERT INTO `payment_with_card` VALUES (6,1);
/*!40000 ALTER TABLE `payment_with_card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Computador',2000),(2,'Impressora',800),(3,'Mouse',80),(4,'Mesa de escritório',300),(5,'Toalha',50),(6,'Colcha',200),(7,'TV true color',1200),(8,'Roçadeira',800),(9,'Abajour',100),(10,'Pendente',180),(11,'Shampoo',90),(12,'Product 12',10),(13,'Product 13',10),(14,'Product 14',10),(15,'Product 15',10),(16,'Product 16',10),(17,'Product 17',10),(18,'Product 18',10),(19,'Product 19',10),(20,'Product 20',10),(21,'Product 21',10),(22,'Product 22',10),(23,'Product 23',10),(24,'Product 24',10),(25,'Product 25',10),(26,'Product 26',10),(27,'Product 27',10),(28,'Product 28',10),(29,'Product 29',10),(30,'Product 30',10),(31,'Product 31',10),(32,'Product 32',10),(33,'Product 33',10),(34,'Product 34',10),(35,'Product 35',10),(36,'Product 36',10),(37,'Product 37',10),(38,'Product 38',10),(39,'Product 39',10),(40,'Product 40',10),(41,'Product 41',10),(42,'Product 42',10),(43,'Product 43',10),(44,'Product 44',10),(45,'Product 45',10),(46,'Product 46',10),(47,'Product 47',10),(48,'Product 48',10),(49,'Product 49',10),(50,'Product 50',10);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productcategory`
--

DROP TABLE IF EXISTS `productcategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 
CREATE TABLE `productcategory` (
  `productid` int(11) NOT NULL,
  `categoryid` int(11) NOT NULL,
  KEY `FKk1euy3h7ak6hevr0uolfrwdho` (`categoryid`),
  KEY `FKcecnby5es8ai227f84lkph4od` (`productid`),
  CONSTRAINT `FKcecnby5es8ai227f84lkph4od` FOREIGN KEY (`productid`) REFERENCES `product` (`id`),
  CONSTRAINT `FKk1euy3h7ak6hevr0uolfrwdho` FOREIGN KEY (`categoryid`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productcategory`
--

LOCK TABLES `productcategory` WRITE;
/*!40000 ALTER TABLE `productcategory` DISABLE KEYS */;
INSERT INTO `productcategory` VALUES (1,1),(1,4),(2,1),(2,2),(2,4),(3,1),(3,4),(4,2),(5,3),(6,3),(7,4),(8,5),(9,6),(10,6),(11,7),(12,1),(13,1),(14,1),(15,1),(16,1),(17,1),(18,1),(19,1),(20,1),(21,1),(22,1),(23,1),(24,1),(25,1),(26,1),(27,1),(28,1),(29,1),(30,1),(31,1),(32,1),(33,1),(34,1),(35,1),(36,1),(37,1),(38,1),(39,1),(40,1),(41,1),(42,1),(43,1),(44,1),(45,1),(46,1),(47,1),(48,1),(49,1),(50,1);
/*!40000 ALTER TABLE `productcategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profiles`
--

DROP TABLE IF EXISTS `profiles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 
CREATE TABLE `profiles` (
  `customer_id` int(11) NOT NULL,
  `profiles` int(11) DEFAULT NULL,
  KEY `FKhgvrt829sn9iv725c2ffh86hw` (`customer_id`),
  CONSTRAINT `FKhgvrt829sn9iv725c2ffh86hw` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profiles`
--

LOCK TABLES `profiles` WRITE;
/*!40000 ALTER TABLE `profiles` DISABLE KEYS */;
INSERT INTO `profiles` VALUES (1,2),(2,1),(2,2);
/*!40000 ALTER TABLE `profiles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `province`
--

DROP TABLE IF EXISTS `province`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 
CREATE TABLE `province` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `province`
--

LOCK TABLES `province` WRITE;
/*!40000 ALTER TABLE `province` DISABLE KEYS */;
INSERT INTO `province` VALUES (1,'Minas Gerais'),(2,'São Paulo');
/*!40000 ALTER TABLE `province` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request`
--

DROP TABLE IF EXISTS `request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 
CREATE TABLE `request` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` datetime DEFAULT NULL,
  `addressid` int(11) DEFAULT NULL,
  `customerid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKs6d4a3ukx5n9o4bew2nc53gbx` (`addressid`),
  KEY `FKlnb247tuh3x5jet0vmr4ivwur` (`customerid`),
  CONSTRAINT `FKlnb247tuh3x5jet0vmr4ivwur` FOREIGN KEY (`customerid`) REFERENCES `customer` (`id`),
  CONSTRAINT `FKs6d4a3ukx5n9o4bew2nc53gbx` FOREIGN KEY (`addressid`) REFERENCES `address` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request`
--

LOCK TABLES `request` WRITE;
/*!40000 ALTER TABLE `request` DISABLE KEYS */;
INSERT INTO `request` VALUES (1,'2017-09-30 13:32:00',1,1),(2,'2017-10-10 22:35:00',2,1);
/*!40000 ALTER TABLE `request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request_item`
--

DROP TABLE IF EXISTS `request_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 
CREATE TABLE `request_item` (
  `discount` double DEFAULT NULL,
  `price` double DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `productid` int(11) NOT NULL,
  `requestid` int(11) NOT NULL,
  PRIMARY KEY (`productid`,`requestid`),
  KEY `FKlhlslcgy99xv9ha6yxbjlw9th` (`requestid`),
  CONSTRAINT `FKk5wys6mxtdp17ampud350ouvp` FOREIGN KEY (`productid`) REFERENCES `product` (`id`),
  CONSTRAINT `FKlhlslcgy99xv9ha6yxbjlw9th` FOREIGN KEY (`requestid`) REFERENCES `request` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request_item`
--

LOCK TABLES `request_item` WRITE;
/*!40000 ALTER TABLE `request_item` DISABLE KEYS */;
INSERT INTO `request_item` VALUES (0,2000,1,1,1),(100,800,1,2,2),(0,80,2,3,1);
/*!40000 ALTER TABLE `request_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `telephone`
--

DROP TABLE IF EXISTS `telephone`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 
CREATE TABLE `telephone` (
  `customer_id` int(11) NOT NULL,
  `telephones` varchar(255) DEFAULT NULL,
  KEY `FKd2kespl8p4rstkyphbkwxge3o` (`customer_id`),
  CONSTRAINT `FKd2kespl8p4rstkyphbkwxge3o` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `telephone`
--

LOCK TABLES `telephone` WRITE;
/*!40000 ALTER TABLE `telephone` DISABLE KEYS */;
INSERT INTO `telephone` VALUES (1,'27363323'),(1,'93838393'),(2,'93883321'),(2,'34252625');
/*!40000 ALTER TABLE `telephone` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-05 13:04:26
