package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String DATE_PATTERN = "dd.MM.yyyy";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int RATE_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        if (names == null || data == null || dateFrom == null || dateTo == null) {
            throw new IllegalArgumentException("Input arguments must not be null");
        }
        if (names.length == 0 || data.length == 0) {
            return "No salary information available for the specified period";
        }

        LocalDate fromDate = LocalDate.parse(dateFrom.trim(), FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo.trim(), FORMATTER);

        int[] salaries = new int[names.length];

        for (String record : data) {
            String[] parts = record.split(" ");
            LocalDate recordDate = LocalDate.parse(parts[DATE_INDEX], FORMATTER);
            String employeeName = parts[NAME_INDEX];
            int hoursWorked = Integer.parseInt(parts[HOURS_INDEX]);
            int hourlyRate = Integer.parseInt(parts[RATE_INDEX]);

            if (!recordDate.isBefore(fromDate) && !recordDate.isAfter(toDate)) {
                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(employeeName)) {
                        salaries[i] += hoursWorked * hourlyRate;
                        break;
                    }
                }
            }
        }

        StringBuilder report = new StringBuilder();
        report.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (int i = 0; i < names.length; i++) {
            report.append(System.lineSeparator())
                    .append(names[i])
                    .append(" - ")
                    .append(salaries[i]);
        }
        return report.toString();
    }
}

