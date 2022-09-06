-- Users table
ALTER TABLE `wt_library`.`users` 
ADD COLUMN `active` TINYINT NOT NULL DEFAULT 1 AFTER `password`,
CHANGE COLUMN `password` `password` VARCHAR(80) NOT NULL DEFAULT 'password' ;

-- Admins table
ALTER TABLE `wt_library`.`admins` 
ADD COLUMN `role` TINYINT NOT NULL DEFAULT 0 AFTER `user_id`;

-- Transactions table
ALTER TABLE `wt_library`.`transactions` 
CHANGE COLUMN `transaction_type` `transaction_type` TINYINT NULL DEFAULT NULL ,
CHANGE COLUMN `date` `date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP() ;

