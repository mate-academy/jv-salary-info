package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class SalaryInfo {
    static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        Employee[] employees = Arrays.stream(names).map(name -> new Employee(name))
                .toArray(Employee[]::new);

        for (String salaryData : data) {
            String[] splitData = salaryData.split(" ");
            String name = splitData[1];
            for (Employee employee : employees) {
                if (employee.getName().equals(name)) {
                    String dateByData = splitData[0];
                    if (checkDate(dateByData, dateFrom, dateTo)) {
                        int salary = Integer.valueOf(splitData[2]) * Integer.valueOf(splitData[3]);
                        employee.addSalary(salary);
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Report for period ").append(dateFrom).append(" - ").append(dateTo)
                .append(System.lineSeparator());
        for (Employee employee : employees) {
            sb.append(employee.getSalaryInfo()).append(System.lineSeparator());
        }
        return sb.toString().trim();
    }

    public boolean checkDate(String dateCheck, String dateFrom, String dateTo) {
        LocalDate dateFromLocalDate = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate dateToLocalDate = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        LocalDate dateCheckLocalDate = LocalDate.parse(dateCheck, DATE_TIME_FORMATTER);
        return dateFromLocalDate.compareTo(dateCheckLocalDate) <= 0
                && dateToLocalDate.compareTo(dateCheckLocalDate) >= 0;
    }
}
