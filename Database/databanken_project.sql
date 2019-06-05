-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jun 05, 2019 at 02:35 PM
-- Server version: 5.7.19
-- PHP Version: 7.1.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `databanken_project`
--

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `user_id` int(255) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `status` int(255) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user_feed_providers`
--

DROP TABLE IF EXISTS `user_feed_providers`;
CREATE TABLE IF NOT EXISTS `user_feed_providers` (
  `user_id` int(255) NOT NULL,
  `provider_id` int(255) NOT NULL,
  `status` int(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `web_feed`
--

DROP TABLE IF EXISTS `web_feed`;
CREATE TABLE IF NOT EXISTS `web_feed` (
  `feed_id` int(255) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `link` varchar(1000) NOT NULL,
  `description` longtext NOT NULL,
  `published_date` varchar(255) NOT NULL,
  `imported_date` varchar(255) NOT NULL,
  `provider_id` varchar(255) NOT NULL,
  `image` varchar(555) NOT NULL,
  PRIMARY KEY (`feed_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `web_feed_providers`
--

DROP TABLE IF EXISTS `web_feed_providers`;
CREATE TABLE IF NOT EXISTS `web_feed_providers` (
  `provider_id` int(255) NOT NULL AUTO_INCREMENT,
  `name` int(255) NOT NULL,
  `link` longtext NOT NULL,
  `updated_date` varchar(255) NOT NULL,
  `num_feeds` int(255) NOT NULL,
  `error` int(255) NOT NULL,
  PRIMARY KEY (`provider_id`),
  UNIQUE KEY `link` (`link`(255))
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
