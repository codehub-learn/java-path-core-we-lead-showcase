package gr.codelearn.exception;

public class NumberIsZeroException extends Exception {
    public NumberIsZeroException() {
    }

    public NumberIsZeroException(String message) {
        super(message);
    }

    public NumberIsZeroException(String message, Throwable cause) {
        super(message, cause);
    }

    public NumberIsZeroException(Throwable cause) {
        super(cause);
    }
}
