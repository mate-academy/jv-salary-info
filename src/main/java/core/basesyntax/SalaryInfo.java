package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_WORKED_INDEX = 2;
    private static final int HOURLY_RATE_INDEX = 3;
    private static final int EXPECTED_PARTS = 4;
    private static final DateTimeFormatter dateFormatter
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String SPACE = " ";
    private static final String REPORT_HEADER = "Report for period ";
    private static final String DATE_SEPARATOR = " - ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        try {
            LocalDate fromDate = LocalDate.parse(dateFrom, dateFormatter);
            LocalDate toDate = LocalDate.parse(dateTo, dateFormatter);
            result.append(reportGenerator(dateFrom, dateTo));
            for (String name : names) {
                result.append(System.lineSeparator());
                int totalIncome = calculateTotalIncome(data, name, fromDate, toDate);
                result.append(salaryLineGenerator(name, totalIncome));
            }
        } catch (DateTimeParseException e) {
            throw new RuntimeException("Wrong date format" + e.getParsedString(), e);
        }
        return result.toString();
    }

    private boolean isValidEntry(String[] parts, String name,
                                 LocalDate fromDate, LocalDate toDate) {
        if (parts.length != EXPECTED_PARTS) {
            return false;
        }
        try {
            LocalDate entryDate = LocalDate.parse(parts[DATE_INDEX], dateFormatter);
            return !entryDate.isBefore(fromDate)
                        && !entryDate.isAfter(toDate)
                        && parts[NAME_INDEX].equals(name);
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private int calculateTotalIncome(String[] data, String name, LocalDate fromDate,
                                     LocalDate toDate) {
        int totalIncome = 0;
        for (String entry : data) {
            String[] parts = entry.split(SPACE);
            if (isValidEntry(parts, name, fromDate, toDate)) {
                int hoursWorked = Integer.parseInt(parts[HOURS_WORKED_INDEX]);
                int hourlyRate = Integer.parseInt(parts[HOURLY_RATE_INDEX]);
                totalIncome += hoursWorked * hourlyRate;
            }
        }
        return totalIncome;
    }

    private String reportGenerator(String dateFrom, String dateTo) {
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append(REPORT_HEADER)
                .append(dateFrom)
                .append(DATE_SEPARATOR)
                .append(dateTo);
        return reportBuilder.toString();
    }

    private String salaryLineGenerator(String name, int totalIncome) {
        StringBuilder lineBuilder = new StringBuilder();
        lineBuilder.append(name)
                .append(DATE_SEPARATOR)
                .append(totalIncome);
        return lineBuilder.toString();
    }
}
