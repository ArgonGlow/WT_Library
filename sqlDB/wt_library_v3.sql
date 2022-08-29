-- MariaDB dump 10.19  Distrib 10.4.24-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: wt_library
-- ------------------------------------------------------
-- Server version	10.4.24-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admins`
--

DROP TABLE IF EXISTS `admins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admins` (
  `admin_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`admin_id`),
  KEY `fk_user_id_idx` (`user_id`),
  CONSTRAINT `fk_admins_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admins`
--

LOCK TABLES `admins` WRITE;
/*!40000 ALTER TABLE `admins` DISABLE KEYS */;
/*!40000 ALTER TABLE `admins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book_label`
--

DROP TABLE IF EXISTS `book_label`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book_label` (
  `junction_id` int(11) NOT NULL,
  `book_id` int(11) DEFAULT NULL,
  `label_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`junction_id`),
  KEY `fk_junction_books_idx` (`book_id`),
  KEY `fk_junction_labels_idx` (`label_id`),
  CONSTRAINT `fk_junction_books` FOREIGN KEY (`book_id`) REFERENCES `books` (`book_id`),
  CONSTRAINT `fk_junction_labels` FOREIGN KEY (`label_id`) REFERENCES `labels` (`label_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_label`
--

LOCK TABLES `book_label` WRITE;
/*!40000 ALTER TABLE `book_label` DISABLE KEYS */;
/*!40000 ALTER TABLE `book_label` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `books` (
  `book_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `author` varchar(255) DEFAULT NULL,
  `isbn` varchar(13) NOT NULL,
  `cover_image` blob DEFAULT NULL,
  PRIMARY KEY (`book_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (1,'Java and Me',NULL,'',NULL),(2,'Learn JavaScript in 24 Hours','Alex Nordeen','1230004294832',NULL),(3,'Programmeren met JavaScript voor Dummies','Chris Minnick','9789045354705',NULL),(4,'JavaScript voor Beginners','Antoon Crama','9789464066654',NULL),(5,'Head First Java','Kathy Sierra','9781491910740',NULL),(6,'Effective Java','Joshua Bloch','9780134686042',NULL),(7,'Python Crash Course (2nd Edition)','Eric Matthes','9781593279288',NULL),(8,'Voor Dummies - Programmeren met Python voor Dummies','John Paul Mueller','9789045353524',NULL),(9,'C# Programming Basics: Learn C# Coding for Beginners Book 1','Barbara Doyle','9781386552895',NULL),(10,'Programming C# 10','Ian Griffiths','9781098117771',NULL),(11,'C# Programming in easy steps, 2nd edition','Mike Mcgrath','9781840789157',NULL);
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `copies`
--

DROP TABLE IF EXISTS `copies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `copies` (
  `book_id` int(11) NOT NULL,
  `copy_id` int(11) NOT NULL,
  `loaned_by_user` int(11) DEFAULT NULL,
  PRIMARY KEY (`book_id`,`copy_id`),
  KEY `fk_copies_books_idx` (`book_id`),
  KEY `fk_copies_users_idx` (`loaned_by_user`),
  KEY `copy_id_idx` (`copy_id`),
  CONSTRAINT `fk_copies_books` FOREIGN KEY (`book_id`) REFERENCES `books` (`book_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_copies_users` FOREIGN KEY (`loaned_by_user`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `copies`
--

LOCK TABLES `copies` WRITE;
/*!40000 ALTER TABLE `copies` DISABLE KEYS */;
/*!40000 ALTER TABLE `copies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `labels`
--

DROP TABLE IF EXISTS `labels`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `labels` (
  `label_id` int(11) NOT NULL,
  `label_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`label_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Stores labels assignable to a book for user search purposes.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `labels`
--

LOCK TABLES `labels` WRITE;
/*!40000 ALTER TABLE `labels` DISABLE KEYS */;
/*!40000 ALTER TABLE `labels` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservations`
--

DROP TABLE IF EXISTS `reservations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservations` (
  `reservation_id` int(11) NOT NULL AUTO_INCREMENT,
  `book_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`reservation_id`),
  KEY `fk_reservations_books_idx` (`book_id`),
  KEY `fk_reservations_users_idx` (`user_id`),
  CONSTRAINT `fk_reservations_books` FOREIGN KEY (`book_id`) REFERENCES `books` (`book_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_reservations_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservations`
--

LOCK TABLES `reservations` WRITE;
/*!40000 ALTER TABLE `reservations` DISABLE KEYS */;
/*!40000 ALTER TABLE `reservations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transactions`
--

DROP TABLE IF EXISTS `transactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transactions` (
  `transaction_id` int(11) NOT NULL AUTO_INCREMENT,
  `copy_id` int(11) DEFAULT NULL COMMENT 'fk',
  `user_id` int(11) DEFAULT NULL,
  `transaction_type` varchar(10) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`transaction_id`),
  KEY `fk_transactions_copies_idx` (`copy_id`),
  KEY `fk_transactions_users_idx` (`user_id`),
  CONSTRAINT `fk_transactions_copies` FOREIGN KEY (`copy_id`) REFERENCES `copies` (`copy_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_transactions_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transactions`
--

LOCK TABLES `transactions` WRITE;
/*!40000 ALTER TABLE `transactions` DISABLE KEYS */;
/*!40000 ALTER TABLE `transactions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(32) NOT NULL,
  `last_name` varchar(32) NOT NULL,
  `email` varchar(80) NOT NULL,
  `password` varchar(32) NOT NULL DEFAULT 'password',
  `create_time` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
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

-- Dump completed on 2022-08-29 11:33:30
