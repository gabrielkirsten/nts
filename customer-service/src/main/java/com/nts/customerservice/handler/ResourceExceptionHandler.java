package com.nts.customerservice.handler;

import com.nts.customerservice.exception.EmailAlreadyExistsException;
import com.nts.customerservice.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleUnknownException(Exception e) {
        e.printStackTrace();
        return ResponseEntity.badRequest().body("Unknown error !");
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity handleResourceNotFound() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity handleEmailAlreadyExistsException(){
        return ResponseEntity.badRequest().body("Email already exists!");
    }

}
