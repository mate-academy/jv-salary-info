package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String SPACE_SEPARATOR = " ";
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORKING_HOURS_INDEX = 2;
    private static final int INCOME_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        Employee[] employees = getEmployees(names);
        for (String value : data) {
            String[] splitValue = value.split(SPACE_SEPARATOR);
            for (Employee employee : employees) {
                if (splitValue[NAME_INDEX].equals(employee.getName())
                        && isDateWithinLimits(splitValue[DATE_INDEX], dateFrom, dateTo,
                        FORMATTER)) {
                    employee.addIncome(Integer.parseInt(splitValue[INCOME_PER_HOUR_INDEX]),
                            Integer.parseInt(splitValue[WORKING_HOURS_INDEX]));
                }
            }
        }

        StringBuilder builder = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        for (Employee employee : employees) {
            builder.append(System.lineSeparator()).append(employee.getName())
                    .append(" - ").append(employee.getIncome());
        }
        return builder.toString();
    }

    public Employee[] getEmployees(String[] names) {
        Employee[] employees = new Employee[names.length];
        for (int i = 0; i < names.length; i++) {
            employees[i] = new Employee(names[i]);
        }
        return employees;
    }

    public boolean isDateWithinLimits(String date, String dateFrom, String dateTo,
                                      DateTimeFormatter formatter) {
        LocalDate from = LocalDate.parse(dateFrom, formatter);
        LocalDate to = LocalDate.parse(dateTo, formatter);
        LocalDate currentValue = LocalDate.parse(date, formatter);
        return currentValue.isEqual(from) || currentValue.isEqual(to)
                || (currentValue.isAfter(from) && currentValue.isBefore(to));
    }
}
