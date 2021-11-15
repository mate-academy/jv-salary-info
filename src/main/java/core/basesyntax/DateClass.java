package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateClass {
    static final String FORMATTER = "dd.MM.yyyy";
    private DateTimeFormatter formatter;

    public DateClass () {
        formatter = DateTimeFormatter.ofPattern(FORMATTER);
    }

    public LocalDate getLocalDate (String dateValue) {
        try {
            return LocalDate.parse(dateValue, formatter);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("Date value cannot be " + dateValue, e);
        } catch (NullPointerException e) {
            throw new RuntimeException("Date value cannot be NULL", e);
        }
    }
}
