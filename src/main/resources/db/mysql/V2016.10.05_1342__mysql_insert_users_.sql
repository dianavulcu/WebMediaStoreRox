CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(200) NOT NULL,
  `first_name` varchar(70) DEFAULT NULL,
  `last_name` varchar(70) DEFAULT NULL,
  `email` varchar(70) NOT NULL,
  `password` varchar(70) DEFAULT NULL,
  `uuid` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=big5;