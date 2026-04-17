package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int RATE_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

    }

    private String extractName(String[] parseData) {
        return parseData[NAME_INDEX].trim();
    }

    private LocalDate extractDate(String[] parseData) {
        return LocalDate.parse(parseData[DATE_INDEX].trim(), DATE_TIME_FORMATTER);
    }

    private boolean isDateInRange(LocalDate targetDate, LocalDate fromDate, LocalDate toDate) {
        return !targetDate.isBefore(fromDate) && !targetDate.isAfter(toDate);
    }

    private int calculateSalary(String hoursStr, String rateStr) {
        return Integer.parseInt(hoursStr) * Integer.parseInt(rateStr);
    }
}
