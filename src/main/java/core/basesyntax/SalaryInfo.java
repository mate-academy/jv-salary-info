package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class SalaryInfo {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = parseDate(dateFrom);
        LocalDate endDate = parseDate(dateTo);

        Map<String, Integer> salaryMap = initializeSalaryMap(names);

        for (String entry : data) {
            try {
                String[] parts = entry.split(" ");
                LocalDate date = LocalDate.parse(parts[0], formatter);
                String name = parts[1];
                int workHours = Integer.parseInt(parts[2]);
                int perHourRate = Integer.parseInt(parts[3]);

                if (!date.isAfter(endDate) && !date.isBefore(startDate) && salaryMap.containsKey(name)) {
                    int currentSalary = salaryMap.get(name);
                    salaryMap.put(name, currentSalary + workHours * perHourRate);
                }
            } catch (Exception e) {
                System.err.println("Invalid data entry: " + entry);
            }
        }
        return print(salaryMap, dateFrom, dateTo);
    }

    private LocalDate parseDate(String date) {
        try {
            return LocalDate.parse(date.trim(), formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use dd.MM.yyyy");
        }
    }

    private Map<String, Integer> initializeSalaryMap(String[] names) {
        Map<String, Integer> salaryMap = new LinkedHashMap<>();
        for (String name : names) {
            salaryMap.put(name, 0);
        }
        return salaryMap;
    }

    private String print(Map<String, Integer> salaryMap, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom).append(" - ").append(dateTo).append("\n");
        salaryMap.keySet().forEach(name -> result.append(name + " - " + salaryMap.get(name) + "\n"));
        return result.toString().trim();
    }
}