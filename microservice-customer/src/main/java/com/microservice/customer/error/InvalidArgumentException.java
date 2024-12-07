package com.microservice.customer.error;

public class InvalidArgumentException extends Exception{
    public InvalidArgumentException(String message) {
        super(message);
    }
}
