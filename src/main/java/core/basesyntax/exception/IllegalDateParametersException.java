package core.basesyntax.exception;

public class IllegalDateParametersException extends RuntimeException  {
    public IllegalDateParametersException(String errorMsg) {
        super(errorMsg);
    }
}