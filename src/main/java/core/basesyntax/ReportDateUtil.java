package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ReportDateUtil {
    private static final String DATE_PATTERN = "dd.MM.yyyy";
    private final LocalDate date;

    public ReportDateUtil(String dateString) {
        this.date = stringDateToLocalDate(dateString);
    }

    public static LocalDate stringDateToLocalDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_PATTERN));
    }

    public boolean checkIsDateInRange(String rangeStart, String rangeEnd) {
        LocalDate rangeStartDate = stringDateToLocalDate(rangeStart);
        LocalDate rangeEndDate = stringDateToLocalDate(rangeEnd);
        return (date.isAfter(rangeStartDate) || date.isEqual(rangeStartDate))
                && (date.isBefore(rangeEndDate) || date.isEqual(rangeEndDate));
    }
}
