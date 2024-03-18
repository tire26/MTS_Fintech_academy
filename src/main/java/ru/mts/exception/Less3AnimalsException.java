package ru.mts.exception;

public class Less3AnimalsException extends Exception {

    public Less3AnimalsException() {
        super();
    }

    public Less3AnimalsException(String message) {
        super(message);
    }

    public Less3AnimalsException(String message, Throwable cause) {
        super(message, cause);
    }

    public Less3AnimalsException(Throwable cause) {
        super(cause);
    }

    protected Less3AnimalsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
