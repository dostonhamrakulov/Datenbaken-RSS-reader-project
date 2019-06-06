

create schema databanken_project;

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


DROP TABLE IF EXISTS `user_feed_providers`;
CREATE TABLE IF NOT EXISTS `user_feed_providers` (
  `user_id` int(255) NOT NULL,
  `provider_id` int(255) NOT NULL,
  `status` int(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------


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
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `link` longtext NOT NULL,
  `updated_date` varchar(255) NOT NULL,
  `num_feeds` int(255) NOT NULL,
  `error` int(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
COMMIT;


-- ,
--  UNIQUE KEY `link` (`link`(255)

select version();

SHOW VARIABLES LIKE "%version%";


create table user(
id int(255),
name varchar(255),
email varchar(355)
);

insert into my_tab values(1, "Doston");


SET GLOBAL time_zone = '+5:30'

select * from user;


GRANT ALL PRIVILEGES ON *.* TO 'root'@'192.168.0.104' IDENTIFIED BY 'root2019' WITH GRANT OPTION;
 FLUSH PRIVILEGES;
 
 
 
ALTER TABLE web_feed
MODIFY COLUMN provider_id int(255);

ALTER TABLE web_feed_providers
MODIFY COLUMN name VARCHAR(255);

INSERT INTO `web_feed`(`feed_id`, `title`, `link`, `description`, `published_date`, `imported_date`, `provider_id`, `image`) VALUES (1,'Title1','Link1','Description1','2019/01/01','2019/05/05',1,'img_src_1');

INSERT INTO `web_feed`(`feed_id`, `title`, `link`, `description`, `published_date`, `imported_date`, `provider_id`, `image`) VALUES (2,'Title2','Link2','Description2','2019/01/02','2019/05/06',2,'img_src_2');

INSERT INTO `web_feed`(`feed_id`, `title`, `link`, `description`, `published_date`, `imported_date`, `provider_id`, `image`) VALUES (3,'Title3','Link3','Description3','2019/01/03','2019/05/07',3,'img_src_3');


INSERT INTO `users`(`user_id`, `email`, `name`, `password`, `status`) VALUES (1,'user1@gmail.com','user1','user1_pass',1);
INSERT INTO `users`(`user_id`, `email`, `name`, `password`, `status`) VALUES (2,'user2@gmail.com','user2','user2_pass',1);
INSERT INTO `users`(`user_id`, `email`, `name`, `password`, `status`) VALUES (3,'user3@gmail.com','user3','user3_pass',1);

INSERT INTO `web_feed_providers`(`id`, `name`, `link`, `updated_date`, `num_feeds`, `error`) VALUES (1,'wordpress','http://wordpress.org/news/feed/','2018/09/09','10',0);
INSERT INTO `web_feed_providers`(`id`, `name`, `link`, `updated_date`, `num_feeds`, `error`) VALUES (2,'BBC','http://feeds.bbci.co.uk/news/world/europe/rss.xml','2018/04/07','13',0);
INSERT INTO `web_feed_providers`(`id`, `name`, `link`, `updated_date`, `num_feeds`, `error`) VALUES (3,'Random','https://www.zdnet.com/news/rss.xml','2018/02/03','11',0);


INSERT INTO `user_feed_providers`(`user_id`, `provider_id`, `status`) VALUES (1,1,1);
INSERT INTO `user_feed_providers`(`user_id`, `provider_id`, `status`) VALUES (2,2,2);
INSERT INTO `user_feed_providers`(`user_id`, `provider_id`, `status`) VALUES (3,3,3); 


select * from web_feed_providers;
describe web_feed_providers;