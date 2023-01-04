package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class SalaryInfo {
    static final String REPORT_PERIOD_MESSAGE = "Report for period ";
    static final DateTimeFormatter formatter = new DateTimeFormatterBuilder()
            .appendPattern("dd.MM.yyyy").toFormatter();

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, formatter);
        LocalDate endDate = LocalDate.parse(dateTo, formatter);
        Employee[] employees = new Employee[names.length];
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < names.length; i++) {
            employees[i] = new Employee(names[i]);
        }
        for (String d:data) {
            String[] split = d.split(" ");
            LocalDate arrayDate = LocalDate.parse(split[0], formatter);
            if (arrayDate.isAfter(startDate)
                    && arrayDate.isBefore(endDate) | arrayDate.isEqual(endDate)) {
                for (Employee employee : employees) {
                    if (split[1].equals(employee.getName())) {
                        employee.setSalary(employee.getSalary()
                                + Integer.parseInt(split[2]) * Integer.parseInt(split[3]));
                    }
                }
            }
        }
        for (Employee employee:employees) {
            stringBuilder.append(employee);
        }
        return REPORT_PERIOD_MESSAGE + dateFrom + " - " + dateTo
                + System.lineSeparator() + stringBuilder.substring(0, stringBuilder.length() - 2);
    }
}

