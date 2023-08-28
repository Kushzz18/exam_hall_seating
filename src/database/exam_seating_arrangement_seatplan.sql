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
-- Table structure for table `seatplan`
--

DROP TABLE IF EXISTS `seatplan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seatplan` (
  `student_id` int NOT NULL,
  `student_name` varchar(45) DEFAULT NULL,
  `hall_info` varchar(45) DEFAULT NULL,
  `room_no` int DEFAULT NULL,
  `seat_id` varchar(45) DEFAULT NULL,
  `sem` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seatplan`
--

LOCK TABLES `seatplan` WRITE;
/*!40000 ALTER TABLE `seatplan` DISABLE KEYS */;
INSERT INTO `seatplan` VALUES (5300,'Student797','A-1',102,'B5Y','4th'),(5301,'Student2182','A-1',103,'A5Y','4th'),(5302,'Student8519','A-1',102,'A3Y','4th'),(5303,'Student6050','A-1',102,'A2Y','4th'),(5304,'Student4692','A-1',102,'B4X','4th'),(5305,'Student5312','B-2',200,'B2Y','4th'),(5306,'Student2484','A-1',100,'A3Y','4th'),(5307,'Student6485','A-1',102,'B3X','4th'),(5308,'Student4973','A-1',100,'B1Y','4th'),(5309,'Student5412','A-1',101,'B3X','4th'),(5310,'Student2140','A-1',103,'A2Y','4th'),(5311,'Student4466','A-1',102,'A4Y','4th'),(5312,'Student5912','A-1',103,'A2X','4th'),(5313,'Student6160','B-2',200,'B5Y','4th'),(5314,'Student3066','B-2',200,'B1Y','4th'),(5315,'Student6852','A-1',101,'A5X','4th'),(5316,'Student5061','A-1',100,'A1X','4th'),(5317,'Student4748','A-1',100,'B5X','4th'),(5318,'Student8559','A-1',101,'B5Y','4th'),(5319,'Student8550','B-2',200,'A2Y','4th'),(5320,'Student7074','A-1',100,'B2Y','4th'),(5321,'Student9722','A-1',100,'B3Y','4th'),(5322,'Student7390','A-1',103,'B5Y','4th'),(5323,'Student7781','B-2',200,'A3Y','4th'),(5324,'Student6738','A-1',102,'A1X','4th'),(5325,'Student348','B-2',200,'B3Y','4th'),(5326,'Student1527','B-2',200,'A2X','4th'),(5327,'Student6591','A-1',103,'B3Y','4th'),(5328,'Student8375','A-1',102,'B1Y','4th'),(5329,'Student2100','A-1',100,'A4Y','4th'),(5330,'Student5378','A-1',103,'B1Y','4th'),(5331,'Student592','A-1',103,'B2X','4th'),(5332,'Student9390','A-1',100,'A1Y','5th'),(5333,'Student5482','A-1',101,'A2Y','5th'),(5334,'Student9243','A-1',101,'B1X','5th'),(5335,'Student9766','A-1',102,'B5X','5th'),(5336,'Student1105','A-1',102,'B1X','5th'),(5337,'Student6228','A-1',102,'A5Y','5th'),(5338,'Student7823','A-1',103,'A4X','5th'),(5339,'Student431','A-1',100,'A3X','5th'),(5340,'Student8690','B-2',200,'B3X','5th'),(5341,'Student2154','A-1',101,'B2X','5th'),(5342,'Student4702','A-1',102,'A1Y','5th'),(5343,'Student7048','A-1',102,'B4Y','5th'),(5344,'Student1133','A-1',103,'A4Y','5th'),(5345,'Student4523','B-2',200,'B1X','5th'),(5346,'Student9218','A-1',100,'B2X','5th'),(5347,'Student2520','B-2',200,'B5X','5th'),(5348,'Student4949','B-2',200,'A1X','5th'),(5349,'Student7183','A-1',100,'B5Y','5th'),(5350,'Student1070','A-1',100,'B4X','5th'),(5400,'Student4833','A-1',102,'A5X','3rd'),(5401,'Student6164','A-1',101,'B2Y','3rd'),(5402,'Student6319','B-2',201,'B3Y','3rd'),(5403,'Student3104','B-2',200,'B4X','3rd'),(5404,'Student6564','A-1',102,'B2Y','3rd'),(5405,'Student3510','A-1',103,'A3Y','3rd'),(5406,'Student7855','A-1',102,'A4X','3rd'),(5407,'Student8749','B-2',200,'A4X','3rd'),(5408,'Student179','B-2',200,'A4Y','3rd'),(5409,'Student4651','B-2',200,'B4Y','3rd'),(5410,'Student2715','A-1',102,'A2X','3rd'),(5411,'Student9625','A-1',103,'A3X','3rd'),(5412,'Student9978','B-2',200,'B2X','3rd'),(5413,'Student1015','A-1',103,'B5X','3rd'),(5414,'Student5142','A-1',100,'A2Y','3rd'),(5415,'Student2668','A-1',101,'B3Y','3rd'),(5416,'Student7913','A-1',101,'A3Y','3rd'),(5417,'Student1564','B-2',200,'A5Y','3rd'),(5418,'Student4078','A-1',101,'B1Y','3rd'),(5419,'Student5700','A-1',103,'B3X','3rd'),(5420,'Student6268','A-1',100,'A5X','3rd'),(5421,'Student4240','A-1',103,'A1X','3rd'),(5422,'Student2397','A-1',101,'B5X','3rd'),(5423,'Student9265','A-1',103,'B1X','3rd'),(5424,'Student9135','A-1',101,'A2X','3rd'),(5425,'Student7878','A-1',102,'B2X','3rd'),(5426,'Student1987','B-2',200,'A3X','3rd'),(5427,'Student6300','A-1',101,'B4Y','3rd'),(5428,'Student5541','A-1',101,'A5Y','3rd'),(5429,'Student8805','A-1',101,'B4X','3rd'),(5430,'Student7403','A-1',101,'A3X','3rd'),(5431,'Student601','A-1',101,'A1X','3rd'),(5432,'Student3799','A-1',103,'B4Y','6th'),(5433,'Student5787','A-1',100,'A4X','6th'),(5434,'Student7537','A-1',103,'B2Y','6th'),(5435,'Student327','A-1',101,'A4X','6th'),(5436,'Student9022','A-1',101,'A4Y','6th'),(5437,'Student4130','A-1',103,'B4X','6th'),(5438,'Student3587','A-1',102,'B3Y','6th'),(5439,'Student5542','A-1',100,'B4Y','6th'),(5440,'Student6951','A-1',100,'A5Y','6th'),(5441,'Student8131','A-1',100,'B1X','6th'),(5442,'Student9801','B-2',201,'B2X','6th'),(5443,'Student4611','B-2',200,'A1Y','6th'),(5444,'Student3655','A-1',100,'A2X','6th'),(5445,'Student4443','A-1',103,'A1Y','6th'),(5446,'Student1250','A-1',102,'A3X','6th'),(5447,'Student2921','B-2',200,'A5X','6th'),(5448,'Student857','A-1',101,'A1Y','6th'),(5449,'Student5523','A-1',100,'B3X','6th'),(5450,'Student5042','A-1',103,'A5X','6th');
/*!40000 ALTER TABLE `seatplan` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-08-27 12:03:45
