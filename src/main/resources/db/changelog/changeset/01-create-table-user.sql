CREATE TABLE IF NOT EXISTS `user`
(
    `id` INT NOT NULL AUTO_INCREMENT,
    `full_name` VARCHAR(256) NULL,
    `email` VARCHAR(128) NULL,
    `phone_number` VARCHAR(255) NOT NULL,
    `password` VARCHAR(128) NOT NULL,
    `department` TINYINT NOT NULL,
    `role` TINYINT NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `user_email_unique` (`email` ASC) VISIBLE
)