CREATE TABLE IF NOT EXISTS `ticket`
(
    `id` INT NOT NULL AUTO_INCREMENT,
    `operation` TINYINT NOT NULL,
    `status` BIT(1) NOT NULL,
    `when_opened` DATETIME(6) NOT NULL,
    `when_closed` DATETIME(6) NULL DEFAULT NULL,
    `broker_id` INT NOT NULL,
    `chief_broker_id` INT NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_ticket_broker_idx` (`broker_id` ASC) VISIBLE,
    CONSTRAINT `fk_ticket_broker_idx`
        FOREIGN KEY (`broker_id`)
            REFERENCES `user` (`id`),
    INDEX `fk_ticket_chief_broker_idx` (`chief_broker_id` ASC) VISIBLE,
    CONSTRAINT `fk_ticket_chief_broker_idx`
        FOREIGN KEY (`chief_broker_id`)
            REFERENCES `user` (`id`)
)