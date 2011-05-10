DROP DATABASE IF EXISTS `dopplereffect`;
CREATE DATABASE `dopplereffect`;

CREATE TABLE `dopplereffect`.`career_data` (
  `id`          int(11)     NOT NULL AUTO_INCREMENT,
  `join_date`   timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `career_plan` varchar(30) NOT NULL,
  `level`       varchar(30) NOT NULL,
  `percentage`  int(11)     NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `dopplereffect`.`address` (
  `id`      int(11)       NOT NULL AUTO_INCREMENT,
  `street`  varchar(200)  NOT NULL,
  `number`  int(11)       NOT NULL,
  `city`    varchar(100)  NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `dopplereffect`.`personal_data` (
  `id`            int(11)       NOT NULL AUTO_INCREMENT,
  `first_name`    varchar(30)   NOT NULL,
  `phone_number`  varchar(30)   DEFAULT NULL,
  `email`         varchar(100)  DEFAULT NULL,
  `address_id`    int(11)       NOT NULL,
  PRIMARY KEY (`id`),
  KEY `personal_data_address_fk` (`address_id`),
  CONSTRAINT `personal_data_address_fk`
    FOREIGN KEY (`address_id`) REFERENCES `address` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `dopplereffect`.`employee_data` (
  `id`            int(11) NOT NULL AUTO_INCREMENT,
  `dni`           int(11) NOT NULL,
  `first_name`    varchar(30) NOT NULL,
  `last_name`     varchar(50) NOT NULL,
  `phone_number`  varchar(30) DEFAULT NULL,
  `email`         varchar(100) DEFAULT NULL,
  `address_id`    int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `employee_data_address_fk` (`address_id`),
  CONSTRAINT `employee_data_address_fk`
    FOREIGN KEY (`address_id`) REFERENCES `address` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `dopplereffect`.`employee` (
  `id`                int(11) NOT NULL AUTO_INCREMENT,
  `employee_data_id`  int(11) NOT NULL,
  `career_data_id`    int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `employee_employee_data_fk` (`employee_data_id`),
  KEY `employee_career_data_fk` (`career_data_id`),
  CONSTRAINT `employee_employee_data_fk`
    FOREIGN KEY (`employee_data_id`) REFERENCES `employee_data` (`id`),
  CONSTRAINT `employee_career_data_fk`
    FOREIGN KEY (`career_data_id`) REFERENCES `career_data` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ***********************************************************
-- Al ejecutar por primera vez, descomentar lo siguiente
-- y correr como root
-- ***********************************************************
-- CREATE USER 'dopplereffect' IDENTIFIED BY 'dopplereffect';
-- GRANT ALL ON dopplereffect.* TO 'dopplereffect';

