package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public LocalDate convertString(String date) {
        return LocalDate.parse(date, formatter);
    }

    public DateTimeFormatter getFormatter() {
        return formatter;
    }
}
