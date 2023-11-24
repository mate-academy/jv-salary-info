package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String DATE_SEPARATOR = " - ";
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int RATE_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate to = LocalDate.parse(dateTo, DATE_FORMATTER);
        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom).append(DATE_SEPARATOR).append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String item : data) {
                String[] parts = item.split(" ");
                if (isWithinRange(parts[DATE_INDEX], from, to)
                        && name.equals(parts[NAME_INDEX])) {
                    int hours = Integer.parseInt(parts[HOURS_INDEX]);
                    int rate = Integer.parseInt(parts[RATE_INDEX]);
                    salary += hours * rate;
                }
            }
            report.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return report.toString();
    }

    private static boolean isWithinRange(String date, LocalDate from, LocalDate to) {
        LocalDate current = LocalDate.parse(date, DATE_FORMATTER);
        return !current.isBefore(from) && !current.isAfter(to);
    }
}
