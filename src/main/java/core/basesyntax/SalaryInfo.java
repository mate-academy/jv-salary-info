package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String REPORT = "Report for period ";
    private static final String DASH = " - ";
    private static final String SEPARATOR = " ";
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int RATE_INDEX = 3;
    private static final DateTimeFormatter DATE_TIME_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder();
        LocalDate fromDate = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        int totalSalary = 0;

        report.append(REPORT).append(dateFrom)
                .append(DASH).append(dateTo);

        for (String name : names) {
            int employeeSalary = calculateEmployeeSalary(name, data, fromDate, toDate);
            report.append(System.lineSeparator()).append(name).append(DASH).append(employeeSalary);
            totalSalary += employeeSalary;
        }

        return report.toString();
    }

    private static int calculateEmployeeSalary(String name, String[] data,
                                               LocalDate fromDate, LocalDate toDate) {
        int salary = 0;

        for (String entry : data) {
            String[] parts = entry.split(SEPARATOR);
            LocalDate date = LocalDate.parse(parts[DATE_INDEX], DATE_TIME_FORMATTER);
            String employeeName = parts[NAME_INDEX];

            if ((fromDate.isBefore(date) || fromDate.equals(date))
                    && (toDate.isAfter(date) || toDate.equals(date))
                    && employeeName.equals(name)) {
                int hours = Integer.parseInt(parts[HOURS_INDEX]);
                int hourlyRate = Integer.parseInt(parts[RATE_INDEX]);
                salary += hourlyRate * hours;
            }
        }
        return salary;
    }
}
