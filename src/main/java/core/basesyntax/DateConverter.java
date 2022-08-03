package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateConverter {
    private static final DateTimeFormatter dateTimeFormatter
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public LocalDate convert(String date) {
        return LocalDate.parse(date, dateTimeFormatter);
    }
}
