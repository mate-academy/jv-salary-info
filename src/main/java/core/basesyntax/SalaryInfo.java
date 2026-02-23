package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        if (names == null || data == null || dateFrom == null || dateTo == null) {
            throw new IllegalArgumentException("Input arrays and dates must not be null.");
        }

        LocalDate dateFrom1 = LocalDate.parse(dateFrom.trim(), FORMATTER);
        LocalDate dateTo1 = LocalDate.parse(dateTo.trim(), FORMATTER);
        StringBuilder result = new StringBuilder();
        Map<String, Integer> salaries = new HashMap<>();

        for (String name : names) {
            salaries.put(name, 0);
        }

        for (String row : data) {
            String[] parts = row.split("\s+");
            if (parts.length < 4) {
                continue;
            }
            String dateStr = parts[0];
            String nameS = parts[1];
            int hours = Integer.parseInt(parts[2]);
            int pay = Integer.parseInt(parts[3]);
            LocalDate workedDay = LocalDate.parse(parts[0], FORMATTER);

            if ((workedDay.isAfter(dateFrom1) || workedDay.isEqual(dateFrom1))
                    && (workedDay.isEqual(dateTo1) || workedDay.isBefore(dateTo1))) {
                int amount = hours * pay;
                if (salaries.containsKey(nameS)) {
                    salaries.merge(nameS, amount, Integer::sum);
                }
            }
        }

        result.append("Report for period ")
                .append(FORMATTER.format(dateFrom1))
                .append(" - ")
                .append(FORMATTER.format(dateTo1));

        for (String name : names) {
            result.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(salaries.get(name));
            }
    return result.toString();
    }
}




