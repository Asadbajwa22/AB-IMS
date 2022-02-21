drop schema ims;

CREATE SCHEMA IF NOT EXISTS `ims`;

USE `ims` ;

CREATE TABLE IF NOT EXISTS `ims`.`customers` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) DEFAULT NULL,
    `surname` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`id`)
);



CREATE TABLE IF NOT EXISTS `ims`.`item` (
    ID int AUTO_INCREMENT,
    Name varchar(255),
    item_value varchar(255),
    PRIMARY KEY (ID)
);


CREATE TABLE IF NOT EXISTS `ims`.`orders` (
    id int NOT NULL AUTO_INCREMENT,
    customer_id int,
    item_id int,
    PRIMARY KEY (id),
    FOREIGN KEY (customer_id) REFERENCES customers(ID),
    FOREIGN KEY (item_id) REFERENCES item(ID)
);