package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DataValidator {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public void validate(String[] names, String[] data, String dateFrom, String dateTo) {
        if (dateFrom == null || dateTo == null) {
            throw new DataValidationException("Input data is incorrect: "
             + "Date From and Date To cannot be null");
        }
        if (names.length == 0 || data.length == 0) {
            throw new DataValidationException("Input data is incorrect: "
                     + "Names and Data arrays cannot be empty");
        }
        for (String name : names) {
            if (name == null || name.isEmpty()) {
                throw new DataValidationException("Input data is incorrect: "
                        + "Elements in array Names cannot be null or empty");
            }
        }
        for (String datum : data) {
            if (datum == null || datum.isEmpty()) {
                throw new DataValidationException("Input data is incorrect: "
                        + "Elements in array Data cannot be null or empty");
            }
        }

        try {
            LocalDate.parse(dateFrom, FORMATTER);
            LocalDate.parse(dateTo, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Format of date is invalid (should be dd.MM.yyyy): "
                     + "Date from: " + dateFrom + " "
                     + "Date to: " + dateTo);
        }
    }
}
