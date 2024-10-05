package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_STR_INDEX = 0;
    private static final int EMPLOYEE_NAME_INDEX = 1;
    private static final int HOURS_WORKED_INDEX = 2;
    private static final int INCOME_PER_HOUR_INDEX = 3;
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data,
                                String dateFrom, String dateTo) {
        LocalDate dateFromComparable = convertDateToComparable(dateFrom);
        LocalDate dateToComparable = convertDateToComparable(dateTo);
        int[] totalEarnings = new int[names.length];

        for (String entry : data) {
            String[] parts = entry.split(" ");
            String dateStr = parts[DATE_STR_INDEX];
            String employeeName = parts[EMPLOYEE_NAME_INDEX];
            int hoursWorked = Integer.parseInt(parts[HOURS_WORKED_INDEX]);
            int incomePerHour = Integer.parseInt(parts[INCOME_PER_HOUR_INDEX]);
            LocalDate entryDateComparable = convertDateToComparable(dateStr);
            if (!entryDateComparable.isBefore(dateFromComparable)
                    && !entryDateComparable.isAfter(dateToComparable)) {
                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(employeeName)) {
                        int earnings = hoursWorked * incomePerHour;
                        totalEarnings[i] += earnings;
                        break;
                    }
                }
            }
        }

        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo);

        for (int i = 0; i < names.length; i++) {
            report.append(System.lineSeparator())
                    .append(names[i]).append(" - ")
                    .append(totalEarnings[i]);
        }

        return report.toString();
    }

    private static LocalDate convertDateToComparable(String date) {
        return LocalDate.parse(date, DATE_FORMATTER);
    }
}
