package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    private static final int DATE_FROM_ARRAY = 0;
    private static final int NAME_FROM_ARRAY = 1;
    private static final int HOUR_FROM_ARRAY = 2;
    private static final int SALARY_FROM_ARRAY = 3;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        LocalDate startDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, FORMATTER).plusDays(1);
        for (String name: names) {
            int salary = 0;
            report.append(System.lineSeparator());
            for (String employee: data) {
                String[] employeeData = employee.split(" ");
                if (employeeData[NAME_FROM_ARRAY].equals(name)) {
                    LocalDate workDay = LocalDate.parse(employeeData[DATE_FROM_ARRAY], FORMATTER);
                    if (workDay.isAfter(startDate) && workDay.isBefore(endDate)) {
                        salary = salary + Integer.parseInt(employeeData[HOUR_FROM_ARRAY])
                                * Integer.parseInt(employeeData[SALARY_FROM_ARRAY]);
                    }
                }
            }
            report.append(name).append(" - ").append(salary);
        }
        return report.toString();
    }
}
