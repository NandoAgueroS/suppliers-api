package com.microservice.order.error;

import com.microservice.order.error.InvalidArgumentException;
import com.microservice.order.error.ResourceNotFoundException;
import com.microservice.order.error.dto.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorMessage> NotFoundException(ResourceNotFoundException exception){
        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND,"Resource Not Found" , exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(message);
    }
    @ExceptionHandler(InvalidArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessage> invalidArgumentException(InvalidArgumentException exception){
        ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST,"Invalid Argument", exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(message);
    }
    @ExceptionHandler(DuplicateEntityException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorMessage> AlreadyExistsException(DuplicateEntityException exception){
        ErrorMessage message = new ErrorMessage(HttpStatus.CONFLICT,"Duplicate Resource", exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(message);
    }
}
