package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, DATE_FORMATTER);

        int[] salaries = new int[names.length];  // Store the total salary for each employee
        
        for (String entry : data) {
            processEntry(entry, names, salaries, startDate, endDate);
        }

        return generateReport(names, salaries, dateFrom, dateTo);
    }

    private void processEntry(String entry, String[] names, int[] salaries, LocalDate startDate, LocalDate endDate) {
        String[] parts = entry.split(" ");
        LocalDate entryDate = LocalDate.parse(parts[0], DATE_FORMATTER);
        
        if (entryDate.isBefore(startDate) || entryDate.isAfter(endDate)) {
            return; // Skip entries outside the date range
        }

        String employeeName = parts[1];
        int hoursWorked = Integer.parseInt(parts[2]);
        int payPerHour = Integer.parseInt(parts[3]);

        for (int i = 0; i < names.length; i++) {
            if (names[i].equals(employeeName)) {
                salaries[i] += hoursWorked * payPerHour;
            }
        }
    }

    private String generateReport(String[] names, int[] salaries, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom).append(" - ").append(dateTo).append(System.lineSeparator());

        for (int i = 0; i < names.length; i++) {
            report.append(names[i]).append(" - ").append(salaries[i]).append(System.lineSeparator());
        }

        return report.toString().trim(); // Trim the final new line
    }
}
