package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final int dateIndex = 0;
    private static final int nameIndex = 1;
    private static final int hoursWorkedIndex = 2;
    private static final int hourlyRateIndex = 3;
    private static final int expectedParts = 4;
    private static final DateTimeFormatter dateFormatter
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private boolean isValidEntry(String[] parts, String name,
                                 LocalDate fromDate, LocalDate toDate) {
        if (parts.length == expectedParts) {
            try {
                LocalDate entryDate = LocalDate.parse(parts[dateIndex], dateFormatter);
                return !entryDate.isBefore(fromDate)
                        && !entryDate.isAfter(toDate)
                        && parts[nameIndex].equals(name);
            } catch (DateTimeParseException e) {
                return false;
            }
        }
        return false;
    }

    private int calculateTotalIncome(String[] data, String name, LocalDate fromDate,
                                     LocalDate toDate) {
        int totalIncome = 0;
        for (String entry : data) {
            String[] parts = entry.split(" ");
            if (isValidEntry(parts, name, fromDate, toDate)) {
                int hoursWorked = Integer.parseInt(parts[hoursWorkedIndex]);
                int hourlyRate = Integer.parseInt(parts[hourlyRateIndex]);
                totalIncome += hoursWorked * hourlyRate;
            }
        }
        return totalIncome;
    }

    private String reportGenerator(String dateFrom, String dateTo) {
        return "Report for period " + dateFrom + " - " + dateTo;
    }

    private String salaryLineGenerator(String name, int totalIncome) {
        return name + " - " + totalIncome;
    }

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        result.append(reportGenerator(dateFrom, dateTo));
        try {
            LocalDate fromDate = LocalDate.parse(dateFrom.trim(), dateFormatter);
            LocalDate toDate = LocalDate.parse(dateTo.trim(), dateFormatter);
            for (String name : names) {
                result.append(System.lineSeparator());
                int totalIncome = calculateTotalIncome(data, name, fromDate, toDate);
                result.append(salaryLineGenerator(name, totalIncome));
            }
        } catch (DateTimeParseException e) {
            throw new RuntimeException("Wrong date format", e);
        }
        return result.toString();
    }
}
