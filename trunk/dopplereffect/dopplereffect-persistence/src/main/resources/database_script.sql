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

CREATE TABLE `dopplereffect`.`leave_request_type` (
  `id`                int(11)       NOT NULL AUTO_INCREMENT,
  `subclass_type`     varchar(100)  NOT NULL,
  `reason`            varchar(100)  DEFAULT NULL,
  `max_days_in_year`  int(11)       DEFAULT NULL,
  `min_limit`         int(11)       DEFAULT NULL,
  `max_limit`         int(11)       DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `dopplereffect`.`duration_strategy` (
  `id`            int(11)       NOT NULL AUTO_INCREMENT,
  `subclass_type` varchar(100)  NOT NULL,
  `date`          timestamp     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `start_date`    timestamp     NOT NULL DEFAULT '0000-00-00 00:00:00',
  `end_date`      timestamp     NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE  `dopplereffect`.`assignable` (
  `id`          int(11) NOT NULL AUTO_INCREMENT,
  `employee_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `assignable_employee_fk` (`employee_id`),
  CONSTRAINT `assignable_employee_fk`
    FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE  `dopplereffect`.`leave_request` (
  `id`                    int(11) NOT NULL AUTO_INCREMENT,
  `assignable_id`         int(11) NOT NULL,
  `type_id`               int(11) NOT NULL,
  `duration_strategy_id`  int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `leave_request_assignable_fk` (`assignable_id`),
  KEY `leave_request_type_fk` (`type_id`),
  KEY `leave_request_duration_strategy_id` (`duration_strategy_id`),
  CONSTRAINT `leave_request_assignable_fk`
    FOREIGN KEY (`assignable_id`) REFERENCES `assignable` (`id`),
  CONSTRAINT `leave_request_type_fk`
    FOREIGN KEY (`type_id`) REFERENCES `leave_request_type` (`id`),
  CONSTRAINT `leave_request_duration_strategy_id`
    FOREIGN KEY (`duration_strategy_id`) REFERENCES `duration_strategy` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `dopplereffect`.`client_data` (
  `id`               int(11)       NOT NULL AUTO_INCREMENT,
  `personal_data_id` int(11)       NOT NULL,
  PRIMARY KEY (`id`),
  KEY `personal_data_fk` (`personal_data_id`),
  CONSTRAINT `personal_data_fk`
    FOREIGN KEY (`personal_data_id`) REFERENCES `personal_data` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `dopplereffect`.`project_assignment_strategy` (
  `id`                int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `dopplereffect`.`project` (
  `id`                             int(11) NOT NULL AUTO_INCREMENT,
  `name`                           VARCHAR(45) ,
  `maxEffort`                      int(11) ,
  `currentEffort`                  int(11),
  `timeProyect`                    VARCHAR(255),
  `project_assignment_strategy_id` int(11),
  `client_data_id`                 int(11),
  PRIMARY KEY (`id`),
  KEY `project_assignment_strategy_fk` (`project_assignment_strategy_id`),
  KEY `client_data_fk` (`client_data_id`),
  CONSTRAINT `project_assignment_strategy_fk`
    FOREIGN KEY (`project_assignment_strategy_id`) REFERENCES `project_assignment_strategy` (`id`),
  CONSTRAINT `client_data_fk`
    FOREIGN KEY (`client_data_id`) REFERENCES `client_data` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `dopplereffect`.`project_assignment` (
  `id`                int(11) NOT NULL AUTO_INCREMENT,
  `project_id`        int(11) NOT NULL ,
  PRIMARY KEY (`id`),
  KEY `project_fk` (`project_id`),
  CONSTRAINT `proyect_fk`
    FOREIGN KEY (`project_id`) REFERENCES `project` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `dopplereffect`.`skill_type` (
  `id`                int(11) NOT NULL AUTO_INCREMENT,
  `name`  VARCHAR(45) ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `dopplereffect`.`skill` (
  `id`       int(11) NOT NULL AUTO_INCREMENT,
  `level`    VARCHAR(45) ,
  `type_id`  int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `skill_type_fk` (`type_id`),
  CONSTRAINT `skill_type_fk`
    FOREIGN KEY (`type_id`) REFERENCES `skill_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `dopplereffect`.`project_skill` (
  `project_id`  int(11) NOT NULL,
  `skill_id` 	int(11) NOT NULL,
  PRIMARY KEY (`project_id` , `skill_id`),
  KEY `project_fk` (`project_id`),
  KEY `skill_fk` (`skill_id`),
  CONSTRAINT `project_fk`
    FOREIGN KEY (`project_id`) REFERENCES `project` (`id`),
  CONSTRAINT `skill_fk`
    FOREIGN KEY (`skill_id`) REFERENCES `skill` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `dopplereffect`.`user` (
  `id`       int(11) NOT NULL AUTO_INCREMENT,
  `name`    VARCHAR(45) ,
  `password`    VARCHAR(255) ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ***********************************************************
-- Al ejecutar por primera vez, descomentar lo siguiente
-- y correr como root
-- ***********************************************************
-- CREATE USER 'dopplereffect' IDENTIFIED BY 'dopplereffect';
-- GRANT ALL ON dopplereffect.* TO 'dopplereffect';