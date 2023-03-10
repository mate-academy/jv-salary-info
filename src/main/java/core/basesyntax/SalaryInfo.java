package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        final LocalDate dateStart = LocalDate.parse(dateFrom, formatter);
        final LocalDate dateFinish = LocalDate.parse(dateTo, formatter);
        final int namesNumber = names.length;
        LocalDate dateEmployee;
        StringBuilder[] results = new StringBuilder[namesNumber + 1];
        int[] salary = new int[namesNumber];

        results[0] = new StringBuilder("Report for period ");
        results[0].append(dateFrom).append(" - ").append(dateTo);

        if (data == null) {
            return "";
        }

        for (int j = 0; j < names.length; j++) {
            for (int i = 0; i < data.length; i++) {
                String[] record = data[i].split(" ");
                dateEmployee = LocalDate.parse(record[0], formatter);
                if (names[j].equals(record[1])
                        && (dateEmployee.isEqual(dateStart) || dateEmployee.isAfter(dateStart))
                        && (dateEmployee.isEqual(dateFinish)
                        || dateEmployee.isBefore(dateFinish))) {
                    results[j + 1] = new StringBuilder(names[j] + " - ");
                    salary[j] += Integer.parseInt(record[2]) * Integer.parseInt(record[3]);
                } else if (!dateEmployee.isBefore(dateFinish)) {
                    results[j + 1] = new StringBuilder(names[j] + " - ");
                    salary[j] += 0;
                }
            }
        }

        for (int i = 0; i < results.length - 1; i++) {
            results[i + 1].append(salary[i]);
        }

        for (int i = 1; i <= results.length - 1; i++) {
            results[0].append(System.lineSeparator())
                    .append(results[i]);
        }

        return new String(results[0].toString());
    }
}
