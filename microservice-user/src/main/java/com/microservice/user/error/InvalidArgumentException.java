package com.microservice.user.error;

public class InvalidArgumentException extends Exception{
    public InvalidArgumentException(String message) {
        super(message);
    }
}
