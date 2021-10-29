package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        Employee[] employees = Arrays.stream(names)
                .map(Employee::new)
                .toArray(Employee[]::new);

        for (String salaryData : data) {
            String[] splittedData = salaryData.split(" ");
            final String name = splittedData[1];
            for (Employee employee : employees) {
                if (employee.getName().equals(name)) {
                    String dateByData = splittedData[0];
                    if (checkDate(dateByData, dateFrom, dateTo)) {
                        int wage = Integer.valueOf(splittedData[2]);
                        int workingHours = Integer.valueOf(splittedData[3]);
                        int salary = wage * workingHours;
                        employee.addSalary(salary);
                    }
                }
            }
        }

        StringBuilder reportBuild = new StringBuilder();
        reportBuild.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (Employee employee : employees) {
            reportBuild.append(System.lineSeparator())
                    .append(employee.getName())
                    .append(" - ")
                    .append(employee.getSalary());
        }
        return reportBuild.toString();
    }

    private boolean checkDate(String dateCheck, String dateFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate to = LocalDate.parse(dateTo, DATE_FORMATTER);
        LocalDate checkDate = LocalDate.parse(dateCheck, DATE_FORMATTER);
        return checkDate.isAfter(from)
                && checkDate.isBefore(to.plusDays(1));
    }
}
