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
        for (String name : names) {
            salaryMap.put(name, 0);
        }

        for (String record : data) {
            String[] parts = record.split(" ");
            LocalDate workDate = LocalDate.parse(parts[0], formatter);
            String employee = parts[1];
            int hours = Integer.parseInt(parts[2]);
            int rate = Integer.parseInt(parts[3]);

            if (!salaryMap.containsKey(employee)) continue;
            if (!workDate.isBefore(startDate) && !workDate.isAfter(endDate)) {
                salaryMap.put(employee, salaryMap.get(employee) + (hours * rate));
            }
        }

        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom).append(" - ").append(dateTo).append("\n");
        for (String name : names) {
            report.append(name).append(" - ").append(salaryMap.get(name)).append("\n");
        }

        return report.toString().stripTrailing();

    }
}
