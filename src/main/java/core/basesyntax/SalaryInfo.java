package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class SalaryInfo {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom, formatter);
        LocalDate to = LocalDate.parse(dateTo, formatter);

        Map<String, Integer> salary = new HashMap<>();
        for (String name : names) {
            salary.put(name, 0);
        }

        for (String row : data) {
            String[] parts = row.split(" ");
            LocalDate currentDate = LocalDate.parse(parts[0], formatter);
            if (currentDate.isBefore(from) || currentDate.isAfter(to)) {
                continue;
            }

            String name = parts[1];

            if (!salary.containsKey(name)) {
                continue;
            }

            int hours = Integer.parseInt(parts[2]);
            int rate = Integer.parseInt(parts[3]);
            int earned = hours * rate;
            salary.put(name, salary.get(name) + earned);
        }

        StringBuilder builder = new StringBuilder();

        builder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());

        for (int i = 0; i < names.length; i++) {
            builder.append(names[i])
                    .append(" - ")
                    .append(salary.get(names[i]));

            if (i < names.length - 1) {
                builder.append(System.lineSeparator());
            }

        }
        return builder.toString();
    }
}
