package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    static final int PARSED_DATE = 0;
    static final int PARSED_NAME = 1;
    static final int PARSED_HOURS = 2;
    static final int PARSED_HOURLY_RATE = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        final LocalDate start = LocalDate.parse(dateFrom, FORMATTER);
        final LocalDate end = LocalDate.parse(dateTo, FORMATTER);
        final int[] salary = new int[names.length];
        StringBuilder sb = new StringBuilder("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo).append(System.lineSeparator());

        for (String line : data) {
            String[] lines = line.split(" ");
            java.time.LocalDate check = LocalDate.parse(lines[PARSED_DATE], FORMATTER);
            boolean isBetween = !check.isBefore(start) && !check.isAfter(end);

            for (int i = 0; i < names.length; i++) {
                if (isBetween && lines[PARSED_NAME].equals(names[i])) {
                    salary[i] += Integer.parseInt(lines[PARSED_HOURS]) * Integer.parseInt(lines[PARSED_HOURLY_RATE]);
                }
            }
        }

        for (int i = 0; i < names.length; i++) {
            sb.append(names[i]).append(" - ").append(salary[i]);
            if (i < names.length - 1) {
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }
}
