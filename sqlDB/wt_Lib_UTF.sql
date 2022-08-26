-- MySQL Script generated by MySQL Workbench
-- Fri Aug 26 12:12:29 2022
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`users` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(32) NOT NULL,
  `last_name` VARCHAR(32) NOT NULL,
  `email` VARCHAR(80) NOT NULL,
  `password` VARCHAR(32) NOT NULL DEFAULT 'password',
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `mydb`.`admins`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`admins` (
  `admin_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`admin_id`),
  INDEX `fk_user_id_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_admins_users`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`users` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `mydb`.`books`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`books` (
  `book_id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(80) NULL DEFAULT NULL,
  `author` VARCHAR(255) NULL DEFAULT NULL,
  `isbn` VARCHAR(17) NOT NULL,
  `cover_image` BLOB NULL DEFAULT NULL,
  PRIMARY KEY (`book_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `mydb`.`labels`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`labels` (
  `label_id` INT NOT NULL,
  `label_name` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`label_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci
COMMENT = 'Stores labels assignable to a book for user search purposes.';


-- -----------------------------------------------------
-- Table `mydb`.`book_label`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`book_label` (
  `junction_id` INT NOT NULL,
  `book_id` INT NULL DEFAULT NULL,
  `label_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`junction_id`),
  INDEX `fk_junction_books_idx` (`book_id` ASC) VISIBLE,
  INDEX `fk_junction_labels_idx` (`label_id` ASC) VISIBLE,
  CONSTRAINT `fk_junction_books`
    FOREIGN KEY (`book_id`)
    REFERENCES `mydb`.`books` (`book_id`),
  CONSTRAINT `fk_junction_labels`
    FOREIGN KEY (`label_id`)
    REFERENCES `mydb`.`labels` (`label_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `mydb`.`copies`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`copies` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `copy_id` VARCHAR(4) NULL DEFAULT NULL,
  `reserved` TINYINT NULL DEFAULT NULL,
  `loaned` TINYINT NULL DEFAULT NULL,
  `book_id` INT NULL DEFAULT NULL,
  `user_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_id_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_book_id_idx` (`book_id` ASC) VISIBLE,
  INDEX `fk_copy_id_idx` (`copy_id` ASC) VISIBLE,
  CONSTRAINT `fk_copies_books`
    FOREIGN KEY (`book_id`)
    REFERENCES `mydb`.`books` (`book_id`),
  CONSTRAINT `fk_copies_users`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`users` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `mydb`.`transactions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`transactions` (
  `transaction_id` INT NOT NULL,
  `copy_id` VARCHAR(4) NULL DEFAULT NULL COMMENT 'fk',
  `user_id` INT NULL DEFAULT NULL,
  `transaction_type` VARCHAR(10) NULL DEFAULT NULL,
  `timestamp` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`transaction_id`),
  INDEX `fk_copy_id_idx` (`copy_id` ASC) VISIBLE,
  INDEX `fk_user_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_transactions_copies`
    FOREIGN KEY (`copy_id`)
    REFERENCES `mydb`.`copies` (`copy_id`),
  CONSTRAINT `fk_transactions_users`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`users` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
