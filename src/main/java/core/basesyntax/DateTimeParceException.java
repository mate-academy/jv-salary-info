package core.basesyntax;

import java.time.DateTimeException;

public class DateTimeParceException extends DateTimeException {
    public DateTimeParceException(String message) {
        super(message);
    }
}
