package com.heapix.service.exception;

import com.heapix.service.reusable.EntityType;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(EntityType entityType, Object id) {
        super("unable to get <" + entityType.name() + " " + id + ">");
    }
}
