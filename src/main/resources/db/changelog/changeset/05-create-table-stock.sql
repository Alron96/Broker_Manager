CREATE TABLE IF NOT EXISTS `stock`
(
    `id` INT NOT NULL AUTO_INCREMENT,
    `company_name` VARCHAR(128) NOT NULL,
    `price_buy` DOUBLE NOT NULL,
    `price_sell` DOUBLE NOT NULL,
    PRIMARY KEY (`id`)
)