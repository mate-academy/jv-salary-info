package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate from = LocalDate.parse(dateFrom, formatter);
        LocalDate to = LocalDate.parse(dateTo, formatter);
        int[] salaries = new int[names.length];

        for (String records : data) {
            String[] parts = records.split(" ");
            LocalDate recordDate = LocalDate.parse(parts[0], formatter);
            String recordName = parts[1];
            int recordHour = Integer.parseInt(parts[2]);
            int recordRate = Integer.parseInt(parts[3]);

            if ((recordDate.isEqual(from) || recordDate.isAfter(from))
                    && (recordDate.isEqual(to) || recordDate.isBefore(to))) {
                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(recordName)) {
                        salaries[i] += recordHour * recordRate;
                    }
                }

            }
        }
        StringBuilder result = new StringBuilder();
        result.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());

        for (int i = 0; i < names.length; i++) {
            result.append(names[i])
                    .append(" - ")
                    .append(salaries[i])
                    .append(System.lineSeparator());
        }
        return result.toString().trim();
    }
}
