package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDateFormatted = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate endDateFormatted = LocalDate.parse(dateTo, FORMATTER);
        String[] namesAndSalaries = new String[names.length];

        for (int i = 0; i < names.length; i++) {
            int salary = getEmployeeSalary(names[i], data, startDateFormatted, endDateFormatted);
            namesAndSalaries[i] = names[i] + " - " + salary;
        }

        StringBuilder str = new StringBuilder();
        str.append("Report for period ").append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());
        for (String nameAndSalary : namesAndSalaries) {
            str.append(nameAndSalary).append(System.lineSeparator());
        }

        return str.toString().stripTrailing();
    }

    private int getEmployeeSalary(String employeeName, String[] data,
                                  LocalDate dateFrom, LocalDate dateTo) {
        int salarySum = 0;
        for (String day : data) {
            String[] values = day.split(" ");
            LocalDate date = LocalDate.parse(values[0], FORMATTER);
            String name = values[1];
            Integer hours = Integer.parseInt(values[2]);
            Integer wage = Integer.parseInt(values[3]);
            boolean dateWithinRange = (date.isAfter(dateFrom) || date.isEqual(dateFrom))
                    && (date.isBefore(dateTo) || date.isEqual(dateTo));

            if (dateWithinRange && name.equals(employeeName)) {
                salarySum += hours * wage;
            }
        }
        return salarySum;
    }
}
