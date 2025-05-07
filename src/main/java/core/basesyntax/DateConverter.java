package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateConverter {
    private final DateTimeFormatter formatter;

    public DateConverter(String pattern) {
        formatter = DateTimeFormatter.ofPattern(pattern);
    }

    public LocalDate stringDateToLocale(String date) {
        return LocalDate.parse(date, formatter);
    }
}
