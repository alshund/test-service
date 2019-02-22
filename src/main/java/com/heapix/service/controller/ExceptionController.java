package com.heapix.service.controller;

import com.heapix.service.controller.model.ErrorModel;
import com.heapix.service.exception.EntityNotFoundException;
import com.heapix.service.exception.FileStorageException;
import com.heapix.service.exception.PermissionDeniedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(FileStorageException.class)
    public ResponseEntity fileStorage(FileStorageException e) {
        ErrorModel model = new ErrorModel(e.getMessage());
        return new ResponseEntity<>(model, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity entityNotFound(EntityNotFoundException e) {
        ErrorModel model = new ErrorModel(e.getMessage());
        return new ResponseEntity<>(model, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PermissionDeniedException.class)
    public ResponseEntity handle(PermissionDeniedException e) {
        ErrorModel model = new ErrorModel(e.getMessage());
        return new ResponseEntity<>(model, HttpStatus.FORBIDDEN);
    }

}
