package com.avk.employeeprojectmanagement.response;

import java.time.LocalDateTime;

public class ApiResponse<T> {

    private int code;
    private String status;
    private String message;
    private T data;
    private LocalDateTime time;

    public ApiResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.time = LocalDateTime.now();

        switch (code) {
            case 200 -> this.status = "OK";
            case 201 -> this.status = "Created";
            case 400 -> this.status = "Bad Request";
            case 404 -> this.status = "Not Found";
            case 500 -> this.status = "Error";
            default -> this.status = "Unknown";
        }
    }

    public int getCode() { return code; }
    public String getStatus() { return status; }
    public String getMessage() { return message; }
    public T getData() { return data; }
    public LocalDateTime getTime() { return time; }
}
