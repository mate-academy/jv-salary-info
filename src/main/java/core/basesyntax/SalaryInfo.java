package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate from = LocalDate.parse(dateFrom.trim(), formatter);
        LocalDate to = LocalDate.parse(dateTo.trim(), formatter);

        int[] salaries = new int[names.length];

        for (int i = 0;i < data.length;i++) {
            String[] parts = data[i].split(" ");

            LocalDate recordDate = LocalDate.parse(parts[0], formatter);
            String name = parts[1];

            int hours = Integer.parseInt(parts[2]);
            int rate = Integer.parseInt(parts[3]);

            if (recordDate.isBefore(from) || recordDate.isAfter(to)) {
                continue;
            }
            for (int j = 0;j < names.length;j++) {
                if (names[j].equals(name)) {
                    salaries[j] += hours * rate;
                }
            }
        }
        String lineSeparator = System.lineSeparator();
        StringBuilder result = new StringBuilder();

        result.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(lineSeparator);

        for (int i = 0; i < names.length; i++) {
            result.append(names[i])
                    .append(" - ")
                    .append(salaries[i]);

            if (i < names.length - 1) {
                result.append(lineSeparator);
            }
        }

        return result.toString();
    }
}
