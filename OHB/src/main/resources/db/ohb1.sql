/*
SQLyog Community v12.4.2 (64 bit)
MySQL - 5.6.28-log : Database - ohb1
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ohb1` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `ohb1`;

/*Table structure for table `booking` */

DROP TABLE IF EXISTS `booking`;

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

/*Table structure for table `city` */

DROP TABLE IF EXISTS `city`;

CREATE TABLE `city` (
  `city_id` int(11) NOT NULL AUTO_INCREMENT,
  `city_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`city_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `city` */

insert  into `city`(`city_id`,`city_name`) values 
(1,'Noida'),
(2,'Pune'),
(3,'Delhi'),
(4,'Delhi'),
(5,'New Delhi');

/*Table structure for table `hotel` */

DROP TABLE IF EXISTS `hotel`;

CREATE TABLE `hotel` (
  `hotel_id` int(11) NOT NULL AUTO_INCREMENT,
  `hotel_address` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `hotel_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `hotel_rating` int(11) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `city_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`hotel_id`),
  KEY `FKbokd03omc650xyj5hlc15w2a8` (`category_id`),
  KEY `FKf1iabdv6bi2yohh9h48wce42x` (`city_id`),
  CONSTRAINT `FKbokd03omc650xyj5hlc15w2a8` FOREIGN KEY (`category_id`) REFERENCES `hotel_category` (`category_id`),
  CONSTRAINT `FKf1iabdv6bi2yohh9h48wce42x` FOREIGN KEY (`city_id`) REFERENCES `city` (`city_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `hotel` */

insert  into `hotel`(`hotel_id`,`hotel_address`,`hotel_name`,`hotel_rating`,`category_id`,`city_id`) values 
(1,'Plot No. 361/2 3/5, Opposite Pune Central Mall Bund Garden,Bund Garden Road','OHB Rooms Pune',3,NULL,2),
(2,'Plot No. 361/2 3/5, Opposite Pune Central Mall Bund Garden,Bund Garden Road','OHB Rooms 029 Bund Garden',4,NULL,2),
(3,'Plot No. 361/2 3/5, Opposite Pune Central Mall Bund Garden,Bund Garden Road','OHB Rooms 023 Bund Garden',3,NULL,2),
(4,'Plot No. 361/7 3/8, Opposite Pune Central Mall Bund Garden,Bund Garden Road','OHB Rooms 023 Bund Garden',2,NULL,2),
(5,'Plot No. 361/7 7/8, Opposite Pune Central Mall Bund Garden,Bund Garden Road','OHB Rooms 023 Bund Garden',4,NULL,2),
(6,'Plot No. 361/7 7/9, Opposite Pune Central Mall Bund Garden,Bund Garden Road','OHB Rooms 02hjg Bund Garden',3,NULL,2),
(7,'136 Sector 45','OHB Flagship',4,2,1),
(8,'004 Sector 51','OHB Townhouse',4,2,1);

/*Table structure for table `hotel_category` */

DROP TABLE IF EXISTS `hotel_category`;

CREATE TABLE `hotel_category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `hotel_category` */

insert  into `hotel_category`(`category_id`,`category_name`) values 
(1,'Luxury'),
(2,'Apartment Hotel'),
(3,'Motel'),
(4,'Bed and Breakfast'),
(5,'Resort');

/*Table structure for table `hotel_images` */

DROP TABLE IF EXISTS `hotel_images`;

CREATE TABLE `hotel_images` (
  `hotel_hotel_id` int(11) NOT NULL,
  `images` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  KEY `FK4n29dpw8hm4akhehk81f5seiv` (`hotel_hotel_id`),
  CONSTRAINT `FK4n29dpw8hm4akhehk81f5seiv` FOREIGN KEY (`hotel_hotel_id`) REFERENCES `hotel` (`hotel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `hotel_images` */

/*Table structure for table `image` */

DROP TABLE IF EXISTS `image`;

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

/*Table structure for table `review` */

DROP TABLE IF EXISTS `review`;

CREATE TABLE `review` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `comment_date` datetime DEFAULT NULL,
  `comment_status` bit(1) DEFAULT NULL,
  `comment_description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `hotel_id` int(11) DEFAULT NULL,
  `user_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `comment_key` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `FKi0ly7ivbh8ijdgoi7cwtuoavt` (`hotel_id`),
  KEY `FKiyf57dy48lyiftdrf7y87rnxi` (`user_id`),
  CONSTRAINT `FKi0ly7ivbh8ijdgoi7cwtuoavt` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`hotel_id`),
  CONSTRAINT `FKiyf57dy48lyiftdrf7y87rnxi` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `review` */

/*Table structure for table `room` */

DROP TABLE IF EXISTS `room`;

CREATE TABLE `room` (
  `room_id` int(11) NOT NULL AUTO_INCREMENT,
  `floor` int(11) NOT NULL,
  `price` double DEFAULT NULL,
  `room_number` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `hotel_id` int(11) DEFAULT NULL,
  `room_type_id` int(11) DEFAULT NULL,
  `rooms_key` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`room_id`),
  KEY `FKdosq3ww4h9m2osim6o0lugng8` (`hotel_id`),
  KEY `FKd468eq7j1cbue8mk20qfrj5et` (`room_type_id`),
  CONSTRAINT `FKd468eq7j1cbue8mk20qfrj5et` FOREIGN KEY (`room_type_id`) REFERENCES `room_type` (`room_type_id`),
  CONSTRAINT `FKdosq3ww4h9m2osim6o0lugng8` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`hotel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `room` */

/*Table structure for table `room_date_reserved` */

DROP TABLE IF EXISTS `room_date_reserved`;

CREATE TABLE `room_date_reserved` (
  `room_room_id` int(11) NOT NULL,
  `days_reserved` int(11) DEFAULT NULL,
  `date_reserved_key` datetime NOT NULL,
  PRIMARY KEY (`room_room_id`,`date_reserved_key`),
  CONSTRAINT `FKbtdlhcscom1e1234bmg8mcjrr` FOREIGN KEY (`room_room_id`) REFERENCES `room` (`room_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `room_date_reserved` */

/*Table structure for table `room_days_reserved` */

DROP TABLE IF EXISTS `room_days_reserved`;

CREATE TABLE `room_days_reserved` (
  `room_room_id` int(11) NOT NULL,
  `days_reserved` int(11) DEFAULT NULL,
  `days_reserved_key` datetime NOT NULL,
  PRIMARY KEY (`room_room_id`,`days_reserved_key`),
  CONSTRAINT `FKngokkktet3cgvitw4snf1y118` FOREIGN KEY (`room_room_id`) REFERENCES `room` (`room_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `room_days_reserved` */

/*Table structure for table `room_type` */

DROP TABLE IF EXISTS `room_type`;

CREATE TABLE `room_type` (
  `room_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `occupancy` int(11) DEFAULT NULL,
  PRIMARY KEY (`room_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `room_type` */

insert  into `room_type`(`room_type_id`,`description`,`occupancy`) values 
(1,'Single',1),
(2,'Double',2),
(3,'Studio',2),
(4,'Presidential Suite',6);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

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

insert  into `user`(`user_id`,`address`,`create_date`,`email`,`first_name`,`last_login`,`last_name`,`password`,`phone`,`status`,`token`,`user_name`) values 
('127e4ca3e137484d96781057ec646dd1','3/10,Ashoka Road Shipra sun city Indirapuram ghaziabad','2017-06-16 14:28:22','nsnikita86@gmail.com','Nikita',NULL,'Sharma','48d0415192834f7adc319ba3c02d2610','8982560559',1,NULL,'nsnikita86');

/*Table structure for table `user_tokens` */

DROP TABLE IF EXISTS `user_tokens`;

CREATE TABLE `user_tokens` (
  `token` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `expiration_date` datetime NOT NULL,
  `login_date` datetime NOT NULL,
  `user_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `user_tokens` */

insert  into `user_tokens`(`token`,`expiration_date`,`login_date`,`user_id`) values 
('d9330f5b45ff47a9806696f16fcea74c','2017-07-01 08:58:42','2017-06-16 08:58:42','127e4ca3e137484d96781057ec646dd1');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
