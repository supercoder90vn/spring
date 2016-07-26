-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema spring_test
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema spring_test
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `spring_test` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `spring_test` ;

-- -----------------------------------------------------
-- Table `spring_test`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `spring_test`.`users` ;

CREATE TABLE IF NOT EXISTS `spring_test`.`users` (
  `username` VARCHAR(60) NOT NULL COMMENT '',
  `password` VARCHAR(80) NULL COMMENT '',
  `authority` VARCHAR(45) NULL COMMENT '',
  `name` VARCHAR(100) NOT NULL COMMENT '',
  `enabled` TINYINT(1) NULL DEFAULT 1 COMMENT '',
  `email` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`username`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `spring_test`.`offers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `spring_test`.`offers` ;

CREATE TABLE IF NOT EXISTS `spring_test`.`offers` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `text` TEXT NOT NULL COMMENT '',
  `username` VARCHAR(60) NOT NULL COMMENT '',
  PRIMARY KEY (`id`, `username`)  COMMENT '',
  INDEX `fk_offers_users_idx` (`username` ASC)  COMMENT '',
  CONSTRAINT `fk_offers_users`
    FOREIGN KEY (`username`)
    REFERENCES `spring_test`.`users` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `spring_test`.`messages`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `spring_test`.`messages` ;

CREATE TABLE IF NOT EXISTS `spring_test`.`messages` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `subject` VARCHAR(100) NOT NULL COMMENT '',
  `content` VARCHAR(1000) NOT NULL COMMENT '',
  `name` VARCHAR(100) NOT NULL COMMENT '',
  `email` VARCHAR(60) NOT NULL COMMENT '',
  `username` VARCHAR(60) NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_messages_users1_idx` (`username` ASC)  COMMENT '',
  CONSTRAINT `fk_messages_users1`
    FOREIGN KEY (`username`)
    REFERENCES `spring_test`.`users` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
