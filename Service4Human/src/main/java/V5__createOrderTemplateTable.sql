CREATE TABLE `order_template` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`user` int(11) DEFAULT NULL,
`provider` varchar(255) DEFAULT NULL,
`language_from` varchar(255) DEFAULT NULL,
`language_to` varchar(255) DEFAULT NULL,
`price` float(10) DEFAULT NULL,
PRIMARY KEY (`id`)
);