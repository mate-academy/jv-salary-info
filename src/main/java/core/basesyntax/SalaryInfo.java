package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        // Date formatter to parse dates
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        // Parsing start and end dates
        LocalDate startDate = LocalDate.parse(dateFrom.trim(), formatter);
        LocalDate endDate = LocalDate.parse(dateTo.trim(), formatter);

        // Map to hold the calculated salaries for each employee
        Map<String, Integer> salaryMap = new HashMap<>();
        for (String name : names) {
            salaryMap.put(name, 0);
        }

        // Process each entry in the data array
        for (String entry : data) {
            String[] parts = entry.split(" ");
            LocalDate date = LocalDate.parse(parts[0], formatter);
            String employeeName = parts[1];
            int hoursWorked = Integer.parseInt(parts[2]);
            int incomePerHour = Integer.parseInt(parts[3]);

            // Check if the date is within the specified range
            if (!date.isBefore(startDate) && !date.isAfter(endDate)) {
                if (salaryMap.containsKey(employeeName)) {
                    int currentSalary = salaryMap.get(employeeName);
                    int additionalSalary = hoursWorked * incomePerHour;
                    salaryMap.put(employeeName, currentSalary + additionalSalary);
                }
            }
        }

        // Build the report string
        StringBuilder report = new StringBuilder();
        report.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());

        for (String name : names) {
            report.append(name)
                    .append(" - ")
                    .append(salaryMap.get(name))
                    .append(System.lineSeparator());
        }

        return report.toString().trim();
    }
}
