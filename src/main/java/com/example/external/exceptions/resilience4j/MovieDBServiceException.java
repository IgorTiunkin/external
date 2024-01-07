package com.example.external.exceptions.resilience4j;

public class MovieDBServiceException extends RuntimeException {
    public MovieDBServiceException(String s) {
        super(s);
    }
}
