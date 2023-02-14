package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateParser {
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public LocalDate date(String date) {
        return LocalDate.parse(date, dateTimeFormatter);
    }
}
