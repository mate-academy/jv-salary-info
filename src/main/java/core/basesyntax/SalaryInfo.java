package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int RATE_INDEX = 3;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ")
                .append(startDate.format(FORMATTER))
                .append(" - ")
                .append(endDate.format(FORMATTER))
                .append(System.lineSeparator());
        for (String name : names) {
            int salary = 0;
            for (String entry : data) {
                String[] parts = entry.split(" ");
                LocalDate entryDate = LocalDate.parse(parts[DATE_INDEX], FORMATTER);
                if (name.equals(parts[NAME_INDEX])
                        && entryDate.isAfter(startDate.minusDays(1))
                        && entryDate.isBefore(endDate.plusDays(1))) {
                    int rate = Integer.parseInt(parts[RATE_INDEX]);
                    int hours = Integer.parseInt(parts[HOURS_INDEX]);
                    salary += rate * hours;
                }
            }
            builder.append(name)
                    .append(" - ")
                    .append(salary)
                    .append(System.lineSeparator());
        }
        return builder.toString().trim();
    }
}

