package com.nts.campaignservice.handler;

import com.nts.campaignservice.exception.ResourceNotFoundException;
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

}
