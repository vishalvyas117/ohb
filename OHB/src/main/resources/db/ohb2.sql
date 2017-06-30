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
  `booking_begin_date` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `booking_end_date` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `booking_status` bit(1) DEFAULT NULL,
  `user_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`booking_id`),
  KEY `FKkgseyy7t56x7lkjgu3wah5s3t` (`user_id`),
  CONSTRAINT `FKkgseyy7t56x7lkjgu3wah5s3t` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `booking` */

LOCK TABLES `booking` WRITE;

insert  into `booking`(`booking_id`,`booking_begin_date`,`booking_end_date`,`booking_status`,`user_id`) values 
(1,'06/28/2017','06/29/2017',0x,'ac6fd4e1efa04483b30f64a17c4396f6'),
(2,'06/28/2017','06/29/2017',0x,'ac6fd4e1efa04483b30f64a17c4396f6'),
(3,'06/28/2017','06/29/2017',0x,'ac6fd4e1efa04483b30f64a17c4396f6'),
(4,'06/28/2017','06/29/2017',0x,'ac6fd4e1efa04483b30f64a17c4396f6'),
(5,'06/28/2017','06/29/2017',0x,'ac6fd4e1efa04483b30f64a17c4396f6');

UNLOCK TABLES;

/*Table structure for table `booking_room` */

DROP TABLE IF EXISTS `booking_room`;

CREATE TABLE `booking_room` (
  `bookings_booking_id` int(11) NOT NULL,
  `room_room_id` int(11) NOT NULL,
  `bookings_key` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`bookings_booking_id`,`room_room_id`),
  KEY `FK8slc1wce3n4vous1wff6lgn6v` (`room_room_id`),
  CONSTRAINT `FK8slc1wce3n4vous1wff6lgn6v` FOREIGN KEY (`room_room_id`) REFERENCES `room` (`room_id`),
  CONSTRAINT `FKjvldgjf4oace9pdynnva1xchf` FOREIGN KEY (`bookings_booking_id`) REFERENCES `booking` (`booking_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `booking_room` */

LOCK TABLES `booking_room` WRITE;

insert  into `booking_room`(`bookings_booking_id`,`room_room_id`,`bookings_key`) values 
(2,2,NULL),
(3,3,NULL),
(4,4,NULL),
(5,5,NULL);

UNLOCK TABLES;

/*Table structure for table `city` */

DROP TABLE IF EXISTS `city`;

CREATE TABLE `city` (
  `city_id` int(11) NOT NULL AUTO_INCREMENT,
  `city_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`city_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `city` */

LOCK TABLES `city` WRITE;

insert  into `city`(`city_id`,`city_name`) values 
(1,'Noida'),
(2,'Pune'),
(3,'Delhi'),
(4,'Delhi'),
(5,'New Delhi'),
(6,'Mumbai'),
(7,'Goa'),
(8,'Indore'),
(9,'Ujjain'),
(10,'Agra'),
(11,'Aligarh'),
(12,'Chandigarh'),
(13,'Kanpur');

UNLOCK TABLES;

/*Table structure for table `hotel` */

DROP TABLE IF EXISTS `hotel`;

CREATE TABLE `hotel` (
  `hotel_id` int(11) NOT NULL AUTO_INCREMENT,
  `hotel_address` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `hotel_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `hotel_rating` int(11) DEFAULT NULL,
  `status` bit(1) NOT NULL,
  `category_id` int(11) NOT NULL,
  `city_id` int(11) NOT NULL,
  `user_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`hotel_id`),
  KEY `FKbokd03omc650xyj5hlc15w2a8` (`category_id`),
  KEY `FKf1iabdv6bi2yohh9h48wce42x` (`city_id`),
  KEY `FKi0j3nnn6eecin1ry6cujioqq2` (`user_id`),
  CONSTRAINT `FKbokd03omc650xyj5hlc15w2a8` FOREIGN KEY (`category_id`) REFERENCES `hotel_category` (`category_id`),
  CONSTRAINT `FKf1iabdv6bi2yohh9h48wce42x` FOREIGN KEY (`city_id`) REFERENCES `city` (`city_id`),
  CONSTRAINT `FKi0j3nnn6eecin1ry6cujioqq2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `hotel` */

LOCK TABLES `hotel` WRITE;

insert  into `hotel`(`hotel_id`,`hotel_address`,`hotel_name`,`hotel_rating`,`status`,`category_id`,`city_id`,`user_id`) values 
(3,'No 1, Sector 10,Belapur, Near CIDCO Bhavan','The Park Navi Mumbai',5,0x,5,5,'ac6fd4e1efa04483b30f64a17c4396f6'),
(4,'Phase II, Hinjawadi','Marunji Hinjawadi Phase 2',3,0x,2,1,'ac6fd4e1efa04483b30f64a17c4396f6'),
(5,'Dr D B Bandodkar Road, Panaji, Goa, 403001, India','Vivanta by Taj - Panaji, Goa',3,0x,1,7,'ac6fd4e1efa04483b30f64a17c4396f6'),
(6,'Varca Beach, Salcete, Varca, Goa, 403 721, India','Caravela Beach Resort',5,0x,5,7,'ac6fd4e1efa04483b30f64a17c4396f6'),
(7,'Mobor Cavelossim, Cavelossim, Goa, 403 731, India','The Leela Goa',5,0x,5,7,'ac6fd4e1efa04483b30f64a17c4396f6'),
(8,'178 &179 Pedda, Varca, Varca, Goa, 403 721, India','The Zuri White Sands, Goa Resort & Casino',5,0x,5,7,'ac6fd4e1efa04483b30f64a17c4396f6'),
(9,'Rajbag Beach, Canacona, Goa, India','The PentaCon Resort',5,0x,5,7,'ac6fd4e1efa04483b30f64a17c4396f6'),
(10,'Cavelossim Beach, Salcette, Cavelossim, Goa, 403731 India','Radisson Blu Resort Goa Cavelossim Beach',5,0x,5,7,'ac6fd4e1efa04483b30f64a17c4396f6'),
(11,'H-57 Sector 51, Near Manav Rachna International School, Noida, 201301, India','Mint Marbella Suites',3,0x,5,1,'ac6fd4e1efa04483b30f64a17c4396f6'),
(12,'Main Dadri Road NSEZ, Sector 82 Hotel White Castle Noida, Uttar Pradesh 201304 India','FabHotel WhiteCastleSEZ',3,0x,5,1,'ac6fd4e1efa04483b30f64a17c4396f6'),
(13,'D-72, D Block, Sector 47, Noida, India','OYO Rooms Noida Expressway Boulevard',3,0x,5,1,'ac6fd4e1efa04483b30f64a17c4396f6'),
(14,'A Block, Near Kanchanjunga Market, Sector – 53, Near Community Center, Noida, Uttar Pradesh, 201301, India','Hotel Sarthi',3,0x,2,1,'ac6fd4e1efa04483b30f64a17c4396f6'),
(15,'Plot No. SD-137, Sector 45, Noida, India','OYO Flagship Sector 45 Noida',3,0x,2,1,'ac6fd4e1efa04483b30f64a17c4396f6'),
(16,'C 1 Sector 18, Noida, Uttar Pradesh, 201301, India','Mosaic Hotels',4,0x,2,1,'ac6fd4e1efa04483b30f64a17c4396f6'),
(17,'Elixir Business Park Sector 127 Noida Uttar Pradesh 201304 India','Hide Away Suites',3,0x,2,1,'ac6fd4e1efa04483b30f64a17c4396f6'),
(18,'C-56 Sector 44, Noida, 201301, India','Ahuja Residency Sector 44 Noida',3,0x,2,1,'ac6fd4e1efa04483b30f64a17c4396f6'),
(19,'Plot No. C-7, Block C, Sector 61, Noida, Uttar Pradesh, 201301 India','OYO Flagship Shopprix Mall',3,0x,2,1,'ac6fd4e1efa04483b30f64a17c4396f6'),
(20,'No. 195 Assotech Business Cresterra, Sector 135, Noida Expressway, Noida, Uttar Pradesh, 201301, India','SandalSuites Operated By LemonTreeHotels',4,0x,1,1,'ac6fd4e1efa04483b30f64a17c4396f6');

UNLOCK TABLES;

/*Table structure for table `hotel_category` */

DROP TABLE IF EXISTS `hotel_category`;

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

DROP TABLE IF EXISTS `image`;

CREATE TABLE `image` (
  `image_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `insertion_date` datetime NOT NULL,
  `image_path` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `hotel_id` int(11) NOT NULL,
  `images_key` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`image_id`),
  KEY `FKnmctq4w6r7lkp880d4utoop2l` (`hotel_id`),
  CONSTRAINT `FKnmctq4w6r7lkp880d4utoop2l` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`hotel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `image` */

LOCK TABLES `image` WRITE;

UNLOCK TABLES;

/*Table structure for table `review` */

DROP TABLE IF EXISTS `review`;

CREATE TABLE `review` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `comment_date` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `comment_status` bit(1) DEFAULT NULL,
  `comment_description` varchar(10000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `hotel_id` int(11) NOT NULL,
  `user_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `comments_key` int(11) DEFAULT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `FKi0ly7ivbh8ijdgoi7cwtuoavt` (`hotel_id`),
  KEY `FKiyf57dy48lyiftdrf7y87rnxi` (`user_id`),
  CONSTRAINT `FKi0ly7ivbh8ijdgoi7cwtuoavt` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`hotel_id`),
  CONSTRAINT `FKiyf57dy48lyiftdrf7y87rnxi` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `review` */

LOCK TABLES `review` WRITE;

insert  into `review`(`comment_id`,`comment_date`,`comment_status`,`comment_description`,`hotel_id`,`user_id`,`comments_key`) values 
(2,'06/27/2017',0x01,'Our stay at Shanti Morada was perfect! They have the most amazing welcoming staff, the food is incredible, especially the veg tandoor platter. Get breakfast included if you can as it\'s totally worth it! They gave us a free upgrade, helped us renting a scooter for our stay, arranged for a taxi to the airport for us. We barely felt like we needed to leave the hotel as we were so comfortable.l and feeling at home. The area is very nice and quite, the harmony garden is a gem. Top notch! Loved it.',3,'ac6fd4e1efa04483b30f64a17c4396f6',NULL);

UNLOCK TABLES;

/*Table structure for table `room` */

DROP TABLE IF EXISTS `room`;

CREATE TABLE `room` (
  `room_id` int(11) NOT NULL AUTO_INCREMENT,
  `floor` int(11) NOT NULL,
  `price` double DEFAULT NULL,
  `room_number` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `hotel_id` int(11) NOT NULL,
  `room_type_id` int(11) NOT NULL,
  `rooms_key` int(11) DEFAULT NULL,
  PRIMARY KEY (`room_id`),
  KEY `FKdosq3ww4h9m2osim6o0lugng8` (`hotel_id`),
  KEY `FKd468eq7j1cbue8mk20qfrj5et` (`room_type_id`),
  CONSTRAINT `FKd468eq7j1cbue8mk20qfrj5et` FOREIGN KEY (`room_type_id`) REFERENCES `room_type` (`room_type_id`),
  CONSTRAINT `FKdosq3ww4h9m2osim6o0lugng8` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`hotel_id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `room` */

LOCK TABLES `room` WRITE;

insert  into `room`(`room_id`,`floor`,`price`,`room_number`,`hotel_id`,`room_type_id`,`rooms_key`) values 
(1,2,1799,'2108',3,2,NULL),
(2,2,1799,'218',3,2,NULL),
(3,2,1799,'219',3,2,NULL),
(4,2,1799,'210',3,2,NULL),
(5,2,1799,'206',3,2,NULL),
(6,2,1799,'201',3,2,NULL),
(7,2,1799,'202',3,2,NULL),
(8,2,1799,'203',3,2,NULL),
(9,2,1799,'204',3,2,NULL),
(10,2,1799,'205',3,2,NULL),
(11,2,1799,'206',3,2,NULL),
(12,3,1899,'301',3,3,NULL),
(13,3,1899,'302',3,3,NULL),
(14,3,1899,'303',3,3,NULL),
(15,3,1899,'304',3,3,NULL),
(16,3,1899,'305',3,3,NULL),
(17,3,1899,'306',3,3,NULL),
(18,3,1899,'308',3,3,NULL),
(19,3,1899,'309',3,3,NULL),
(20,3,1899,'310',3,3,NULL),
(21,3,1899,'302',4,3,NULL),
(22,3,1899,'303',4,3,NULL),
(23,3,1899,'304',4,3,NULL),
(24,3,1899,'305',4,3,NULL),
(25,3,1899,'306',4,3,NULL),
(26,3,1899,'307',4,3,NULL),
(27,2,3099,'2010',5,2,NULL),
(28,2,3099,'2011',5,2,NULL),
(29,2,3099,'2012',5,2,NULL),
(30,2,3099,'2013',5,2,NULL),
(31,2,3099,'2014',5,2,NULL),
(32,1,2099,'1014',6,2,NULL),
(33,1,2099,'1015',6,2,NULL),
(34,1,2099,'1016',6,2,NULL),
(35,1,2099,'1017',6,2,NULL),
(36,1,2099,'1018',6,2,NULL),
(37,1,2099,'1019',6,2,NULL),
(38,1,2099,'1020',6,2,NULL),
(42,6,5099,'603',7,4,NULL),
(43,6,5099,'602',7,4,NULL),
(44,6,5099,'601',7,4,NULL),
(45,6,5099,'606',7,4,NULL),
(46,6,5099,'607',7,4,NULL),
(47,6,5099,'608',7,4,NULL),
(48,6,5099,'609',7,4,NULL);

UNLOCK TABLES;

/*Table structure for table `room_date_reserved` */

DROP TABLE IF EXISTS `room_date_reserved`;

CREATE TABLE `room_date_reserved` (
  `room_room_id` int(11) NOT NULL,
  `days_reserved` int(11) DEFAULT NULL,
  `date_reserved_key` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`room_room_id`,`date_reserved_key`),
  CONSTRAINT `FKbtdlhcscom1e1234bmg8mcjrr` FOREIGN KEY (`room_room_id`) REFERENCES `room` (`room_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `room_date_reserved` */

LOCK TABLES `room_date_reserved` WRITE;

insert  into `room_date_reserved`(`room_room_id`,`days_reserved`,`date_reserved_key`) values 
(2,2,'06/28/2017'),
(2,2,'06/29/2017'),
(4,4,'06/28/2017'),
(4,4,'06/29/2017'),
(5,5,'06/28/2017'),
(5,5,'06/29/2017');

UNLOCK TABLES;

/*Table structure for table `room_type` */

DROP TABLE IF EXISTS `room_type`;

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

LOCK TABLES `user` WRITE;

insert  into `user`(`user_id`,`address`,`create_date`,`email`,`first_name`,`last_login`,`last_name`,`password`,`phone`,`status`,`token`,`user_name`) values 
('ac6fd4e1efa04483b30f64a17c4396f6','3/10,Ashoka Road Shipra sun city Indirapuram ghaziabad','2017-06-27 12:33:43','nsnikita87@gmail.com','Nikita',NULL,'Sharma','5c87b162946631dd944d616cad5fea4d','8982560559',1,NULL,'nsnikita87');

UNLOCK TABLES;

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

LOCK TABLES `user_tokens` WRITE;

UNLOCK TABLES;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
