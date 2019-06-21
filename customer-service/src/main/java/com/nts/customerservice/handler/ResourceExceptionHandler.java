package com.nts.customerservice.handler;

import com.nts.customerservice.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleUnknownException() {
        return ResponseEntity.badRequest().body("Unknown error !");
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity handleResourceNotFound() {
        return ResponseEntity.notFound().build();
    }

}
