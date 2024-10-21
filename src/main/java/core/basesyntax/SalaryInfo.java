package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = parseDateFromString(dateFrom);
        LocalDate localDateTo = parseDateFromString(dateTo);
        
        StringBuilder report = new StringBuilder("Report for period ")
                              .append(dateFrom)
                              .append(" - ")
                              .append(dateTo);

        for (String name : names) {
            int totalSalary = calculateSalaryForEmployee(name, data, localDateFrom, localDateTo);
            report.append(System.lineSeparator())
                  .append(name)
                  .append(" - ")
                  .append(totalSalary);
        }
        
        return report.toString();
    }

    private int calculateSalaryForEmployee(String name, String[] data, LocalDate dateFrom, LocalDate dateTo) {
        int totalSalary = 0;

    
        for (String record : data) {
            if (record == null || record.isEmpty()) {
                continue;
            }

            String[] parts = record.split(" ");
            LocalDate workDate = parseDateFromString(parts[0]);
            String employeeName = parts[1];
            int hoursWorked = parseNumberFromString(parts[2]);
            int hourlyRate = parseNumberFromString(parts[3]);

            if (employeeName.equals(name) &&
                (workDate.isEqual(dateFrom) || workDate.isAfter(dateFrom)) &&
                (workDate.isEqual(dateTo) || workDate.isBefore(dateTo))) {
                totalSalary += hoursWorked * hourlyRate;
            }
        }

        return totalSalary;
    }

    private LocalDate parseDateFromString(String date) {
        try {
            return LocalDate.parse(date, DATE_FORMATTER);
        } catch (Exception e) {
            throw new RuntimeException("Cannot parse date from string: " + date);
        }
    }

    private int parseNumberFromString(String value) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            throw new RuntimeException("Cannot parse number from string: " + value);
        }
    }
}
