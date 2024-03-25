package ru.mts.exception;

public class NullArgumentException extends IllegalArgumentException {
    public NullArgumentException() {
        super();
    }

    public NullArgumentException(String s) {
        super(s);
    }

    public NullArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullArgumentException(Throwable cause) {
        super(cause);
    }
}
