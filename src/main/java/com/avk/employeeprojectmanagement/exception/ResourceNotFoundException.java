package com.avk.employeeprojectmanagement.exception;

/**
 * Custom exception used when a requested record is not found.
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
