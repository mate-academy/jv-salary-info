package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateParser {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d.MM.yyyy");

    public LocalDate parseDate(String date) {
        return LocalDate.parse(date, FORMATTER);
    }
}
