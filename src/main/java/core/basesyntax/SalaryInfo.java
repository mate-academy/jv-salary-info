package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int RATE_INDEX = 3;
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        result.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        LocalDate parsedStartDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate parsedEndDate = LocalDate.parse(dateTo, DATE_FORMATTER);

        Employee[] employees = new Employee[names.length];
        for (int i = 0; i < names.length; i++) {
            employees[i] = new Employee();
            employees[i].setName(names[i]);
        }

        for (String info: data) {
            String[] parts = info.split(" ");
            String name = parts[NAME_INDEX];
            String currentDate = parts[DATE_INDEX];
            if (this.isDateInRange(parsedStartDate, parsedEndDate, currentDate)) {
                int indexEmployee = findIndexEmployee(employees, name);
                if (indexEmployee >= 0) {
                    int salary = Integer.parseInt(parts[HOURS_INDEX])
                            * Integer.parseInt(parts[RATE_INDEX]);
                    employees[indexEmployee].changeSalary(salary);
                }
            }
        }

        for (Employee employee: employees) {
            result.append(System.lineSeparator())
                    .append(employee.getName())
                    .append(" - ")
                    .append(employee.getSalary());
        }

        return result.toString();
    }

    private boolean isDateInRange(LocalDate startDate, LocalDate endDate, String currentDate) {
        LocalDate parsedCurrentDate = LocalDate.parse(currentDate, DATE_FORMATTER);
        return !parsedCurrentDate.isBefore(startDate)
                && !parsedCurrentDate.isAfter(endDate);
    }

    private int findIndexEmployee(Employee[] employees, String name) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }
}
