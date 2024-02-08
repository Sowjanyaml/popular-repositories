package com.redcare.popularrepositories.exception;

public class InvalidLimitPerPage extends RuntimeException {
    public InvalidLimitPerPage(String message) {
        super(message);
    }
}