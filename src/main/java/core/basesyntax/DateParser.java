package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateParser {
    private static final String DATE_FORMAT = "dd.MM.yyyy";

    public static LocalDate parse(String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        return LocalDate.parse(date, dateTimeFormatter);
    }
}
