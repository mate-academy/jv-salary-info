package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String DATE = "dd.MM.yyyy";
    private static final String REPORT = "Report for period ";
    private static final String DASH = " - ";
    private static final String SPACE = " ";
    private static final String NEW_LINE = "\n";
    private static final int SECOND_ELEMENT = 1;
    private static final int THIRD_ELEMENT = 2;
    private static final int FOURTH_ELEMENT = 3;
    private static final int ZERO = 0;


    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder();
        LocalDate fromDate = LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern(DATE));
        LocalDate toDate = LocalDate.parse(dateTo, DateTimeFormatter.ofPattern(DATE));
        int totalSalary = ZERO;

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
        int salary = ZERO;

        for (String entry : data) {
            String[] parts = entry.split(SPACE);
            LocalDate date = LocalDate.parse(parts[ZERO],DateTimeFormatter.ofPattern(DATE));
            String employeeName = parts[SECOND_ELEMENT];

            if (date.compareTo(fromDate) >= ZERO && date.compareTo(toDate) <= ZERO
                    && employeeName.equals(name)) {
                int hours = Integer.parseInt(parts[THIRD_ELEMENT]);
                int hourlyRate = Integer.parseInt(parts[FOURTH_ELEMENT]);
                salary += hourlyRate * hours;
            }
        }
        return salary;
    }
}
