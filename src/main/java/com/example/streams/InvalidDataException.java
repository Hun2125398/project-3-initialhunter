package com.example.streams;

/**
 * Custom exception for invalid data scenarios
 * Thrown when stream operations fail or data cannot be processed
 */
public class InvalidDataException extends RuntimeException {

    public InvalidDataException(String message) {
        super(message);
    }

    public InvalidDataException(String message, Throwable cause) {
        super(message, cause);
    }
}