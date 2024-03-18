package ru.mts.exception;

public class IllegalAgeException extends IllegalArgumentException {
    public IllegalAgeException() {
        super();
    }

    public IllegalAgeException(String s) {
        super(s);
    }

    public IllegalAgeException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalAgeException(Throwable cause) {
        super(cause);
    }
}
