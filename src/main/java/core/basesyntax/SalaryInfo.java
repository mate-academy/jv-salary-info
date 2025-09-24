package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");
    private static final int DATE_IDX = 0;
    private static final int NAME_IDX = 1;
    private static final int HOURS_IDX = 2;
    private static final int RATE_IDX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom,
                                String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate to = LocalDate.parse(dateTo, DATE_FORMATTER);

        int[] salaries = new int[names.length];

        for (String record : data) {
            String[] tokens = record.split("\\s+");

            LocalDate workDate = LocalDate.parse(tokens[DATE_IDX], DATE_FORMATTER);
            String name = tokens[NAME_IDX];
            int hours = Integer.parseInt(tokens[HOURS_IDX]);
            int rate = Integer.parseInt(tokens[RATE_IDX]);

            // Перевірка, чи дата в заданому проміжку включно
            if (!workDate.isBefore(from) && !workDate.isAfter(to)) {
                int dailySalary = hours * rate;
                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(name)) {
                        salaries[i] += dailySalary;
                        break;
                    }
                }
            }
        }

        StringBuilder report = new StringBuilder("Report for period " + dateFrom + " - "
                + dateTo + System.lineSeparator());
        for (int i = 0; i < names.length; i++) {
            report.append(names[i])
                    .append(" - ")
                    .append(salaries[i])
                    .append(System.lineSeparator());
        }

        return report.toString().trim();
    }
}


