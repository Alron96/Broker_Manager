CREATE TABLE IF NOT EXISTS `bank_account_transaction`
(
    `id` INT NOT NULL AUTO_INCREMENT,
    `operation` TINYINT NOT NULL,
    `transfer_amount` DOUBLE NOT NULL,
    `when_done` DATETIME(6) NOT NULL,
    `sender_bank_account_id` INT NOT NULL,
    `recipient_bank_account_id` INT NOT NULL,
    `sender_broker_id` INT NOT NULL,
    `amount_stock` INT NULL DEFAULT NULL,
    `price_stock` DOUBLE NULL DEFAULT NULL,
    `stock_id` INT NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_bank_account_transaction_sender_idx` (`sender_bank_account_id` ASC) VISIBLE,
    CONSTRAINT `fk_bank_account_transaction_sender_idx`
        FOREIGN KEY (`sender_bank_account_id`)
            REFERENCES `bank_account` (`id`),
    INDEX `fk_bank_account_transaction_recipient_idx` (`recipient_bank_account_id` ASC) VISIBLE,
    CONSTRAINT `fk_bank_account_transaction_recipient_idx`
        FOREIGN KEY (`recipient_bank_account_id`)
            REFERENCES `bank_account` (`id`),
    INDEX `fk_bank_account_transaction_user_idx` (`sender_broker_id` ASC) VISIBLE,
    CONSTRAINT `fk_bank_account_transaction_user_idx`
        FOREIGN KEY (`sender_broker_id`)
            REFERENCES `user` (`id`),
    INDEX `fk_bank_account_transaction_stock_idx` (`stock_id` ASC) VISIBLE,
    CONSTRAINT `fk_bank_account_transaction_stock_idx`
        FOREIGN KEY (`stock_id`)
            REFERENCES `stock` (`id`)
)