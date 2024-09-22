package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int intDateStr = 0;
    private static final int intEmployeeName = 1;
    private static final int intHoursWorked = 2;
    private static final int intIncomePerHour = 3;
    private static final DateTimeFormatter date_formatter =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data,
                                       String dateFrom, String dateTo) {
        LocalDate dateFromComparable = convertDateToComparable(dateFrom);
        LocalDate dateToComparable = convertDateToComparable(dateTo);
        int[] totalEarnings = new int[names.length];

        for (String entry : data) {
            String[] parts = entry.split(" ");
            String dateStr = parts[intDateStr];
            String employeeName = parts[intEmployeeName];
            int hoursWorked = Integer.parseInt(parts[intHoursWorked]);
            int incomePerHour = Integer.parseInt(parts[intIncomePerHour]);

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

        return buildReport(names, totalEarnings, dateFrom, dateTo);
    }

    private String buildReport(String[] names, int[] totalEarnings,
                                      String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo);

        for (int i = 0; i < names.length; i++) {
            report.append(System.lineSeparator()).append(names[i]).append(" - ")
                    .append(totalEarnings[i]);
        }
        return report.toString();
    }

    private static LocalDate convertDateToComparable(String date) {
        return LocalDate.parse(date, date_formatter);
    }
}
