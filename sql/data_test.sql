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
-- Table `spring_test`.`offers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `spring_test`.`offers` ;

CREATE TABLE IF NOT EXISTS `spring_test`.`offers` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `name` VARCHAR(100) NOT NULL COMMENT '',
  `email` VARCHAR(60) NOT NULL COMMENT '',
  `text` TEXT NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `spring_test`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `spring_test`.`users` ;

CREATE TABLE IF NOT EXISTS `spring_test`.`users` (
  `username` VARCHAR(60) NOT NULL COMMENT '',
  `password` VARCHAR(80) NULL COMMENT '',
  `enabled` TINYINT(1) NULL DEFAULT 1 COMMENT '',
  PRIMARY KEY (`username`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `spring_test`.`authorities`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `spring_test`.`authorities` ;

CREATE TABLE IF NOT EXISTS `spring_test`.`authorities` (
  `username` VARCHAR(60) NOT NULL COMMENT '',
  `authority` VARCHAR(45) NULL COMMENT '',
  PRIMARY KEY (`username`)  COMMENT '')
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
