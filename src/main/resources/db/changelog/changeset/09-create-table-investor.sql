CREATE TABLE IF NOT EXISTS `investor`
(
    `id` INT NOT NULL AUTO_INCREMENT,
    `full_name` VARCHAR(256) NOT NULL,
    `amount_invested` DOUBLE NOT NULL,
    PRIMARY KEY (`id`)
)