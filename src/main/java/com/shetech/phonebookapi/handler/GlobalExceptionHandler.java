package com.shetech.phonebookapi.handler;

import com.shetech.phonebookapi.domain.ErrorResponse;
import com.shetech.phonebookapi.exception.ContactNotFoundException;
import com.shetech.phonebookapi.exception.DuplicateContactException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    public static final String RESOURCE = "Contact";
    public static final String NOT_FOUND_TYPE = "RESOURCE_NOT-FOUND_EXCEPTION";
    public static final String DUPLICATE_TYPE = "DUPLICATED_RESOURCE_EXCEPTION";

    @ExceptionHandler(ContactNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleContactNotFoundException(ContactNotFoundException ex) {
        /* - prepare response - */
        ErrorResponse error = ErrorResponse.builder().message(ex.getMessage()).resource(RESOURCE).type(NOT_FOUND_TYPE).timestamp(new Date()).build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateContactException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateContactException(DuplicateContactException ex) {
        /* - prepare response - */
        ErrorResponse error = ErrorResponse.builder().message(ex.getMessage()).resource(RESOURCE).type(DUPLICATE_TYPE).timestamp(new Date()).build();
        return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
    }
}
