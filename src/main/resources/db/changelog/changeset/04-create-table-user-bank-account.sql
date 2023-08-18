CREATE TABLE IF NOT EXISTS `user_bank_account`
(
    `user_id` INT NOT NULL,
    `bank_account_id` INT NOT NULL,
    INDEX `fk_user_has_bank_account_idx` (`user_id` ASC) VISIBLE,
    CONSTRAINT `fk_user_has_bank_account_idx`
        FOREIGN KEY (`bank_account_id`)
            REFERENCES `bank_account` (`id`),
    INDEX `fk_bank_account_has_user_idx` (`bank_account_id` ASC) VISIBLE,
    CONSTRAINT `fk_bank_account_has_user_idx`
        FOREIGN KEY (`user_id`)
            REFERENCES `user` (`id`)
)