package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        Employee[] employees = new Employee[names.length];
        for (int i = 0; i < employees.length; i++) {
            employees[i] = new Employee(names[i]);
        }

        for (int i = 0; i < names.length; i++) {
            int sunSalary = 0;
            for (String datum : data) {
                String[] arrayData = datum.split(" ");
                if (names[i].equals(arrayData[1])
                        && isWithinRange(dateFrom, dateTo, arrayData[0])) {

                    sunSalary += Integer.parseInt(arrayData[2])
                            * Integer.parseInt(arrayData[3]);
                }
                employees[i].setSalary(sunSalary);
            }
        }
        return getOutputString(employees, dateFrom, dateTo);
    }

    private boolean isWithinRange(String dateFrom, String dateTo, String arrayDatum) {
        LocalDate startDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, FORMATTER);
        LocalDate testDate = LocalDate.parse(arrayDatum, FORMATTER);

        return (testDate.isEqual(startDate) || testDate.isEqual(endDate))
                || (testDate.isBefore(endDate) && testDate.isAfter(startDate));
    }

    public String getOutputString(Employee[] arrayEmployee, String dateFrom, String dateTo) {
        String nextLine = System.lineSeparator();
        StringBuilder text = new StringBuilder();
        text.append("Report for period ")
                .append(dateFrom).append(" - ")
                .append(dateTo)
                .append(nextLine);

        for (Employee employee : arrayEmployee) {
            text.append(employee.getName())
                    .append(" - ")
                    .append(employee.getSalary())
                    .append(nextLine);
        }
        return text.toString().trim();
    }
}
