package core.basesyntax.exception;

public class IllegalDateParametersException extends Exception {
    @Override
    public String getMessage() {
        return "Wrong parameters";
    }
}
