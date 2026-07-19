package com.yesbank.notification.exception;

public class NotificationAlreadyExistsException extends RuntimeException {

    public NotificationAlreadyExistsException(String message) {
        super(message);
    }

}