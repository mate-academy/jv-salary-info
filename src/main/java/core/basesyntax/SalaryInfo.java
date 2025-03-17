package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        LocalDate startDate = LocalDate.parse(dateFrom, formatter);
        LocalDate endDate = LocalDate.parse(dateTo, formatter);

        Map<String, Integer> salaryMap = new HashMap<>();

        for (String line : data) {
            String[] parts = line.split("\\s+");
            LocalDate recordDate = LocalDate.parse(parts[0], formatter);
            String name = parts[1];
            int hours = Integer.parseInt(parts[2]);
            int hourlyRate = Integer.parseInt(parts[3]);

            if (!recordDate.isBefore(startDate) && !recordDate.isAfter(endDate)) {
                for (String employee : names) {
                    if (employee.equals(name)) {
                        int earnings = hours * hourlyRate;
                        salaryMap.put(name, salaryMap.getOrDefault(name, 0) + earnings);
                    }
                }
            }
        }

        StringBuilder result = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        for (String name : names) {
            int salary = salaryMap.getOrDefault(name, 0);
            result.append("\n   ").append(name).append(" - ").append(salary);
        }
        System.out.println(result);
        return result.toString();
    }
}