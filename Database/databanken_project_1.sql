CREATE DATABASE `Datenbanken` /*!40100 DEFAULT CHARACTER SET latin1 */;

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