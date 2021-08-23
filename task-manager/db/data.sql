/*
SQLyog Ultimate v12.5.1 (64 bit)
MySQL - 5.7.22-log : Database - task_manager
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`task_manager` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `task_manager`;

/*Data for the table `projects` */

insert  into `projects`(`id`,`name`,`user_id`) values
(1,'Project 1',1),
(2,'Project 2',1),
(3,'Project 3',2);

/*Data for the table `roles` */

insert  into `roles`(`id`,`name`) values
(1,'ADMIN'),
(2,'USER');

/*Data for the table `tasks` */

insert  into `tasks`(`id`,`name`,`project_id`,`user_id`,`status`,`description`,`create_date`,`start_date`,`end_date`) values
(1,'Task 1',1,1,'OPEN','Task 1 Description','2021-08-29 19:55:44',NULL,'2021-08-25 21:50:14'),
(2,'Task 2',1,1,'OPEN','Task 2 Description','2021-08-21 23:28:24',NULL,'2021-08-30 21:50:24'),
(3,'Task 3',3,2,'OPEN','Task 3 Description','2021-08-23 15:20:07',NULL,NULL);

/*Data for the table `users` */

insert  into `users`(`id`,`firstname`,`lastname`,`username`,`email`,`role_id`,`password`) values
(1,'Mr','John','admin','admin@gmail.com',1,'$2a$10$77lnePPrk6zi7kKAlfSk6.sS/7aEjqMOdbqtqus3MKoO4DLCy0hi2'),
(2,'Mr','Tom','user','user@gmail.com',2,'$2a$10$77lnePPrk6zi7kKAlfSk6.sS/7aEjqMOdbqtqus3MKoO4DLCy0hi2');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
