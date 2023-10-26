package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateClass {
    private static final String FORMATTER = "dd.MM.yyyy";
    private DateTimeFormatter formatter;

    public DateClass() {
        formatter = DateTimeFormatter.ofPattern(FORMATTER);
    }

    public LocalDate getLocalDate(String dateValue) {
        return LocalDate.parse(dateValue, formatter);
    }
}
