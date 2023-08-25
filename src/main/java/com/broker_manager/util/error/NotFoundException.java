package com.broker_manager.util.error;

public class NotFoundException extends AppException {
    public NotFoundException(String msg) {
        super(msg);
    }

    public NotFoundException(Integer id) {
        super(String.valueOf(id));
    }
}