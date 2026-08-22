package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period " + dateFrom + " - " + dateTo);
        LocalDate dateFromEmployee = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateToEmployee = LocalDate.parse(dateTo, FORMATTER);
        for (String employee : names) {
            int sumSalary = 0;
            for (String entry : data) {
                String[] employeeData = entry.split(" ");
                LocalDate date = LocalDate.parse(employeeData[0], FORMATTER);
                String name = employeeData[1];
                int hours = Integer.parseInt(employeeData[2]);
                int salary = Integer.parseInt(employeeData[3]);
                if (!date.isAfter(dateToEmployee) && !date.isBefore(dateFromEmployee)) {
                    if (employee.equals(name)) {
                        sumSalary += hours * salary;
                    }
                }
            }
            stringBuilder.append(System.lineSeparator() + employee + " - " + sumSalary);
        }
        return stringBuilder.toString();
    }
}
