package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class SalaryInfo {
    public static String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        LocalDate fromDate = LocalDate.parse(dateFrom, formatter);
        LocalDate toDate = LocalDate.parse(dateTo, formatter);

        Map<String, Integer> salaryMap = new HashMap<>();
        for (String name : names) {
            salaryMap.put(name, 0);
        }

        for (String entry : data) {
            String[] parts = entry.split(" ");
            LocalDate currentDate = LocalDate.parse(parts[0], formatter);
            String employeeName = parts[1];
            int hoursWorked = Integer.parseInt(parts[2]);
            int hourlyRate = Integer.parseInt(parts[3]);

            if (!currentDate.isBefore(fromDate) && !currentDate.isAfter(toDate)) {
                if (salaryMap.containsKey(employeeName)) {
                    salaryMap.put(employeeName, salaryMap.get(employeeName) + (hoursWorked * hourlyRate));
                }
            }
        }

        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom).append(" - ").append(dateTo).append("\n");

        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            result.append(name).append(" - ").append(salaryMap.get(name));
            if (i < names.length - 1) {
                result.append("\n");
            }
        }

        return result.toString();
    }
}
