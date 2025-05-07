package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int RATE_INDEX = 3;
    private static final int PARTS_LENGTH = 4;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, DATE_FORMATTER);

        final int[] totalSalary = new int[names.length]; // Constant declaration

        StringBuilder report = new StringBuilder();
        report.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());

        for (String line : data) {
            String[] parts = line.split(" ");
            if (parts.length != PARTS_LENGTH) {
                continue;
            }

            LocalDate date = LocalDate.parse(parts[DATE_INDEX], DATE_FORMATTER);
            if (date.isAfter(toDate) || date.isBefore(fromDate)) {
                continue;
            }

            String name = parts[NAME_INDEX];
            int hours = Integer.parseInt(parts[HOURS_INDEX]);
            int rate = Integer.parseInt(parts[RATE_INDEX]);

            for (int i = 0; i < names.length; i++) {
                if (names[i].equals(name)) {
                    totalSalary[i] += hours * rate;
                    break;
                }
            }
        }

        for (int i = 0; i < names.length; i++) {
            report.append(names[i])
                    .append(" - ")
                    .append(totalSalary[i])
                    .append(System.lineSeparator());
        }

        return report.toString().trim();
    }
}
