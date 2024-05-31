package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOUR_INDEX = 2;
    private static final int RATE_INDEX = 3;
    private static final DateTimeFormatter DATE_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, DATE_FORMATTER);

        StringBuilder reportLines = new StringBuilder();
        reportLines.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int earned = calculateEarnedSalary(name, data, fromDate, toDate);
            reportLines.append(System.lineSeparator()).append(name).append(" - ").append(earned);
        }
        return reportLines.toString();
    }

    private int calculateEarnedSalary(String name, String[] data,
                                      LocalDate fromDate, LocalDate toDate) {
        int earned = 0;
        for (String entry : data) {
            String[] parts = entry.split(" ");
            LocalDate entryDate = LocalDate.parse(parts[DATE_INDEX], DATE_FORMATTER);
            if (isWithinDateRange(name, entryDate, fromDate, toDate, parts)) {
                int hours = Integer.parseInt(parts[HOUR_INDEX]);
                int rate = Integer.parseInt(parts[RATE_INDEX]);
                earned += hours * rate;
            }
        }
        return earned;
    }

    private boolean isWithinDateRange(String name, LocalDate entryDate, LocalDate fromDate,
                                      LocalDate toDate, String[] parts) {
        return name.equals(parts[NAME_INDEX])
                && !entryDate.isBefore(fromDate)
                && !entryDate.isAfter(toDate);
    }
}
