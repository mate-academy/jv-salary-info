package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int INCOME_PER_HOUR_INDEX = 3;
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate;
        LocalDate endDate;

        try {
            startDate = LocalDate.parse(dateFrom, DATE_FORMAT);
            endDate = LocalDate.parse(dateTo, DATE_FORMAT);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format");
        }

        int[] salaries = new int[names.length];

        for (String record : data) {
            String[] parts = record.split(" ");

            try {
                LocalDate recordDate = LocalDate.parse(parts[DATE_INDEX], DATE_FORMAT);
                String employeeName = parts[NAME_INDEX];
                int hoursWorked = Integer.parseInt(parts[HOURS_INDEX]);
                int ratePerHour = Integer.parseInt(parts[INCOME_PER_HOUR_INDEX]);

                if (!recordDate.isBefore(startDate) && !recordDate.isAfter(endDate)) {
                    for (int i = 0; i < names.length; i++) {
                        if (names[i].equals(employeeName)) {
                            salaries[i] += hoursWorked * ratePerHour;
                        }
                    }
                }
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("Invalid date format");
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (int i = 0; i < names.length; i++) {
            stringBuilder.append(System.lineSeparator())
                    .append(names[i]).append(" - ").append(salaries[i]);
        }

        return stringBuilder.toString();
    }
}
