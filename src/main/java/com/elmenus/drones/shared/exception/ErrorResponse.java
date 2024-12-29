package com.elmenus.drones.shared.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorResponse {
    private String message;
    private String details;
    private HttpStatus status;

    public ErrorResponse(String message, String details, HttpStatus status) {
        this.message = message;
        this.details = details;
        this.status = status;
    }
}
