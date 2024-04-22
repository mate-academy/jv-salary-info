package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    public static final int DATE_PART = 0;
    public static final int NAME_PART = 1;
    public static final int WORKED_HOURS_PART = 2;
    public static final int RATE_PART = 3;
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate parsedDateFrom;
        LocalDate parsedDateTo;
        StringBuilder report = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        try {
            parsedDateFrom = LocalDate.parse(dateFrom.trim(), FORMATTER);
            parsedDateTo = LocalDate.parse(dateTo.trim(), FORMATTER);
        } catch (DateTimeParseException e) {
            return "The date format is incorrect or the date is invalid.";
        }
        for (String name : names) {
            int currentPersonEarnings = 0;
            for (int i = 0; i < data.length; i++) {
                String[] allInfo = data[i].split(" ");
                if (!allInfo[NAME_PART].equals(name)) {
                    continue;
                }
                LocalDate parsedDatePart = LocalDate.parse(allInfo[DATE_PART], FORMATTER);
                if (isDateIncludes(parsedDatePart, parsedDateFrom, parsedDateTo)) {
                    int earningsInCurrentEntry = Integer.parseInt(allInfo[WORKED_HOURS_PART])
                            * Integer.parseInt(allInfo[RATE_PART]);
                    currentPersonEarnings += earningsInCurrentEntry;
                }
            }
            report.append(System.lineSeparator())
                    .append(name).append(" - ").append(currentPersonEarnings);
        }
        return report.toString();
    }

    private boolean isDateIncludes(LocalDate date, LocalDate dateFrom, LocalDate dateTo) {
        return !date.isBefore(dateFrom) && !date.isAfter(dateTo);
    }
}
