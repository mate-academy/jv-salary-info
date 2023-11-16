package core.basesyntax.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ParseDate {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public LocalDate stringToLocalDate(String dateString) {
        return LocalDate.parse(dateString, FORMATTER);
    }
}
