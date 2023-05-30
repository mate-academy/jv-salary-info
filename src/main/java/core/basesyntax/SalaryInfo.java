package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String REPORT = "Report for period ";
    private static final String DASH = " - ";
    private static final String NEW_LINE = "\n";
    private static final int HOURLY_RATE_SECOND = 1;
    private static final int HOURLY_RATE_THIRD = 2;
    private static final int HOURLY_RATE_FOURTH = 3;
    private static final int HOURLY_RATE_ZERO = 0;
    private final DateTimeFormatter dateTimeFormatter
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder();
        LocalDate fromDate = LocalDate.parse(dateFrom, dateTimeFormatter);
        LocalDate toDate = LocalDate.parse(dateTo, dateTimeFormatter);
        int totalSalary = 0;

        report.append(REPORT).append(dateFrom)
                .append(DASH).append(dateTo);

        for (String name : names) {
            int employeeSalary = calculateEmployeeSalary(name,data,fromDate,toDate);
            report.append(NEW_LINE).append(name).append(DASH).append(employeeSalary);
            totalSalary += employeeSalary;
        }

        return report.toString();
    }

    private static int calculateEmployeeSalary(String name, String[] data,
                                               LocalDate fromDate, LocalDate toDate) {
        int salary = 0;

        for (String entry : data) {
            String[] parts = entry.split(System.lineSeparator());
            LocalDate date = LocalDate.parse(parts[HOURLY_RATE_ZERO],
                    DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            String employeeName = parts[HOURLY_RATE_SECOND];

            if (date.compareTo(fromDate) >= 0 && date.compareTo(toDate) <= 0
                    && employeeName.equals(name)) {
                int hours = Integer.parseInt(parts[HOURLY_RATE_THIRD]);
                int hourlyRate = Integer.parseInt(parts[HOURLY_RATE_FOURTH]);
                salary += hourlyRate * hours;
            }
        }
        return salary;
    }
}
