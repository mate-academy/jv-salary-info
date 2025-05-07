package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    public static LocalDate parseDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return LocalDate.parse(date, formatter);
    }

    public static boolean isInRange(LocalDate date, LocalDate from, LocalDate to) {
        return date.isAfter(from.minusDays(1)) && date.isBefore(to.plusDays(1));
    }
}
