package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        Employee[] employees = Arrays.stream(names)
                .map(name -> new Employee(name))
                .toArray(Employee[]::new);

        for (String salaryData : data) {
            String[] splittedData = salaryData.split(" ");
            String name = splittedData[1];
            for (Employee employee : employees) {
                if (employee.getName().equals(name)) {
                    String dateByData = splittedData[0];
                    if (checkDate(dateByData, dateFrom, dateTo)) {
                        int salary = Integer.valueOf(splittedData[2]) * Integer.valueOf(splittedData[3]);
                        employee.addSalary(salary);
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Report for period ").append(dateFrom).append(" - ").append(dateTo)
                .append(System.lineSeparator());
        for (Employee employee : employees) {
            sb.append(employee.getName()).append(" - ").append(employee.getSalary()).toString();
            sb.append(System.lineSeparator());
        }
        return sb.toString().trim();
    }

    private boolean checkDate(String dateCheck, String dateFrom, String dateTo) {
        LocalDate dateFromLocalDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate dateToLocalDate = LocalDate.parse(dateTo, DATE_FORMATTER);
        LocalDate dateCheckLocalDate = LocalDate.parse(dateCheck, DATE_FORMATTER);
        return dateCheckLocalDate.isAfter(dateFromLocalDate)
                && dateCheckLocalDate.isBefore(dateToLocalDate.plusDays(1));
    }
}
