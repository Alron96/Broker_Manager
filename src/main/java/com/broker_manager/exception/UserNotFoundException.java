package com.broker_manager.exception;

/**
 * Исключение, выбрасываемое, если пользователь не найден
 */
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Integer id) {
        super(String.format("Пользователь не найден. ID = %s", id));
    }
}
