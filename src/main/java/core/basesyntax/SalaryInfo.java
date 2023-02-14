package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static final String DAY_DELIMITER = " ";
    public static final int DATE_INDEX = 0;
    public static final int NAME_INDEX = 1;
    public static final int HOURS_INDEX = 2;
    public static final int WAGE_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDateFormatted = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate endDateFormatted = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder str = new StringBuilder();
        str.append("Report for period ").append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());
        for (String name : names) {
            int salary = getEmployeeSalary(name, data, startDateFormatted, endDateFormatted);
            str.append(name).append(" - ").append(salary).append(System.lineSeparator());
        }
        return str.toString().stripTrailing();
    }

    private int getEmployeeSalary(String employeeName, String[] data,
                                  LocalDate dateFrom, LocalDate dateTo) {
        int salarySum = 0;
        for (String day : data) {
            String[] values = day.split(DAY_DELIMITER);
            LocalDate date = LocalDate.parse(values[DATE_INDEX], FORMATTER);
            String name = values[NAME_INDEX];
            Integer hours = Integer.parseInt(values[HOURS_INDEX]);
            Integer wage = Integer.parseInt(values[WAGE_INDEX]);
            boolean dateWithinRange = (date.isAfter(dateFrom) || date.isEqual(dateFrom))
                    && (date.isBefore(dateTo) || date.isEqual(dateTo));

            if (dateWithinRange && name.equals(employeeName)) {
                salarySum += hours * wage;
            }
        }
        return salarySum;
    }
}
