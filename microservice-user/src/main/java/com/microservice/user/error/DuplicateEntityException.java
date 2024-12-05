package com.microservice.user.error;

public class DuplicateEntityException extends Exception{
    public DuplicateEntityException(String message) {
        super(message);
    }
}
