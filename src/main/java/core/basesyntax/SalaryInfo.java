package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, FORMATTER);

        Map<String, Integer> salaryMap = new LinkedHashMap<>();
        for (String name : names) {
            salaryMap.put(name, 0);
        }

        for (String record : data) {
            String[] parts = record.split(" ");
            LocalDate workDate = LocalDate.parse(parts[0], FORMATTER);
            String employee = parts[1];
            int hours = Integer.parseInt(parts[2]);
            int rate = Integer.parseInt(parts[3]);

            if (!salaryMap.containsKey(employee)) continue;
            if (!workDate.isBefore(startDate) && !workDate.isAfter(endDate)) {
                salaryMap.put(employee, salaryMap.get(employee) + (hours * rate));
            }
        }

        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom).append(" - ").append(dateTo).append(System.lineSeparator());
        for (String name : names) {
            report.append(name).append(" - ").append(salaryMap.get(name)).append(System.lineSeparator());
        }

        return report.toString().stripTrailing();
    }
}
