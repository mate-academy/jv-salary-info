package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.y");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        LocalDate localDateFrom;
        LocalDate localDateTo;
        if (names == null || data == null || dateFrom == null || dateTo == null) {
            throw new RuntimeException("Null argument");
        }
        try {
            localDateFrom = LocalDate.parse(dateFrom, formatter);
            localDateTo = LocalDate.parse(dateTo, formatter);
        } catch (DateTimeParseException dateTimeParseException) {
            throw new RuntimeException("Invalid dateFrom or dateTo");
        }

        if (!localDateFrom.isBefore(localDateTo)) {
            throw new RuntimeException("Invalid date range");
        }
        return null;
    }
}
