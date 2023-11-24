package core.basesyntax.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DataUtils {
    public boolean isDateInRange(String fromDate, String toDate, String validatedDate,
            String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        try {
            LocalDate fromLocalDate = LocalDate.parse(fromDate, formatter);
            LocalDate toLocalDate = LocalDate.parse(toDate, formatter);
            LocalDate validatedLocalDate = LocalDate.parse(validatedDate, formatter);
            return !validatedLocalDate.isBefore(fromLocalDate)
                && !validatedLocalDate.isAfter(toLocalDate);
        } catch (DateTimeParseException e) {
            System.out.println("Incorrect '" + format + "' format for parsing." + e);
            return false;
        }
    }
}
