package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder("Report for period "
                + dateFrom
                + " - "
                + dateTo);
        LocalDate parsedDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate parsedDateTo = LocalDate.parse(dateTo, FORMATTER);

        int[] salaries = new int[names.length];

        for (String dataRecord : data) {
            String[] record = dataRecord.split(" ");

            LocalDate currDate = LocalDate.parse(record[0], FORMATTER);

            if (currDate.isBefore(parsedDateFrom) || currDate.isAfter(parsedDateTo)) {
                continue;
            }

            int amount = Integer.parseInt(record[2]) * Integer.parseInt(record[3]);

            for (int i = 0; i < names.length; i++) {
                if (record[1].equals(names[i])) {
                    salaries[i] += amount;
                }
            }
        }

        for (int i = 0; i < names.length; i++) {
            stringBuilder
                    .append(System.lineSeparator())
                    .append(names[i])
                    .append(" - ")
                    .append(salaries[i]);
        }

        return stringBuilder.toString();
    }
}
