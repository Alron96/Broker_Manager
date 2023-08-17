CREATE TABLE IF NOT EXISTS `stock_in_bank_account`
(
    `id` INT NOT NULL AUTO_INCREMENT,
    `amount` INT NOT NULL,
    `stock_id` INT NOT NULL,
    `bank_account_id` INT NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_stock_in_bank_account_bank_account_idx` (`bank_account_id` ASC) VISIBLE,
    CONSTRAINT `fk_stock_in_bank_account_bank_account_idx`
        FOREIGN KEY (`bank_account_id`)
            REFERENCES `bank_account` (`id`),
    INDEX `fk_stock_in_bank_account_stock_idx` (`stock_id` ASC) VISIBLE,
    CONSTRAINT `fk_stock_in_bank_account_stock_idx`
        FOREIGN KEY (`stock_id`)
            REFERENCES `stock` (`id`)
)