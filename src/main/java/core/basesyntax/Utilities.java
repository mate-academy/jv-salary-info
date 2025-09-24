package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utilities {
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static String[] parseLine(String line) {
        return line.trim().split("\\s+", 4);
    }

    public static int indexOfName(String[] names, String name) {
        for (int i = 0; i < names.length; i++) {
            if (names[i].equals(name)) {
                return i;
            }
        }

        return -1;
    }

    public static boolean isDateInBound(String dateFrom, String dateTo, String currentDate) {
        LocalDate from = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate to = LocalDate.parse(dateTo, DATE_FORMATTER);
        LocalDate current = LocalDate.parse(currentDate, DATE_FORMATTER);

        return !current.isBefore(from) && !current.isAfter(to);
    }
}
