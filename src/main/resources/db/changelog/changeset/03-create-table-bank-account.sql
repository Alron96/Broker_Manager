CREATE TABLE IF NOT EXISTS `bank_account`
(
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    `balance` DOUBLE NOT NULL,
    `department` TINYINT NOT NULL,
    `type` TINYINT NOT NULL,
    PRIMARY KEY (`id`)
)