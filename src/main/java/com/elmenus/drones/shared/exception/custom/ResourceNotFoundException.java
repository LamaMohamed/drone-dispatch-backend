package com.elmenus.drones.shared.exception.custom;

public class ResourceNotFoundException extends RuntimeException {

    private static final String MSG_TEMPLATE = "%s with id <%s> is not found";

    public ResourceNotFoundException(String resourceName, String id) {
        super(String.format(MSG_TEMPLATE,resourceName, id));
    }
}
