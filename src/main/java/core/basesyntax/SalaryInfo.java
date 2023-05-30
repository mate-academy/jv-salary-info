package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String REPORT = "Report for period ";
    private static final String DASH = " - ";
    private static final String SEPARATOR = " ";
    private static final int HOURLY_RATE_SECOND = 1;
    private static final int HOURLY_RATE_THIRD = 2;
    private static final int HOURLY_RATE_FOURTH = 3;
    private static final int HOURLY_RATE_ZERO = 0;
    private static final DateTimeFormatter DATE_TIME_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder();
        LocalDate isBefore = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate isAfter = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        int totalSalary = 0;

        report.append(REPORT).append(dateFrom)
                .append(DASH).append(dateTo);

        for (String name : names) {
            int employeeSalary = calculateEmployeeSalary(name,data,isBefore,isAfter);
            report.append(System.lineSeparator()).append(name).append(DASH).append(employeeSalary);
            totalSalary += employeeSalary;
        }

        return report.toString();
    }

    private static int calculateEmployeeSalary(String name, String[] data,
                                               LocalDate isBefore, LocalDate isAfter) {
        int salary = 0;

        for (String entry : data) {
            String[] parts = entry.split(SEPARATOR);
            LocalDate date = LocalDate.parse(parts[HOURLY_RATE_ZERO],DATE_TIME_FORMATTER);
            String employeeName = parts[HOURLY_RATE_SECOND];

            if (date.compareTo(isBefore) >= 0 && date.compareTo(isAfter) <= 0
                    && employeeName.equals(name)) {
                int hours = Integer.parseInt(parts[HOURLY_RATE_THIRD]);
                int hourlyRate = Integer.parseInt(parts[HOURLY_RATE_FOURTH]);
                salary += hourlyRate * hours;
            }
        }
        return salary;
    }
}
