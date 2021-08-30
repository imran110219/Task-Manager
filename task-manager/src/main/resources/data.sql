/*Data for the table `roles` */

insert  into `roles`(`id`,`name`) values
(1,'ADMIN'),
(2,'USER');

/*Data for the table `users` */

insert  into `users`(`id`,`firstname`,`lastname`,`username`,`email`,`role_id`,`password`) values
(1,'Mr','John','admin','admin@gmail.com',1,'$2a$10$77lnePPrk6zi7kKAlfSk6.sS/7aEjqMOdbqtqus3MKoO4DLCy0hi2'),
(2,'Mr','Tom','user','user@gmail.com',2,'$2a$10$77lnePPrk6zi7kKAlfSk6.sS/7aEjqMOdbqtqus3MKoO4DLCy0hi2');

/*Data for the table `projects` */

insert  into `projects`(`id`,`name`,`user_id`) values
(1,'Project 1',1),
(2,'Project 2',1),
(3,'Project 3',2),
(4,'Project 4',2);

/*Data for the table `tasks` */

insert  into `tasks`(`id`,`name`,`project_id`,`user_id`,`status`,`description`,`create_date`,`start_date`,`end_date`) values
(1,'Task 1',1,1,'OPEN','Task 1 Description','2021-08-29 19:55:44',NULL,'2021-08-25 21:50:14'),
(2,'Task 2',1,1,'OPEN','Task 2 Description','2021-08-21 23:28:24',NULL,'2021-08-30 06:00:00'),
(3,'Task 3',3,2,'CLOSED','Task 3 Description','2021-08-23 15:20:07',NULL,NULL),
(4,'Task Admin',1,1,'INPROGRESS','description','2021-08-26 22:44:17',NULL,'2022-12-23 06:00:00');