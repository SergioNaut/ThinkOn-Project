package com.thinkon.userManager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;

//Basic Exception Handler
@ControllerAdvice
public class GlobalExceptionHandler{
    //Entity not Found Error
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleEntityNotFound(EntityNotFoundException ex){
        Map<String, Object> body = new HashMap<>();
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    //Generic Errors
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericExceptions(Exception ex){
        Map<String, Object> body = new HashMap<>();
        body.put("message","ERROR");
        body.put("details",ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //Validation Errors
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(ConstraintViolationException ex){
        Map<String, Object> body = new HashMap<>();
        body.put("errors", ex.getConstraintViolations());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}