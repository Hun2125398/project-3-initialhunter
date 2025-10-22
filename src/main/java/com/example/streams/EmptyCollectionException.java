package com.example.streams;

/**
 * Custom exception for empty collection scenarios
 * Thrown when an operation requires a non-empty collection
 */
public class EmptyCollectionException extends RuntimeException {

    public EmptyCollectionException(String message) {
        super(message);
    }

    public EmptyCollectionException(String message, Throwable cause) {
        super(message, cause);
    }
}