package core.basesyntax.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DataUtils {
    static final String FORMAT = "dd.MM.yyyy";
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(FORMAT);

    public boolean isDateInRange(String fromDate, String toDate, String validatedDate) {
        try {
            LocalDate fromLocalDate = LocalDate.parse(fromDate, FORMATTER);
            LocalDate toLocalDate = LocalDate.parse(toDate, FORMATTER);
            LocalDate validatedLocalDate = LocalDate.parse(validatedDate, FORMATTER);
            return !validatedLocalDate.isBefore(fromLocalDate)
                && !validatedLocalDate.isAfter(toLocalDate);
        } catch (DateTimeParseException e) {
            System.out.println("Incorrect '" + FORMAT + "' format for parsing." + e);
            return false;
        }
    }
}
