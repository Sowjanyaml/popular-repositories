package com.redcare.popularrepositories.exception;

public class DateInFutureException extends RuntimeException {
    public DateInFutureException(String message) {
        super(message);
    }
}