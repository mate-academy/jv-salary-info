package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateService {
    public static boolean dateInRange(String date, String fromDate, String toDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        LocalDate dateFormatted = LocalDate.parse(date, formatter);
        LocalDate fromDateFormatted = LocalDate.parse(fromDate, formatter);
        LocalDate toDateFormatted = LocalDate.parse(toDate, formatter);

        return !dateFormatted.isBefore(fromDateFormatted)
                && !dateFormatted.isAfter(toDateFormatted);
    }
}
