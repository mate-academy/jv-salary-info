package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int RATE_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom.trim(), FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo.trim(), FORMATTER);

        int[] salaries = new int[names.length];

        for (String record : data) {
            String[] details = record.split(" ");
            LocalDate date = LocalDate.parse(details[DATE_INDEX], FORMATTER);
            String name = details[NAME_INDEX];
            int hours = Integer.parseInt(details[HOURS_INDEX]);
            int rate = Integer.parseInt(details[RATE_INDEX]);

            if (!startDate.isAfter(date) && !endDate.isBefore(date)) {
                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(name)) {
                        salaries[i] += hours * rate;
                        break;
                    }
                }
            }
        }

        StringBuilder report = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo).append(System.lineSeparator());

        for (int i = 0; i < names.length; i++) {
            report.append(names[i]).append(" - ").append(salaries[i]);
            if (i < names.length - 1) {
                report.append(System.lineSeparator());
            }
        }

        return report.toString();
    }
}
