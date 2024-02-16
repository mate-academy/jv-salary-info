package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int NOT_FOUND = -1;
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int RATE_INDEX = 3;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, FORMATTER);
        int[] salaries = new int[names.length];
        for (String entry : data) {
            String[] parts = entry.split(" ");
            LocalDate entryDate = LocalDate.parse(parts[DATE_INDEX], FORMATTER);
            String name = parts[NAME_INDEX];
            if (entryDate.isAfter(startDate.minusDays(1))
                    && entryDate.isBefore(endDate.plusDays(1))) {
                int index = indexOfName(name, names);
                if (index != NOT_FOUND) {
                    salaries[index] += Integer.parseInt(parts[RATE_INDEX]) * Integer.parseInt(parts[HOURS_INDEX]);
                }
            }
        }
        return generateReport(startDate, endDate, names, salaries);
    }

    private int indexOfName(String name, String[] names) {
        for (int i = 0; i < names.length; i++) {
            if (name.equals(names[i])) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    private String generateReport(LocalDate startDate, LocalDate endDate,
                                  String[] names, int[] salaries) {
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ")
                .append(startDate.format(FORMATTER))
                .append(" - ")
                .append(endDate.format(FORMATTER))
                .append(System.lineSeparator());
        for (int i = 0; i < names.length; i++) {
            builder.append(names[i])
                    .append(" - ")
                    .append(salaries[i])
                    .append(System.lineSeparator());
        }
        return builder.toString().trim();
    }
}
