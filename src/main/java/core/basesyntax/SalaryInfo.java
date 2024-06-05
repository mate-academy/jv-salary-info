package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_WORKED_INDEX = 2;
    private static final int HOURLY_RATE_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data,
                                String dateFrom, String dateTo) {
        LocalDate fromDate = parseDate(dateFrom);
        LocalDate toDate = parseDate(dateTo);

        StringBuilder report = new StringBuilder();
        report.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());

        for (String name : names) {
            int totalSalary = calculateSalary(name, data, fromDate, toDate);
            report.append(name)
                    .append(" - ")
                    .append(totalSalary)
                    .append(System.lineSeparator());
        }

        return report.toString().trim();
    }

    private LocalDate parseDate(String date) {
        try {
            return LocalDate.parse(date.trim(), DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format: " + date, e);
        }
    }

    private int calculateSalary(String name, String[] data,
                                LocalDate fromDate, LocalDate toDate) {
        int totalSalary = 0;

        for (String entry : data) {
            String[] parts = entry.split(" ");
            LocalDate entryDate = parseDate(parts[DATE_INDEX]);

            if (parts[NAME_INDEX].equals(name) && !entryDate.isBefore(fromDate)
                    && !entryDate.isAfter(toDate)) {
                try {
                    int hoursWorked = Integer.parseInt(parts[HOURS_WORKED_INDEX]);
                    int hourlyRate = Integer.parseInt(parts[HOURLY_RATE_INDEX]);
                    totalSalary += hoursWorked * hourlyRate;
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid number format in entry: "
                            + entry, e);
                }
            }
        }

        return totalSalary;
    }
}
