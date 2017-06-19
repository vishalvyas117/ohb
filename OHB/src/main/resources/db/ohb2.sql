/*
SQLyog Community
MySQL - 5.6.28-log : Database - ohb1
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `booking` */

CREATE TABLE `booking` (
  `booking_id` int(11) NOT NULL AUTO_INCREMENT,
  `booking_begin_date` datetime DEFAULT NULL,
  `booking_end_date` datetime DEFAULT NULL,
  `booking_status` bit(1) DEFAULT NULL,
  `room_id` int(11) DEFAULT NULL,
  `user_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `bookings_key` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`booking_id`),
  KEY `FKq83pan5xy2a6rn0qsl9bckqai` (`room_id`),
  KEY `FKkgseyy7t56x7lkjgu3wah5s3t` (`user_id`),
  CONSTRAINT `FKkgseyy7t56x7lkjgu3wah5s3t` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKq83pan5xy2a6rn0qsl9bckqai` FOREIGN KEY (`room_id`) REFERENCES `room` (`room_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `booking` */

LOCK TABLES `booking` WRITE;

UNLOCK TABLES;

/*Table structure for table `city` */

CREATE TABLE `city` (
  `city_id` int(11) NOT NULL AUTO_INCREMENT,
  `city_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`city_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `city` */

LOCK TABLES `city` WRITE;

insert  into `city`(`city_id`,`city_name`) values 
(1,'Noida'),
(2,'Pune'),
(3,'Delhi'),
(4,'Delhi'),
(5,'New Delhi');

UNLOCK TABLES;

/*Table structure for table `hotel` */

CREATE TABLE `hotel` (
  `hotel_id` int(11) NOT NULL AUTO_INCREMENT,
  `hotel_address` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `hotel_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `hotel_rating` int(11) DEFAULT NULL,
  `status` bit(1) NOT NULL,
  `category_id` int(11) DEFAULT NULL,
  `city_id` int(11) DEFAULT NULL,
  `user_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`hotel_id`),
  KEY `FKbokd03omc650xyj5hlc15w2a8` (`category_id`),
  KEY `FKf1iabdv6bi2yohh9h48wce42x` (`city_id`),
  KEY `FKi0j3nnn6eecin1ry6cujioqq2` (`user_id`),
  CONSTRAINT `FKbokd03omc650xyj5hlc15w2a8` FOREIGN KEY (`category_id`) REFERENCES `hotel_category` (`category_id`),
  CONSTRAINT `FKf1iabdv6bi2yohh9h48wce42x` FOREIGN KEY (`city_id`) REFERENCES `city` (`city_id`),
  CONSTRAINT `FKi0j3nnn6eecin1ry6cujioqq2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `hotel` */

LOCK TABLES `hotel` WRITE;

insert  into `hotel`(`hotel_id`,`hotel_address`,`hotel_name`,`hotel_rating`,`status`,`category_id`,`city_id`,`user_id`) values 
(1,'Plot No. 361/2 3/5, Opposite Pune Central Mall Bund Garden,Bund Garden Road','OHB Rooms Pune',3,'\0',NULL,2,NULL),
(2,'Plot No. 361/2 3/5, Opposite Pune Central Mall Bund Garden,Bund Garden Road','OHB Rooms 029 Bund Garden',4,'\0',NULL,2,NULL),
(3,'Plot No. 361/2 3/5, Opposite Pune Central Mall Bund Garden,Bund Garden Road','OHB Rooms 023 Bund Garden',3,'\0',NULL,2,NULL),
(4,'Plot No. 361/7 3/8, Opposite Pune Central Mall Bund Garden,Bund Garden Road','OHB Rooms 023 Bund Garden',2,'\0',NULL,2,NULL),
(5,'Plot No. 361/7 7/8, Opposite Pune Central Mall Bund Garden,Bund Garden Road','OHB Rooms 023 Bund Garden',4,'\0',4,2,NULL),
(6,'Plot No. 361/7 7/9, Opposite Pune Central Mall Bund Garden,Bund Garden Road','OHB Rooms 02hjg Bund Garden',3,'\0',NULL,2,NULL),
(7,'136 Sector 45','OHB Flagship',4,'\0',2,1,NULL),
(8,'004 Sector 51','OHB Townhouse',4,'\0',2,1,NULL);

UNLOCK TABLES;

/*Table structure for table `hotel_category` */

CREATE TABLE `hotel_category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `hotel_category` */

LOCK TABLES `hotel_category` WRITE;

insert  into `hotel_category`(`category_id`,`category_name`) values 
(1,'Luxury'),
(2,'Apartment Hotel'),
(3,'Motel'),
(4,'Bed and Breakfast'),
(5,'Resort');

UNLOCK TABLES;

/*Table structure for table `image` */

CREATE TABLE `image` (
  `image_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `insertion_date` datetime NOT NULL,
  `image_path` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `hotel_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`image_id`),
  KEY `FKnmctq4w6r7lkp880d4utoop2l` (`hotel_id`),
  CONSTRAINT `FKnmctq4w6r7lkp880d4utoop2l` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`hotel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `image` */

LOCK TABLES `image` WRITE;

UNLOCK TABLES;

/*Table structure for table `review` */

CREATE TABLE `review` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `comment_date` datetime DEFAULT NULL,
  `comment_status` bit(1) DEFAULT NULL,
  `comment_description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `hotel_id` int(11) DEFAULT NULL,
  `user_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `FKi0ly7ivbh8ijdgoi7cwtuoavt` (`hotel_id`),
  KEY `FKiyf57dy48lyiftdrf7y87rnxi` (`user_id`),
  CONSTRAINT `FKi0ly7ivbh8ijdgoi7cwtuoavt` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`hotel_id`),
  CONSTRAINT `FKiyf57dy48lyiftdrf7y87rnxi` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `review` */

LOCK TABLES `review` WRITE;

UNLOCK TABLES;

/*Table structure for table `room` */

CREATE TABLE `room` (
  `room_id` int(11) NOT NULL AUTO_INCREMENT,
  `floor` int(11) NOT NULL,
  `price` double DEFAULT NULL,
  `room_number` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `hotel_id` int(11) DEFAULT NULL,
  `room_type_id` int(11) DEFAULT NULL,
  `hotel_hotel_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`room_id`),
  KEY `FKdosq3ww4h9m2osim6o0lugng8` (`hotel_id`),
  KEY `FKd468eq7j1cbue8mk20qfrj5et` (`room_type_id`),
  KEY `FKf3qp5qppboq1vkxlmawalakf7` (`hotel_hotel_id`),
  CONSTRAINT `FKd468eq7j1cbue8mk20qfrj5et` FOREIGN KEY (`room_type_id`) REFERENCES `room_type` (`room_type_id`),
  CONSTRAINT `FKdosq3ww4h9m2osim6o0lugng8` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`hotel_id`),
  CONSTRAINT `FKf3qp5qppboq1vkxlmawalakf7` FOREIGN KEY (`hotel_hotel_id`) REFERENCES `hotel` (`hotel_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `room` */

LOCK TABLES `room` WRITE;

insert  into `room`(`room_id`,`floor`,`price`,`room_number`,`hotel_id`,`room_type_id`,`hotel_hotel_id`) values 
(1,2,1099,'204',1,2,1),
(2,2,1099,'201',1,2,NULL),
(3,2,1099,'203',1,2,1),
(4,2,1099,'203',1,2,1),
(5,2,799,'203',5,2,5),
(6,2,999,'201',5,3,5),
(7,2,999,'210',5,3,5),
(8,2,799,'209',5,3,5),
(9,2,799,'208',5,3,5),
(10,2,799,'207',5,3,5),
(11,2,799,'206',5,3,5),
(12,2,799,'205',5,3,5),
(13,2,799,'208',5,3,5);

UNLOCK TABLES;

/*Table structure for table `room_date_reserved` */

CREATE TABLE `room_date_reserved` (
  `room_room_id` int(11) NOT NULL,
  `days_reserved` int(11) DEFAULT NULL,
  `date_reserved_key` datetime NOT NULL,
  PRIMARY KEY (`room_room_id`,`date_reserved_key`),
  CONSTRAINT `FKbtdlhcscom1e1234bmg8mcjrr` FOREIGN KEY (`room_room_id`) REFERENCES `room` (`room_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `room_date_reserved` */

LOCK TABLES `room_date_reserved` WRITE;

UNLOCK TABLES;

/*Table structure for table `room_type` */

CREATE TABLE `room_type` (
  `room_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `occupancy` int(11) DEFAULT NULL,
  PRIMARY KEY (`room_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `room_type` */

LOCK TABLES `room_type` WRITE;

insert  into `room_type`(`room_type_id`,`description`,`occupancy`) values 
(1,'Single',1),
(2,'Double',2),
(3,'Studio',2),
(4,'Presidential Suite',6);

UNLOCK TABLES;

/*Table structure for table `user` */

CREATE TABLE `user` (
  `user_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `address` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `create_date` datetime NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `first_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `last_login` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `last_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `phone` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `status` int(11) DEFAULT NULL,
  `token` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `user` */

LOCK TABLES `user` WRITE;

UNLOCK TABLES;

/*Table structure for table `user_tokens` */

CREATE TABLE `user_tokens` (
  `token` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `expiration_date` datetime NOT NULL,
  `login_date` datetime NOT NULL,
  `user_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `user_tokens` */

LOCK TABLES `user_tokens` WRITE;

UNLOCK TABLES;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
