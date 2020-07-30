package core.basesyntax.exception;

public class IllegalDateParametersException extends Exception {
    public IllegalDateParametersException(String direction) {
        super(direction);
    }

}
