-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 05, 2022 at 11:15 AM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `wt_library`
--
CREATE DATABASE IF NOT EXISTS `wt_library` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `wt_library`;

-- --------------------------------------------------------

--
-- Table structure for table `admins`
--

CREATE TABLE `admins` (
  `admin_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `email` varchar(80) NOT NULL,
  `role` tinyint(4) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `books`
--

CREATE TABLE `books` (
  `book_id` int(11) NOT NULL,
  `title` varchar(100) DEFAULT NULL,
  `author` varchar(255) DEFAULT NULL,
  `isbn` varchar(13) NOT NULL,
  `cover_image` blob DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `books`
--

INSERT INTO `books` (`book_id`, `title`, `author`, `isbn`, `cover_image`) VALUES
(1, 'Java and Me', NULL, '', NULL),
(2, 'Learn JavaScript in 24 Hours', 'Alex Nordeen', '1230004294832', NULL),
(3, 'Programmeren met JavaScript voor Dummies', 'Chris Minnick', '9789045354705', NULL),
(4, 'JavaScript voor Beginners', 'Antoon Crama', '9789464066654', NULL),
(5, 'Head First Java', 'Kathy Sierra', '9781491910740', NULL),
(6, 'Effective Java', 'Joshua Bloch', '9780134686042', NULL),
(7, 'Python Crash Course (2nd Edition)', 'Eric Matthes', '9781593279288', NULL),
(8, 'Voor Dummies - Programmeren met Python voor Dummies', 'John Paul Mueller', '9789045353524', NULL),
(9, 'C# Programming Basics: Learn C# Coding for Beginners Book 1', 'Barbara Doyle', '9781386552895', NULL),
(10, 'Programming C# 10', 'Ian Griffiths', '9781098117771', NULL),
(11, 'C# Programming in easy steps, 2nd edition', 'Mike Mcgrath', '9781840789157', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `book_label`
--

CREATE TABLE `book_label` (
  `junction_id` int(11) NOT NULL,
  `book_id` int(11) DEFAULT NULL,
  `label_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `copies`
--

CREATE TABLE `copies` (
  `book_id` int(11) NOT NULL,
  `copy_id` int(11) NOT NULL,
  `loaned_by_user` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `copies`
--

INSERT INTO `copies` (`book_id`, `copy_id`, `loaned_by_user`) VALUES
(2, 2, 1),
(1, 1, 2),
(1, 2, 3),
(2, 13, 3);

-- --------------------------------------------------------

--
-- Table structure for table `labels`
--

CREATE TABLE `labels` (
  `label_id` int(11) NOT NULL,
  `label_name` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Stores labels assignable to a book for user search purposes.';

-- --------------------------------------------------------

--
-- Table structure for table `reservations`
--

CREATE TABLE `reservations` (
  `reservation_id` int(11) NOT NULL,
  `book_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `transactions`
--

CREATE TABLE `transactions` (
  `transaction_id` int(11) NOT NULL,
  `copy_id` int(11) DEFAULT NULL COMMENT 'fk',
  `user_id` int(11) DEFAULT NULL,
  `transaction_type` tinyint(4) DEFAULT NULL,
  `date` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `first_name` varchar(32) NOT NULL,
  `last_name` varchar(32) NOT NULL,
  `email` varchar(80) NOT NULL,
  `password` varchar(80) NOT NULL DEFAULT 'password',
  `active` tinyint(4) NOT NULL DEFAULT 1,
  `create_time` timestamp NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `first_name`, `last_name`, `email`, `password`, `active`, `create_time`) VALUES
(1, 'a', 'a', 'a', 'a', 1, '2022-08-31 11:42:13'),
(2, 'b', 'b', 'b', 'b', 1, '2022-08-31 11:42:13'),
(3, 'c', 'c', 'c', 'c', 1, '2022-08-31 11:42:13');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admins`
--
ALTER TABLE `admins`
  ADD PRIMARY KEY (`admin_id`),
  ADD KEY `fk_admins_users` (`user_id`),
  ADD KEY `fk_email_users` (`email`);

--
-- Indexes for table `books`
--
ALTER TABLE `books`
  ADD PRIMARY KEY (`book_id`);

--
-- Indexes for table `book_label`
--
ALTER TABLE `book_label`
  ADD PRIMARY KEY (`junction_id`),
  ADD KEY `fk_junction_books_idx` (`book_id`),
  ADD KEY `fk_junction_labels_idx` (`label_id`);

--
-- Indexes for table `copies`
--
ALTER TABLE `copies`
  ADD PRIMARY KEY (`book_id`,`copy_id`),
  ADD KEY `fk_copies_books_idx` (`book_id`),
  ADD KEY `fk_copies_users_idx` (`loaned_by_user`),
  ADD KEY `copy_id_idx` (`copy_id`);

--
-- Indexes for table `labels`
--
ALTER TABLE `labels`
  ADD PRIMARY KEY (`label_id`);

--
-- Indexes for table `reservations`
--
ALTER TABLE `reservations`
  ADD PRIMARY KEY (`reservation_id`),
  ADD KEY `fk_reservations_books_idx` (`book_id`),
  ADD KEY `fk_reservations_users_idx` (`user_id`);

--
-- Indexes for table `transactions`
--
ALTER TABLE `transactions`
  ADD PRIMARY KEY (`transaction_id`),
  ADD KEY `fk_transactions_copies_idx` (`copy_id`),
  ADD KEY `fk_transactions_users_idx` (`user_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD KEY `email_idx` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admins`
--
ALTER TABLE `admins`
  MODIFY `admin_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `books`
--
ALTER TABLE `books`
  MODIFY `book_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `reservations`
--
ALTER TABLE `reservations`
  MODIFY `reservation_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `transactions`
--
ALTER TABLE `transactions`
  MODIFY `transaction_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `admins`
--
ALTER TABLE `admins`
  ADD CONSTRAINT `fk_admins_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `fk_email_users` FOREIGN KEY (`email`) REFERENCES `users` (`email`);

--
-- Constraints for table `book_label`
--
ALTER TABLE `book_label`
  ADD CONSTRAINT `fk_junction_books` FOREIGN KEY (`book_id`) REFERENCES `books` (`book_id`),
  ADD CONSTRAINT `fk_junction_labels` FOREIGN KEY (`label_id`) REFERENCES `labels` (`label_id`);

--
-- Constraints for table `copies`
--
ALTER TABLE `copies`
  ADD CONSTRAINT `fk_copies_books` FOREIGN KEY (`book_id`) REFERENCES `books` (`book_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_copies_users` FOREIGN KEY (`loaned_by_user`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `reservations`
--
ALTER TABLE `reservations`
  ADD CONSTRAINT `fk_reservations_books` FOREIGN KEY (`book_id`) REFERENCES `books` (`book_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_reservations_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `transactions`
--
ALTER TABLE `transactions`
  ADD CONSTRAINT `fk_transactions_copies` FOREIGN KEY (`copy_id`) REFERENCES `copies` (`copy_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_transactions_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
