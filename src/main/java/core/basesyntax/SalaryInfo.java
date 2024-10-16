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

        Map<String, Integer> salaryMap = calculateSalaries(names, data, startDate, endDate);

        return generateReport(names, salaryMap, dateFrom, dateTo);
    }

    private Map<String, Integer> calculateSalaries(String[] names, String[] data, LocalDate startDate, LocalDate endDate) {
        Map<String, Integer> salaryMap = new HashMap<>();
        for (String name : names) {
            salaryMap.put(name, 0);
        }

        for (String entry : data) {
            String[] parts = entry.split(" ");
            LocalDate entryDate = LocalDate.parse(parts[0], DATE_FORMATTER);
            String employeeName = parts[1];
            int hoursWorked = Integer.parseInt(parts[2]);
            int payPerHour = Integer.parseInt(parts[3]);

            if (!entryDate.isBefore(startDate) && !entryDate.isAfter(endDate) && salaryMap.containsKey(employeeName)) {
                int currentSalary = salaryMap.get(employeeName);
                salaryMap.put(employeeName, currentSalary + (hoursWorked * payPerHour));
            }
        }

        return salaryMap;
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
