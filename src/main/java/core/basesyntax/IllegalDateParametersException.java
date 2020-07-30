package core.basesyntax;

public class IllegalDateParametersException extends RuntimeException {
    public IllegalDateParametersException(String errorMsg) {
        super(errorMsg);
    }
}
