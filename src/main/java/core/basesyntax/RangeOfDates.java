package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class RangeOfDates {
    private final LocalDate dateFrom;
    private final LocalDate dateTo;
    private final DateTimeFormatter formatter
            = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);

    public RangeOfDates(String dateFrom, String dateTo) {
        this.dateFrom = LocalDate.parse(dateFrom,formatter);
        this.dateTo = LocalDate.parse(dateTo, formatter);
    }

    public boolean isInDates(String date) {
        LocalDate dateInfo = LocalDate.parse(date, formatter);
        return dateFrom.compareTo(dateInfo) <= 0 && dateTo.compareTo(dateInfo) >= 0;
    }
}
