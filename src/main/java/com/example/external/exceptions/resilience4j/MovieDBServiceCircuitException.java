package com.example.external.exceptions.resilience4j;

public class MovieDBServiceCircuitException extends RuntimeException {
    public MovieDBServiceCircuitException(String s) {
        super(s);
    }
}
