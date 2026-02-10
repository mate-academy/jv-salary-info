package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SalaryInfo {
    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder(
                "Report for period " + dateFrom + " - " + dateTo
        );
        LocalDate parsedStartDate = LocalDate.parse(dateFrom, formatter);
        LocalDate parsedEndDate = LocalDate.parse(dateTo, formatter);

        Map<String, Integer> employees = new HashMap<>();
        Set<String> nameSet = new HashSet<>(Arrays.asList(names));

        for (String info: data) {
            String[] parts = info.split(" ");
            String name = parts[1];
            if (nameSet.contains(name)) {
                String currentDate = parts[0];
                if (this.compareDate(parsedStartDate, parsedEndDate, currentDate)) {
                    int salary = Integer.parseInt(parts[2]) * Integer.parseInt(parts[3]);
                    employees.merge(name, salary, Integer::sum);
                }
            }
        }

        for (String name: names) {
            if (!employees.containsKey(name)) {
                result.append(System.lineSeparator()).append(name).append(" - 0");
                continue;
            }
            result.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(employees.get(name));
        }

        return result.toString();
    }

    private boolean compareDate(LocalDate startDate, LocalDate endDate, String currentDate) {
        LocalDate parsedCurrentDate = LocalDate.parse(currentDate, formatter);
        return !parsedCurrentDate.isBefore(startDate)
                && !parsedCurrentDate.isAfter(endDate);
    }
}
