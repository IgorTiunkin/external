package com.example.external.exceptions.resilience4j;

public class MovieDBServiceTooManyRequestsException extends RuntimeException {
    public MovieDBServiceTooManyRequestsException(String s) {
        super(s);
    }
}
