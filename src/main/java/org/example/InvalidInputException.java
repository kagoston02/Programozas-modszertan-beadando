package org.example;

/**
 * Helytelen bemeneti értékek esetén kivételez.
 */
public class InvalidInputException extends Exception {
    public InvalidInputException(String message) {
        super(message);
    }
}
