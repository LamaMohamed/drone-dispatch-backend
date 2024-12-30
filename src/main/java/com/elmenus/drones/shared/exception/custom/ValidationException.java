package com.elmenus.drones.shared.exception.custom;

public class ValidationException extends RuntimeException {
    private static final String MSG_TEMPLATE = "Validation Exception: %s";

    public ValidationException(String message) {
        super(String.format(MSG_TEMPLATE,message));
    }
}
