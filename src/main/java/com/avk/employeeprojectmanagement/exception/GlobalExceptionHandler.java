package com.avk.employeeprojectmanagement.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    // RESOURCE NOT FOUND

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(ResourceNotFoundException ex) {
        return buildResponse(
                404,
                "RESOURCE_NOT_FOUND",
                ex.getMessage(),
                null
        );
    }


    // VALIDATION ERRORS (@Valid)

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex) {

        Map<String, String> fieldErrors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                fieldErrors.put(error.getField(), error.getDefaultMessage())
        );

        return buildResponse(
                400,
                "VALIDATION_ERROR",
                "One or more fields are invalid",
                fieldErrors
        );
    }


    // SQL CONSTRAINT ERRORS

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, Object>> handleSQLConstraint(DataIntegrityViolationException ex) {

        return buildResponse(
                400,
                "DATABASE_ERROR",
                ex.getMostSpecificCause().getMessage(),
                null
        );
    }


    // GENERIC EXCEPTION

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneric(Exception ex) {

        return buildResponse(
                500,
                "INTERNAL_SERVER_ERROR",
                ex.getMessage(),
                null
        );
    }


    // RESPONSE BUILDER

    private ResponseEntity<Map<String, Object>> buildResponse(
            int code,
            String status,
            String message,
            Object errors
    ) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status);
        body.put("code", code);
        body.put("message", message);
        body.put("errors", errors);

        return new ResponseEntity<>(body, HttpStatus.valueOf(code));
    }
}
