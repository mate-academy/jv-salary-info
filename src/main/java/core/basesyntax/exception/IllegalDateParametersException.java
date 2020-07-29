package core.basesyntax.exception;

public class IllegalDateParametersException extends RuntimeException {
    public IllegalDateParametersException() {
    }

    public IllegalDateParametersException(String message) {
        super(message);
    }
}
