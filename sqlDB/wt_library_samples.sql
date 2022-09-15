-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 14, 2022 at 12:52 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6



INSERT INTO `books` (`id`, `title`, `author`, `isbn`, `cover_image`) VALUES
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
(11, 'C# Programming in easy steps, 2nd edition', 'Mikey \"The braggart\" Mcgrath', '9781840789157', NULL),
(12, 'How to get Harry', 'Harry Bottoms', '696969696969', NULL);

-- --------------------------------------------------------

INSERT INTO `copies` (`book_id`, `id`, `version`, `user_id`) VALUES
(1, 1, 1, NULL),
(1, 2, 2, NULL),
(2, 3, 1, NULL),
(2, 4, 2, NULL),
(2, 5, 3, NULL),
(3, 6, 1, NULL),
(3, 7, 2, NULL),
(4, 8, 1, NULL),
(4, 9, 2, NULL),
(5, 10, 1, NULL),
(5, 11, 2, NULL),
(6, 12, 1, NULL),
(6, 13, 2, NULL),
(7, 14, 1, NULL),
(7, 15, 2, NULL),
(8, 16, 1, NULL),
(8, 17, 2, NULL),
(9, 18, 1, NULL),
(9, 19, 2, NULL),
(10, 20, 1, NULL),
(10, 21, 2, NULL);

-- -------------------------------------------------------

-- --------------------------------------------------------

INSERT INTO `users` (`id`, `first_name`, `last_name`, `email`, `passphrase`, `active`, `create_time`, `role`) VALUES
(1, 'Harry', 'Bottoms', 'getting@harry.com', '$2a$10$JEAsNlWraIH2GzLi5ozmW.6Y6y1QDcPNHU6qTaZPNH4im3Wbo0u/y', 1, '2022-08-31 11:42:13', 1),
(2, 'Mike', 'Hawk', 'Mike@Hawk.com', '$2a$12$KwUFQe67K.aoPU6hVNEcBuFVNgmDIxjFXGYbUlJW8U.CDxPpZ8lTi', 1, '2022-08-31 11:42:13', 2),
(3, 'Ivana', 'Tinkle', 'Tinkle@live.com', '$2a$12$NZU9H2f8L6zWmljrJCzMfOPMyH5AsXIxANY0X1A45nnGnVEilLwk.', 1, '2022-08-31 11:42:13', 2),
(4, 'I.P.', 'Daily', 'Arlis.Latorre@EasyMailer.live', '$2a$10$ZHj6aQXUpj5DY0RI/IQlXOT6xRgXDkh62knMEUyekSqLrTgsamjEa', 1, '2022-09-08 14:07:26', 2),
(6, 'Marry', 'Oftens', 'yandere@live.com', '$2a$10$DmTXyFvhdrEc2GK4t3MS7Otx/fEcsJqW1zelzqUH1.WQ4tZFijhcO', 1, '2022-09-12 15:26:23', 2);
