package core.basesyntax;

public class WrongDataException extends RuntimeException {
    WrongDataException(String message) {
        super(message);
    }
}
