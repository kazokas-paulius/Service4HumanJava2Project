CREATE TABLE `order_template` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`users_id` int(11) DEFAULT NULL,
`provider` varchar(255) DEFAULT NULL,
`language_from` varchar(255) DEFAULT NULL,
`language_to` varchar(255) DEFAULT NULL,
`order_date` date DEFAULT NULL,
`estimated_date` date DEFAULT NULL,
`price_hour` float(10) DEFAULT NULL,
`translation_text` varchar(3999) DEFAULT NULL,
PRIMARY KEY (`id`),
FOREIGN KEY (`users_id`) REFERENCES user_data(`id`)
);