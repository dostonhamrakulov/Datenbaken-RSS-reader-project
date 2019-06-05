
SHOW DATABASES;

CREATE SCHEMA StudentsInfo;	

CREATE TABLE Students
(
StudentID int,
StudentName varchar(255),
ParentName varchar(255),
Address varchar(255),
PostalCode int,
City varchar(255)
);

CREATE TABLE ExampleTable AS
SELECT Studentname, Parentname
FROM Students;

USE StudentsInfo;

INSERT INTO Students(StudentID, StudentName, ParentName, Address, PostalCode, City)
VALUES (1, 'Doston', 'Banjara Hills', 'Reinchainer str 37', 09126, 'Chemnitz');
 
INSERT INTO Students
VALUES (2, 'Bek','Camel John', 'Olcha str 23', 700096, 'Tashkent');

SET SQL_SAFE_UPDATES = 0;
UPDATE Students SET StudentName = 'Doston', City= 'Frankfurt' WHERE StudentID = 1;

select * from Students;

CREATE TABLE `PLAYER` (
  `PLAYER_ID` INT(6) NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(50) NOT NULL,
  `AGE` INT(3) NOT NULL,
  `MATCHES` INT(3) NOT NULL,
  PRIMARY KEY (`PLAYER_ID`)
);

INSERT INTO `PLAYER`(`NAME`, `AGE`, `MATCHES`) 
            VALUES ("Sachin Tendulkar",41,200);
INSERT INTO `PLAYER`(`NAME`, `AGE`, `MATCHES`) 
                VALUES ("Shane Warne",44,145);
INSERT INTO `PLAYER`(`NAME`, `AGE`, `MATCHES`) 
            VALUES ("Kevin Pietersen",34,104);
INSERT INTO `PLAYER`(`NAME`, `AGE`, `MATCHES`) 
                VALUES ("Shahid Afridi",35,27);
INSERT INTO `PLAYER`(`NAME`, `AGE`, `MATCHES`) 
                VALUES ("Brian Lara",45,131);
INSERT INTO `PLAYER`(`NAME`, `AGE`, `MATCHES`) 
                VALUES ("Graeme Smith",34,117);
INSERT INTO `PLAYER`(`NAME`, `AGE`, `MATCHES`) 
            VALUES ("Mahela Jayawardene",37,145);
            
select * from PLAYER;         

  

