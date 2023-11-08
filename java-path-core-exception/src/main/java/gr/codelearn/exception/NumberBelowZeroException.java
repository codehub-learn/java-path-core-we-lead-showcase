package gr.codelearn.exception;

public class NumberBelowZeroException extends RuntimeException {
    public NumberBelowZeroException() {
    }

    public NumberBelowZeroException(String message) {
        super(message);
    }

    public NumberBelowZeroException(String message, Throwable cause) {
        super(message, cause);
    }

    public NumberBelowZeroException(Throwable cause) {
        super(cause);
    }
}
