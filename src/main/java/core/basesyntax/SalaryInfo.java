package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    private static final DateTimeFormatter
            DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_WORKED_INDEX = 2;
    private static final int INCOME_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom.trim(), DATE_FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo.trim(), DATE_FORMATTER);

        int[] salaries = new int[names.length];

        for (String entry : data) {
            String[] parts = entry.split(" ");
            LocalDate date = LocalDate.parse(parts[DATE_INDEX].trim(), DATE_FORMATTER);
            String employeeName = parts[NAME_INDEX].trim();
            int hoursWorked = Integer.parseInt(parts[HOURS_WORKED_INDEX].trim());
            int incomePerHour = Integer.parseInt(parts[INCOME_PER_HOUR_INDEX].trim());

            // Check if the date is within the specified range
            if (!date.isBefore(startDate) && !date.isAfter(endDate)) {
                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(employeeName)) {
                        salaries[i] += hoursWorked * incomePerHour;
                        break;
                    }
                }
            }
        }

        // Build the report string
        StringBuilder report = new StringBuilder();
        report.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());

        for (int i = 0; i < names.length; i++) {
            report.append(names[i])
                    .append(" - ")
                    .append(salaries[i])
                    .append(System.lineSeparator());
        }

        return report.toString().trim();
    }
}
