package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String DATE_FORMAT = "dd.MM.yyyy";
    private static final String HEADING = "Report for period %s - %s";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int MONTH_INDEX = 2;
    private static final int SALARY_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder info = new StringBuilder(String.format(HEADING, dateFrom, dateTo));
        LocalDate startDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, FORMATTER);
        for (String name : names) {
            int totalSalary = 0;
            for (String currentData : data) {
                String[] employeeData = currentData.split(" ");
                LocalDate date = LocalDate.parse(employeeData[DATE_INDEX], FORMATTER);
                String employeeName = employeeData[NAME_INDEX];
                int countMonths = Integer.parseInt(employeeData[MONTH_INDEX]);
                int salary = Integer.parseInt(employeeData[SALARY_INDEX]);
                if (name.equals(employeeName)
                        && date.isAfter(startDate.minusDays(1))
                        && date.isBefore(endDate.plusDays(1))) {
                    totalSalary += countMonths * salary;
                }
            }
            info.append(System.lineSeparator()).append(name).append(" - ");
            info.append(totalSalary);
        }
        return info.toString();
    }
}
