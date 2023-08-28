-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: exam_seating_arrangement
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `id` int NOT NULL,
  `name` varchar(45) NOT NULL,
  `sem` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (5300,'Student797','4th'),(5301,'Student2182','4th'),(5302,'Student8519','4th'),(5303,'Student6050','4th'),(5304,'Student4692','4th'),(5305,'Student5312','4th'),(5306,'Student2484','4th'),(5307,'Student6485','4th'),(5308,'Student4973','4th'),(5309,'Student5412','4th'),(5310,'Student2140','4th'),(5311,'Student4466','4th'),(5312,'Student5912','4th'),(5313,'Student6160','4th'),(5314,'Student3066','4th'),(5315,'Student6852','4th'),(5316,'Student5061','4th'),(5317,'Student4748','4th'),(5318,'Student8559','4th'),(5319,'Student8550','4th'),(5320,'Student7074','4th'),(5321,'Student9722','4th'),(5322,'Student7390','4th'),(5323,'Student7781','4th'),(5324,'Student6738','4th'),(5325,'Student348','4th'),(5326,'Student1527','4th'),(5327,'Student6591','4th'),(5328,'Student8375','4th'),(5329,'Student2100','4th'),(5330,'Student5378','4th'),(5331,'Student592','4th'),(5332,'Student9390','5th'),(5333,'Student5482','5th'),(5334,'Student9243','5th'),(5335,'Student9766','5th'),(5336,'Student1105','5th'),(5337,'Student6228','5th'),(5338,'Student7823','5th'),(5339,'Student431','5th'),(5340,'Student8690','5th'),(5341,'Student2154','5th'),(5342,'Student4702','5th'),(5343,'Student7048','5th'),(5344,'Student1133','5th'),(5345,'Student4523','5th'),(5346,'Student9218','5th'),(5347,'Student2520','5th'),(5348,'Student4949','5th'),(5349,'Student7183','5th'),(5350,'Student1070','5th'),(5400,'Student4833','3rd'),(5401,'Student6164','3rd'),(5402,'Student6319','3rd'),(5403,'Student3104','3rd'),(5404,'Student6564','3rd'),(5405,'Student3510','3rd'),(5406,'Student7855','3rd'),(5407,'Student8749','3rd'),(5408,'Student179','3rd'),(5409,'Student4651','3rd'),(5410,'Student2715','3rd'),(5411,'Student9625','3rd'),(5412,'Student9978','3rd'),(5413,'Student1015','3rd'),(5414,'Student5142','3rd'),(5415,'Student2668','3rd'),(5416,'Student7913','3rd'),(5417,'Student1564','3rd'),(5418,'Student4078','3rd'),(5419,'Student5700','3rd'),(5420,'Student6268','3rd'),(5421,'Student4240','3rd'),(5422,'Student2397','3rd'),(5423,'Student9265','3rd'),(5424,'Student9135','3rd'),(5425,'Student7878','3rd'),(5426,'Student1987','3rd'),(5427,'Student6300','3rd'),(5428,'Student5541','3rd'),(5429,'Student8805','3rd'),(5430,'Student7403','3rd'),(5431,'Student601','3rd'),(5432,'Student3799','6th'),(5433,'Student5787','6th'),(5434,'Student7537','6th'),(5435,'Student327','6th'),(5436,'Student9022','6th'),(5437,'Student4130','6th'),(5438,'Student3587','6th'),(5439,'Student5542','6th'),(5440,'Student6951','6th'),(5441,'Student8131','6th'),(5442,'Student9801','6th'),(5443,'Student4611','6th'),(5444,'Student3655','6th'),(5445,'Student4443','6th'),(5446,'Student1250','6th'),(5447,'Student2921','6th'),(5448,'Student857','6th'),(5449,'Student5523','6th'),(5450,'Student5042','6th');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-08-27 12:03:46
