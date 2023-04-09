package com.manu.BergfexScraper.errorhandling;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.MissingResourceException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    public RestExceptionHandler() {
        super();
    }

    @ExceptionHandler(InvalidApiKeyException.class)
    protected ResponseEntity<Object> handleUnauthorizedAccess(InvalidApiKeyException ex) {
        APIRequestError error = new APIRequestError(ex.getMessage(), HttpStatus.UNAUTHORIZED, ex);
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ResponseStatusException.class)
    protected ResponseEntity<Object> handleBadRequest(ResponseStatusException ex) {
        APIRequestError error = new APIRequestError(ex.getMessage(), HttpStatus.BAD_REQUEST, ex);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingResourceException.class)
    protected ResponseEntity<Object> handleNoSuchResource(MissingResourceException ex) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        APIRequestError error = new APIRequestError(ex.getMessage(), status, ex);
        return new ResponseEntity<>(error, status);
    }
}
