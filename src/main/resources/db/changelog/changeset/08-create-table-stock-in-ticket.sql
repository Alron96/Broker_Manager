CREATE TABLE IF NOT EXISTS `stock_in_ticket`
(
    `id` INT NOT NULL AUTO_INCREMENT,
    `amount` INT NOT NULL,
    `status` BIT(1) NOT NULL,
    `ticket_id` INT NOT NULL,
    `stock_id` INT NOT NULL,
    `bank_account_transaction_id` INT NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `stock_id_unique` (`stock_id` ASC) VISIBLE,
    CONSTRAINT `fx_stock_in_ticket_stock_idx`
        FOREIGN KEY (`stock_id`)
            REFERENCES `stock` (`id`),
    UNIQUE INDEX `bank_account_transaction_id_unique` (`bank_account_transaction_id` ASC) VISIBLE,
    CONSTRAINT `fx_stock_in_ticket_bank_account_transaction_idx`
        FOREIGN KEY (`bank_account_transaction_id`)
            REFERENCES `bank_account_transaction` (`id`),
    INDEX `fk_stock_in_ticket_ticket_idx` (`ticket_id` ASC) VISIBLE,
    CONSTRAINT `fk_stock_in_ticket_ticket_idx`
        FOREIGN KEY (`ticket_id`)
            REFERENCES `ticket` (`id`)
)