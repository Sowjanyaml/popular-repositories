package com.redcare.popularrepositories.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<String> handleHttpClientErrorException(HttpClientErrorException ex) {
        return ResponseEntity.badRequest().body("An error occurred during the API call: " + ex.getMessage());
    }


    @ExceptionHandler(InvalidDateException.class)
    public ResponseEntity<String> handleInvalidCityException(InvalidDateException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(InvalidLimitPerPage.class)
    public ResponseEntity<String> handleInvalidLimitPerPageException(InvalidLimitPerPage ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(DateInFutureException.class)
    public ResponseEntity<String> handleWeatherHistoryNotFoundException(DateInFutureException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
