package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ValidDate {
    public boolean checkValidDate(String currentDayStr, String dateFromStr, String dateToStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate currentDay = LocalDate.parse(currentDayStr, formatter);
        LocalDate dateFrom = LocalDate.parse(dateFromStr, formatter);
        LocalDate dateTo = LocalDate.parse(dateToStr, formatter);
        return ((currentDay.isEqual(dateFrom)) || currentDay.isAfter(dateFrom))
                && ((currentDay.isEqual(dateTo)) || currentDay.isBefore(dateTo));
    }
}
