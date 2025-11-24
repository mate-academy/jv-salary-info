package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SalaryInfo {
    private static final String DATE_FORMAT = "dd.MM.yyyy";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);

    // Нові константи для індексів елементів даних
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int RATE_INDEX = 3;
    private static final int MIN_PARTS_COUNT = 4;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        Set<String> employeeNames = new HashSet<>(Arrays.asList(names));

        int[] totalSalaries = new int[names.length];

        LocalDate startDate;
        LocalDate endDate;

        try {
            startDate = LocalDate.parse(dateFrom, FORMATTER);
            endDate = LocalDate.parse(dateTo, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("Error parsing date: " + e.getMessage(), e);
        }

        for (String record : data) {
            String[] parts = record.split(" ");

            // Використання константи MIN_PARTS_COUNT
            if (parts.length < MIN_PARTS_COUNT || !employeeNames.contains(parts[NAME_INDEX])) {
                continue;
            }

            // Використання констант DATE_INDEX та NAME_INDEX
            String dateStr = parts[DATE_INDEX];
            String name = parts[NAME_INDEX];

            try {
                LocalDate recordDate = LocalDate.parse(dateStr, FORMATTER);

                if (!recordDate.isBefore(startDate) && !recordDate.isAfter(endDate)) {
                    // Використання констант HOURS_INDEX та RATE_INDEX
                    int hours = Integer.parseInt(parts[HOURS_INDEX]);
                    int rate = Integer.parseInt(parts[RATE_INDEX]);
                    int earnings = hours * rate;

                    int employeeIndex = -1;
                    for (int i = 0; i < names.length; i++) {
                        if (names[i].equals(name)) {
                            employeeIndex = i;
                            break;
                        }
                    }

                    if (employeeIndex != -1) {
                        totalSalaries[employeeIndex] += earnings;
                    }
                }
            } catch (DateTimeParseException | NumberFormatException e) {
                continue;
            }
        }

        StringBuilder report = new StringBuilder();
        String lineSeparator = System.lineSeparator(); // Отримуємо кросплатформний роздільник

        report.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(lineSeparator); // Використовуємо lineSeparator

        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            int totalSalary = totalSalaries[i];
            report.append(name).append(" - ").append(totalSalary);

            if (i < names.length - 1) {
                report.append(lineSeparator); // Використовуємо lineSeparator
            }
        }

        return report.toString();
    }
}
