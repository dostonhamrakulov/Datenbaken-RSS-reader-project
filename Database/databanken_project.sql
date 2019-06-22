

-- create schema databanken_project_main;

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `status` int(255) NOT NULL,
  `feedage` int(255) NOT NULL,
  `updateperiod` int(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------


-- DROP TABLE IF EXISTS `user_feed_providers`;
-- CREATE TABLE IF NOT EXISTS `user_feed_providers` (
--   `user_id` int(255) NOT NULL,
--   `provider_id` int(255) NOT NULL,
--   `status` int(255) NOT NULL
-- ) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------


DROP TABLE IF EXISTS `web_feed`;
CREATE TABLE IF NOT EXISTS `web_feed` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `link` varchar(1000) NOT NULL,
  `description` longtext NOT NULL,
  `publisheddate` varchar(255) NOT NULL,
  `importeddate` varchar(255) NOT NULL,
  `providerid` int(255) NOT NULL DEFAULT 1000,
  `deleted` varchar(255) NOT NULL,
  `error` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY `ft_wfid` (`providerid`) REFERENCES `web_feed_providers` (`id`)
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
  `updateddate` varchar(255) NOT NULL,
  `latestrecorddate` varchar(255) NOT NULL, 
  `lastattempt` varchar(255) NOT NULL,
  `numfeeds` int(255) NOT NULL,
  `error` int(255) NOT NULL,
  `userid` int(255) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY `ft_provider` (`userid`) REFERENCES `user` (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
COMMIT;


-- ,
--  UNIQUE KEY `link` (`link`(255)



-- SHOW VARIABLES LIKE "%version%";




SET GLOBAL time_zone = '+5:30';

-- select * from user;
-- ALTER USER 'databanken_project'@'localhost' IDENTIFIED BY 'root2019';
--  CREATE USER 'root1'@'localhost' IDENTIFIED BY 'root2019';
-- GRANT ALL PRIVILEGES ON *.* TO 'root1'@'localhost' IDENTIFIED BY 'root2019' WITH GRANT OPTION;
--  FLUSH PRIVILEGES;
 



-- INSERT INTO `web_feed`(`id`, `title`, `link`, `description`, `published_date`, `imported_date`, `providerid`, `image`) VALUES (1,'Title1','Link1','Description1','2019/01/01','2019/05/05',1000,'img_src_1');
-- 
-- INSERT INTO `web_feed`(`id`, `title`, `link`, `description`, `published_date`, `imported_date`, `providerid`, `image`) VALUES (2,'Title2','Link2','Description2','2019/01/02','2019/05/06',2,'img_src_2');
-- 
-- INSERT INTO `web_feed`(`id`, `title`, `link`, `description`, `published_date`, `imported_date`, `providerid`, `image`) VALUES (3,'Title3','Link3','Description3','2019/01/03','2019/05/07',3,'img_src_3');
-- 
-- 
INSERT INTO `user`(`id`, `email`, `name`, `password`, `status`, `feedage`, `updateperiod`) VALUES (101,'user1@gmail.com','user1','user1_pass',1, 30, 5);
-- INSERT INTO `user`(`id`, `email`, `name`, `password`, `status`, `feedage`, `updateperiod`) VALUES (102,'user2@gmail.com','user2','user2_pass',1, 30, 1);
-- INSERT INTO `user`(`id`, `email`, `name`, `password`, `status`, `feedage`, `updateperiod`) VALUES (103,'user3@gmail.com','user3','user3_pass',1, 30, 5);
-- 
--   
-- INSERT INTO `web_feed_providers`(`id`, `name`, `link`, `updateddate`,`latestrecorddate`,`lastattempt`, `numfeeds`, `error`, `userid`) VALUES (1000,'NEW YORK Times','https://rss.nytimes.com/services/xml/rss/nyt/HomePage.xml','2018/09/09','2018/09/09','2018/09/09','10',0, 101);
-- INSERT INTO `web_feed_providers`(`id`, `name`, `link`, `updateddate`,`latestrecorddate`,`lastattempt`, `numfeeds`, `error`, `userid`) VALUES (1001,'BBC','http://feeds.bbci.co.uk/news/world/europe/rss.xml','2018/04/07','2018/09/09','2018/09/09','13',0,101);
-- INSERT INTO `web_feed_providers`(`id`, `name`, `link`, `updateddate`,`latestrecorddate`,`lastattempt`, `numfeeds`, `error`, `userid`) VALUES (1003,'ZDnet news','https://www.zdnet.com/news/rss.xml','2018/02/03','2018/09/09','2018/09/09','11',0, 1022);
-- 
-- 
-- INSERT INTO `user_feed_providers`(`id`, `provider_id`, `status`) VALUES (1,1,1);
-- INSERT INTO `user_feed_providers`(`id`, `provider_id`, `status`) VALUES (2,2,2);
-- INSERT INTO `user_feed_providers`(`id`, `provider_id`, `status`) VALUES (3,3,3); 


select * from web_feed_providers where userid = 1022;
describe web_feed_providers;
select * from web_feed;
select * from web_feed where providerid = 786;
select * from user;

-- update user s set s.feedage = 100 where s.id = 101;
-- update web_feed w set w.title = 'updated title' where w.id = 1322;
-- delete from web_feed_providers where userid = 1022;
-- 
-- update web_feed w set w.importeddate = 'Mon, 10 Feb 2019 23:20:00 CEST' where w.id = 1500;
-- update web_feed w set w.importeddate = 'Mon, 10 Feb 2019 23:20:00 CEST' where w.id = 1501;
-- 
-- select * from web_feed_providers wf where wf.userid = 101;


-- select count(*) from web_feed where providerid = 1;
-- 
-- select count(*) from web_feed where error = "False" and providerid = 4461;
-- select * from web_feed;
-- select * from web_feed_providers;
-- update web_feed set error = 'True' where providerid = 1 and id < 5;

-- update web_feed set deleted = "True" where id = 3334433;

-- insert into web_feed value(55, "title", "link", "decs", "Thu, 20 Jun 2019 22:58:22 CEST", "Thu, 20 Jun 2019 22:58:22 CEST", 1, "False", "False"),
-- (56, "title", "link", "decs", "Thu, 20 Jun 2019 22:58:22 CEST", "Thu, 20 Jun 2019 22:58:22 CEST", 1, "False", "False"),
-- (57, "title", "link", "decs", "Thu, 20 Jun 2019 22:58:22 CEST", "Thu, 20 Jun 2019 22:58:22 CEST", 1, "False", "False"),
-- (58, "title", "link", "decs", "Thu, 20 Jun 2019 22:58:22 CEST", "Thu, 20 Jun 2019 22:58:22 CEST", 1, "False", "False");
-- 
SHOW GRANTS;