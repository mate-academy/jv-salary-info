package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, DATE_FORMATTER);

        Map<String, Integer> salaries = calculateSalaries(names, data, startDate, endDate);

        return generateReport(names, salaries, dateFrom, dateTo);
    }

    private Map<String, Integer> calculateSalaries(String[] names, String[] data, LocalDate startDate, LocalDate endDate) {
        // Initialize map to store salaries
        Map<String, Integer> salaryMap = initializeSalaryMap(names);

        for (String entry : data) {
            processEntry(entry, salaryMap, startDate, endDate);
        }

        return salaryMap;
    }

    private Map<String, Integer> initializeSalaryMap(String[] names) {
        Map<String, Integer> salaryMap = new HashMap<>();
        for (String name : names) {
            salaryMap.put(name, 0);
        }
        return salaryMap;
    }

    private void processEntry(String entry, Map<String, Integer> salaryMap, LocalDate startDate, LocalDate endDate) {
        String[] parts = entry.split(" ");
        LocalDate entryDate = LocalDate.parse(parts[0], DATE_FORMATTER);
        String employeeName = parts[1];
        int hoursWorked = Integer.parseInt(parts[2]);
        int payPerHour = Integer.parseInt(parts[3]);

        if (isDateWithinRange(entryDate, startDate, endDate) && salaryMap.containsKey(employeeName)) {
            int currentSalary = salaryMap.get(employeeName);
            salaryMap.put(employeeName, currentSalary + (hoursWorked * payPerHour));
        }
    }

    private boolean isDateWithinRange(LocalDate date, LocalDate startDate, LocalDate endDate) {
        return !date.isBefore(startDate) && !date.isAfter(endDate);
    }

    private String generateReport(String[] names, Map<String, Integer> salaryMap, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder();
        report.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());

        for (int i = 0; i < names.length; i++) {
            report.append(names[i])
                  .append(" - ")
                  .append(salaryMap.get(names[i]));
            
            if (i < names.length - 1) {
                report.append(System.lineSeparator());
            }
        }

        return report.toString();
    }
}
