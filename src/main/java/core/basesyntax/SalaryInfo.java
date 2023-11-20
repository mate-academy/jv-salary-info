package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        if (names == null || data == null || dateFrom.isEmpty() || dateTo.isEmpty()) {
            throw new IllegalArgumentException("Dates or data cannot be null or empty");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate startDate = LocalDate.parse(dateFrom, formatter);
        LocalDate endDate = LocalDate.parse(dateTo, formatter);
        Map<String, Integer> salaryMap = new HashMap<>();

        for (String name : names) {
            salaryMap.put(name, 0);
        }

        for (String info : data) {
            String[] parts = info.split(" ");
            LocalDate date = LocalDate.parse(parts[0], formatter);
            if ((date.isEqual(startDate) || date.isAfter(startDate))
                    && (date.isEqual(endDate) || date.isBefore(endDate))) {
                String name = parts[1];
                int hoursWorked = Integer.parseInt(parts[2]);
                int payPerHour = Integer.parseInt(parts[3]);
                if (salaryMap.containsKey(name)) {
                    salaryMap.put(name, salaryMap.get(name) + (hoursWorked * payPerHour));
                }
            }
        }

        StringBuilder sb = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo + "\n");
        for (String name : names) {
            sb.append(name).append(" - ").append(salaryMap.get(name)).append("\n");
        }

        return sb.toString().trim();
    }
}
