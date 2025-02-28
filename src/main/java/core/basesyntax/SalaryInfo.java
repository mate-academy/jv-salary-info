package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate to = LocalDate.parse(dateTo, FORMATTER);
        Map<String, Integer> salaries = new HashMap<>();

        for (String name : names) {
            salaries.put(name, 0);
        }

        for (String record : data) {
            String[] parts = record.split(" ");
            LocalDate date = LocalDate.parse(parts[0], FORMATTER);
            String employee = parts[1];
            int hours = Integer.parseInt(parts[2]);
            int rate = Integer.parseInt(parts[3]);

            if (!date.isBefore(from) && !date.isAfter(to) && salaries.containsKey(employee)) {
                salaries.put(employee, salaries.get(employee) + hours * rate);
            }
        }

        StringBuilder report = new StringBuilder("Report for period " + dateFrom + " - " + dateTo + System.lineSeparator());
        for (String name : names) {
            report.append(name).append(" - ").append(salaries.get(name)).append(System.lineSeparator());
        }

        return report.toString().trim();
    }
}
