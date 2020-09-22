package ua.edu.sumdu.j2ee.zykov.exception;

public abstract class NotExistException extends Exception {
    public NotExistException(String message) {
        super(message);
    }

    public NotExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
