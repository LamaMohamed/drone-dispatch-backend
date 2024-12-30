package com.elmenus.drones.shared.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
public class ErrorResponse {
    private final String message;
    private final String details;
    private final Date timestamp;

    public ErrorResponse(Date timestamp, String message, String details) {
        this.message = message;
        this.details = details;
        this.timestamp = timestamp;
    }
}
