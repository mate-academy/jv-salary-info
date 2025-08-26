package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_IDX = 0;
    private static final int NAME_IDX = 1;
    private static final int HOURS_IDX = 2;
    private static final int RATE_IDX = 3;

    public String getSalaryInfo(String[] names, String[] data,
                                String dateFrom, String dateTo) {
        int[] salary = new int[names.length];

        LocalDate start = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate end = LocalDate.parse(dateTo, FORMATTER);

        for (String record : data) {
            String[] parts = record.split("\\s+");
            LocalDate current = LocalDate.parse(parts[DATE_IDX], FORMATTER);

            if (!current.isBefore(start) && !current.isAfter(end)) {
                for (int i = 0; i < names.length; i++) {
                    if (parts[NAME_IDX].equals(names[i])) {
                        int hours = Integer.parseInt(parts[HOURS_IDX]);
                        int rate = Integer.parseInt(parts[RATE_IDX]);
                        salary[i] += hours * rate;
                    }
                }
            }
        }

        StringBuilder report = new StringBuilder();
        report.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (int i = 0; i < names.length; i++) {
            report.append(System.lineSeparator())
                    .append(names[i])
                    .append(" - ")
                    .append(salary[i]);
        }

        return report.toString();
    }
}
