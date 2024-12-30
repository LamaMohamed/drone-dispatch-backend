package com.elmenus.drones.shared.exception;

import com.elmenus.drones.shared.exception.custom.ResourceNotFoundException;
import com.elmenus.drones.shared.exception.custom.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private static final String NOT_FOUND_MSG = "Not Found.";
    private static final String VALIDATION_MSG = "Not Valid";

    private static final String UNEXPECTED_ERROR_MSG = "An unexpected error occurred";

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex, WebRequest request) {
        log.error("Unexpected error occurred while handling request <{}>",request.getDescription(true));
        log.error("Exception <{}> , Error msg: <{}>, Stack trace: {}", ex.getClass().getSimpleName(), ex.getLocalizedMessage(), ex.getStackTrace());
        ErrorResponse errorResponse = new ErrorResponse(
                new Date(),
                UNEXPECTED_ERROR_MSG,
               ""
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        String errorMessage = "Invalid input: " + ex.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleNotFountException(ResourceNotFoundException ex){
        ErrorResponse errorResponse = new ErrorResponse(new Date(), NOT_FOUND_MSG, ex.getLocalizedMessage());
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> handleValidationException(ValidationException ex){
        ErrorResponse errorResponse = new ErrorResponse(new Date(), VALIDATION_MSG, ex.getLocalizedMessage());
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }

}
