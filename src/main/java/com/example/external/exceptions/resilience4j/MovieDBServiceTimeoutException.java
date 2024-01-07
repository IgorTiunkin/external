package com.example.external.exceptions.resilience4j;

public class MovieDBServiceTimeoutException extends RuntimeException {
    public MovieDBServiceTimeoutException(String s) {
        super(s);
    }
}
