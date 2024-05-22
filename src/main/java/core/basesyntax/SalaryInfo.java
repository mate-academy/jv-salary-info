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
    private static final String SEPARATOR = " - ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom.trim(), DATE_FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo.trim(), DATE_FORMATTER);

        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ")
                .append(dateFrom)
                .append(SEPARATOR)
                .append(dateTo)
                .append(System.lineSeparator());

        for (String name : names) {
            int employeeMoneyEarned = 0;
            builder.append(name).append(SEPARATOR);
            for (String currentData : data) {
                String[] parts = currentData.split(" ");
                LocalDate date = LocalDate.parse(parts[DATE_INDEX].trim(), DATE_FORMATTER);
                String employeeName = parts[NAME_INDEX].trim();
                int hoursWorked = Integer.parseInt(parts[HOURS_WORKED_INDEX].trim());
                int incomePerHour = Integer.parseInt(parts[INCOME_PER_HOUR_INDEX].trim());

                if (!date.isBefore(startDate) && !date.isAfter(endDate)
                        && name.equals(employeeName)) {
                    employeeMoneyEarned += hoursWorked * incomePerHour;
                }
            }
            builder.append(employeeMoneyEarned).append(System.lineSeparator());
        }

        return builder.toString().trim();
    }
}
