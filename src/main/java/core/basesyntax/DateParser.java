package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateParser {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private final LocalDate fromDate;
    private final LocalDate toDate;

    public DateParser(String dateFrom, String dateTo) {
        this.fromDate = LocalDate.parse(dateFrom, FORMATTER);
        this.toDate = LocalDate.parse(dateTo, FORMATTER);
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public LocalDate parseDate(String date) {
        return LocalDate.parse(date, FORMATTER);
    }
}
