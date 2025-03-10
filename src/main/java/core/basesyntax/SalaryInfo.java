package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATA_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOUR_INDEX = 2;
    private static final int RATE_INDEX = 3;
    private static final String SEPARATOR = " ";
    private static final String REPORT_SEPARATOR = " - ";
    private static final String REPORT = "Report for period ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder(REPORT)
                .append(dateFrom)
                .append(REPORT_SEPARATOR)
                .append(dateTo);
        LocalDate dateAfter = parseDate(dateFrom);
        LocalDate dateBefore = parseDate(dateTo);
        int[] salaries = new int[names.length];
        for (int i = 0; i < names.length; i++) {
            for (String currentData : data) {
                String[] part = currentData.split(SEPARATOR);
                LocalDate currentDay = parseDate(part[DATA_INDEX]);
                String currentName = part[NAME_INDEX];
                int oneDayHours = parseToInt(part[HOUR_INDEX]);
                int currentRateIndex = parseToInt(part[RATE_INDEX]);
                int salary = oneDayHours * currentRateIndex;
                if (!currentDay.isBefore(dateAfter)
                        && !currentDay.isAfter(dateBefore)
                        && names[i].equals(currentName)) {
                    salaries[i] += salary;
                }
            }
            result.append(System.lineSeparator())
                    .append(names[i])
                    .append(REPORT_SEPARATOR)
                    .append(salaries[i]);
        }

        return result.toString();
    }

    private LocalDate parseDate(String date) {
        try {
            return LocalDate.parse(date, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("Can`t parse date: " + date);
        }
    }

    private int parseToInt(String date) {
        try {
            return Integer.parseInt(date);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Can`t parse date to int: " + date);
        }
    }
}
