-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: localhost    Database: mymath
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `math_questions`
--

DROP TABLE IF EXISTS `math_questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `math_questions` (
  `question_id` int(11) NOT NULL AUTO_INCREMENT,
  `topic_id` int(11) DEFAULT NULL,
  `question` varchar(1000) DEFAULT NULL,
  `points` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`question_id`)
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `math_questions`
--

LOCK TABLES `math_questions` WRITE;
/*!40000 ALTER TABLE `math_questions` DISABLE KEYS */;
INSERT INTO `math_questions` VALUES (3,2,'2x ( 3 - 4 ) = 5x','10'),(4,1,'2 ( 3x - 5 + 2) = 10 ( x - 2 ) + 5x','20'),(5,1,'2x - 3 + 5 ( 2 - 4x ) = 2','10'),(6,1,'4x + 3 = 5 ( 3 + x )','10'),(8,2,'4x - 3 = 2 ( 2 + 3x )','20'),(9,2,'2x + 3 = 2 - 5','10');
/*!40000 ALTER TABLE `math_questions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `math_quizzes`
--

DROP TABLE IF EXISTS `math_quizzes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `math_quizzes` (
  `quiz_id` int(11) NOT NULL AUTO_INCREMENT,
  `duedate` datetime DEFAULT NULL,
  `quiz_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`quiz_id`)
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `math_quizzes`
--

LOCK TABLES `math_quizzes` WRITE;
/*!40000 ALTER TABLE `math_quizzes` DISABLE KEYS */;
INSERT INTO `math_quizzes` VALUES (1,'2019-03-13 23:59:00','Quiz 1'),(2,'2019-03-12 23:59:00','Quiz 2'),(3,'2019-12-05 23:59:00','Quiz 3'),(4,'2019-08-20 14:02:00','Exam 1'),(5,'2020-10-09 23:59:00','Exam 2');
/*!40000 ALTER TABLE `math_quizzes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `math_topics`
--

DROP TABLE IF EXISTS `math_topics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `math_topics` (
  `topic_id` int(11) NOT NULL AUTO_INCREMENT,
  `topic_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`topic_id`)
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `math_topics`
--

LOCK TABLES `math_topics` WRITE;
/*!40000 ALTER TABLE `math_topics` DISABLE KEYS */;
INSERT INTO `math_topics` VALUES (1,'Linear Equation'),(2,'Simplify Equation'),(3,'Quadratic Equation'),(5,'Probability'),(6,'Time and Work');
/*!40000 ALTER TABLE `math_topics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quiz_questions`
--

DROP TABLE IF EXISTS `quiz_questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `quiz_questions` (
  `quiz_id` int(11) DEFAULT NULL,
  `question_id` int(11) DEFAULT NULL
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quiz_questions`
--

LOCK TABLES `quiz_questions` WRITE;
/*!40000 ALTER TABLE `quiz_questions` DISABLE KEYS */;
INSERT INTO `quiz_questions` VALUES (1,1),(1,3),(2,1),(2,2),(2,3),(3,1),(3,4),(4,3),(4,5),(5,4),(5,5),(5,6);
/*!40000 ALTER TABLE `quiz_questions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `users` (
  `username` varchar(45) NOT NULL,
  `firstname` varchar(45) DEFAULT NULL,
  `lastname` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `role` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`username`)
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('admin','Admin','Admin','123456','professor'),('avj0003','Abhishek','Jariwala','123456','student'),('fzj0007','Fatemeh','Jamshidi','123456','student'),('npn007','Nirmit','Patel','123456','student');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-01-26  9:54:28
