package com.example.user.service;

import java.io.Serial;

public class EmailNotificationServiceException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public EmailNotificationServiceException(String message) {
        super(message);
    }
}
