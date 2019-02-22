package com.heapix.service.exception;

public class FileStorageException extends Exception {
    public FileStorageException(String target) {
        super("unable to create <" + target + ">");
    }
}
